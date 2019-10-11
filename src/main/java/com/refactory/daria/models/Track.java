package com.refactory.daria.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "tracks")
public class Track {

    private ObjectId id;

    private String title;
    private String description;
    private Integer minutesPracticed;

    private List<String> subscriberIds;
    private List<String> contributorIds;

}
