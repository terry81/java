// All objects in Java have a toString() method, inherited from the Object class.

// The method equals() tests strings for equality. In contrast, == tests for objects identity, i.e. whether the two tested arguments are the same.

// Example with the string method contains()
String log = "There is an emergency";
if(log.contains("emergency")) pageSomeone();

// Splitting text with split and regex
String text = "4231,     Java Programming, 1000.00";
String [] fields = text.split("\\s*,\\s*");
// fields = "4231", "Java Programming", "1000.00"

// Splitting text wih scanner and regex
String text = "4231,     Java Programming, 1000.00";
Scanner scanner = new Scanner( text ).useDelimiter("\\s*,\\s*");
int checkNumber = scanner.nextInt(); // 4231
String description = scanner.next(); // "Java Programming"
float amount = scanner.nextFloat(); // 1000.00