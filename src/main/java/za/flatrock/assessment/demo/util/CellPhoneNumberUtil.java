package za.flatrock.assessment.demo.util;

public class CellPhoneNumberUtil {
    public static String format(String value) {
        return value.replaceAll("[\\s()-]", "");
    }
}
