package com.refactory.daria.repositories;

import com.refactory.daria.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, ObjectId> {
    List<User> findByUsername(String username);
    List<User> findByEmail(String email);
    List<User> findByIdIn(List<String> ids);
    List<User> findBySubscribedTrackIds(ObjectId trackId);
    List<User> findByContributedTrackIds(ObjectId trackId);
}
