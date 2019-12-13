export function IntCodeComputer() {

  let codes = [];

  /**
   * Main entry point
   *
   * @param {int[]} input An array of integers representing an IntCode program, organized in groups of 4:
   *        index 0: operation code (opcode);
   *        index 1: source index #1;
   *        index 2: source index #2;
   *        index 3: output index
   */
  function run(input) {
    codes = input;

    for (let i = 0; i < codes.length; i += 4) {
      let commandGroup = codes.slice(i, i + 4);

      switch(commandGroup[0]) {
        case 1: // Add
          opcode1(commandGroup);
          break;
        case 2: // Multiply
          opcode2(commandGroup);
          break;
        case 99: // Abort program
          return codes;
        default: // Unrecognized OpCode, do nothing
          break;
      }
    }

    return codes;
  }

  // Operation: Add
  function opcode1(commandGroup) {
    codes[commandGroup[3]] = codes[commandGroup[1]] + codes[commandGroup[2]];
  }

  // Operation: Multiply
  function opcode2(commandGroup) {
    codes[commandGroup[3]] = codes[commandGroup[1]] * codes[commandGroup[2]];
  }

  return {
    run: run
  }
}

