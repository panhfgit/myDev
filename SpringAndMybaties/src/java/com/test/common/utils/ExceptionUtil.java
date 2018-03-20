package com.test.common.utils;

/**
 * Created by dizl on 2015/6/3.
 * 业务异常工具类
 */
public final class ExceptionUtil {
    public static void throwBusinessException(String key, Object[] args) throws BusinessException{
        throw new BusinessException(key, args);
    }

    public static void throwBusinessException(String key) throws BusinessException {
        throw new BusinessException(key);
    }

    public static void throwBusinessException(String key, String val1) throws BusinessException {
        throw new BusinessException(key, new String[] { val1 });
    }

    public static void throwBusinessException(String key, String val1, String val2) throws BusinessException {
        throw new BusinessException(key, new String[] { val1, val2 });
    }

    public static void throwBusinessException(String key, String val1, String val2, String val3) throws BusinessException {
        throw new BusinessException(key, new String[] { val1, val2, val3 });
    }
}
