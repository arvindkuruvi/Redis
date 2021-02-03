package com.arvind;

import java.util.Map;

public interface UserRepo{

    void save(User user);
    Map<String, User> findAll();
    User findById(String id);
    void update(User user);
    void delete(String id);
}
