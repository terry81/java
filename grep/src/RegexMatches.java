import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {

	public static void regexMatches(String content, String pattern) {

		try {
			Pattern p = Pattern.compile(pattern, Pattern.DOTALL);

			Matcher regexMatcher = p.matcher(content);
			if (regexMatcher.find()) {
				System.out.println(regexMatcher.group(1));
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
