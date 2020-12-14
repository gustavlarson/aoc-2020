package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day14 implements Day {

    class Op {
        private final Long address;
        private final Long value;

        Op(Long address, Long value) {
            this.address = address;
            this.value = value;
        }
    }

    private final List<String> input;
    private final List<Op> writes;
    private final String mask;
    private static final Pattern pattern = Pattern.compile("mem\\[(\\d+)\\] = (\\d+)");

    public Day14(final List<String> input) {
        this.input = input;
        this.mask = input.get(0).split(" ")[2];
        this.writes = input.stream().skip(1).map(line -> {
            Matcher m = pattern.matcher(line);
            if (!m.find()) throw new IllegalArgumentException();
            return new Op(Long.parseLong(m.group(1)), Long.parseLong(m.group(2)));
        }).collect(Collectors.toList());
    }

    private Op getOp(String line) {
        Matcher m = pattern.matcher(line);
        if (!m.find()) throw new IllegalArgumentException();
        return new Op(Long.parseLong(m.group(1)), Long.parseLong(m.group(2)));
    }

    @Override
    public long solvePart1() {
        Map<Long, Long> memory = new HashMap<>();
        String mask = "";
        for (var line : input) {
            if (line.contains("mask")) {
                mask = input.get(0).split(" ")[2];
            } else {
                Op op = getOp(line);
                memory.put(op.address, mask(mask, op.value));
            }
        }
        return memory.values().stream().mapToLong(Long::longValue).sum();
    }

    private static Long mask(String mask, Long value) {
        for (var i = 0; i < mask.length(); i++) {
            var pos = mask.length() - i - 1;
            switch (mask.substring(i, i + 1)) {
                case "0" -> value &= ~(1 << pos);
                case "1" -> value |= 1 << pos;
            }
        }
        return value;
    }

    @Override
    public long solvePart2() {
        return 0;
    }
}
