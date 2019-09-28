package com.refactory.daria;

import com.refactory.daria.models.Track;
import com.refactory.daria.models.User;
import com.refactory.daria.repositories.TrackRepository;
import com.refactory.daria.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDataLoader {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrackRepository trackRepository;

    @Test
    public void generateData() {
        userRepository.deleteAll();
        trackRepository.deleteAll();
        List<User> users = new ArrayList<>();
        users.add(User.builder().username("daria").createdAt(new Date()).subscribedTrackIds(new ArrayList<>()).contributedTrackIds(new ArrayList<>()).build());
        users.add(User.builder().username("vladimir").createdAt(new Date()).subscribedTrackIds(new ArrayList<>()).contributedTrackIds(new ArrayList<>()).build());
        users = (ArrayList) userRepository.saveAll(users);
        List<Track> tracks = new ArrayList<>();
        tracks.add(Track.builder().title("Finnish with Vladimir").minutesPracticed(0).build());
        tracks.add(Track.builder().title("English with Vladimir").minutesPracticed(0).build());
        tracks = (ArrayList) trackRepository.saveAll(tracks);
        users.get(0).setSubscribedTrackIds(Arrays.asList(tracks.get(0).getId().toHexString(), tracks.get(1).getId().toHexString()));
        users.get(1).setContributedTrackIds(Arrays.asList(tracks.get(0).getId().toHexString(), tracks.get(1).getId().toHexString()));
        userRepository.saveAll(users);
        tracks.get(0).setSubscriberIds(Arrays.asList(users.get(0).getId().toHexString()));
        tracks.get(0).setContributorIds(Arrays.asList(users.get(1).getId().toHexString()));
        tracks.get(1).setSubscriberIds(Arrays.asList(users.get(0).getId().toHexString()));
        tracks.get(1).setContributorIds(Arrays.asList(users.get(1).getId().toHexString()));
        trackRepository.saveAll(tracks);
        users = (ArrayList) userRepository.findAll();
        tracks = (ArrayList) trackRepository.findAll();
        assertThat(users.get(0).getUsername()).isEqualTo("daria");
        assertThat(tracks.get(0).getTitle()).isEqualTo("Finnish with Vladimir");

    }

}