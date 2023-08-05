package io.github.arvind142.framework.utils;

public class CommonUtility {

    public static boolean isNullOrEmpty(Object o){
        return o == null || (String.valueOf(o).trim().equalsIgnoreCase(""));
    }
}
