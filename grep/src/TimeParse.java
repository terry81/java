import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeParse {
	public static void main(String[] args) {
		String inputStr = "Time is 11 minutes past 11PM.";
		String pattern = "'Time is 'm' minutes past 'ha.";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
		LocalTime time = LocalTime.parse(inputStr, dtf);
		System.out.println(time);
	}
}