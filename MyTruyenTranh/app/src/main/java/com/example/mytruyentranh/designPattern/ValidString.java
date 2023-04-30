package com.example.mytruyentranh.designPattern;

public class ValidString implements IValidation {

    @Override
    public boolean valid(String data) {
        if(data.isEmpty()){
            return false;
        }
        return true;
    }
    @Override
    public boolean valid(String data, String data2) {
        if(!data.equals(data2)){
            return false;
        }
        return true;
    }
}
