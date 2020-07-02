package am.basic.springTest.util;

public class PasswordValidator {



    public static boolean isInvalid(String password){
        return password == null || password.length() < 8;
    }
}
