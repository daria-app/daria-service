package com.refactory.daria.services;

import com.refactory.daria.models.User;
import org.bson.types.ObjectId;
import java.util.List;

public interface UserService {

    User findOneById(ObjectId id);
    List<User> findSubscribersByUserIds(List<String> subscriberIds);
    List<User> findContributorsByUserIds(List<String> contributorIds);

}
