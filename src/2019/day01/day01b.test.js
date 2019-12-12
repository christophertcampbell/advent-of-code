var assert = require('assert');
import { input } from './day01.input';
import * as day01b from './day01b';

describe('Day 1 Challenge - Part 2', function() {
  describe('Challenge parameters', function() {
    it('mass of 14 should require 2 fuel units', function() {
      assert.equal(day01b.calculateFuel(14), 2);
    });
    it('mass of 1969 should require 966 fuel units', function() {
      assert.equal(day01b.calculateFuel(1969), 966);
    });
    it('mass of 100756 should require 50346 fuel units', function() {
      assert.equal(day01b.calculateFuel(100756), 50346);
    });
  });

  describe('Handling of unexpected input', function() {
    it('mass of "14" should require 2 fuel units', function() {
      assert.equal(day01b.calculateFuel("14"), 2);
    });
    it('mass of "xyzzy" should return -1', function() {
      assert.equal(day01b.calculateFuel("xyzzy"), -1);
    });
  });

  describe('Output', function() {
    it(`output is: ${day01b.run(input)}`, function() {
      assert.equal(true, true);
    });
  })
});
