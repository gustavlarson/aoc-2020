package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
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
        final List<String> input = getAsList("""
                ..##.......
                #...#...#..
                .#....#..#.
                ..#.#...#.#
                .#...##..#.
                ..#.##.....
                .#.#.#....#
                .#........#
                #.##...#...
                #...##....#
                .#..#...#.#
                                """);

        final Day day = new Day03(input);
        assertEquals(336, day.solvePart2());
    }

    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(3);
        final Day day = new Day03(input);
        assertEquals(278, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(3);
        final Day day = new Day03(input);
        assertEquals(9709761600L, day.solvePart2());
    }
}
