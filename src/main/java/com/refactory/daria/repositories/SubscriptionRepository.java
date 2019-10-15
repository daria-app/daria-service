package com.refactory.daria.repositories;

import com.refactory.daria.models.Track;
import com.refactory.daria.models.TrackSubscription;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SubscriptionRepository extends PagingAndSortingRepository<TrackSubscription, String> {
    List<TrackSubscription> findBySubscriberId(String subscriberId);
    List<TrackSubscription> findByProviderId(String providerId);
    TrackSubscription findBySubscriberIdAndTrackId(String subscriberId, String trackId);
    void deleteByTrackId(String trackId);
}
