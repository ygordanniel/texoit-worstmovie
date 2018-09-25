package com.texoit.worstmovie.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StringUtil {

    private StringUtil() {}

    public static List<String> splitStringGetMappedValidValues(String str, String splitRegex,
                                                         Predicate<String> validationPredicate,
                                                         Function<String, String> mapper) {
        return Arrays.asList(str.split(splitRegex))
                .stream()
                .filter(validationPredicate)
                .map(mapper)
                .collect(Collectors.toList());
    }
}
