
public abstract class Vehicle {
	
	public String model;
	public double engineCapacity;
	public int yearOfProduction;
	
	private int minYearOfProduction = 1980;
	private int maxYearOfProduction = 2017;
	
	protected double minEngineCapacity = 1.5;
	protected double maxEngineCapacity = 2.9;
	
	private Boolean needServicing = true;
	
	protected int registrationTax = 100;
	protected int serviceTax = 200;
	protected int luxuryTax = 0;
	
	private double fuelInTank = 0.0;
	protected double maxTankCapacity = 50;
	private int totalDrives = 0;
	protected double fuelConsumptionPerKm = 0.07;
		
	
	
	private enum AvailableModels {
		TOYOTA, TESLA, NISSAN, SUBARU;
		
		private static String getRandom() {
			return AvailableModels.values()[(int)(Math.random()*AvailableModels.values().length)].toString();
		}
	}
	
	public Vehicle() {
		this.model = AvailableModels.getRandom();
		this.yearOfProduction = minYearOfProduction + (int)(Math.random() * ((maxYearOfProduction - minYearOfProduction) + 1));
		this.engineCapacity = this.minEngineCapacity + (Math.random() * ((this.maxEngineCapacity - this.minEngineCapacity) + 1));
		}
	
	public void describeMe() {
		System.out.println("Model: " + this.model );
		System.out.println("Engine Capacity: " + engineCapacity );
		System.out.println("Year of production: " + this.yearOfProduction);
	}
	
	public void service() {
		System.out.println("Your vehicle is serviced!");
		needServicing = false;
	}
	
	public void computeTaxes() {
		int totalTaxes = registrationTax + serviceTax + luxuryTax;
		System.out.println("Total taxes are: " + totalTaxes);
		
	}
	
	public void addFuel() {
		this.fuelInTank = this.maxTankCapacity;
		System.out.println("Refueling..");
	}
	
	public void drive(double mileage) {
		if(needServicing) {
			service();
		}
		try {
			if (fuelInTank - mileage * fuelConsumptionPerKm < 0) {
			throw new NotEnoughFuelException("You don't have enough fuel for this drive.");
			}
		} catch (NotEnoughFuelException e) {
			addFuel();
		}
		fuelInTank = fuelInTank - mileage * fuelConsumptionPerKm;
		System.out.println("Driving...");
		totalDrives++;
		if(totalDrives % 8 ==0) {
			needServicing = true;
		}
		System.out.println("Destination reached!");		
	}
	
	

}
