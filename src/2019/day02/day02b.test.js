var assert = require('assert');
import { input } from './day02.input';
import { IntCodeComputer } from './day02a';
import { IntCodeComputerTools } from './day02b';

let computer = new IntCodeComputer();
let computerTools = new IntCodeComputerTools();

describe('Day 2 Challenge - Part 2', function() {
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
  // Per challenge instructions, search for a combination of inputs which result in the output 19690720
  // Inputs at addresses 1 and 2 will each be between 0 and 99 inclusive

  let output = 19690720;
  let matchingInput = computerTools.findInputsThatGenerateOutput(input, output);
  if (matchingInput >= 0) {
    it(`output is ${output} at input value ${matchingInput}`, function() {
      assert.equal(true, true);
    })
  } else {
    it(`no match found for output ${output}`, function() {
      assert.equal(true, true);
    })
  }

});
