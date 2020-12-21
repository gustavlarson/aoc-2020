package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day21 implements Day {

    Pattern pattern = Pattern.compile("(?<ingredients>[\\w ]+) \\(contains (?<allergens>.*)\\)");

    class Food {
        Set<String> ingredients;
        Set<String> allergens;

        Food(String input) {
            Matcher m = pattern.matcher(input);
            if (!m.find()) throw new IllegalArgumentException();
            ingredients = Arrays.stream(m.group("ingredients").split(" ")).collect(Collectors.toSet());
            allergens = Arrays.stream(m.group("allergens").split(",")).map(String::trim).collect(Collectors.toSet());
        }
    }

    private final List<Food> foods;

    public Day21(final List<String> input) {
        this.foods = input.stream().map(Food::new).collect(Collectors.toList());
    }

    private Set<String> getHypoallergenic() {
        Set<String> ingredients = new HashSet<>();
        foods.stream().forEach(food -> ingredients.addAll(food.ingredients));
        Set<String> allergens = new HashSet<>();
        foods.stream().forEach(food -> allergens.addAll(food.allergens));

        Set<String> hypoallergenic = new HashSet<>();

        for (var ingredient : ingredients) {
            boolean potentialAllergen = false;
            for (var allergen : allergens) {
                if (foods.stream()
                        .filter(food -> food.allergens.contains(allergen))
                        .allMatch(food -> food.ingredients.contains(ingredient))) {
                    potentialAllergen = true;
                    break;
                }

            }
            if (!potentialAllergen) {
                hypoallergenic.add(ingredient);
            }
        }

        return hypoallergenic;
    }

    @Override
    public long solvePart1() {
        Set<String> hypoallergenic = getHypoallergenic();
        return hypoallergenic.stream().mapToLong(
                ingredient -> foods.stream().mapToLong(food -> food.ingredients.contains(ingredient) ? 1L : 0L).sum()
        ).sum();
    }

    @Override
    public long solvePart2() {
        Set<String> hypoallergenic = getHypoallergenic();
        Map<String, Set<String>> candidaters = new HashMap<>();

        for (var food : foods) {
            for (var ingredient : food.ingredients) {
                for (var allergen : food.allergens) {
                    if (!hypoallergenic.contains(ingredient)) {
                        if (!candidaters.containsKey(allergen)) {
                            candidaters.put(allergen, new HashSet<>());
                        }
                        candidaters.get(allergen).add(ingredient);
                    }
                }
            }
        }

        System.out.println(candidaters);
        Map<String, String> solution = new TreeMap<>();

        for (var allergen : candidaters.keySet()) {
            candidaters.get(allergen).removeIf(ingredient -> !foods.stream()
                    .filter(food -> food.allergens.contains(allergen))
                    .allMatch(food -> food.ingredients.contains(ingredient)));
        }

        while (candidaters.size() > 0) {
            System.out.println(candidaters);
            String allergen = candidaters.keySet().stream().filter(a -> candidaters.get(a).size() == 1).findFirst().orElseThrow();
            String ingredient = (String) candidaters.get(allergen).toArray()[0];
            solution.put(allergen, ingredient);

            candidaters.remove(allergen);
            for (var c : candidaters.keySet()) {
                candidaters.get(c).remove(ingredient);
            }
        }

        System.out.println(solution);

        return 0;
    }
}

//mfp,mgvfmvp,nhdjth,hcdchl,dvkbjh,dcvrf,bcjz,mhnrqp