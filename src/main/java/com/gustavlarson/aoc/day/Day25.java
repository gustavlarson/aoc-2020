package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;

public class Day25 implements Day {

    private final List<String> input;

    public Day25(final List<String> input) {
        this.input = input;
    }

    @Override
    public long solvePart1() {
        long cardPublicKey = Long.parseLong(input.get(0));
        long doorPublicKey = Long.parseLong(input.get(1));

        long cardLoopSize = findLoopSize(cardPublicKey, 7);


        return transform(doorPublicKey, cardLoopSize);
    }

    private static long transform(long subjectNumber, long loopSize) {
        long value = 1;
        for (long i = 0; i < loopSize; i++) {
            value = transformStep(value, subjectNumber);
        }
        return value;
    }

    private static long findLoopSize(long publicKey, long subjectNumber) {
        long loopSize = 0;
        long value = 1;
        while (value != publicKey) {
            loopSize++;
            value = transformStep(value, subjectNumber);
        }
        return loopSize;
    }

    private static long transformStep(long value, long subjectNumber) {
        value *= subjectNumber;
        return value % 20201227;
    }

    @Override
    public long solvePart2() {
        return 0;
    }
}
