# Advent of Code - 2020

Solutions to [Advent of Code 2020](https://adventofcode.com/2020) challenges, written in [Java](https://www.oracle.com/java/).

## Organization

I have organized the solutions here into multiple files with a package name corresponding to the day (eg: day01). I have organized helper files into a common location which all days can import from.

Simpler solutions (eg: day01) may be contained in a single Java file for simplicity.

## Compiling and running the solutions:

### From within the `/src` directory:

In the terminal, inside the `src/` directory:

    > javac day01/Day01.java
    > java day01.Day01

### From within the specific day's directory:

In the terminal, inside the day's directory (eg: `src/day01`):

    > javac -cp .. -d .. Day01.java
    > java -cp .. day01.Day01

Explanation:

- `-cp ..` is short for `-classpath ..`, which tells the Java compiler to start one directory up when looking for package references
- `-d ..` (directory) tells the Java compiler to start one directory up when placing the compiled .class files.
  - Since the compiler places the .class files in a directory structure corresponding to the package name, by using `-d ..` the .class files will end up in the same directory as our .java source files

## License

This repository is licensed under the MIT license (http://opensource.org/licenses/MIT)

## GitHub Repo

https://github.com/christophertcampbell/advent-of-code