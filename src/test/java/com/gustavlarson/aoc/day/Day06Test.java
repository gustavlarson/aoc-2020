package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test {
    @Test
    public void testPart101() {
        final Day day = new Day06();
        final List<String> input = List.of("abc");
        final String result = day.solvePart1(input);
        assertEquals("3", result);
    }

    @Test
    public void testPart102() {
        final Day day = new Day06();
        final List<String> input = List.of("abc", "", "a", "b", "c");
        final String result = day.solvePart1(input);
        assertEquals("6", result);
    }

}
