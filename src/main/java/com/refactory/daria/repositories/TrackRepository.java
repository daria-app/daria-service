package com.refactory.daria.repositories;

import com.refactory.daria.models.Track;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TrackRepository extends PagingAndSortingRepository<Track, ObjectId> {
    List<Track> findBySubscriberIds(ObjectId userId);
    List<Track> findByContributorIds(ObjectId userId);
}
