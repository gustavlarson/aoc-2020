package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day14Test {
    @Test
    public void testPart101() {
        final List<String> input = getAsList("""
                mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
                mem[8] = 11
                mem[7] = 101
                mem[8] = 0""");
        final Day day = new Day14(input);
        assertEquals(165, day.solvePart1());
    }

    @Test
    public void testPart102() {
        final List<String> input = getAsList("""
                mask = X111000X0101100001000000100011X0000X
                mem[4812] = 133322396
                mem[39136] = 1924962""");
        final Day day = new Day14(input);
        assertEquals(30157310144L + 30157310176L, day.solvePart1());
    }

    @Test
    public void testPart103() {
        final List<String> input = getAsList("""
                mask = X111000X0101100001000000100011X0000X
                mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
                mem[8] = 11
                mem[7] = 101
                mem[8] = 0""");
        final Day day = new Day14(input);
        assertEquals(165, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = getAsList("""
                mask = 000000000000000000000000000000X1001X
                mem[42] = 100
                mask = 00000000000000000000000000000000X0XX
                mem[26] = 1""");
        final Day day = new Day14(input);
        assertEquals(208, day.solvePart2());
    }

    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(14);
        final Day day = new Day14(input);
        assertEquals(8570568288597L, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(14);
        final Day day = new Day14(input);
        assertEquals(3289441921203L, day.solvePart2());
    }
}
