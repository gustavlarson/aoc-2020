package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day15Test {
    @Test
    public void testPart101() {
        final List<String> input = List.of("0,3,6");
        final Day day = new Day15(input);
        assertEquals(436, day.solvePart1());
    }

    @Test
    public void testPart102() {
        final List<String> input = List.of("1,3,2");
        final Day day = new Day15(input);
        assertEquals(1, day.solvePart1());
    }

    @Test
    public void testPart103() {
        final List<String> input = List.of("2,1,3");
        final Day day = new Day15(input);
        assertEquals(10, day.solvePart1());
    }

    @Test
    public void testPart104() {
        final List<String> input = List.of("1,2,3");
        final Day day = new Day15(input);
        assertEquals(27, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = List.of("0,3,6");
        final Day day = new Day15(input);
        assertEquals(175594, day.solvePart2());
    }

    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(15);
        final Day day = new Day15(input);
        assertEquals(610, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(15);
        final Day day = new Day15(input);
        assertEquals(1407, day.solvePart2());
    }
}
