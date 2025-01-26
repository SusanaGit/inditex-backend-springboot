package com.hackathon.inditex.utils;

import java.util.Optional;
import java.util.function.Consumer;

public final class Utils {

    private Utils() {
    }

    public static <T> void ifNotNull(T value, Consumer<T> action) {
        Optional.ofNullable(value).ifPresent(action);
    }

}
