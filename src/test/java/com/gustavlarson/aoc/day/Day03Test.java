package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {
    @Test
    public void testPart101() {
        final Day day = new Day03();
        final List<String> input = List.of("..##.......");
        final String result = day.solvePart1(input);
        assertEquals("0", result);
    }

    @Test
    public void testPart102() {
        final Day day = new Day03();
        final List<String> input = List.of("..##.......", "#...#...#..");
        final String result = day.solvePart1(input);
        assertEquals("0", result);
    }

    @Test
    public void testPart103() {
        final Day day = new Day03();
        final List<String> input = List.of("..##.......", "#...#...#..", ".#....#..#.");
        final String result = day.solvePart1(input);
        assertEquals("1", result);
    }
}
