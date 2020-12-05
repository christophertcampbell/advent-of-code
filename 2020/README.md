# Advent of Code - 2020

Solutions to [Advent of Code 2020](https://adventofcode.com/2020) challenges, written in [Java](https://www.oracle.com/java/).

## Organization

I organize most solutions here into multiple files with a package name corresponding to the day (eg: day02). I organize helper files into a common location which all days can import from.

Simpler solutions (eg: day01) may be contained in a single Java file for simplicity.

## Compiling and running the solutions:

### Solution not organized as a package

In the terminal, inside the day's directory:

    > javac Day01.java
    > java Day01

### Solution organized as a package

In the terminal, inside the day's directory:

    > javac -cp .. -d .. Day02.java
    > java -cp .. day02.Day02

Explanation:

- `-cp ..` is short for `-classpath ..`, which tells the Java compiler to start one directory up when looking for package references
- `-d ..` (directory) tells the Java compiler to start one directory up when placing the compiled .class files.
  - Since the compiler places the .class files in a directory structure corresponding to the package name, by using `-d ..` the .class files will end up in the same directory as our .java source files

## License

This repository is licensed under the MIT license (http://opensource.org/licenses/MIT)

## GitHub Repo

https://github.com/christophertcampbell/advent-of-code