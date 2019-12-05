var assert = require('assert');
import * as day01a from './day01a';

describe('Day 1 Challenge', function() {
	describe('Challenge parameters', function() {
		it('mass of 12 should require 2 fuel units', function() {
			assert.equal(day01a.calculateFuel(12), 2);
		});
		it('mass of 14 should require 2 fuel units', function() {
			assert.equal(day01a.calculateFuel(14), 2);
		});
		it('mass of 1969 should require 654 fuel units', function() {
			assert.equal(day01a.calculateFuel(1969), 654);
		});
		it('mass of 100756 should require 33583 fuel units', function() {
			assert.equal(day01a.calculateFuel(100756), 33583);
		});
	});
	
	describe('Handling of unexpected input', function() {
		it('mass of "12" should require 2 fuel units', function() {
			assert.equal(day01a.calculateFuel("12"), 2);
		});
		it('mass of "xyzzy" should return -1', function() {
			assert.equal(day01a.calculateFuel("xyzzy"), -1);
		});
	});
	
	describe('Output', function() {
		it(`output is: ${day01a.run()}`, function() {
			assert.equal(true, true);
		});
	})
});