var user; // User ID for Security

// Work-around for missing JQuery.postJSON
jQuery["postJSON"] = function(url,data,callback) {
	var options = {
	  url:url,
	  type:'POST',
	  data:JSON.stringify(data),
	  contentType:'application/json',
	  dataType:'json',
	  success: callback
	};
	$.ajax(options);
};

var customer;

function jobSuccess(ignored) {
	window.alert("New job posted.");

	$('#jobdescription').val('');
	$('#jobdate').val('');
	$('#joblocation').val('');

	$('#jobdiv').hide();
	$('#maindiv').show();
	$.getJSON('http://localhost:8085/jobs?customer='+customer.id,showJobs);
}

function handleNewJob() {
	var desc = $('#jobdescription').val();
	var date = $('#jobdate').val();
	var loc = $('#joblocation').val();

	if(desc == "")
		window.alert("You must provide a description.");
	else if(date == "")
		window.alert("You must provide a job date.");
	else if(loc == "")
		window.alert("You must provide a location.");
	else {
		var jobObject = {description:desc,date:date,location:loc,customer:customer.id};
		$('#jobsuccess').text('');
		$.postJSON('http://localhost:8085/jobs',jobObject,jobSuccess);
	}
}

function showJobs(jobs) {
	$('#jobs li').remove();
	var list = $("#jobs");
	var n,newitem;
	for(n = 0;n < jobs.length;n++) {
		newitem = $("<li>").html('<b>'+jobs[n].description+':</b>'+jobs[n].date + '/' + jobs[n].location);
		list.append(newitem);
	}
}

function loginStageTwo(data) {
	customer = data;
	$('#logindiv').hide();
	$('#maindiv').show();
  $.getJSON('http://localhost:8085/jobs?customer='+customer.id,showJobs);
}

function loginStageOne(data) {
	if(data == 0)
		return;
	// On successful login the server will send us a user id
	$.getJSON('http://localhost:8085/people/'+data,loginStageTwo);
}

function handleLogin() {
	var user = $('#user').val();
	var pwd = $('#password').val();
	if(user == "")
		window.alert("You must provide a user name.");
	else if(pwd == "")
		window.alert("You must provide a password.");
	else
		$.getJSON('http://localhost:8085/people?user='+user+'&password='+pwd,loginStageOne);
}

function gotoCreate() {
	$('#maindiv').hide();
	$('#jobdiv').show();
}

function gotoLogin() {
	$('#landingdiv').hide();
	$('#logindiv').show();
}

// Event handler function for the document ready event.
function setUpButtons() {
	// Set up event handlers for the buttons
	$('#doLogin').click(gotoLogin);
	$('#login').click(handleLogin);
	$('#toCreate').click(gotoCreate);
	$('#createjob').click(handleNewJob);

	// Hide all sections but the first
	$('#logindiv').hide();
	$('#maindiv').hide();
	$('#jobdiv').hide();
}

$(document).ready(setUpButtons);
