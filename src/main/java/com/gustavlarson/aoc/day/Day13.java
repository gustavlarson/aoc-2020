package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;
import java.util.stream.Collectors;

public class Day13 implements Day {

    private final List<String> busNumbers;
    private final int timeToLeave;

    public Day13(final List<String> input) {
        this.timeToLeave = Integer.parseInt(input.get(0));
        this.busNumbers = List.of(input.get(1).split(","));
    }

    @Override
    public long solvePart1() {
        List<Integer> buses = busNumbers.stream().filter(line -> !line.equals("x")).map(Integer::parseInt).collect(Collectors.toList());
        var minWait = Integer.MAX_VALUE;
        var minBus = 0;
        for (Integer bus : buses) {
            var waitTime = bus - (timeToLeave % bus);
            if (waitTime < minWait) {
                minWait = waitTime;
                minBus = bus;
            }
        }
        return minBus * minWait;
    }

    @Override
    public long solvePart2() {
        long timestamp = 0;
        long stride = 1;
        for (var offset = 0; offset < busNumbers.size(); offset++) {
            try {
                final int bus = Integer.parseInt(busNumbers.get(offset));

                while ((timestamp + offset) % bus != 0) {
                    timestamp += stride;
                }

                stride *= bus;
            } catch (NumberFormatException ignored) {

            }

        }
        return timestamp;
    }
}
