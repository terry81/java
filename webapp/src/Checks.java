import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Checks")
public class Checks extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String info = "<html>";
		String hostname = "<br><b>Host: </b>" + InetAddress.getLocalHost().getHostName();
		String request_info = "<br><br><b>Request info</b>";
		String context_path = "<br><br><b>Context path:</b> " + request.getContextPath();

		String uptime = " <br> Uptime: ";
		try {
			String line;
			Process p = Runtime.getRuntime().exec("uptime");

			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = in.readLine()) != null) {
				uptime += line;
			}
			in.close();
		} catch (Exception e) {
			
		}

		String sys_info = "<br><br><b>System info:</b> Free memory: " + Runtime.getRuntime().freeMemory()
				+ " Processors: " + Runtime.getRuntime().availableProcessors();

		info += hostname;
		info += ("<br><br><b>JVM:</b> " + System.getProperty("java.version") + "<br>");
		Enumeration<String> names = request.getHeaderNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			Enumeration<String> values = request.getHeaders(name); 
	
			if (values != null) {
				while (values.hasMoreElements()) {
					String value = (String) values.nextElement();
					request_info += ("<br>" + name + ": " + value);
				}
			}
		}

		RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
		List<String> arguments = runtimeMxBean.getInputArguments();

		for (String arg : arguments) {
			info += arg;
		}

		response.getWriter().append(info + sys_info + uptime + context_path + request_info + "</html>");

	}

}
