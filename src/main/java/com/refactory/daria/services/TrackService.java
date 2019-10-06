package com.refactory.daria.services;

import com.refactory.daria.models.Track;
import org.bson.types.ObjectId;

import java.util.List;

public interface TrackService {

    Track findTrackById(ObjectId id);
    List<Track> findSubscribedTracksByUserId(ObjectId userId);
    List<Track> findContributedTracksByUserId(ObjectId userId);
    List<Track> findAllTracks();

    Track saveTrack(Track track);

}
