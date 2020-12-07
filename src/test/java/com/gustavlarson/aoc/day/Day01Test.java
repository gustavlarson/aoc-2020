package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {
    @Test
    public void test01() {
        final List<String> input = List.of("1721", "299");
        final Day day = new Day01(input);
        final String result = day.solvePart1();
        assertEquals(1721 * 299, Integer.parseInt(result));
    }

    @Test
    public void test02() {
        final List<String> input = List.of("1010", "1010");
        final Day day = new Day01(input);
        final String result = day.solvePart1();
        assertEquals(1010 * 1010, Integer.parseInt(result));
    }

    @Test
    public void test03() {
        final List<String> input = List.of("1721", "979", "366", "299", "675", "1456");
        final Day day = new Day01(input);
        final String result = day.solvePart1();
        assertEquals(1721 * 299, Integer.parseInt(result));
    }

    @Test
    public void test04() {
        final List<String> input = List.of("1721", "979", "366", "299", "675", "1456");
        final Day day = new Day01(input);
        final String result = day.solvePart2();
        assertEquals(979 * 366 * 675, Integer.parseInt(result));
    }
}
