import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;

public class Main extends JPanel {
	private static final long serialVersionUID = 1L;
	static EclipseTime ec = new EclipseTime();
	static int ss_center_x = 300;
	static int ss_center_y = 300;

	public Main() {
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (int kredsl�bT�ller = 0; kredsl�bT�ller < SolSystem.size(); kredsl�bT�ller++) {
			SolSystem.get(kredsl�bT�ller).tegn(g2d);
			SolSystem.get(kredsl�bT�ller).beregnPlanetensGradIKredsl�bet(ec.getSSClick());
			SolSystem.get(kredsl�bT�ller).tegnPlanet((Graphics2D) g, ec);

		}
		Analyse.get(0).tegn(g2d);
		Analyse.get(0).planet.setDistancePrClick(lavesteGradIndtilNu);
		Analyse.get(0).tegnPlanet((Graphics2D) g, ec);

	}

	// attributter til Main
	static ArrayList<Kredsl�b> SolSystem = new ArrayList<Kredsl�b>();
	static ArrayList<Kredsl�b> Analyse = new ArrayList<Kredsl�b>();
	static EclipseTime ss_time;
	static double MAX_CLICK = 100;
	static int lavesteGradIndtilNu = 360;

	public static void initWorld() {

		Kredsl�b kredsl�bMars = new Kredsl�b(400, ss_center_x, ss_center_y, "D", 300, 000, 000, 255);
		Kredsl�b kredsl�bJorden = new Kredsl�b(300, ss_center_x, ss_center_y, "C", 150, 000, 255, 000);
		Kredsl�b kredsl�bVenus = new Kredsl�b(200, ss_center_x, ss_center_y, "B", 50, 255, 000, 000);
		Kredsl�b kredsl�bMerkur = new Kredsl�b(100, ss_center_x, ss_center_y, "A", 25, 000, 000, 000);
		Kredsl�b kredsl�bMinGrad = new Kredsl�b(200, 100, 100, "X", 100, 000, 000, 000);

		kredsl�bMars.planet.setDistancePrClick(1);
		kredsl�bJorden.planet.setDistancePrClick(1);
		kredsl�bVenus.planet.setDistancePrClick(1);
		kredsl�bMerkur.planet.setDistancePrClick(1);
		kredsl�bMinGrad.planet.setDistancePrClick(0);

		SolSystem.add(kredsl�bMars);
		SolSystem.add(kredsl�bJorden);
		SolSystem.add(kredsl�bVenus);
		SolSystem.add(kredsl�bMerkur);
		// ****************************************
		Analyse.add(kredsl�bMinGrad);
		// *****************************************

	}

	public int getMinGrad() {
		// find den mindste grad i solsystemet
		int minGrad = 360;
		for (int i = 0; i < SolSystem.size(); i++) {
			if (SolSystem.get(i).getPlanetensGradIKredsl�bet() < minGrad)
				minGrad = SolSystem.get(i).getPlanetensGradIKredsl�bet();
		}
		return minGrad;
	}

	public int getMaxGrad() {
		// find den h�jeste grad i solsystemet
		int minGrad = 0;
		for (int i = 0; i < SolSystem.size(); i++) {
			if (SolSystem.get(i).getPlanetensGradIKredsl�bet() > minGrad)
				minGrad = SolSystem.get(i).getPlanetensGradIKredsl�bet();
		}
		return minGrad;
	}

	public static int getGradP�T�ttesteNabo(int p) throws IOException {
		// returnere graden p� den t�tteste nabo til planet(p) i solsystemet
		//
		int p_grad = SolSystem.get(p).getPlanetensGradIKredsl�bet() * -1;
		int lavesteForskel = 360;
		int loop_planet_grad = 0;
		for (int i = 0; i < SolSystem.size(); i++) {
			if (i != p) {
				loop_planet_grad = SolSystem.get(i).getPlanetensGradIKredsl�bet() * -1;
				if (lavesteForskel > p_grad - loop_planet_grad)
					lavesteForskel = p_grad - loop_planet_grad;
			}
		}
		// System.out.println("Forskel(" + lavesteForskel + ")");
		return lavesteForskel;
	}

	public static int getHvorTaetErPlaneternePaaHinanden() throws IOException {
		int sumAfGrader = 0;

		for (int i = 0; i < SolSystem.size(); i++) {
			sumAfGrader += getGradP�T�ttesteNabo(i);
		}
		sumAfGrader *= -1;

		if (lavesteGradIndtilNu > sumAfGrader)
			lavesteGradIndtilNu = sumAfGrader;
		System.out.println("Sum ad grader = " + sumAfGrader + " lavesteIndtilNu(" + lavesteGradIndtilNu + ")");
		SolSystem.get(SolSystem.size() - 1).setPlanetensGradIKredsl�bet(lavesteGradIndtilNu);
		return sumAfGrader;
	}

	@SuppressWarnings("resource")
	public static void pauseProg() {
		System.out.println("Press enter to continue...");
		Scanner keyboard = new Scanner(System.in);
		keyboard.nextLine();
	}

	public static void main(String[] args) throws InterruptedException, IOException {

		initWorld();

		JFrame frame = new JFrame("MultiEclipse");
		Main universe = new Main();
		frame.add(universe);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

while (true) {
			
			getHvorTaetErPlaneternePaaHinanden();
			//pauseProg();
			
			universe.repaint();

			Thread.sleep(100);
			
			ec.click();

		}

	}

}
