<%-- IMPORTS --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%@ page import="se.smartson.selection.Person" %>
<%@ page import="java.util.List" %>

<%-- HTML --%>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
</head>

<body>

<%
	String[][] filters = new String[3][2];
	filters[0][0] = "Male";
	filters[0][1] = "Female";
	filters[1][0] = "City";
	filters[1][1] = "Countryside";
	filters[2][0] = "Age";
	pageContext.setAttribute("filters", filters);
	%>
	
<%
	String a = request.getParameter("gender");
	String b = request.getParameter("living");
	int gender = -1;
	int living = -1;
		
	if (a != null) {
		gender = Integer.parseInt(a);
		pageContext.setAttribute("gender", a);
	}
	if (b != null) {
		living = Integer.parseInt(b);
		pageContext.setAttribute("living", b);
	}
%>

<form action="/selection.jsp" method="get">
	<%= filters[0][0]%> - <%= filters[0][1]%><br>
	<input type="range" name="gender" min="0" max="100" value="50"/><br>
	<%= filters[1][0]%> - <%= filters[1][1]%><br>
	<input type="range" name="living" min="0" max="100" value="50"/><br>
	<%= filters[2][0]%><br>
	<input type="checkbox"><input type="checkbox"><input type="checkbox"><input type="checkbox"><br>
	<input type="submit" value="Fetch individuals"/>
</form>



<%

//TODO Handle error if persons = 0 (Maybe in LoadServlet.java)

	Object o = request.getAttribute("persons");
	if (o != null) {
	List<Person> persons = (List<Person>) o;
	int resultSize = persons.size();
	pageContext.setAttribute("resultSize", resultSize);
%>

	<p> Result: <%= resultSize %> </p><br>

<%		
	String resultName;
	for (int i=0; i<5; i++) {
		resultName = persons.get(i).firstname + " " + persons.get(i).secondname;
		pageContext.setAttribute("resultName", resultName);
		%> <%= resultName %> <br> <%
	}
	if (resultSize > 5) {
		%> ... <%
	}
}
%>

<form action="/create" method="post">
	<div><input type="submit" value="Create persons"/></div>
</form>


</body>
</html>