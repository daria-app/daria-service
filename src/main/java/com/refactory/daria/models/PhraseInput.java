package com.refactory.daria.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhraseInput {

    private String id;
    private String text;
    private String trackId;
    private Integer order;

}
