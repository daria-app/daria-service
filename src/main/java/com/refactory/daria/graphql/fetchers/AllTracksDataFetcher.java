package com.refactory.daria.graphql.fetchers;

import com.refactory.daria.models.Track;
import com.refactory.daria.models.User;
import com.refactory.daria.services.TrackService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllTracksDataFetcher implements DataFetcher<List<Track>>{

    private final TrackService trackService;

    @Autowired
    public AllTracksDataFetcher(TrackService trackService){
        this.trackService = trackService;
    }

    @Override
    public List<Track> get(DataFetchingEnvironment env) {
        List<Track> tracks = trackService.findAllTracks();
        return tracks;
    }
}
