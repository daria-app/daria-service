package com.refactory.daria.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "tracks")
public class Track {

    @Id
    private String id;

    private String title;
    private String description;
    private String authorId;

    @Transient
    private User author;

    @Transient
    private Boolean subscribed;

    @Transient
    private String subscriptionId;

    @Transient
    private List<Phrase> phrases;

}
