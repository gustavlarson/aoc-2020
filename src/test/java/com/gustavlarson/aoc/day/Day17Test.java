package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day17Test {
    @Test
    public void testPart101() {
        final List<String> input = getAsList("""
                .#.
                ..#
                ###""");
        final Day day = new Day17(input);
        assertEquals(112, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = getAsList("""
                1
                2
                """);
        final Day day = new Day17(input);
        assertEquals(0, day.solvePart2());
    }

    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(17);
        final Day day = new Day17(input);
        assertEquals(0, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(17);
        final Day day = new Day17(input);
        assertEquals(0, day.solvePart2());
    }
}
