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
@Document(collection = "subscriptions")
public class TrackSubscription {

    @Id
    private String id;

    private String providerId;
    private String subscriberId;
    private String trackId;

    @Transient
    private Track track;

    @Transient
    private User provider;

    @Transient
    private User subscriber;

}
