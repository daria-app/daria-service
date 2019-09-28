package com.refactory.daria.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class User {

    private ObjectId id;

    private String username;
    private Date createdAt;
    private List<String> subscribedTrackIds;
    private List<String> contributedTrackIds;

}
