package com.laohu.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by @Author tachai on 2018/1/19.
 *
 * @Email 1206966083@qq.com
 */
public class JsonUtil {
    public static String toJson(Object object){
        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson=gsonBuilder.create();
        return gson.toJson(object);
    }
}
