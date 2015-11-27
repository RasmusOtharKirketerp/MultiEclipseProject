import java.awt.Color;

//
//https://www.matematikfessor.dk/lessons/enhedscirklen-cosinus-og-sinus-107
public class Planet {
	// atrubutter for Planet

	private double distancePrClick; // km/t
	private int planetensStartAfstandKredsl�bet;	
	private String name;
	private Color color;

	// Konstruktor
	public Planet(String n, int startAfstand, int r, int g, int b) {
		this.name = n;
		setColor(new Color(r, g, b));
		this.setPlanetensStartAfstandKredsl�bet(startAfstand);
	}

	// Metoder
	public double getDistancePrClick() {
		return distancePrClick;
	}

	public void setDistancePrClick(double fart) {
		this.distancePrClick = fart;
	}

	public double beregnAfstandTilbagelagtIalt(double click) {
		// afstand tilbagelagt i kredsl�bet i alt
		double retVal = click * this.getDistancePrClick();
		return retVal;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getPlanetensStartAfstandKredsl�bet() {
		return planetensStartAfstandKredsl�bet;
	}

	public void setPlanetensStartAfstandKredsl�bet(int planetensStartAfstandKredsl�bet) {
		this.planetensStartAfstandKredsl�bet = planetensStartAfstandKredsl�bet;
	}

}
