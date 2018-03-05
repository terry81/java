package jmxExampleClient;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import jmxExampleServer.HelloMBean;

public class HelloClient {

	public static final String HOST = "localhost";
	public static final String PORT = "9013";

	public static void main(String[] args) throws IOException, MalformedObjectNameException {
		
		long start = System.currentTimeMillis();

		
		JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + HOST + ":" + PORT + "/jmxrmi");

		JMXConnector jmxConnector = JMXConnectorFactory.connect(url);
		MBeanServerConnection mbeanServerConnection = jmxConnector.getMBeanServerConnection();
		// ObjectName should be same as your MBean name
		ObjectName mbeanName = new ObjectName("jmxExampleServer:type=Hello");

		HelloMBean mbeanProxy = (HelloMBean) MBeanServerInvocationHandler
				.newProxyInstance(mbeanServerConnection, mbeanName, HelloMBean.class, true);

		// let's make some calls to mbean through proxy and see the results.
		System.out.println("Name:" + mbeanProxy.getName());

		// close the connection
		jmxConnector.close();
		long end = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");

		System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds.\n");
	}
}