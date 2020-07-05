package com.zero.vqueue.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;

public class ObjectMapperUtil {
    private static final ModelMapper modelMapper;
    private static final ObjectMapper objectMapper;
    
    static {
        modelMapper = new ModelMapper();
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(Include.NON_NULL);
    }
    
    private ObjectMapperUtil() {
    }
    
    public static <S, D> D map(final S entityToMap, Class<D> expectedClass) {
        return modelMapper.map(entityToMap, expectedClass);
    }
    
    public static <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }
    
    public static String mapObjectToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    public static <T> T mapJsonToObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
