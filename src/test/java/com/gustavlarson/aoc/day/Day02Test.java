package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {
    @Test
    public void testPart101() {
        final List<String> input = List.of("1-3 a: abcde");
        final Day day = new Day02(input);
        assertEquals("1", day.solvePart1());
    }

    @Test
    public void testPart102() {
        final List<String> input = List.of("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc");
        final Day day = new Day02(input);
        assertEquals("2", day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = List.of("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc");
        final Day day = new Day02(input);
        assertEquals("1", day.solvePart2());
    }
}
