package edu.lawrence.freecycle;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class FreecycleE2ETest {

    @Test
public void fullWorkflowTest() {

    String token =
        given()
            .contentType("application/json")
            .body("""
                {
                    "username":"duang",
                    "password":"1234"
                }
            """)
        .when()
            .post("http://localhost:8085/auth/login")
        .then()
            .statusCode(200)
            .extract()
            .asString();

    System.out.println(token);
}
}