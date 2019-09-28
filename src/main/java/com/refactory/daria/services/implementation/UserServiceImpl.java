package com.refactory.daria.services.implementation;

import com.refactory.daria.models.User;
import com.refactory.daria.repositories.UserRepository;
import com.refactory.daria.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User findOneById(ObjectId id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findContributorsByUserIds(List<String> contributorIds) {
        return userRepository.findByIdIn(contributorIds);
    }

    @Override
    public List<User> findSubscribersByUserIds(List<String> subscriberIds) {
        return userRepository.findByIdIn(subscriberIds);
    }

}
