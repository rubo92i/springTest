package am.basic.springTest.util.encoder;

public class Generator {

    public static final String digits = "0123456789";


    public static String getRandomDigits(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(digits.charAt((int) (Math.random() * digits.length())));
        }

        return stringBuilder.toString();
    }
}
