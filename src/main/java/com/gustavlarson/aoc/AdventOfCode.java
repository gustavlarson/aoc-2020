package com.gustavlarson.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class AdventOfCode {
    private static final String INPUT_FOLDER = "input";

    public static void main(final String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException();
        }

        final int dayArg = Integer.parseInt(args[0]);

        final Day day = DayFactory.getDay(dayArg);

        final List<String> input = loadInput(dayArg);

        System.out.println("Solution part 1: " + day.solvePart1(input));
        System.out.println("Solution part 2: " + day.solvePart2(input));

    }

    private static List<String> loadInput(final int day) {
        final String fileName = String.format("day%02d.txt", day);
        final Path path = Path.of(INPUT_FOLDER, fileName);
        final List<String> input;

        try (final var r = new BufferedReader(new FileReader(path.toString()))) {
            input = r.lines().collect(Collectors.toList());
        } catch (final IOException e) {
            throw new IllegalArgumentException("File not found", e);
        }

        return input;
    }
}
