package com._99Recharge.util;

import java.util.regex.Pattern;

public class Validator {
    public static boolean isValidMobileNumber(String mobileNo) {
        if (mobileNo == null) {
            return false;
        }
        return Pattern.compile("^(?:\\+971|00971|0)(?!2)((?:2|3|4|5|6|7|9|50|51|52|55|56)[0-9]{7,})$").matcher(mobileNo).matches();

    }

    public static boolean isValidEmailID(String email) {
        return Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", 2).matcher(email.trim()).matches();
    }

    public static boolean isValidFullName(String fullName) {
        return fullName.charAt(0) != '.';
    }

    public static boolean isValidNationalIdMin(String NID) {
        return NID.length() > 9;
    }

    public static boolean isValidNationalId(String NID) {
        return NID.length() < 18;
    }

    public static boolean isValidPassword(String password) {
        return password.length() > 5 && password.length() < 20;
    }

    public static boolean isValidFullNameLength(String fullName) {
        return fullName.length() >= 2;
    }
}
