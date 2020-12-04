package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private final Pattern byrp = Pattern.compile("byr:(\\d+)");
    private final Pattern iyrp = Pattern.compile("iyr:(\\d+)");
    private final Pattern eyrp = Pattern.compile("eyr:(\\d+)");
    private final Pattern hgtp = Pattern.compile("hgt:(\\d+)(cm|in)");
    private final Pattern hclp = Pattern.compile("hcl:#[0-9a-f]{6}\\s");
    private final Pattern eclp = Pattern.compile("ecl:(amb|blu|brn|gry|grn|hzl|oth)");
    private final Pattern pidp = Pattern.compile("pid:[0-9]{9}\\s");

    @Override
    public String solvePart2(final List<String> input) {
        final List<String> passports = new ArrayList<>();
        var tempString = "";
        for (final var line : input) {
            if (line.length() > 0) {
                tempString = tempString.concat(" " + line);
            } else {
                passports.add(tempString + " ");
                tempString = "";
            }
        }
        passports.add(tempString + " ");

        var counter = 0;
        for (final var passport : passports) {
            final Matcher byrm = byrp.matcher(passport);
            if (!byrm.find()) {
                continue;
            }
            int year = Integer.parseInt(byrm.group(1));
            if (year < 1920 || year > 2002) {
                continue;
            }
            final Matcher iyrm = iyrp.matcher(passport);
            if (!iyrm.find()) {
                continue;
            }
            year = Integer.parseInt(iyrm.group(1));
            if (year < 2010 || year > 2020) {
                continue;
            }
            final Matcher eyrm = eyrp.matcher(passport);
            if (!eyrm.find()) {
                continue;
            }
            year = Integer.parseInt(eyrm.group(1));
            if (year < 2020 || year > 2030) {
                continue;
            }
            final Matcher hgtm = hgtp.matcher(passport);
            if (!hgtm.find()) {
                continue;
            }
            final int hgt = Integer.parseInt(hgtm.group(1));
            if (hgtm.group(2).equals("cm") && (hgt < 150 || hgt > 193)) {
                continue;
            }
            if (hgtm.group(2).equals("in") && (hgt < 59 || hgt > 76)) {
                continue;
            }
            final Matcher hclm = hclp.matcher(passport);
            if (!hclm.find()) {
                continue;
            }
            final Matcher eclm = eclp.matcher(passport);
            if (!eclm.find()) {
                continue;
            }
            final Matcher pidm = pidp.matcher(passport);
            if (!pidm.find()) {
                continue;
            }

            counter++;
        }

        return "" + counter;
    }
}
