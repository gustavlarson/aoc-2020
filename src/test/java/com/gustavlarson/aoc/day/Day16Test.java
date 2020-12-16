package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day16Test {

    public static final String INPUT1 = """
            class: 1-3 or 5-7
            row: 6-11 or 33-44
            seat: 13-40 or 45-50
                            
            your ticket:
            7,1,14
                            
            nearby tickets:
            7,3,47
            40,4,50
            55,2,20
            38,6,12""";

    public static final String INPUT3 = """
            departure class: 0-1 or 4-19
            row: 0-5 or 8-19
            departure seat: 0-13 or 16-19
                        
            your ticket:
            11,12,13
                        
            nearby tickets:
            3,9,18
            15,1,5
            5,14,9
            """;

    @Test
    public void testPart101() {
        final List<String> input = getAsList(INPUT1);
        final Day day = new Day16(input);
        assertEquals(71, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = getAsList(INPUT3);

        final Day day = new Day16(input);
        assertEquals(12 * 13, day.solvePart2());
    }

    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(16);
        final Day day = new Day16(input);
        assertEquals(20231, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(16);
        final Day day = new Day16(input);
        assertEquals(1940065747861L, day.solvePart2());
    }

}
