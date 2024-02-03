package dev.parikh.kinoserver.movie;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class Movie {
    @JsonAnyGetter
    public Map<String, Object> properties = new HashMap<>();

    @JsonAnySetter
    public void add(String property, Object value){
        properties.put(property, value);
    }
}