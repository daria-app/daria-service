package com.refactory.daria.graphql.fetchers;

import com.refactory.daria.models.Track;
import com.refactory.daria.models.User;
import com.refactory.daria.services.TrackService;
import com.refactory.daria.services.UserService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContributorsDataFetcher implements DataFetcher<List<User>>{

    private final UserService userService;

    @Autowired
    public ContributorsDataFetcher(UserService userService){
        this.userService = userService;
    }

    @Override
    public List<User> get(DataFetchingEnvironment env) {

        Track track = env.getSource();
        List<User> users = userService.findContributorsByUserIds(track.getContributorIds());

        return users;

    }
}
