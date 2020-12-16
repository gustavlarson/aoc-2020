package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test {
    @Test
    public void testPart102() {
        final List<String> input = getAsList("""
                L.LL.LL.LL
                LLLLLLL.LL
                L.L.L..L..
                LLLL.LL.LL
                L.LL.LL.LL
                L.LLLLL.LL
                ..L.L.....
                LLLLLLLLLL
                L.LLLLLL.L
                L.LLLLL.LL""");
        final Day day = new Day11(input);
        assertEquals(37, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = getAsList("""
                L.LL.LL.LL
                LLLLLLL.LL
                L.L.L..L..
                LLLL.LL.LL
                L.LL.LL.LL
                L.LLLLL.LL
                ..L.L.....
                LLLLLLLLLL
                L.LLLLLL.L
                L.LLLLL.LL""");
        final Day day = new Day11(input);
        assertEquals(26, day.solvePart2());
    }

    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(11);
        final Day day = new Day11(input);
        assertEquals(2238, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(11);
        final Day day = new Day11(input);
        assertEquals(2013, day.solvePart2());
    }

}
