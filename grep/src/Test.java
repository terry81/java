import java.io.IOException;

public class Test {

	static String ACCOUNT = "banjo";

	public static void main(String[] args) {

		try {
			Grep2.main(" Jederzeit :", "/home/toli/", ".*test.*", "(That reminds me.*?swallowed)");
			Grep2.main(" Good lads :", "/home/toli/", ".*test.*", "(Good lads.*?garments)");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
