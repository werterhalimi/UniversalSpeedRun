package ch.werter.api.utils;

public class StringUtils {
    public static boolean containsLowerChar(String string){
        for(char c : string.toCharArray())
            if (Character.isLowerCase(c))
                return true;
        return false;
    }
}
