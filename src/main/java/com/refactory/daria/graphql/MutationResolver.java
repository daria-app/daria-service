package com.refactory.daria.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.refactory.daria.models.*;
import com.refactory.daria.repositories.PhraseRepository;
import com.refactory.daria.repositories.SubscriptionRepository;
import com.refactory.daria.repositories.TrackRepository;
import com.refactory.daria.repositories.UserRepository;
import com.refactory.daria.security.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@AllArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private TrackRepository trackRespository;

    @Autowired
    private SubscriptionRepository subscriptionRespository;

    @Autowired
    private PhraseRepository phraseRepository;

    @Transactional
    public Phrase savePhrase(PhraseInput input) {

        Phrase phrase = Phrase.builder()
                .text(input.getText())
                .order(input.getOrder())
                .build();

        if (input.getId() != null) {
            phrase.setId(input.getId());
        }

        if (input.getTrackId() != null) {
            phrase.setTrackId(input.getTrackId());
        }


        return phraseRepository.save(phrase);

    }

    @Transactional
    public Track saveTrack(TrackInput input) {

        Track track = Track.builder()
                .title(input.getTitle())
                .description(input.getDescription())
                .build();

        if (input.getId() != null) {
            track.setId(input.getId());
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        track.setAuthorId(principal.getId());

        return trackRespository.save(track);

    }

    @Transactional
    public Track deleteTrack(String id) {
        Optional<Track> track = trackRespository.findById(id);
        if (track.isPresent()) {
            subscriptionRespository.deleteByTrackId(id);
            trackRespository.deleteById(id);
        }
        return track.orElseThrow(() -> new ResourceNotFoundException("Track", id));
    }

    @Transactional
    public TrackSubscription saveTrackSubscription(TrackSubscriptionInput input) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        TrackSubscription subscription = subscriptionRespository.findBySubscriberIdAndTrackId(principal.getId(), input.getTrackId());
        if (subscription != null) {
            return subscription;
        }

        Optional<Track> maybeTrack = trackRespository.findById(input.getTrackId());
        Track track = maybeTrack.orElseThrow(() -> new ResourceNotFoundException("Track", input.getTrackId()));

        TrackSubscription trackSubscription = TrackSubscription.builder()
                .subscriberId(principal.getId())
                .trackId(input.getTrackId())
                .build();

        return subscriptionRespository.save(trackSubscription);

    }

    @Transactional
    public TrackSubscription deleteTrackSubscription(String input) {
        Optional<TrackSubscription> trackSubscription = subscriptionRespository.findById(input);
        if (trackSubscription.isPresent()) {
            subscriptionRespository.deleteById(input);
        }
        return trackSubscription.orElseThrow(() -> new ResourceNotFoundException("Subscription", input));
    }


}
