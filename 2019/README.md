# Advent of Code - 2019

Solutions to [Advent of Code 2019](https://adventofcode.com/2019), written in [JavaScript](https://developer.mozilla.org/en-US/docs/Web/javascript).

## Running the Tests

I structure each part of each day's challenge into two parts: a solution file and a test file.  The test file tests the given constraints and also returns the calculated output for the input given by the challenge.

Each day also contains one or more input files containing the puzzle's offical input.

### To install NPM packages (first time only):

`npm install`

### To run all tests

`npm run test`

### To run a single day's tests

`npm run test:day01`

* Note: `day01`, `day02`, `day03` etc must exactly match the day's sub-directory name
* Each day contains two challenges, each with its own test. This command will run both of a day's tests.

### Manual method to directly run a single test

1. Install Mocha and ESM globally:
	* `npm install mocha esm -g`
2. Navigate to the day's folder and run the test:
	* `mocha day01a.test.js -r esm`

* ESM allows testing ES6 modules with Mocha

## License

This repository is licensed under the MIT license (http://opensource.org/licenses/MIT)

## GitHub Repo

https://github.com/christophertcampbell/advent-of-code