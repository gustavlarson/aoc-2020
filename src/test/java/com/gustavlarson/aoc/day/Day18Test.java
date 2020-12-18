package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day18Test {
    private static Day getDay(List<String> input) {
        return new Day18(input);
    }

    @Test
    public void testPart101() {
        final List<String> input = List.of("1 + 2");
        final Day day = getDay(input);
        assertEquals(3, day.solvePart1());
    }

    @Test
    public void testPart102() {
        final List<String> input = List.of("2 * 2");
        final Day day = getDay(input);
        assertEquals(4, day.solvePart1());
    }

    @Test
    public void testPart103() {
        final List<String> input = List.of("1 + 2 * 3");
        final Day day = getDay(input);
        assertEquals(9, day.solvePart1());
    }

    @Test
    public void testPart104() {
        final List<String> input = List.of("1 + 2 * 3 + 4 * 5 + 6");
        final Day day = getDay(input);
        assertEquals(71, day.solvePart1());
    }

    @Test
    public void testPart105() {
        final List<String> input = List.of("1 + (2 * 3) + (4 * (5 + 6))");
        final Day day = getDay(input);
        assertEquals(51, day.solvePart1());
    }

    @Test
    public void testPart106() {
        final List<String> input = List.of("1 + 2 * 3 + 4 * 5 + 6", "1 + (2 * 3) + (4 * (5 + 6))");
        final Day day = getDay(input);
        assertEquals(71 + 51, day.solvePart1());
    }

    @Test
    public void testPart107() {
        final List<String> input = List.of("2 * 3 + (4 * 5)");
        final Day day = getDay(input);
        assertEquals(26, day.solvePart1());
    }

    @Test
    public void testPart108() {
        final List<String> input = List.of("5 + (8 * 3 + 9 + 3 * 4 * 3)");
        final Day day = getDay(input);
        assertEquals(437, day.solvePart1());
    }


    @Test
    public void testPart109() {
        final List<String> input = List.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))");
        final Day day = getDay(input);
        assertEquals(12240, day.solvePart1());
    }

    @Test
    public void testPart110() {
        final List<String> input = List.of("((((2 + 4) * 9) * (((6 + 9) * 8) + 6) + 6) + (2 + 4)) * 2");
        final Day day = getDay(input);
        assertEquals(13632, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = getAsList("""
                1
                2
                """);
        final Day day = getDay(input);
        assertEquals(0, day.solvePart2());
    }

    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(18);
        final Day day = getDay(input);
        assertEquals(0, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(18);
        final Day day = getDay(input);
        assertEquals(0, day.solvePart2());
    }
}
