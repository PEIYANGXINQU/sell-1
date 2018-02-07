package com.laohu.util;

import com.laohu.enums.CodeEnum;

/**
 * Created by @Author tachai on 2018/1/21.
 *
 * @Email 1206966083@qq.com
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
