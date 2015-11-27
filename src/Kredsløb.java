import java.awt.Graphics2D;

public class Kredsløb {
	// http://www.formel-samling.dk/19-formler/matematik-kategorier/geometri/38-cirkel-formler
	// https://www.matematikfessor.dk/lessons/enhedscirklen-cosinus-og-sinus-107
	// Atributter for Kredsløb
	private int radius;
	private int omkreds;
	private int center_x;
	private int center_y;
	private int zoom_factor = 5;
    private int planetensGradIKredsløbet = 0;
    
	private String navn;

	// Atributter som objekter for kredsløb
	public Planet planet;

	// Konstructor
	public Kredsløb(int in_omkreds, int x, int y,String p, int startAfstand, int r, int g, int b) {
		planet = new Planet(p, startAfstand, r, g, b);
		center_x = x;
		center_y = y;
		this.setOmkreds(in_omkreds);
		this.beregnPlanetensGradIKredsløbet(0);
	};

	// Metoder til Kredsløb
	public double getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
		this.omkreds = (int) Math.PI * this.radius * 2;
	}

	public int getOmkreds() {
		return this.omkreds;
	}

	public void setOmkreds(int o) {
		this.omkreds = o;
		this.radius = o / (int) Math.PI / 2;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String n) {
		this.navn = n;
	}

	public void beregnPlanetensGradIKredsløbet(double d) {
		// Denne funktin skal retunere den grad planetet er i kredsløbet
		// ud fra den vinkel en linje skulle tegnes fra centrum og ud
		double afstand = this.planet.getPlanetensStartAfstandKredsløbet() + this.planet.beregnAfstandTilbagelagtIalt(d) % this.omkreds;
		int retVal = (int) ((afstand / omkreds) * 360);
		this.planetensGradIKredsløbet = retVal;
	}
	
	public int getPlanetensGradIKredsløbet(){
		return this.planetensGradIKredsløbet;
	}
	public void setPlanetensGradIKredsløbet(int grad){
		 this.planetensGradIKredsløbet = grad;
	}
	
	public void tegn(Graphics2D g) {
		int r = this.radius * zoom_factor;
		int x = center_x - (r / 2);
		int y = center_y - (r / 2);
		g.setColor(this.planet.getColor());
		g.drawOval(x, y, r, r);
	}

	public void tegnPlanet(Graphics2D g, EclipseTime ec) {
		// TODO Auto-generated method stub
		int r = this.radius * zoom_factor;
		int x = center_x - (r / 2);
		int y = center_y - (r / 2);
		g.setColor(this.planet.getColor());
		g.fillArc(x, y, r, r, (int) this.getPlanetensGradIKredsløbet() * -1, (int) this.planet.getDistancePrClick());
		//g.drawArc(x, y, r, r, (int) this.planetensPostionIKredsløbet(ec) * -1, (int) this.planet.getDistancePrClick());

	}
	

}
