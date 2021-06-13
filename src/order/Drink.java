package order;

public class Drink extends Product{

	private static final long serialVersionUID = -2429031331393022338L;

	private double alcoholPercentage;
	
	public Drink(String name, String description, double price, String path, double alcoholPercentage, int id) {
		super(name, description, price, path, id);
		this.alcoholPercentage = alcoholPercentage;
	}

	public double getAlcoholPercentage() {
		return alcoholPercentage;
	}

	public void setAlcoholPercentage(double alcoholPercentage) {
		this.alcoholPercentage = alcoholPercentage;
	}
	

}
