package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayTest25 {
    private static Day getDay(List<String> input) {
        return new Day25(input);
    }

    @Test
    public void testPart101() {
        final List<String> input = List.of("17807724", "5764801");
        final Day day = getDay(input);
        assertEquals(14897079, day.solvePart1());
    }

    @Test
    public void testPart102() {
        final List<String> input = List.of("5764801", "17807724");
        final Day day = getDay(input);
        assertEquals(14897079, day.solvePart1());
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
        final List<String> input = getFromFile(25);
        final Day day = getDay(input);
        assertEquals(10187657, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(25);
        final Day day = getDay(input);
        assertEquals(0, day.solvePart2());
    }
}
