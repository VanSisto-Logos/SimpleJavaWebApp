package com.vansisto.repositories;

import com.vansisto.entities.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public void save(User user) {
        users.add(user);
    }
}
