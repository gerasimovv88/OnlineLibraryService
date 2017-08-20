package org.vladigeras.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueValidator {

    public static boolean isValidValueOnPattern(String checkValue, Pattern pattern) {
        if (checkValue != null) {
            Matcher matcher = pattern.matcher(checkValue);
            return matcher.matches();
        } else {
            return false;
        }
    }

    public static boolean isValidValueOnPattern(List<String> checkValues, Pattern pattern) {      //on same pattern
        List<Boolean> resultOfValidation = new ArrayList<>();
        for (String checkValue : checkValues) {
            resultOfValidation.add(isValidValueOnPattern(checkValue, pattern));
        }
        boolean mainResult = true;
        for (Boolean each : resultOfValidation) {
            if (!each) {
                mainResult = false;
                return mainResult;
            }
        }
        return mainResult;
    }

    public static boolean isValidId(Long id) {
        return id > 0;
    }
}
