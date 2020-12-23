package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day23 implements Day {

    private final List<Integer> input;

    public Day23(final List<String> input) {
        this.input = input.get(0).chars().boxed().map(c -> c - '0').collect(Collectors.toList());
    }

    @Override
    public long solvePart1() {
        List<Integer> state = input;
        System.out.println("Initial: " + state);

        for (int i = 1; i <= 100; i++) {
            state = move(state);
            System.out.println("Move " + i + " " + state);
        }

        return getLabels(state);
    }

    private static int getLabels(List<Integer> state) {
        StringBuilder result = new StringBuilder();
        var index = state.indexOf(1);
        while (state.size() - 1 > index) {
            result.append(state.remove(index + 1));
        }
        for (var i = 0; i < index; i++) {
            result.append(state.remove(0));
        }
        return Integer.parseInt(result.toString());
    }

    private static List<Integer> move(List<Integer> state) {
        List<Integer> pickup = new ArrayList<>();
        pickup.add(state.remove(1));
        pickup.add(state.remove(1));
        pickup.add(state.remove(1));

        int destination = state.get(0) - 1;
        //System.out.println("Destination: " + destination);
        if (destination == 0) destination = 9;
        while (pickup.contains(destination)) {
            destination--;
            if (destination == 0) destination = 9;
        }

        List<Integer> newState = new ArrayList<>();
        // Add all to the left of destination and the destination
        while (state.get(0) != destination) {
            newState.add(state.remove(0));
        }
        newState.add(state.remove(0));
        // Insert the cups that were picked up
        newState.addAll(pickup);
        // Insert the rest of the cups at the back
        newState.addAll(state);

        // Shift by one
        newState.add(newState.remove(0));

        return newState;
    }

    private static final int SIZE = 9;
    //private static final int SIZE = 1_000_000;
    private static final int ITERATIONS = 100;
    //private static final int ITERATIONS = 10_000_00;

    @Override
    public long solvePart2() {
        List<Integer> state = input;


        int[] cups = new int[SIZE + 2];
        //cups[0] = state.get(0);
        cups[0] = 1;
        for (var i = 0; i < input.size(); i++) {
            var k = state.get(i);
            var v = (i < input.size() - 1) ? state.get(i + 1) : 3;//input.size() + 1;
//            System.out.println("k " + k + " v " + v);
            cups[k] = v;
        }
        for (var i = input.size() + 1; i <= SIZE; i++) {
            var k = i;
            var v = i + 1;
            cups[i] = i + 1;
//            System.out.println("k " + k + " v " + v);

        }
        cups[cups.length - 1] = 3; //TODO
//        var b = cups[cups.length - 2];
//        var a = cups[cups.length - 1];
//        Arrays.stream(cups).forEach(System.out::print);

        System.out.println();

        var currentCup = 3; //TODO

        for (var i = 1; i <= ITERATIONS; i++) {
            //if (i % 10000 == 0) System.out.println("Move " + i);

            var pickup1 = cups[currentCup];
            var pickup2 = cups[pickup1];
            var pickup3 = cups[pickup2];
            System.out.println("Current " + currentCup);
            System.out.println("Pickup " + pickup1 + " " + pickup2 + " " + pickup3);
            var destination = mod((currentCup - 2), SIZE) + 1;
            while (destination == pickup1 || destination == pickup2 || destination == pickup3) {
                destination = mod((destination - 2), SIZE) + 1;
            }
            System.out.println("Destination " + destination);
            var gap_r = cups[pickup3];
            var r_join_r = cups[destination];
            cups[currentCup] = gap_r;
            cups[destination] = pickup1;
            cups[pickup3] = r_join_r;
            currentCup = cups[currentCup];
        }

        int c1 = cups[1];
        System.out.println(c1);
        int c2 = cups[c1];
        System.out.println(c2);

        return (long) c1 * (long) c2;
    }

    private static int mod(int a, int b) {
        var r = a % b;
        if (r < 0) r += b;
        return r;
    }

    private static List<Integer> move2(List<Integer> state) {
        List<Integer> pickup = new ArrayList<>();
        pickup.add(state.remove(1));
        pickup.add(state.remove(1));
        pickup.add(state.remove(1));

        int destination = state.get(0) - 1;
        //System.out.println("Destination: " + destination);
        if (destination == 0) destination = 1_000_000;
        while (pickup.contains(destination)) {
            destination--;
            if (destination == 0) destination = 1_000_000;
        }

        List<Integer> newState = new ArrayList<>();
        // Add all to the left of destination and the destination
        while (state.get(0) != destination) {
            newState.add(state.remove(0));
        }
        newState.add(state.remove(0));
        // Insert the cups that were picked up
        newState.addAll(pickup);
        // Insert the rest of the cups at the back
        newState.addAll(state);

        // Shift by one
        newState.add(newState.remove(0));

        return newState;
    }
}
