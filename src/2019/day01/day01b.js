export function run(input) {
  let totalFuelRequired = 0;
  input.forEach(function(mass) {
    totalFuelRequired += calculateFuel(mass);
  })
  return totalFuelRequired;
}

export function calculateFuel(mass) {
  if (isNaN(mass)) return -1;
  let fuelRequired = Math.floor(mass/3) - 2;
  // Return not just the fuel required, but also the fuel required for this newly-added mass
  return fuelRequired <= 0 ? 0 : fuelRequired + calculateFuel(fuelRequired);
}
