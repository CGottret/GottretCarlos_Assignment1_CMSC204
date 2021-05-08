import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility {
    public static class LengthException extends Exception {
        public LengthException(String errorMessage) {
            super(errorMessage);
        }
    }
    public static class NoDigitException extends Exception {
        public NoDigitException(String errorMessage) {
            super(errorMessage);
        }
    }
    public static class NoUpperAlphaException extends Exception {
        public NoUpperAlphaException(String errorMessage) {
            super(errorMessage);
        }
    }
    public static class NoLowerAlphaException extends Exception {
        public NoLowerAlphaException(String errorMessage) {
            super(errorMessage);
        }
    }
    public static class InvalidSequenceException extends Exception {
        public InvalidSequenceException(String errorMessage) {
            super(errorMessage);
        }
    }
    public static class NoSpecialCharacterException extends Exception {
        public NoSpecialCharacterException(String errorMessage) {
            super(errorMessage);
        }
    }
    public static class WeakPasswordException extends Exception {
        public WeakPasswordException(String errorMessage) {
            super(errorMessage);
        }
    }
    public PasswordCheckerUtility(){}
    public static boolean isValidPassword(String passwordString) throws LengthException,
            NoDigitException, NoUpperAlphaException, NoLowerAlphaException, InvalidSequenceException, NoSpecialCharacterException, WeakPasswordException{
        System.out.print(passwordString.length() < 6);
        if (passwordString.length() < 6){
            throw new LengthException("The password must be at least 6 characters long");
        }
        Pattern pattern = Pattern.compile(".*[A-Z]+.*");
        Matcher matcher = pattern.matcher(passwordString);
        if (!matcher.matches()){
            throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
        }
        // System.out.println("Testing");
        pattern = Pattern.compile(".*[a-z]+.*");
        matcher = pattern.matcher(passwordString);
        if (!matcher.matches()){
            throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character");
        }
        //System.out.println("Testing 2");
        pattern = Pattern.compile(".*[0-9]+.*");
        matcher = pattern.matcher(passwordString);
        if (!matcher.matches()){
            throw new NoDigitException("The password must contain at least one digit");
        }
        pattern = Pattern.compile(".*[*@!#%&()^~{}]+.*");
        matcher = pattern.matcher(passwordString);
        if (!matcher.matches()){
            throw new NoSpecialCharacterException("The password must contain at least one special character");
        }
        pattern = Pattern.compile("^(?:(\\w)(?!\\1{2})|\\W)+$");
        matcher = pattern.matcher(passwordString);
        if (!matcher.matches()){
            throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence.");
        }
//        if (passwordString.length() >= 6 && passwordString.length() <= 9){
//            throw new WeakPasswordException("The password is OK but weak - it contains fewer than 10 characters.");
//        }
        return true;
    }
    public static boolean isWeakPassword(String passwordString){
        return passwordString.length() >= 6 && passwordString.length() <= 9;
    }
    public static ArrayList<String> getInvalidPasswords(List<String> passwords){
        ArrayList<String> invalidPasswordList = new ArrayList<>();
        for (int i = 0; i < passwords.size(); i++){
            try{
                isValidPassword(passwords.get(i));
                continue;
            } catch (NoDigitException e) {
                invalidPasswordList.add(passwords.get(i) + " The password must contain at least one digit");
                continue;
            } catch (NoUpperAlphaException e) {
                invalidPasswordList.add(passwords.get(i) + " The password must contain at least one uppercase alphabetic character");
                continue;
            } catch (WeakPasswordException e) {
                continue;
            } catch (NoLowerAlphaException e) {
                invalidPasswordList.add(passwords.get(i) + " The password must contain at least one lowercase alphabetic character");
                continue;
            } catch (InvalidSequenceException e) {
                invalidPasswordList.add(passwords.get(i) + " The password cannot contain more than two of the same character in sequence.");
                continue;
            } catch (LengthException e) {
                invalidPasswordList.add(passwords.get(i) + " The password must be at least 6 characters long");
                continue;
            } catch (NoSpecialCharacterException e) {
                invalidPasswordList.add(passwords.get(i) + " The password must contain at least one special character");
                continue;
            }
        }
        return invalidPasswordList;
    }
}

