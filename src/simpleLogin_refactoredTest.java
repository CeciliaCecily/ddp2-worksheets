import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimpleLogin_refactoredTest
{
    @Test
    void authCheck_validAdmin()
    {
        assertTrue(simpleLogin_refactored.authCheck("admin", "password"));
    }

    @Test
    void authCheck_validBurhan()
    {
        assertTrue(simpleLogin_refactored.authCheck("burhan", "burunghantu123"));
    }

    @Test
    void authCheck_invalidPassword()
    {
        assertFalse(simpleLogin_refactored.authCheck("admin", "123"));
    }

    @Test
    void authCheck_unknownUser()
    {
        assertFalse(simpleLogin_refactored.authCheck("anjay", "123"));
    }

    @Test
    void evaluate_strongPassword()
    {
        int[] score = simpleLogin_refactored.evaluate("burunghantu123");

        assertEquals(4, score[0]);
        assertEquals(4, score[1]);
    }

    @Test
    void evaluate_tooShort()
    {
        int[] score = simpleLogin_refactored.evaluate("a1");

        assertEquals(3, score[0]); 
    }

    @Test
    void evaluate_noDigit()
    {
        int[] score = simpleLogin_refactored.evaluate("abcdef");

        assertEquals(3, score[0]);
    }

    @Test
    void evaluate_containsSpace()
    {
        int[] score = simpleLogin_refactored.evaluate("abc 123");

        assertEquals(3, score[0]);
    }
}
