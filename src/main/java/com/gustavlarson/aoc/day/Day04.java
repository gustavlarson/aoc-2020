package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day04 implements Day {
    private static final Pattern fieldPattern = Pattern.compile("(?<field>[a-z]{3}):(?<value>[#a-z0-9]+)(\\s|$)");
    private static final Pattern heightPattern = Pattern.compile("(\\d+)(cm|in)");
    private static final Pattern colorPattern = Pattern.compile("^#[0-9a-f]{6}$");
    private static final Pattern passportIdPattern = Pattern.compile("^[0-9]{9}$");

    static class Passport {
        String byr, iyr, eyr, hgt, hcl, ecl, pid;

        Passport(final String line) {
            final Matcher matcher = fieldPattern.matcher(line);
            while (matcher.find()) {
                final String field = matcher.group("field");
                final String value = matcher.group("value");

                switch (field) {
                    case "byr" -> byr = value;
                    case "iyr" -> iyr = value;
                    case "eyr" -> eyr = value;
                    case "hgt" -> hgt = value;
                    case "hcl" -> hcl = value;
                    case "ecl" -> ecl = value;
                    case "pid" -> pid = value;
                }
            }
        }

        boolean hasAllRequiredFields() {
            return (byr != null) &&
                    (iyr != null) &&
                    (eyr != null) &&
                    (hgt != null) &&
                    (hcl != null) &&
                    (ecl != null) &&
                    (pid != null);
        }

        boolean hasOnlyValidFields() {
            return hasAllRequiredFields() &&
                    validateIntBetween(byr, 1920, 2010) &&
                    validateIntBetween(iyr, 2010, 2020) &&
                    validateIntBetween(eyr, 2020, 2030) &&
                    validateHeight() &&
                    validateHairColor() &&
                    validateEyeColor() &&
                    validatePassportId();
        }

        private static boolean validateIntBetween(final String input, final int min, final int max) {
            try {
                final int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return true;
                }
            } catch (final NumberFormatException ignored) {
            }
            return false;
        }

        private boolean validateHeight() {
            final Matcher matcher = heightPattern.matcher(hgt);
            if (!matcher.find()) {
                return false;
            }
            final int height = Integer.parseInt(matcher.group(1));
            final String unit = matcher.group(2);

            if (unit.equals("cm") && height >= 150 && height <= 193) return true;
            if (unit.equals("in") && height >= 50 && height <= 76) return true;

            return false;
        }

        private boolean validateHairColor() {
            final Matcher matcher = colorPattern.matcher(hcl);
            return matcher.find();
        }

        private boolean validateEyeColor() {
            return switch (ecl) {
                case "amb", "blu", "brn", "gry", "grn", "hzl", "oth" -> true;
                default -> false;
            };
        }

        private boolean validatePassportId() {
            final Matcher matcher = passportIdPattern.matcher(pid);
            return matcher.find();
        }
    }

    static List<Passport> getPassports(final List<String> input) {
        return Arrays
                .stream(String.join(" \n", input).split("\n \n"))
                .parallel()
                .map(Passport::new)
                .collect(Collectors.toList());
    }

    @Override
    public String solvePart1(final List<String> input) {
        final List<Passport> passports = getPassports(input);

        return "" + passports.parallelStream().filter(Passport::hasAllRequiredFields).count();
    }

    @Override
    public String solvePart2(final List<String> input) {
        final List<Passport> passports = getPassports(input);

        return "" + passports.parallelStream().filter(Passport::hasOnlyValidFields).count();
    }
}
