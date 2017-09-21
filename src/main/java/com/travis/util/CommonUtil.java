package com.travis.util;

import com.travis.constant.SessionKeyConst;
import com.travis.dto.ActionDto;
import com.travis.dto.MenuDto;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * 共通工具类.
 */
public class CommonUtil {

    /**
     * 方法描述：判断一个字符串是否为null或空字符串
     *
     * @param str 需要判断的字符串
     * @return false：不是空字符串，true：是空字符串
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 生成指定位数的随机整数
     *
     * @param number  位数
     * @return 随机整数
     */
    public static int random(int number){

        return (int)((Math.random() * 9 + 1) * (Math.pow(10,number - 1)));

    }

    /**
     * 生成指定范围的随机整数
     * @param rangeL 最小范围
     * @param rangeR 最大范围
     * @return 随机整数
     */
    public static int random(int rangeL,int rangeR){
        if (rangeR >= rangeL){
            return (int) (Math.random() * (rangeR - rangeL + 1) + rangeL);
        }
        return 0;
    }

    /**
     * 获取UUID
     *
     * @return UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * 隐藏电话号码中间4位
     * @param originalPhone
     * @return 隐藏了中间4位号码的电话号码
     */
    public static String hidePhone(String originalPhone){

        StringBuffer phoneBuffer = new StringBuffer(originalPhone);
        return phoneBuffer.replace(3,7,"****").toString();

    }

    /**
     * 判断session中存放的动作dto列表中是否包含指定的url
     * @param session
     * @param url
     * @param method http动作
     * @return true:包含，false：不包含
     */

    public static boolean containsUrl(HttpSession session,String url,String method){
        Object attribute = session.getAttribute(SessionKeyConst.ACTION_INFO);
        if (attribute != null){
            @SuppressWarnings("unchecked")
            List<ActionDto> actionDtoList = (List<ActionDto>)attribute;

            for (ActionDto actionDto:actionDtoList) {
                // 判断方法类型
                if ( !isEmpty(actionDto.getMethod()) && !actionDto.getMethod().equals(method)){
                    continue;
                }
                // 判断路径
                if (!url.matches(actionDto.getUrl())){
                    continue;
                }
                return true;
            }
        }
        return false;
    }

}
