package edu.lawrence.freecycle;

import java.lang.String;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.springframework.test.context.ActiveProfiles;

import edu.lawrence.freecycle.Classes.UserDTO;
import edu.lawrence.freecycle.Classes.ItemDTO;
import edu.lawrence.freecycle.Classes.InterestDTO;
import edu.lawrence.freecycle.Classes.Transfer;
import edu.lawrence.freecycle.Classes.MessageDTO;
import io.restassured.RestAssured;

@SpringBootTest(classes=FreecycleApplication.class,webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FreecycleApplicationTests {

	private static UserDTO donator;
	private static UserDTO reciever;

	private String donatorToken;
	private String recieverToken;

	private static UUID donatorId;
	private static UUID receiverId;

	private static ItemDTO item;
	private UUID itemId;

	private static Transfer transfer;

	private static UUID messageId;

	@BeforeAll
	public static void setup() 
	{
		RestAssured.port = 8085;
		RestAssured.baseURI = "http://localhost";

		//Setting up dummy objects
		donator = new UserDTO();
		donator.setUsername("test");
		donator.setPassword("1234");

		reciever = new UserDTO();
		reciever.setUsername("test2");
		reciever.setPassword("12345");
	}

	//End to end flow: 
	// 1. Create profiles. 
	// 2. Donator put item up for sale. 
	// 3. Reciever creates an interest. 
	// 4. Donator selects their interest, creating a transfer. 
	// 5. The two exchange messages to agree on a time and place. 
	// 6. They then add the time and date to the transfer.
	// 7. The item is deleted, along with any corresponding interests and transfer items.

	@Test
	@Order(1)
	public void profileCreation() {
		//First we have to create the profiles, so that we can use the User object we get from POSTing to /users
		given()
			.contentType("application/json")
			.body(donator)
			.when().post("/users")
			.then()
			.statusCode(200);

		given()
			.contentType("application/json")
			.body(reciever)
			.when().post("/users")
			.then()
			.statusCode(200);

		//Now we can follow up by logging in to get the tokens
		donatorToken = given()
					.contentType("application/json")
					.body(donator)
					.when().post("/users")
					.then()
					.statusCode(200)
					.extract().asString();

		recieverToken = given()
					.contentType("application/json")
					.body(reciever)
					.when().post("/users")
					.then()
					.statusCode(200)
					.extract().asString();

		//In the future, these will give authorization for protected paths
		given()
            .header("Authorization", "Bearer " + donatorToken)
            .when().post("/items")
            .then()
			.statusCode(200);
	}

	@Test
	@Order(2)
	public void itemCreation()
	{
		//Define the item first
		item = new ItemDTO();
		item.setItemName("shirt");
		item.setStatus("a");

		//Post the item 
		itemId = given()
			.header("Authorization", "Bearer " + donatorToken)
			.contentType("application/json")
			.body(item)
			.when().post("/items")
			.then()
			.statusCode(200)
			.extract()
			.path("");
			
	}

	@Test
	@Order(3)
	public void findItem()
	{
		itemId = given().
			when().get("/items")
			.then()
			.statusCode(200)
			.extract()
			.path("[0].getItemId"); //Get the first item ID after extracting the full list of items
	}

	@Test
	@Order(4)
	public void placeInterestandCreateTransfer()
	{
		//Create the interest DTO
		InterestDTO interest = new InterestDTO();
		
		given()
		.header("Authorization","Bearer "+ recieverToken)
		.contentType("application/json")
		.body(interest)
		.when()
		.post("/+ itemId +/interests")
		.then()
		.statusCode(201);

		//Get the interest ID to create the transfer
		UUID interestId = given()
			.header("Authorization","Bearer "+ donatorToken)
			.contentType("application/json")
			.when().get("/interests")
			.then()
			.statusCode(200)
			.extract()
			.path("[0].getId");

		//Then use the recipientID out of the interest object and the donatorId out of the item attached to the interest
		transfer = new Transfer(); //This feels like a really poor bandaid way of fixing the method about messages below
		//but I don't know what else to do
		transfer.setRecipientId(interest.getUserId());

		//Here, we get the item out of the interest, then extract the donorId
		UUID donorId = given()
			.header("Authorization","Bearer "+ donatorToken)
			.contentType("application/json")
			.queryParam("itemid", interest.getItemId())
			.when().get("/interests")
			.then()
			.statusCode(200)
			.extract()
			.path("[0].getDonorId");
		
		//And set the donorId we just got into the transfer objet
		transfer.setDonorId(donorId);
		

		//With the interestId, we select the reciever by creating a transfer with them as the recipient
		UUID transferId = UUID.fromString(given()
			.header("Authorization","Bearer "+ donatorToken)
			.contentType("application/json")
			.body(transfer)
			.when().post("/transfers")
			.then()
			.statusCode(200)
			.extract()
			.path("id").toString());

		transfer.setTransferId(transferId); //This is for test 6
	}

	//We're going to send messages both ways now. The assumption is that inside of the transfer screen, there's some kind of chatbox.
	@Test
	@Order(5)
	public void sendMessages()
	{
		//Setup the message DTO
		MessageDTO fromDtoR = new MessageDTO();
		fromDtoR.setSenderId(donatorId);
		fromDtoR.setReceiverId(receiverId);
		fromDtoR.setMessage("test1");

		//Send off the first message and save its ID so we can mark it as read
		messageId = UUID.fromString(given()
			.contentType("application/json")
			.body(fromDtoR)
			.when().post("/messages")
			.then()
			.statusCode(200)
			.extract()
			.path("id").toString());

		//The reciever then checks their chatbox
		given()
			.when().get("/messages/" + receiverId)
			.then()
			.statusCode(200)
			.body("[0].message", is("test1")); //Get the first message out of the list and read its text

		//and marks the first message they see as read
		given()
			.when().put("/messages/" + messageId + "/read")
			.then()
			.statusCode(200)
			.body("read", is(true));
		//Setup the return message DTO
		MessageDTO fromRtoD = new MessageDTO();
		fromRtoD.setSenderId(receiverId);
		fromRtoD.setReceiverId(donatorId);
		fromRtoD.setMessage("test2???? woah");

		//Same as before - the receiver sends off a message to the donator
		messageId = UUID.fromString(given()
			.contentType("application/json")
			.when().post("/messages")
			.then()
			.statusCode(200)
			.extract()
			.path("id").toString());

		//Donator checks inbox
		given()
			.when().get("/messages/" + messageId + "/read")
			.then()
			.statusCode(200)
			.body("[0].message", is("test2???? woah"));

		//and marks the message as read
		given()
			.when().put("/messages/" + messageId + "/read")
			.then()
			.statusCode(200)
			.body("read", is(true));
	}

	@Test
	@Order(6)
	public void addTimeAndSiteToTransfer()
	{
		//Here, we use a PATCH request to update the site and time, assuming that the two figured it out in messages.
		given()
			.queryParam("transferId", transfer.getTransferId()) //We saved this at the end of method 4
			.queryParam("site", "testsite1")
			.queryParam("time", "testtime1")
			.when().patch("/transfers")
			.then()
			.statusCode(200);
	}

	@Test
	@Order(7)
	public void completeTransfer()
	{
		//This lets us finish the transfer, deleting it and everything related after the item has been given away.
		given()
			.queryParam("transferId", transfer.getTransferId())
			.when().delete("/transfers")
			.then()
			.statusCode(200);
	}



}
