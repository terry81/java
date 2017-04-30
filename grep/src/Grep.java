import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Grep {
	public static void main(String problem, String path, String filePattern, String pattern) {
		try {
			Path startingDir = Paths.get(path);
			Files.walk(Paths.get(startingDir.toString())).filter(Files::isRegularFile).filter(Files::isReadable)
					.forEach((f) -> {

						String file = f.toString();

						if (file.matches(filePattern)) {

							try {
								String content = ReadFile.readFile(file);
								ArrayList<String> matches = RegexMatches.regexMatches(content, pattern);
								if (!matches.isEmpty()) {
									System.out.printf("Found problem %s in file %s\n", problem, file);
									for (String i : matches) {
										System.out.printf("%s\n", i);
									}
								}

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
