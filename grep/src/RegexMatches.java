import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {

	public static ArrayList<String> regexMatches(String content, String pattern) {

		ArrayList<String> matches = new ArrayList<String>();

		try {
			Pattern p = Pattern.compile(pattern, Pattern.DOTALL);
			Matcher regexMatcher = p.matcher(content);

			while (regexMatcher.find()) {
				for (int i = 1; i <= regexMatcher.groupCount(); i++) {
					matches.add(regexMatcher.group(i));
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return matches;
	}

}
