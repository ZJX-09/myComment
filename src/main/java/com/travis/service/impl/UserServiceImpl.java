package com.travis.service.impl;

import com.travis.bean.User;
import com.travis.dao.UserDao;
import com.travis.dto.UserDto;
import com.travis.dto.UserDtoForReset;
import com.travis.service.UserService;
import com.travis.util.CommonUtil;
import com.travis.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean validate(UserDto userDto) {


        if (userDto != null && !CommonUtil.isEmpty(userDto.getName()) && !CommonUtil.isEmpty(userDto.getPassword())) {
            User user = new User();
            BeanUtils.copyProperties(userDto, user);
            List<User> list = userDao.select(user);
            if(list.size() == 1) {
                BeanUtils.copyProperties(list.get(0),userDto);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean add(UserDto userDto) {

        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(MD5Util.getMD5(userDto.getPassword()));
        return userDao.insert(user) == 1;

    }

    @Override
    public boolean remove(Long id) {

        return userDao.delete(id) == 1;

    }


    @Override
    public List<UserDto> getList() {

        List<UserDto> result = new ArrayList<>();
        List<User> userList = userDao.select(new User());
        for (User user : userList) {
            UserDto userDto = new UserDto();
            result.add(userDto);
            BeanUtils.copyProperties(user, userDto);
            userDto.setpId(0);
        }
        return result;

    }

    @Override
    public UserDto getById(Long id) {

        UserDto userDto = new UserDto();
        User user = userDao.selectById(id);
        BeanUtils.copyProperties(user, userDto);
        return userDto;

    }

    @Override
    public boolean modify(UserDto userDto) {

        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        if(!CommonUtil.isEmpty(userDto.getPassword())) {
            user.setPassword(MD5Util.getMD5(userDto.getPassword()));
        }
        return userDao.update(user) == 1;

    }

    @Override
    public boolean checkPwd(UserDtoForReset dto) {

        if (dto != null && !CommonUtil.isEmpty(dto.getName()) && !CommonUtil.isEmpty(dto.getOldPassword())) {
            User user = new User();
            user.setName(dto.getName());
            user.setPassword(dto.getOldPassword());
            List<User> userList = userDao.select(user);
            return userList.size() == 1;
        }
        return false;
    }

    @Override
    public boolean resetPwd(UserDtoForReset dto) {

        User user = new User();
        user.setId(dto.getId());
        user.setPassword(dto.getNewPassword());
        return userDao.update(user) == 1;

    }

}
