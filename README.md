# Advent of code 2020

![GitHub Workflow Status](https://img.shields.io/github/workflow/status/gustavlarson/aoc-2020/Build%20and%20test)

Solutions for Advent of Code 2020 https://adventofcode.com/2020

Run using `./gradlew run --args <day>`

To execute tests run `./gradlew test` or `./gradlew --continuous test` to run in continuous mode.

### Auto download input
Create a file named `local.properties` with the content
```properties
session=<SESSION>
```
where `<SESSION>` is the value of the session cookie from AoC.

Download the days input using `./gradlew getInput` to get the current days input or `AOC_DAY=<day> ./gradlew getInput`
