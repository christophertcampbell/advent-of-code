import { IntCodeComputer } from './day02a';

export function IntCodeComputerTools() {

  /**
   * Find the pair of inputs that produce a certain output
   *
   * @param {int[]} input An array of integers representing an IntCode program, organized in groups of 4:
   *        index 0: operation code (opcode);
   *        index 1: source index #1;
   *        index 2: source index #2;
   *        index 3: output index
   *
   * @param {int} output The output to find corresponding inputs for
   */
  function findInputsThatGenerateOutput(input, output) {

    let computer = new IntCodeComputer();

    for (let i = 0; i <= 99; i++) {
      for (let j = 0; j <= 99; j++) {
        let modifiedInput = input.slice();
        modifiedInput[1] = i;
        modifiedInput[2] = j;
        let finalState = computer.run(modifiedInput);
        if (finalState[0] == output) {
          return i * 100 + j;
        }
      }
    }

    return -1;

  }

  return {
    findInputsThatGenerateOutput: findInputsThatGenerateOutput
  }

}
