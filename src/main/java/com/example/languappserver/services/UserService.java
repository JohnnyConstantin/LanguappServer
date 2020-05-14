package com.example.languappserver.services;

import com.example.languappserver.models.User;
import com.example.languappserver.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Сервис - подушка между репозиторием и контроллером

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Transactional(readOnly = true)
    public User getUser(Integer id){
        return userRepo.findById(id).orElse(null);
    }

    @Transactional
    public User saveUser(User user){
        userRepo.save(user);
        return userRepo.findById(user.getId()).orElse(null);
    }

    @Transactional
    public User findUserById(String id){
        return userRepo.findUserById(id);
    }
}
