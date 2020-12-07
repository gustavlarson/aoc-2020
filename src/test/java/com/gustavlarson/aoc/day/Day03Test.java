package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {
    @Test
    public void testPart101() {
        final List<String> input = List.of("..##.......");
        final Day day = new Day03(input);
        final String result = day.solvePart1();
        assertEquals("0", result);
    }

    @Test
    public void testPart102() {
        final List<String> input = List.of("..##.......", "#...#...#..");
        final Day day = new Day03(input);
        final String result = day.solvePart1();
        assertEquals("0", result);
    }

    @Test
    public void testPart103() {
        final List<String> input = List.of("..##.......", "#...#...#..", ".#....#..#.");
        final Day day = new Day03(input);
        final String result = day.solvePart1();
        assertEquals("1", result);
    }

    @Test
    public void testPart201() {
        final List<String> input = List.of(
                "..##.......",
                "#...#...#..",
                ".#....#..#.",
                "..#.#...#.#",
                ".#...##..#.",
                "..#.##.....",
                ".#.#.#....#",
                ".#........#",
                "#.##...#...",
                "#...##....#",
                ".#..#...#.#");
        final Day day = new Day03(input);
        final String result = day.solvePart2();
        assertEquals("336", result);
    }
}
