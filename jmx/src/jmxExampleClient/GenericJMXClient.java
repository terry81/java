package jmxExampleClient;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Set;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class GenericJMXClient {

	public static void main(String[] args) throws IOException, MalformedObjectNameException {
		
		String HOST = args[0];
	    String PORT = args[1];

		long start = System.currentTimeMillis();

		JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + HOST + ":" + PORT + "/jmxrmi");

		JMXConnector jmxConnector = JMXConnectorFactory.connect(url);
		MBeanServerConnection mbeanServerConnection = jmxConnector.getMBeanServerConnection();

		Set<ObjectName> mySet = mbeanServerConnection.queryNames(new ObjectName("*:*"), null);

		Iterator<ObjectName> it = mySet.iterator();

		while (it.hasNext()) {

			ObjectName myName = (ObjectName) it.next();

			try {

				System.out.println(" -> " + myName.getCanonicalName());

				MBeanAttributeInfo[] atribs = mbeanServerConnection.getMBeanInfo(myName).getAttributes();
			        

				for (int i = 0; i < atribs.length; i++) {

					System.out.println(" -->  " + atribs[i].getName() +

							"  : " + mbeanServerConnection.getAttribute(myName, atribs[i].getName()).toString());

				}

			} catch (Exception ex) {

				//ex.printStackTrace();

			}

		}
		jmxConnector.close();
		long end = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");

		System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds.\n");
	}
}
