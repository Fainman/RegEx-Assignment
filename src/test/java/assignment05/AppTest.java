package assignment05;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test void testAll(){
        RegExMatcher re = new RegExMatcher();
        
        assertEquals(re.recognizes("((AB)|((ACB)|C)|Z)", "C"), true);
        assertEquals(re.recognizes("((AB)|((ACB)|C)|Z)", "AZB"), false);

        assertEquals(re.recognizes("((AB)|((ACB)|(C+B))|Z)", "CCCB"), true);
        assertEquals(re.recognizes("((AB)|((ACB)|(C+B))|Z)", "B"), false);

        assertEquals(re.recognizes("((AB)|((AC?B)|C)|Z)", "ACB"), true);
        assertEquals(re.recognizes("((AB)|((AC?B)|C)|Z)", "ACCB"), false);

        assertEquals(re.recognizes("((A.B)|((ACB)|C)|Z)", "A2B"), true);
        assertEquals(re.recognizes("((A.B)|((ACB)|C)|Z)", "AB"), false);

        assertEquals(re.recognizes("(A|B|C|D|E)", "D"), true);
        assertEquals(re.recognizes("(A|B|C|D|E)", "F"), false);
    }
}
