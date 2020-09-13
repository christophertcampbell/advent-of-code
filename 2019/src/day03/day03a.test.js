var assert = require("assert");
import { OrthagonalPath } from "./day03a";
import { input1, input2 } from "./day03a.input";

describe("Day 3 Challenge - Part 1", function() {
  describe("Challenge parameters", function() {
    it("Test path 1 - Closest intersection should be at distance = 6", function() {
      let path1 = new OrthagonalPath(0, 0, ["R8", "U5", "L5", "D3"]);
      let path2 = new OrthagonalPath(0, 0, ["U7", "R6", "D4", "L4"]);
      assert.deepStrictEqual(path1.getDistanceToClosestIntersection(path2, 0, 0), 6);
    });
    it("Test path 2 - Closest intersection should be at distance = 159", function() {
      let path1 = new OrthagonalPath(0, 0, ["R75","D30","R83","U83","L12","D49","R71","U7","L72"]);
      let path2 = new OrthagonalPath(0, 0, ["U62","R66","U55","R34","D71","R55","D58","R83"]);
      assert.deepStrictEqual(path1.getDistanceToClosestIntersection(path2, 0, 0), 159);
    });
    it("Test path 3 - Closest intersection should be at distance = 135", function() {
      let path1 = new OrthagonalPath(0, 0, ["R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51"]);
      let path2 = new OrthagonalPath(0, 0, ["U98","R91","D20","R16","D67","R40","U7","R15","U6","R7"]);
      assert.deepStrictEqual(path1.getDistanceToClosestIntersection(path2, 0, 0), 135);
    });
    it("Overlapping horizontal path segments should not count as intersections", function() {
      let path1 = new OrthagonalPath(0, 0, ["R98"]);
      let path2 = new OrthagonalPath(0, 0, ["R7"]);
      assert.deepStrictEqual(path1.getDistanceToClosestIntersection(path2, 0, 0), -1);
    });
    it("Overlapping vertical path segments should not count as intersections", function() {
      let path1 = new OrthagonalPath(0, 0, ["U98"]);
      let path2 = new OrthagonalPath(0, 0, ["U7"]);
      assert.deepStrictEqual(path1.getDistanceToClosestIntersection(path2, 0, 0), -1);
    });
    it("Non-intersecting paths should have no intersections", function() {
      let path1 = new OrthagonalPath(0, 0, ["R98"]);
      let path2 = new OrthagonalPath(0, 1, ["U7"]);
      assert.deepStrictEqual(path1.getDistanceToClosestIntersection(path2, 0, 0), -1);
    });
  })
});

describe("Output", function() {
  let path1 = new OrthagonalPath(0, 0, input1);
  let path2 = new OrthagonalPath(0, 0, input2);
  let distanceToClosestIntersection = path1.getDistanceToClosestIntersection(path2, 0, 0);
  it(`output is: ${distanceToClosestIntersection}`, function() {
    assert.strictEqual(true, true);
  })
});