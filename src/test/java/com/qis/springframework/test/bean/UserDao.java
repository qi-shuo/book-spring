package com.qis.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: qishuo
 * @date: 2022/12/8 14:42
 */
public class UserDao implements IUserDao{
    private static final Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "qis");
    }
    @Override
    public String queryUserName(String uid) {
        return hashMap.get(uid);
    }
}
