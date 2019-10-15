package com.refactory.daria.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.refactory.daria.models.Phrase;
import com.refactory.daria.models.TrackSubscription;
import com.refactory.daria.models.Track;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhraseRepository phraseRepository;


    public List<Track> getTracks() {
        return (ArrayList<Track>)trackRepository.findAll();
    }

    public List<Track> getFollowedTracks() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        List<TrackSubscription> subscriptions = subscriptionRepository.findBySubscriberId(principal.getId());

        return subscriptions.stream().map(subscription -> trackRepository.findById(subscription.getTrackId()).get())
                .collect(Collectors.toList());

    }

    public List<Track> getAvailableTracks() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        List<TrackSubscription> subscriptions = subscriptionRepository.findBySubscriberId(principal.getId());
        List<String> trackIds = subscriptions.stream().map(subscription -> subscription.getTrackId())
                .collect(Collectors.toList());

        return trackRepository.findByIdNotIn(trackIds);

    }

    public List<Track> getManagedTracks() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        return trackRepository.findByAuthorId(principal.getId());

    }

    public Track getTrack(String id) {

        Track track = trackRepository.findById(id).orElse(null);
        if (track == null) {
            throw new ResourceNotFoundException("Track", id);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        TrackSubscription subscription = subscriptionRepository.findBySubscriberIdAndTrackId(principal.getId(), id);
        track.setPhrases(phraseRepository.findByTrackId(id));
        if (subscription != null) {
            track.setSubscribed(true);
            track.setSubscriptionId(subscription.getId());
        }

        return track;

    }

    public List<TrackSubscription> getTrackSubscriptionsByProviderId(String providerId) {
        return subscriptionRepository.findByProviderId(providerId);
    }

    public List<TrackSubscription> getTrackSubscriptionsBySubscriberId(String subscriberId) {
        return subscriptionRepository.findBySubscriberId(subscriberId);
    }

    public TrackSubscription getTrackSubscription(String id) {
        return subscriptionRepository.findById(id).orElse(null);
    }

    public List<Phrase> getPhrasesByTrackId(String trackId) {
        return phraseRepository.findByTrackId(trackId);
    }

}
