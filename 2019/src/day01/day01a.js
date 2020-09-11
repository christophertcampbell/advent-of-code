export function run(input) {
  let totalFuelRequired = 0;
  input.forEach(function(mass) {
    totalFuelRequired += calculateFuel(mass);
  })
  return totalFuelRequired;
}

export function calculateFuel(mass) {
  if (isNaN(mass)) return -1;
  return Math.floor(mass/3) - 2;
}
