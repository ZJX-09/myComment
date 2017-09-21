package com.travis.service.impl;


import com.travis.bean.Member;
import com.travis.cache.CodeCache;
import com.travis.cache.TokenCache;
import com.travis.dao.MemberDao;
import com.travis.service.MemberService;
import com.travis.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberDao memberDao;

    private Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Override
    public boolean exists(Long phone) {

        Member member = new Member();
        member.setPhone(phone);
        List<Member> list = memberDao.select(member);
        return list != null && list.size() == 1;

    }

    @Override
    public boolean saveCode(Long phone, String code) {
        // TODO 在真实环境中，改成借助第三方实现
        CodeCache codeCache = CodeCache.getInstance();
        return codeCache.save(phone, MD5Util.getMD5(code));
    }

    @Override
    public boolean sendCode(Long phone, String content) {
        // TODO 真实环境调用手机信息通道
        logger.info(phone + "|" + content);
        return true;
    }

    @Override
    public String getCode(Long phone) {
        CodeCache codeCache = CodeCache.getInstance();
        return codeCache.getCode(phone);
    }

    @Override
    public void saveToken(String token, Long phone) {
        TokenCache tokenCache = TokenCache.getInstance();
        tokenCache.save(token, phone);
    }

    @Override
    public Long getPhone(String token) {
        TokenCache tokenCache = TokenCache.getInstance();
        return tokenCache.getPhone(token);
    }

    @Override
    public Long getIdByPhone(Long phone) {
        Member member = new Member();
        member.setPhone(phone);
        List<Member> list = memberDao.select(member);
        if (list != null && list.size() == 1) {
            return list.get(0).getId();
        }
        return null;
    }


}
