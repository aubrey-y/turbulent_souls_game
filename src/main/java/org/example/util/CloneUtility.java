package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CloneUtility {

    public static Object deepCopy(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        Object copy;
        try {
            copy = objectMapper.readValue(objectMapper.writeValueAsString(o), o.getClass());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return copy;
    }
}
