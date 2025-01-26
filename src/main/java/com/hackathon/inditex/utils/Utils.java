package com.hackathon.inditex.utils;

import java.util.function.Consumer;

public final class Utils {

    private Utils() {
    }

    public static <T> void updateIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }

}
