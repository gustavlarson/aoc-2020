package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.ArrayList;
import java.util.List;

public class Day04 implements Day {

    @Override
    public String solvePart1(final List<String> input) {
        final List<String> passports = new ArrayList<>();
        var tempString = "";
        for (final var line : input) {
            if (line.length() > 0) {
                tempString = tempString.concat(" " + line);
            } else {
                passports.add(tempString);
                tempString = "";
            }
        }
        passports.add(tempString);

        var counter = 0;
        for (final var passport : passports) {
            if (passport.contains("byr:") &&
                    passport.contains("iyr:") &&
                    passport.contains("eyr:") &&
                    passport.contains("hgt:") &&
                    passport.contains("hcl:") &&
                    passport.contains("ecl:") &&
                    passport.contains("pid:")
            ) {
                counter++;
            }
        }

        return "" + counter;
    }

    @Override
    public String solvePart2(final List<String> input) {
        return null;
    }
}
