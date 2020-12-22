package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day22Test {

    public static final List<String> TEST_INPUT = getAsList("""
            Player 1:
            9
            2
            6
            3
            1
                            
            Player 2:
            5
            8
            4
            7
            10                
            """);

    private static Day getDay(List<String> input) {
        return new Day22(input);
    }

    @Test
    public void testPart101() {
        final List<String> input = getAsList("""
                Player 1:
                9
                                
                Player 2:
                5             
                """);
        final Day day = getDay(input);
        assertEquals(5 + 9 * 2, day.solvePart1());
    }

    @Test
    public void testPart102() {
        final List<String> input = TEST_INPUT;
        final Day day = getDay(input);
        assertEquals(306, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = TEST_INPUT;
        final Day day = getDay(input);
        assertEquals(291, day.solvePart2());
    }

    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(22);
        final Day day = getDay(input);
        assertEquals(32472, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(22);
        final Day day = getDay(input);
        assertEquals(36463, day.solvePart2());
    }
}
