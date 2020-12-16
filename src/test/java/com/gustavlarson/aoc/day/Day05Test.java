package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test {
    @Test
    public void testPart101() {
        final List<String> input = List.of("FBFBBFFRLR");
        final Day day = new Day05(input);
        assertEquals(357, day.solvePart1());
    }

    @Test
    public void testPart102() {
        final List<String> input = List.of("FBFBBFFRLR", "BFFFBBFRRR");
        final Day day = new Day05(input);
        assertEquals(567, day.solvePart1());
    }


    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(5);
        final Day day = new Day05(input);
        assertEquals(874, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(5);
        final Day day = new Day05(input);
        assertEquals(594, day.solvePart2());
    }
}
