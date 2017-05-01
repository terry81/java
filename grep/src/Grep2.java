import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Grep2 {
	public static void main(String problem, String path, String filePattern, String pattern) throws IOException {
		Path startingDir = Paths.get(path);
		List<Path> files = Files.walk(Paths.get(startingDir.toString())).filter(Files::isRegularFile)
				.collect(Collectors.toList());

		for (Path f : files) {
			String file = f.toString();

			if (file.matches(filePattern)) {
				String content = ReadFile.readFile(file);
				ArrayList<String> matches = RegexMatches.regexMatches(content, pattern);
				if (!matches.isEmpty()) {
					System.out.printf("Found problem %s in file %s\n", problem, file);
					for (String i : matches) {
						System.out.printf("%s\n", i);
					}
				}

			}
		}

	}

}
