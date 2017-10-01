
public class Truck extends Vehicle {
	
	public Truck() {
		super();
		this.minEngineCapacity = 3.5;
		this.maxEngineCapacity = 4.9;
		this.engineCapacity = minEngineCapacity + (Math.random() * (maxEngineCapacity - minEngineCapacity));
		this.registrationTax = 500;
		this.serviceTax = 750;
		if (this.engineCapacity > 4.0) {
			this.luxuryTax = 1000;
		}
		this.maxTankCapacity = 90;
		this.fuelConsumptionPerKm = 0.09;
	}
	
	public void loadCargo() {
		System.out.println("Loading cargo..");
		this.fuelConsumptionPerKm = 2 * this.fuelConsumptionPerKm;
	}
}
