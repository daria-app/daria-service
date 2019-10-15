package com.refactory.daria.services.implementation;

import com.refactory.daria.models.Track;
import com.refactory.daria.repositories.TrackRepository;
import com.refactory.daria.services.TrackService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;

    @Autowired
    TrackServiceImpl(TrackRepository trackRepository){
        this.trackRepository = trackRepository;
    }

    @Override
    public Track findTrackById(String id) {
        return trackRepository.findById(id).orElse(null);
    }

    @Override
    public List<Track> findAllTracks() {
        return (ArrayList)trackRepository.findAll();
    }

    @Override
    public Track saveTrack(Track track) {
        return trackRepository.save(track);
    }

}
