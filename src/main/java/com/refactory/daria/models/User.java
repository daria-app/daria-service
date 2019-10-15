package com.refactory.daria.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String username;
    private String name;
    private String email;
    private String password;
    private Date createdAt;
    private String imageUrl;
    private Boolean emailVerified;
    private AuthProvider provider;
    private String providerId;

}
