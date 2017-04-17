<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.Socket,java.io.IOException" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Check connectivity</title>
</head>
<body>

<%
if ((request.getParameter("host") == null || request.getParameter("host") == "") ||
		(request.getParameter("port") == null || request.getParameter("port") == "")) {
%>
Please specify host and port.
<form action="connect.jsp" method="GET">
Host: <input type="text" name="host">
<br />
Port: <input type="text" name="port" />
<input type="submit" value="Submit" />
</form>
<%
} else {
	
	String host = request.getParameter("host");
	int port = Integer.parseInt(request.getParameter("port"));
	out.print("Connecting to: " + request.getParameter("host") + ":" + request.getParameter("port") + "<br>");
	try {
		Socket client = new Socket(host, port);
		out.print("Successfully connected to " + client.getRemoteSocketAddress());
		client.close();
	} catch (IOException e) {
		out.print(e);
	}
out.print("<br><a href=\"/webapp/connect.jsp\">New check</a>");
}
%>
</body>
</html>