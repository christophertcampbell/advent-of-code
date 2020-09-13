var assert = require('assert');
import { input } from './day01.input';
import { FuelCalculator } from './day01a';

let fuelCalculator = new FuelCalculator();

describe('Day 1 Challenge - Part 1', function() {
  describe('Challenge parameters', function() {
    it('mass of 12 should require 2 fuel units', function() {
      assert.equal(fuelCalculator.calculate(12), 2);
    });
    it('mass of 14 should require 2 fuel units', function() {
      assert.equal(fuelCalculator.calculate(14), 2);
    });
    it('mass of 1969 should require 654 fuel units', function() {
      assert.equal(fuelCalculator.calculate(1969), 654);
    });
    it('mass of 100756 should require 33583 fuel units', function() {
      assert.equal(fuelCalculator.calculate(100756), 33583);
    });
  });

  describe('Handling of unexpected input', function() {
    it('mass of "12" should require 2 fuel units', function() {
      assert.equal(fuelCalculator.calculate("12"), 2);
    });
    it('mass of "xyzzy" should return -1', function() {
      assert.equal(fuelCalculator.calculate("xyzzy"), -1);
    });
  });

  describe('Output', function() {
    it(`output is: ${fuelCalculator.calculate(input)}`, function() {
      assert.equal(true, true);
    });
  })
});
