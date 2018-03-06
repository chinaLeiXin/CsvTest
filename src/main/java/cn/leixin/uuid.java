package cn.leixin;

import java.util.UUID;

public class uuid {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
