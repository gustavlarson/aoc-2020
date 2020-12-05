package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test {
    @Test
    public void testPart101() {
        final Day day = new Day05();
        final List<String> input = List.of("FBFBBFFRLR");
        final String result = day.solvePart1(input);
        assertEquals("357", result);
    }

    @Test
    public void testPart102() {
        final Day day = new Day05();
        final List<String> input = List.of("FBFBBFFRLR", "BFFFBBFRRR");
        final String result = day.solvePart1(input);
        assertEquals("567", result);
    }
}
