<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Message Receiver</title>
</head>
<body>
<%
 
response.setIntHeader("Refresh", 5);

%>

Message From GCP : ${res}

</body>
</html>