package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTemplate {
    @Test
    public void testPart101() {
        final List<String> input = List.of("1", "2");
        final Day day = new DayTemplate(input);
        assertEquals(0, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = getAsList("""
                1
                2
                """);
        final Day day = new DayTemplate(input);
        assertEquals(0, day.solvePart2());
    }

}
