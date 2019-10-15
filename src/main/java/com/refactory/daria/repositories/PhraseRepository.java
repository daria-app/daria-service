package com.refactory.daria.repositories;

import com.refactory.daria.models.Phrase;
import com.refactory.daria.models.Track;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PhraseRepository extends PagingAndSortingRepository<Phrase, String> {
    List<Phrase> findByTrackId(String trackId);
}
