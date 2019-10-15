package com.refactory.daria.repositories;

import com.refactory.daria.models.Track;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TrackRepository extends PagingAndSortingRepository<Track, String> {
    List<Track> findByIdNotIn(List<String> ids);
    List<Track> findByAuthorId(String authorId);
}
