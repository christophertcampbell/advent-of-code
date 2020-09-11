var assert = require('assert');
import { input } from './day02.input';
import { IntCodeComputer } from './day02a';

let computer = new IntCodeComputer();

describe('Day 2 Challenge - Part 1', function() {
  describe('Challenge parameters', function() {
    it('[1, 0, 0, 0, 99] should process correctly', function() {
      assert.deepEqual(computer.run([1, 0, 0, 0, 99]), [2, 0, 0, 0, 99]);
    });
    it('[2, 3, 0, 3, 99] should process correctly', function() {
      assert.deepEqual(computer.run([2, 3, 0, 3, 99]), [2, 3, 0, 6, 99]);
    });
    it('[2, 4, 4, 5, 99, 0] should process correctly', function() {
      assert.deepEqual(computer.run([2, 4, 4, 5, 99, 0]), [2, 4, 4, 5, 99, 9801]);
    });
    it('[1, 1, 1, 4, 99, 5, 6, 0, 99] should process correctly', function() {
      assert.deepEqual(computer.run([1, 1, 1, 4, 99, 5, 6, 0, 99]), [30, 1, 1, 4, 2, 5, 6, 0, 99]);
    });
  });
});

describe('Output', function() {
  // Per challenge instructions, modify two codes before running
  let modifiedInput = input.slice();
  modifiedInput[1] = 12;
  modifiedInput[2] = 2;
  it(`output is: ${computer.run(modifiedInput)}`, function() {
    assert.equal(true, true);
  })
});
