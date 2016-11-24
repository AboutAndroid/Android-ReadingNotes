package com.ssyijiu.retrofit.bean.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssyijiu on 2016/11/24.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class UserListDTO implements Mapper<List<User>>{
    public int version;  // 一个与View无关的属性
    private List<UserDTO> userList;

    @Override
    public List<User> transform() {
        List<User> list = new ArrayList<>();
        for(UserDTO dto : userList){
            list.add(dto.transform());
        }
        return list;
    }

    private static class UserDTO implements Mapper<User>{
        private String uid;
        private String user_name;
        private String gender;

        @Override
        public User transform() {
            User user = new User();
            user.id = uid;
            user.name = user_name == null ? "未知" : user_name;
            user.isMale = "0".equals(gender);
            return user;
        }
    }
}
