package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test {
    @Test
    public void testPart101() {
        final List<String> input = List.of("abc");
        final Day day = new Day06(input);
        final String result = day.solvePart1();
        assertEquals("3", result);
    }

    @Test
    public void testPart102() {
        final List<String> input = List.of("abc", "", "a", "b", "c");
        final Day day = new Day06(input);
        final String result = day.solvePart1();
        assertEquals("6", result);
    }

    @Test
    public void testPart103() {
        final List<String> input = List.of("abc", "", "a", "b", "c", "", "ab", "ac", "", "a", "a", "a", "a", "", "b");
        final Day day = new Day06(input);
        final String result = day.solvePart1();
        assertEquals("11", result);
    }

    @Test
    public void testPart201() {
        final List<String> input = List.of("abc");
        final Day day = new Day06(input);
        final String result = day.solvePart2();
        assertEquals("3", result);
    }

    @Test
    public void testPart202() {
        final List<String> input = List.of("abc", "", "a", "b", "c");
        final Day day = new Day06(input);
        final String result = day.solvePart2();
        assertEquals("3", result);
    }
}
