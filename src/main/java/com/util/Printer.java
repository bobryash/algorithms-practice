package com.util;

import java.util.List;
import java.util.stream.Collectors;

public class Printer {

    public static <T> String getFlatList(List<List<T>> lists) {
        return lists.stream()
                .map(list -> "[" + list.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")) + "]")
                .collect(Collectors.joining(", "));
    }
}
