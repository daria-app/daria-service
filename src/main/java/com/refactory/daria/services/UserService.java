package com.refactory.daria.services;

import com.refactory.daria.models.User;
import com.refactory.daria.payload.LoginRequest;
import org.bson.types.ObjectId;

import javax.security.auth.login.LoginContext;
import java.util.List;

public interface UserService {

    User findOneByEmail(String email);
    User findOneById(ObjectId id);
    List<User> findSubscribersByUserIds(List<String> subscriberIds);
    List<User> findContributorsByUserIds(List<String> contributorIds);

    User createUser(LoginRequest loginRequest);

}
