package com.refactory.daria.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.refactory.daria.models.Track;
import com.refactory.daria.repositories.TrackRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    private TrackRepository trackRepository;

    public List<Track> getTracks() {
        return (ArrayList<Track>)trackRepository.findAll();
    }

    public Track getTrack(String id) {
        return trackRepository.findById(new ObjectId(id)).orElse(null);
    }

}
