package com.xieziming.stap.core.model.user.converter;

import com.xieziming.stap.core.model.user.dao.UserRoleDao;
import com.xieziming.stap.core.model.user.dto.UserDto;
import com.xieziming.stap.core.model.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 8/13/16.
 */
@Component
public class UserConverter {
    @Autowired
    private UserRoleDao userRoleDao;

    public List<UserDto> convertAll(List<User> userList){
        List<UserDto> userDtoList = new ArrayList<UserDto>();
        for (User user : userList){
            userDtoList.add(convert(user));
        }
        return userDtoList;
    }

    public UserDto convert(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setNickName(user.getNickName());
        userDto.setEmail(user.getEmail());
        userDto.setUserRole(userRoleDao.findById(user.getUserRoleId()));
        userDto.setAvatar(user.getAvatar());
        return userDto;
    }
}
