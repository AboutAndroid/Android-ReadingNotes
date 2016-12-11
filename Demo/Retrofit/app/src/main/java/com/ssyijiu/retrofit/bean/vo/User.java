package com.ssyijiu.retrofit.bean.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssyijiu on 2016/11/24.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class User {
    public String id;
    public String name;
    public boolean isMale;

    public List<Course> courses;

    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        courses = new ArrayList<>();
        courses.add(new Course("c-" + id));
        courses.add(new Course("c-" + name));
    }

    public static class Course {
        public String cname;
        public String cscore;

        public Course(String cname) {
            this.cname = cname;
        }
    }
}
