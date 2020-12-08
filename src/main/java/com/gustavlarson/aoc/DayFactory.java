package com.gustavlarson.aoc;

import com.gustavlarson.aoc.day.*;

import java.util.List;

public class DayFactory {

    public static Day getDay(final int day, final List<String> input) {
        return switch (day) {
            case 1 -> new Day01(input);
            case 2 -> new Day02(input);
            case 3 -> new Day03(input);
            case 4 -> new Day04(input);
            case 5 -> new Day05(input);
            case 6 -> new Day06(input);
            case 7 -> new Day07(input);
            default -> throw new IllegalArgumentException("Day not found");
        };

    }

}
