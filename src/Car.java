
public class Car extends Vehicle {
	
	public Car() {
		super();
		if (this.engineCapacity > 2.0) {
			this.luxuryTax = 200;
		}
	}
}
