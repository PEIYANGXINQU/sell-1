package com.laohu.util;

import java.util.Random;

/**
 * Created by @Author tachai on 2018/1/12.
 *
 * @Email 1206966083@qq.com
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 时间加唯一数
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random random=new Random();


        Integer number=random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
