# Advent of Code - 2019

Solutions to [Advent of Code](https://adventofcode.com/2019) challenges for the year 2019, written in [JavaScript](https://developer.mozilla.org/en-US/docs/Web/javascript).

## Running the Tests

I structure each part of each day's challenge into two parts: a solution file and a test file.  The test file tests the given constraints and also returns the calculated output for the input given by the challenge.

Each day also contains one or more input files containing the puzzle's offical input.

### To run all tests

1. Install local NPM packages (first time only):
	* `npm install`
1. Run all tests:
	* `npm run test`

### To run a single day's tests

1. Install Mocha and ESM globally:
	* `npm install mocha esm -g`
2. Navigate to the day's folder and run the test:
	* `mocha day01a.test.js -r esm`

ESM allows testing ES6 modules with Mocha

## License

This repository is licensed under the MIT license (http://opensource.org/licenses/MIT)

## GitHub Repo

https://github.com/christophertcampbell/advent-of-code