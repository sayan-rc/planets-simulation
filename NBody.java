public class NBody {

	public static double readRadius(String file) {
		In in = new In(file);
		in.readInt();
		return in.readDouble();
	}

	public static Planet[] readPlanets(String file) {
		In in = new In(file);
		Planet[] ps = new Planet[in.readInt()];
		in.readDouble();

		for (int i = 0; i < ps.length; i++) {
			ps[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
							   in.readDouble(), in.readDouble(), in.readString());
		}
		return ps;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double r = readRadius(filename);
		Planet[] ps = readPlanets(filename);
		StdDraw.setScale(-r, r);
		double t = 0;

		while (t < T) {
			double[] xForces = new double[ps.length];
			double[] yForces = new double[ps.length];

			for (int i = 0; i < ps.length; i++) {
				xForces[i] = ps[i].calcNetForceExertedByX(ps);
				yForces[i] = ps[i].calcNetForceExertedByY(ps);
			}

			for (int i = 0; i < ps.length; i++) {
				ps[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, "/images/starfield.jpg");

			for (Planet p : ps) {
				p.draw();
			}

			StdDraw.show(10);
			t += dt;
		}

		StdOut.printf("%d\n", ps.length);
		StdOut.printf("%.2e\n", r);

		for (int i = 0; i < ps.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   			ps[i].xxPos, ps[i].yyPos, ps[i].xxVel, ps[i].yyVel, ps[i].mass, ps[i].imgFileName);	
		}	
	}
}
