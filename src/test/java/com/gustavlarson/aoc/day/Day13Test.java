package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
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
        final List<String> input = getAsList("""
                1
                2
                """);
        final Day day = new Day13(input);
        assertEquals(0, day.solvePart2());
    }

}
