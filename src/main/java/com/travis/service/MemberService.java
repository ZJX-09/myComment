package com.travis.service;


public interface MemberService {

    /**
     * 判断手机号是否存在
     * @param phone 手机号
     * @return true：存在，false：不存在
     */
    boolean exists(Long phone);

    /**
     * 保存手机号与对应的验证码的MD5码到缓存中
     * @param phone 手机号
     * @param code 验证码
     * @return 是否保存成功：true：保存成功，false：保存失败
     */
    boolean saveCode(Long phone,String code);

    /**
     * 下发短信验证码
     * @param phone 手机号
     * @param content 验证码
     * @return 是否发送成功：true：发送成功，false：发送失败
     */
    boolean sendCode(Long phone,String content);

    /**
     * 根据手机号获取验证码的MD5码值
     * @param phone 手机号
     * @return 验证码的MD5码值
     */
    String getCode(Long phone);

    /**
     * 保存token与对应的手机号
     * @param token
     * @param phone 手机号
     */
    void saveToken(String token,Long phone);

    /**
     * 根据token获取用户信息(手机号)
     * @param token
     * @return 手机号
     */
    Long getPhone(String token);

    /**
     * 根据手机号获取会员表主键
     * @param phone 手机号
     * @return 会员表主键
     */
    Long getIdByPhone(Long phone);

}
