package com.example.demo.services.dbservices.dbserviceImpl;

import com.example.demo.models.entities.User;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.dbservices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public User createUser(String name) {
        User user = new User();
        user.setName(name);
        return userRepository.save(user);
    }
}
