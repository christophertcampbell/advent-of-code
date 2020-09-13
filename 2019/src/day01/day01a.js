export function FuelCalculator() {

  /**
   * Main entry point
   * 
   * Calculates the fuel requirements for the modules aboard the spacecraft
   * 
   * @param {int|int[]} mass The module mass(es) to use in calculating the fuel requirements
   */
  function calculate(mass) {
    return Array.isArray(mass) ? calculateMultiple(mass) : calculateSingle(mass);
  }

  // Calculates fuel requirements for multiple modules
  function calculateMultiple(masses) {
    let totalFuelRequired = 0;
    masses.forEach(function(mass) {
      totalFuelRequired += calculateSingle(mass);
    })
    return totalFuelRequired;
  }

  // Calculates fuel requirements for a single module
  function calculateSingle(mass) {
    if (isNaN(mass)) return -1;
    return Math.floor(mass/3) - 2;
  }

  return {
    calculate: calculate
  }
}
