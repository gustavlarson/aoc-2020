package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13Test {
    @Test
    public void testPart101() {
        final List<String> input = List.of("9", "7");
        final Day day = new Day13(input);
        assertEquals(35, day.solvePart1());
    }

    @Test
    public void testPart102() {
        final List<String> input = List.of("939", "7,13,x,x,59,x,31,19");
        final Day day = new Day13(input);
        assertEquals(295, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = List.of("0", "17,x,13,19");
        final Day day = new Day13(input);
        assertEquals(3417, day.solvePart2());
    }

    @Test
    public void testPart202() {
        final List<String> input = List.of("0", "67,7,59,61");
        final Day day = new Day13(input);
        assertEquals(754018, day.solvePart2());
    }

    @Test
    public void testPart203() {
        final List<String> input = List.of("0", "67,x,7,59,61");
        final Day day = new Day13(input);
        assertEquals(779210, day.solvePart2());
    }

    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(13);
        final Day day = new Day13(input);
        assertEquals(1835, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(13);
        final Day day = new Day13(input);
        assertEquals(247086664214628L, day.solvePart2());
    }
}
