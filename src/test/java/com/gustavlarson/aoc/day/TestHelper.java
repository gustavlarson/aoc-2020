package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.AdventOfCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class TestHelper {

    static List<String> getAsList(final String input) {
        return Arrays.asList(input.split("\n"));
    }

    static List<String> getFromFile(final int day) {
        final String fileName = String.format("day%02d.txt", day);
        final Path path = Path.of(AdventOfCode.INPUT_FOLDER, fileName);
        final List<String> input;

        try (final var r = new BufferedReader(new FileReader(path.toString()))) {
            input = r.lines().collect(Collectors.toList());
        } catch (final IOException e) {
            throw new IllegalArgumentException("File not found", e);
        }

        return input;
    }
}
