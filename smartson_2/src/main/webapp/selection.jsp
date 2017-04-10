<%-- IMPORTS --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%@ page import="se.smartson.selection.Person" %>
<%@ page import="se.smartson.selection.Campaign" %>
<%@ page import="java.util.List" %>

<%-- HTML --%>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
</head>

<body>

<%
	//TODO Handle error if no campaign is assigned 
	// Campaign campaign = (Campaign) session.getAttribute("campaign");

	%>
	
<%--Hardcoded for trial--%>
<form action="/filter" method="post">
	Size of output<br>
	<input type="text" name="output" value="100"/>
	Gender<br>
	Female --- Male<br>
	<input type="range" name="gender" min="0" max="100" value="50"/><br>
	Age<br>
	<%--For example age1 is 18-29 --%>
	<label><input type="checkbox" name="age1">18-29</label>
	<label><input type="checkbox" name="age2">30-39</label>
	<label><input type="checkbox" name="age3">40-49</label>
	<label><input type="checkbox" name="age4">50+</label><br>
	Location<br>
	<%--For example big cities in the country --%>
	<label><input type="checkbox" name="loc" value="Stockholm">Stockholm</label>
	<label><input type="checkbox" name="loc" value="Gothenburg">Gothenburg</label>
	<label><input type="checkbox" name="loc" value="Malmo">Malmo</label>
	<label><input type="checkbox" name="loc" value="Sweden">Sweden</label><br>
	
	<%
	for (int i=0; i<campaign.questions.size(); i++) {
		%> <%= campaign.questions.get(i) %><br>
		<label><input type="checkbox" name="yes<%= i%>">Yes</label><br>
		<label><input type="checkbox" name="no<%= i%>">No</label><br>
		<%
	}
	%>
	
	<input type="submit" value="Fetch individuals"/>
</form>



<%

//TODO Handle error if persons = 0 (in LoadServlet.java and here?)
	List <Person> persons = (List <Person>) session.getAttribute("persons");
	Object checkFiltered = request.getAttribute("personsFiltered");
	int totalSize = persons.size();
	int resultSize = 0;
	if (checkFiltered != null) {
		List <Person> personsFiltered = (List <Person>) checkFiltered;
		resultSize = personsFiltered.size();
	}
%>

	<p> Result: <%= resultSize %> Total: <%= totalSize %></p><br>




</body>
</html>