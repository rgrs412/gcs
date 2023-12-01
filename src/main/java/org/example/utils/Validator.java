package org.example.utils;

public class Validator {
    public static boolean isInt(String num){
        try{
            Integer.parseInt(num);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public static boolean isIntBetweenInclusive(int start, int end, String num){
        if(!isInt(num)) return false;
        int val = Integer.parseInt(num);
        return val >= start && val <= end;
    }
}
