
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author
 *
 */
public class PasswordCheckerSTUDENT_Test {
    ArrayList<String> studentPasswords2;

    @Before
    public void setUp() throws Exception {
        String[] sp2 = {"557722AA", "Ur2%awesome2I", "peter5YYY", "gertrude44&", "1nwardstretch",
                "june31", "ghijkl", "Bananamm#", "cc44b", "firstProject", "thePassword", "thePassword12"};
        studentPasswords2 = new ArrayList<String>();
        studentPasswords2.addAll(Arrays.asList(sp2)); // puts strings into the ArrayList
    }

    @After
    public void tearDown() throws Exception {
        studentPasswords2 = null;
    }

    /**
     * Test if the password is less than 8 characters long.
     * This test should throw a LengthException for second case.
     */
    @Test
    public void testIsValidPasswordTooShort()
    {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword("#defDEF2"));
            PasswordCheckerUtility.isValidPassword("#De34");
            assertTrue("Did not throw lengthException",false);
        }
        catch(PasswordCheckerUtility.LengthException e)
        {
            assertTrue("Successfully threw a lengthExcepetion",true);
        }
        catch(Exception e)
        {
            assertTrue("Threw some other exception besides lengthException",false);
        }
    }

    /**
     * Test if the password has at least one uppercase alpha character
     * This test should throw a NoUpperAlphaException for second case
     */
    @Test
    public void testIsValidPasswordNoUpperAlpha()
    {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword("#89101121314bB"));
            PasswordCheckerUtility.isValidPassword("#89101121314b");
            assertTrue("Did not throw NoUpperAlphaException",false);
        }
        catch(PasswordCheckerUtility.NoUpperAlphaException e)
        {
            assertTrue("Successfully threw a NoUpperAlphaExcepetion",true);
        }
        catch(Exception e)
        {
            assertTrue("Threw some other exception besides NoUpperAlphaException",false);
        }
    }

    /**
     * Test if the password has at least one lowercase alpha character
     * This test should throw a NoLowerAlphaException for second case
     */
    @Test
    public void testIsValidPasswordNoLowerAlpha()
    {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword("#89101121314bB"));
            PasswordCheckerUtility.isValidPassword("#89101121314B");
            assertTrue("Did not throw NoLowerAlphaException",false);
        }
        catch(PasswordCheckerUtility.NoLowerAlphaException e)
        {
            assertTrue("Successfully threw a NoLowerAlphaExcepetion",true);
        }
        catch(Exception e)
        {
            assertTrue("Threw some other exception besides NoLowerAlphaException",false);
        }
    }
    /**
     * Test if the password has more than 2 of the same character in sequence
     * This test should throw a InvalidSequenceException for second case
     */
    @Test
    public void testIsWeakPassword()
    {
        try{
            assertEquals(true,PasswordCheckerUtility.isValidPassword("#5678bbBB"));
            boolean weakPwd = PasswordCheckerUtility.isWeakPassword("#5678bbB");
            assertTrue(weakPwd);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            assertTrue("Threw some incorrect exception",false);
        }
    }

    /**
     * Test if the password has more than 2 of the same character in sequence
     * This test should throw a InvalidSequenceException for second case
     */
    @Test
    public void testIsValidPasswordInvalidSequence()
    {
        try{
            assertEquals(true,PasswordCheckerUtility.isValidPassword("#5678bbBB"));
            PasswordCheckerUtility.isValidPassword("#5678bBBB");
            assertTrue("Did not throw an InvalidSequenceException",false);
        }
        catch(PasswordCheckerUtility.InvalidSequenceException e)
        {
            assertTrue("Successfully threw an InvalidSequenceExcepetion",true);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            assertTrue("Threw some other exception besides an InvalidSequenceException",false);
        }
    }

    /**
     * Test if the password has at least one digit
     * One test should throw a NoDigitException
     */
    @Test
    public void testIsValidPasswordNoDigit()
    {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword("#5678bB"));
            PasswordCheckerUtility.isValidPassword("thisDoesnt#");
            assertTrue("Did not throw NoDigitException",false);
        }
        catch(PasswordCheckerUtility.NoDigitException e)
        {
            assertTrue("Successfully threw a NoDigitException",true);
        }
        catch(Exception e)
        {
            assertTrue("Threw some other exception besides NoDigitException",false);
        }
    }

    /**
     * Test correct passwords
     * This test should not throw an exception
     */
    @Test
    public void testIsValidPasswordSuccessful()
    {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword("#5678bB"));
        }
        catch(PasswordCheckerUtility.NoDigitException e)
        {
            assertTrue("Successfully threw a NoDigitException",true);
        }
        catch(Exception e)
        {
            assertTrue("Threw some other exception besides NoDigitException",false);
        }
    }

    /**
     * Test the invalidPasswords method
     * Check the results of the ArrayList of Strings returned by the validPasswords method
     */
    @Test
    public void testInvalidPasswords()
    {
        ArrayList<String> results;
        results = PasswordCheckerUtility.getInvalidPasswords(studentPasswords2);
        Scanner scan = new Scanner(results.get(0)); //
        assertEquals(scan.next(), "557722AA");
        String nextResults = scan.nextLine().toLowerCase();
        assertTrue(nextResults.contains("lowercase"));
        //assertEquals(scan.nextLine(), " The password must contain at least one lowercase alphabetic character.");
        scan = new Scanner(results.get(1)); //
        assertEquals(scan.next(), "peter5YYY");
        nextResults = scan.nextLine().toLowerCase();
        assertTrue(nextResults.contains("special"));
        //assertEquals(scan.nextLine(), " The password cannot contain more than two of the same character in sequence.");
        scan = new Scanner(results.get(3)); //
        assertEquals(scan.next(), "1nwardstretch");
        nextResults = scan.nextLine().toLowerCase();
        assertTrue(nextResults.contains("uppercase"));
        //assertEquals(scan.nextLine(), " The password must contain at least one uppercase alphabetic character.");
        scan = new Scanner(results.get(5)); //
        assertEquals(scan.next(), "ghijkl");
        nextResults = scan.nextLine().toLowerCase();
        assertTrue(nextResults.contains("uppercase") || nextResults.contains("digit"));
        //assertEquals(scan.nextLine(), " The password must contain at least one digit.");
        scan = new Scanner(results.get(6)); //a
        assertEquals(scan.next(), "Bananamm#");
        nextResults = scan.nextLine().toLowerCase();
        assertTrue(nextResults.contains("digit"));
        //assertEquals(scan.nextLine(), " The password must contain at least one digit.");
    }

}