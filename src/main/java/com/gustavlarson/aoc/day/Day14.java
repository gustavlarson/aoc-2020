package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static final Pattern pattern = Pattern.compile("mem\\[(\\d+)\\] = (\\d+)");

    public Day14(final List<String> input) {
        this.input = input;
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
                mask = line.split(" ")[2];
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
                case "0" -> value &= ~(1L << pos);
                case "1" -> value |= 1L << pos;
            }
        }
        return value & 0xFFFFFFFFFL;
    }

    @Override
    public long solvePart2() {
        Map<Long, Long> memory = new HashMap<>();
        String mask = "";

        for (var line : input) {
            if (line.contains("mask")) {
                mask = line.split(" ")[2];
            } else {
                Op op = getOp(line);
                String maskedAddress = getMaskedAddress(mask, op.address);
                List<String> addresses = new ArrayList<>();
                generateAddresses(maskedAddress, addresses);
                for (var address : addresses) {
                    memory.put(Long.parseLong(address, 2), op.value);
                }
            }
        }

        return memory.values().stream().mapToLong(Long::longValue).sum();
    }

    private static String getMaskedAddress(String mask, Long address) {

        char[] addressChars = Long.toBinaryString(address).toCharArray();
        char[] maskChars = mask.toCharArray();
        for (int i = 0; i < maskChars.length; i++) {
            char c = maskChars[maskChars.length - i - 1];
            if (c == '0') {
                maskChars[maskChars.length - i - 1] = (addressChars.length - i - 1 >= 0) ? addressChars[addressChars.length - i - 1] : '0';
            }
        }

        return String.valueOf(maskChars);
    }

    private static void generateAddresses(String mask, List<String> addresses) {
        if (!mask.contains("X")) {
            addresses.add(mask);
            return;
        }

        generateAddresses(mask.replaceFirst("X", "1"), addresses);
        generateAddresses(mask.replaceFirst("X", "0"), addresses);
    }
}
