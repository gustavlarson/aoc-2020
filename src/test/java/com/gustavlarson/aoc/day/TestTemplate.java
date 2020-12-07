package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTemplate {
    @Test
    public void testPart101() {
        final List<String> input = List.of("1", "2");
        final Day day = new Day01(input);
        final String result = day.solvePart1();
        assertEquals("1", result);
    }

    @Test
    public void testPart201() {
        final List<String> input = List.of("1", "2");
        final Day day = new Day01(input);
        final String result = day.solvePart2();
        assertEquals("1", result);
    }
}
