package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
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
    public void testPart201() {
        final List<String> input = getAsList("""
                mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
                mem[8] = 11
                mem[7] = 101
                mem[8] = 0""");
        final Day day = new Day14(input);
        assertEquals(0, day.solvePart2());
    }

}
