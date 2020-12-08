package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day08Test {
    @Test
    public void testPart101() {
        final List<String> input = getAsList("""
                nop +0
                acc +1
                jmp +4
                acc +3
                jmp -3
                acc -99
                acc +1
                jmp -4
                acc +6""");
        final Day day = new Day08(input);
        assertEquals(5, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = getAsList("""
                1
                2
                """);
        final Day day = new Day08(input);
        assertEquals(0, day.solvePart2());
    }

}
