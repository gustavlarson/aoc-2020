package com.gustavlarson.aoc;

import com.gustavlarson.aoc.day.Day01;
import com.gustavlarson.aoc.day.Day02;

import java.util.HashMap;
import java.util.Map;

public class DayFactory {
    private static final Map<Integer, Day> DAYS = new HashMap<>();

    static {
        register(1, new Day01());
        register(2, new Day02());
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
