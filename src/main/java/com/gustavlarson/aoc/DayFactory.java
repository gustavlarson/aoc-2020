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
            case 8 -> new Day08(input);
            case 9 -> new Day09(input);
            case 10 -> new Day10(input);
            case 11 -> new Day11(input);
            case 12 -> new Day12(input);
            case 13 -> new Day13(input);
            case 14 -> new Day14(input);
            case 15 -> new Day15(input);
            case 16 -> new Day16(input);
            case 17 -> new Day17(input);
            case 18 -> new Day18(input);
            case 19 -> new Day19(input);
            case 20 -> new Day20(input);
            case 21 -> new Day21(input);
            case 22 -> new Day22(input);
            case 23 -> new Day23(input);
            default -> throw new IllegalArgumentException("Day not found");
        };

    }

}
