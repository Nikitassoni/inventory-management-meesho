package com.meesho.interview.repository;

import com.meesho.interview.model.User;

import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {
    public static ConcurrentHashMap<String, User> userRepository = new ConcurrentHashMap<>();
}
