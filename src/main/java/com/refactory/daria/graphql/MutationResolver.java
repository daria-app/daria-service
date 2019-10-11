package com.refactory.daria.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.refactory.daria.models.Track;
import com.refactory.daria.models.TrackInput;
import com.refactory.daria.repositories.TrackRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@AllArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private TrackRepository trackRespository;

    @Transactional
    public Track saveTrack(TrackInput input) {

        Track track = Track.builder()
                .title(input.getTitle())
                .description(input.getDescription())
                .build();

        if (input.getId() != null) {
            track.setId(new ObjectId(input.getId()));
        }

        return trackRespository.save(track);

    }

    @Transactional
    public Track deleteTrack(String id) {
        Optional<Track> track = trackRespository.findById(new ObjectId(id));
        if (track.isPresent()) {
            trackRespository.deleteById(new ObjectId(id));
        }
        return track.orElseThrow(() -> new ResourceNotFoundException("Track", id));
    }

}
