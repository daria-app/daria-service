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
    }

}
