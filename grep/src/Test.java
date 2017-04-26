import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {

	public static void main(String[] args) {
		try {

			Path startingDir = Paths.get("/home/toli/Desktop/");
			String filePattern = ".*test.*";
			String pattern = "this\n(.*) - any time";

			Files.walk(Paths.get(startingDir.toString())).filter(Files::isRegularFile).forEach((f) -> {
				String file = f.toString();
				if (file.matches(filePattern)) {
					try {
						String content = ReadFile.readFile(file);
						RegexMatches.regexMatches(content, pattern);

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
