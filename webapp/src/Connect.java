
import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/Connect" })
public class Connect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static String conn_test(String host, int port) {
		String output = "Connecting to " + host + " on port " + port + "\n\n";
		try {
			Socket client = new Socket(host, port);

			output += "Successfully connected to " + client.getRemoteSocketAddress();
			client.close();
		} catch (IOException e) {
			output += e;
		}
		return output;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String output = "";

		if (request.getParameter("host") == null) {
			output += "Please specify host as a GET parameter";
		} else if (request.getParameter("port") == null || Integer.parseInt(request.getParameter("port")) < 1) {
			output += "Please specify port as a GET parameter and an integer > 1";
		} else {
			String host = request.getParameter("host");
			int port = Integer.parseInt(request.getParameter("port"));
			output += conn_test(host, port);
		}
		response.getWriter().append(output);
	}

}
