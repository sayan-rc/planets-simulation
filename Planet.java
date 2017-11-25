public class Planet {
	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		return Math.pow((p.xxPos - xxPos) * (p.xxPos - xxPos) + (p.yyPos - yyPos) * (p.yyPos - yyPos), 0.5);
	}

	public double calcForceExertedBy(Planet p) {
		double r = this.calcDistance(p);
		return (6.67e-11) * mass * p.mass / (r * r);
	}

	public double calcForceExertedByX(Planet p) {
		return this.calcForceExertedBy(p) * (p.xxPos - xxPos) / this.calcDistance(p);
	}

	public double calcForceExertedByY(Planet p) {
		return this.calcForceExertedBy(p) * (p.yyPos - yyPos) / this.calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] ps) {
		double f = 0;

		for (Planet p : ps) {
			if (!this.equals(p)) {
				f += this.calcForceExertedByX(p);
			}
		}

		return f;
	}

	public double calcNetForceExertedByY(Planet[] ps) {
		double f = 0;

		for (Planet p : ps) {
			if (!this.equals(p)) {
				f += this.calcForceExertedByY(p);
			}
		}

		return f;
	}

	public void update(double dt, double fX, double fY) {
		xxVel = xxVel + dt * (fX / mass);
		yyVel = yyVel + dt * (fY / mass);
		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;		
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "/images/" + imgFileName);
	}
}