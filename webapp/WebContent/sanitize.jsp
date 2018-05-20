<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.IOException,java.io.BufferedReader,java.io.InputStreamReader"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Try sanitization</title>
</head>
<body>
	6 Try sanitization:
	<form action="sanitize.jsp" method="POST">
		Command: <input type="text" name="someInput"> <br /> 
		<input type="submit" value="Submit" />
	</form>
	

	<%
		//    String alphaRegex = "[a-zA-Z]+\\.?";
		String alphaRegex = "[a-zA-Z0-9\\.]+";

		String input = request.getParameter("someInput");
		if (input.matches(alphaRegex)) {
			out.print("Input is fine: " + input);
		} else {
			out.print("Bad incput: " + input);
		}
	%>
</body>
</html>