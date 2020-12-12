package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Test {
    @Test
    public void testPart101() {
        final List<String> input = getAsList("""
                F10
                N3
                F7""");
        final Day day = new Day12(input);
        assertEquals(20, day.solvePart1());
    }

    @Test
    public void testPart102() {
        final List<String> input = getAsList("""
                F10
                N3
                F7
                R90
                F11""");
        final Day day = new Day12(input);
        assertEquals(25, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = getAsList("""
                F10
                N3
                F7
                R90
                F11""");
        final Day day = new Day12(input);
        assertEquals(286, day.solvePart2());
    }

    @Test
    public void testPart202() {
        final List<String> input = getAsList("""
                F10
                N3
                F7
                L270
                F11""");
        final Day day = new Day12(input);
        assertEquals(286, day.solvePart2());
    }

}
