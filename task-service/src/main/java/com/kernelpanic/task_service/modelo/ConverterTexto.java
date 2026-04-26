package com.kernelpanic.task_service.modelo;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class ConverterTexto implements AttributeConverter<List<Integer>, String> {

    @Override
    public String convertToDatabaseColumn(List<Integer> list) {
        if (list == null || list.isEmpty()) return "";
        return list.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    @Override
    public List<Integer> convertToEntityAttribute(String joined) {
        if (joined == null || joined.trim().isEmpty()) return List.of();
        return Arrays.stream(joined.split(","))
                     .map(Integer::valueOf)
                     .collect(Collectors.toList());
    }
}
