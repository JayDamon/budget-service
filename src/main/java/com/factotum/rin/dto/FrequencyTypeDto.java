package com.factotum.rin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "name"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FrequencyTypeDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    public FrequencyTypeDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
