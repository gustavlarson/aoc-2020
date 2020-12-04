package com.gustavlarson.aoc;

import com.gustavlarson.aoc.day.*;

import java.util.HashMap;
import java.util.Map;

public class DayFactory {
    private static final Map<Integer, Day> DAYS = new HashMap<>();

    static {
        register(1, new Day01());
        register(2, new Day02());
        register(3, new Day03());
        register(4, new Day04());
        register(5, new Day05());

    }

    private static void register(final int day, final Day instance) {
        if (instance != null) {
            DAYS.put(day, instance);
        }
    }

    public static Day getDay(final int day) {
        if (DAYS.containsKey(day)) {
            return DAYS.get(day);
        }
        throw new IllegalArgumentException("Day not found");
    }

}
