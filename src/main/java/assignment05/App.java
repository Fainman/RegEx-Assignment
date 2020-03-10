package assignment05;

public class App {
  public static void main(String[] args) {
    // TODO
    new App().demo("((AB)|((ACB)|C)|Z)", "C");
    new App().demo("((AB)|((ACB)|C)|Z)", "AZB");

    new App().demo("((AB)|((ACB)|(C+B))|Z)", "CCCB");
    new App().demo("((AB)|((ACB)|(C+B))|Z)", "B");

    new App().demo("((AB)|((AC?B)|C)|Z)", "ACB");
    new App().demo("((AB)|((AC?B)|C)|Z)", "ACCB");

    new App().demo("((A.B)|((ACB)|C)|Z)", "A2B");
    new App().demo("((A.B)|((ACB)|C)|Z)", "AB");

    new App().demo("(A|B|C|D|E)", "D");
    new App().demo("(A|B|C|D|E)", "F");
  }
  public void demo(String pattern, String input) {
    RegExMatcher re = new RegExMatcher();
    System.out.println("Pattern: " + pattern);
    System.out.println("Match for " + input + " is " + re.recognizes(pattern, input));
  }
}
