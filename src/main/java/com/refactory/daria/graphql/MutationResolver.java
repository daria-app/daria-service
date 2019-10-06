package com.refactory.daria.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.refactory.daria.models.Track;
import com.refactory.daria.models.TrackInput;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {

    @Transactional
    public Track saveTrack(TrackInput input) {
        return Track.builder().title("test").build();
        //return articleRepository.saveAndFlush(new Article(null, input.getTitle(), input.getText(), input.getAuthorId()));
    }

}
