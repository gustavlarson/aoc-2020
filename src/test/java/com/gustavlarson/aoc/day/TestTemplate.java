package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTemplate {
    @Test
    public void testPart101() {
        final Day day = new Day01();
        final List<String> input = List.of("line1", "line2");
        final String result = day.solvePart1(input);
        assertEquals("", result);
    }

}
