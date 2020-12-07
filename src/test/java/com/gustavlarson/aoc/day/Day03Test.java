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
        assertEquals(0, day.solvePart1());
    }

    @Test
    public void testPart102() {
        final List<String> input = List.of("..##.......", "#...#...#..");
        final Day day = new Day03(input);
        assertEquals(0, day.solvePart1());
    }

    @Test
    public void testPart103() {
        final List<String> input = List.of("..##.......", "#...#...#..", ".#....#..#.");
        final Day day = new Day03(input);
        assertEquals(1, day.solvePart1());
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
        assertEquals(336, day.solvePart2());
    }
}
