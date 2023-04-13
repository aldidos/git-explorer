package jikim.git.explorer.utils;

import java.nio.charset.StandardCharsets;

public class Utils 
{
    public static String byte2String(byte[] data)
    {
        return new String(data, StandardCharsets.UTF_8);
    }
    
}
