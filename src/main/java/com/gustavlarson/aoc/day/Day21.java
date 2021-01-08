package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day21 implements Day {

    private static final Pattern pattern = Pattern.compile("(?<ingredients>[\\w ]+) \\(contains (?<allergens>.*)\\)");

    static class Food {
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
        Set<String> allergens = new HashSet<>();
        foods.forEach(food -> {
            ingredients.addAll(food.ingredients);
            allergens.addAll(food.allergens);
        });

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

            if (!potentialAllergen) hypoallergenic.add(ingredient);
        }

        return hypoallergenic;
    }

    @Override
    public long solvePart1() {
        Set<String> hypoallergenic = getHypoallergenic();
        return hypoallergenic.stream().mapToLong(
                ingredient -> foods.stream().filter(food -> food.ingredients.contains(ingredient)).count()
        ).sum();
    }

    @Override
    public long solvePart2() {
        Set<String> hypoallergenic = getHypoallergenic();
        Map<String, TreeSet<String>> candidates = new HashMap<>();

        for (var food : foods) {
            for (var ingredient : food.ingredients) {
                if (!hypoallergenic.contains(ingredient)) {
                    for (var allergen : food.allergens) {
                        if (!candidates.containsKey(allergen)) {
                            candidates.put(allergen, new TreeSet<>());
                        }
                        candidates.get(allergen).add(ingredient);
                    }
                }
            }
        }


        for (var allergen : candidates.keySet()) {
            candidates.get(allergen).removeIf(ingredient -> !foods.stream()
                    .filter(food -> food.allergens.contains(allergen))
                    .allMatch(food -> food.ingredients.contains(ingredient)));
        }

        Map<String, String> solution = new TreeMap<>();

        while (candidates.size() > 0) {
            String allergen = candidates.keySet().stream().filter(a -> candidates.get(a).size() == 1).findFirst().orElseThrow();
            String ingredient = candidates.get(allergen).first();
            solution.put(allergen, ingredient);

            candidates.remove(allergen);
            for (var c : candidates.keySet()) {
                candidates.get(c).remove(ingredient);
            }
        }

        String result = solution.keySet().stream().map(solution::get).reduce((a, b) -> a + "," + b).orElseThrow();
        
        System.out.println(result);
        return 0;
    }
}
