package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day19Test {


    private static Day getDay(List<String> input) {
        return new Day19(input);
    }

    @Test
    public void testPart101() {
        final List<String> input = getAsList("""
                0: 4 1 5
                1: 2 3 | 3 2
                2: 4 4 | 5 5
                3: 4 5 | 5 4
                4: "a"
                5: "b"

                ababbb
                bababa
                abbbab
                aaabbb
                aaaabbb""");
        final Day day = getDay(input);
        assertEquals(2, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = getAsList("""
                42: 9 14 | 10 1
                9: 14 27 | 1 26
                10: 23 14 | 28 1
                1: "a"
                11: 42 31
                5: 1 14 | 15 1
                19: 14 1 | 14 14
                12: 24 14 | 19 1
                16: 15 1 | 14 14
                31: 14 17 | 1 13
                6: 14 14 | 1 14
                2: 1 24 | 14 4
                0: 8 11
                13: 14 3 | 1 12
                15: 1 | 14
                17: 14 2 | 1 7
                23: 25 1 | 22 14
                28: 16 1
                4: 1 1
                20: 14 14 | 1 15
                3: 5 14 | 16 1
                27: 1 6 | 14 18
                14: "b"
                21: 14 1 | 1 14
                25: 1 1 | 1 14
                22: 14 14
                8: 42
                26: 14 22 | 1 20
                18: 15 15
                7: 14 5 | 1 21
                24: 14 1
                                
                abbbbbabbbaaaababbaabbbbabababbbabbbbbbabaaaa
                bbabbbbaabaabba
                babbbbaabbbbbabbbbbbaabaaabaaa
                aaabbbbbbaaaabaababaabababbabaaabbababababaaa
                bbbbbbbaaaabbbbaaabbabaaa
                bbbababbbbaaaaaaaabbababaaababaabab
                ababaaaaaabaaab
                ababaaaaabbbaba
                baabbaaaabbaaaababbaababb
                abbbbabbbbaaaababbbbbbaaaababb
                aaaaabbaabaaaaababaa
                aaaabbaaaabbaaa
                aaaabbaabbaaaaaaabbbabbbaaabbaabaaa
                babaaabbbaaabaababbaabababaaab
                aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba""");
        final Day day = getDay(input);
        assertEquals(12, day.solvePart2());
    }

    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(19);
        final Day day = getDay(input);
        assertEquals(198, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(19);
        final Day day = getDay(input);
        assertEquals(372, day.solvePart2());
    }
}
