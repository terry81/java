<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.net.URL,java.io.*,javax.net.ssl.HttpsURLConnection,java.security.cert.Certificate, java.security.cert.X509Certificate"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Check the SSL connection to host</title>
</head>
<body>
	Connect to an SSL host.
	<form action="ssl_connect.jsp" method="GET">
		https://<input type="text" name="ssl_host"> <br /> <input
			type="submit" value="Submit" />
	</form>
	<%
		if (request.getParameter("ssl_host") != null && !request.getParameter("ssl_host").isEmpty()) {
			try {
				String httpsURL = "https://" + request.getParameter("ssl_host");
				URL myurl = new URL(httpsURL);
				HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
		        con.connect();
		        Certificate[] certs = con.getServerCertificates();
		        for (Certificate cert : certs) {
		        	out.print("Certificate is: " + cert +"<br>");
		            if(cert instanceof X509Certificate) {
		                    X509Certificate x = (X509Certificate ) cert;
		                    out.print(x.getIssuerDN()+"<br>");
		            }
		        }
				
			} catch (Exception e) {
				out.print(e);
			}
		}
	%>
</body>
</html>