package com.wj.myssm.utils;

import java.util.UUID;

public class CreateUUIDUtils {
    /**
     *
     * @param
     * @return java.lang.String
     * @author daodao
     * @date 2020/3/26 23:03
     * 用于生成主键id的UUID
     */
    public static String createID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
