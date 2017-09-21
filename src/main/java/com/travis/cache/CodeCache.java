package com.travis.cache;


import java.util.HashMap;
import java.util.Map;

/**
 * 验证码缓存，存放用户手机号与所下发的验证码(单例模式)
 */
public class CodeCache {

    private static CodeCache codeCache = null;
    private Map<Long, String> codeMap = null;

    private CodeCache() {
        codeMap = new HashMap<>();
    }

    public static CodeCache getInstance() {
        if (codeCache == null)
        {
            synchronized (CodeCache.class){
                if (codeCache == null){
                    codeCache = new CodeCache();
                }
            }
        }
        return codeCache;
    }

    /**
     * 保存手机号与验证码
     * @param phone 手机号
     * @param code 验证码
     * @return true：保存成功，false：保存失败，手机号已存在
     */
    public boolean save(Long phone,String code){
        if (codeMap.containsKey(phone)) {
            return false;
        } else {
            codeMap.put(phone,code);
            return true;
        }
    }

    /**
     * 根据手机号获取验证码
     * @param phone 手机号
     * @return 验证码
     */
    public String getCode(Long phone) {
        return codeMap.get(phone);
    }







}
