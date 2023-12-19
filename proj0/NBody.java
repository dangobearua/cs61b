public class NBody {

	public static double readRadius(String file) {
		In in = new In(file);
		in.readInt();
		return in.readDouble();
	}

	public static Planet[] readPlanets(String file) {
		In in = new In(file);
		int numberOfPlanets = in.readInt();
		in.readDouble();
		Planet[] planets = new Planet[numberOfPlanets];

		for (int i = 0; i < numberOfPlanets; i = i + 1) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
		}

		return planets;
	}

	public static void main(String[] args) {
		String zeroArg = args[0];
		String firstArg = args[1];
		double T = Double.parseDouble(zeroArg);
		double dt = Double.parseDouble(firstArg);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");

		for (Planet planet : planets) {
			planet.draw();
		}

		double time = 0;

		StdDraw.enableDoubleBuffering();

		while (time < T) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for (int i = 0; i < planets.length; i = i + 1) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for (int i = 0; i < planets.length; i = i + 1) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet planet : planets) {
				planet.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

			time += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		} 

	}
}