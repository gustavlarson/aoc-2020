package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test {
    @Test
    public void testPart101() {
        final List<String> input = List.of("abc");
        final Day day = new Day06(input);
        assertEquals(3, day.solvePart1());
    }

    @Test
    public void testPart102() {
        final List<String> input = List.of("abc", "", "a", "b", "c");
        final Day day = new Day06(input);
        assertEquals(6, day.solvePart1());
    }

    @Test
    public void testPart103() {
        final List<String> input = List.of("abc", "", "a", "b", "c", "", "ab", "ac", "", "a", "a", "a", "a", "", "b");
        final Day day = new Day06(input);
        assertEquals(11, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = List.of("abc");
        final Day day = new Day06(input);
        assertEquals(3, day.solvePart2());
    }

    @Test
    public void testPart202() {
        final List<String> input = List.of("abc", "", "a", "b", "c");
        final Day day = new Day06(input);
        assertEquals(3, day.solvePart2());
    }


    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(6);
        final Day day = new Day06(input);
        assertEquals(6351, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(6);
        final Day day = new Day06(input);
        assertEquals(3143, day.solvePart2());
    }
}
