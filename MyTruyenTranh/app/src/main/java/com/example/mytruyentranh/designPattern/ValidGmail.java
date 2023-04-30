package com.example.mytruyentranh.designPattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidGmail implements IValidation {

    private static Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";// cấu trúc 1 email thông thường

    public ValidGmail() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    // Class kiểm định dạng email
    @Override
    public boolean valid(String data) {
        matcher = pattern.matcher(data);
        return matcher.matches();
    }

    @Override
    public boolean valid(String data, String data2) {
        if(!data.equals(data2)){
            return false;
        }
        return true;
    }
}
