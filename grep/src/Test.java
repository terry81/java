import java.io.IOException;

public class Test {

	static String ACCOUNT = "banjo";
	
	public static void main(String[] args) {

		try {
			Grep2.main("-Jederzeit-:", "/home/toli/", ".*test.*", "(That reminds me.*?swallowed)");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

//
//“That reminds me!” he said. “Forgot to take my prescription.” He
//swallowed two pills, made a face, and picking up an armload of shoes and
//a banjo case, approached Brick.