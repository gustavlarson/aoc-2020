package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day23Test {
    private static Day getDay(List<String> input) {
        return new Day23(input);
    }

    @Test
    public void testPart101() {
        final List<String> input = List.of("389125467");
        final Day day = getDay(input);
        assertEquals(67384529, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = List.of("389125467");
        final Day day = getDay(input);
        assertEquals(149245887792L, day.solvePart2());
    }

    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(23);
        final Day day = getDay(input);
        assertEquals(95648732, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(23);
        final Day day = getDay(input);
        assertEquals(0, day.solvePart2());
    }
}
