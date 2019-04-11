package com.libDB.util;

import java.lang.Character;

public class StringUtils {

    public static boolean IsNullOrEmpty(String value)
    {
    if (value != null)
        return value.length() == 0;
    else
        return true;
    }

    public static boolean IsNullOrWhiteSpace(String value)
    {
    if (value == null)
        return true;
    for (int index = 0; index < value.length(); ++index)
    {
        if (!Character.isWhitespace(value.charAt(index)))
            return false;
    }
    return true;
    }
}