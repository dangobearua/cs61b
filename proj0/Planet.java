public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass  = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass  = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double dx = (this.xxPos - p.xxPos);
		double dy = (this.yyPos - p.yyPos);

		double r = Math.sqrt((dx * dx) + (dy * dy));

		return r;
	}

	public double calcForceExertedBy(Planet p) {
		double distance = this.calcDistance(p);
		double force = (G * this.mass * p.mass) / (distance * distance);
		return force;
	}

	public double calcForceExertedByX(Planet p) {
		double force = this.calcForceExertedBy(p);
		double distance = this.calcDistance(p);
		double dx = p.xxPos - this.xxPos;
		double xForce = (force * dx) / distance;
		return xForce;

	}

	public double calcForceExertedByY(Planet p) {
		double force = this.calcForceExertedBy(p);
		double distance = this.calcDistance(p);
		double dy = p.yyPos - this.yyPos;
		double yForce = (force * dy) / distance;
		return yForce;

	}

	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double netForceX = 0;
		for (Planet p : allPlanets) {
			if (this.equals(p)) {
				continue;
			}
			netForceX += this.calcForceExertedByX(p);
		}
		return netForceX;

	}

	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double netForceY = 0;
		for (Planet p : allPlanets) {
			if (this.equals(p)) {
				continue;
			}
			netForceY += this.calcForceExertedByY(p);
		}
		return netForceY;
		
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX / this.mass;
		double aY = fY / this.mass;

		double vX = this.xxVel + (dt * aX);
		double vY = this.yyVel + (dt * aY);

		double pX = this.xxPos + (dt * vX);
		double pY = this.yyPos + (dt * vY);

		this.xxVel = vX;
		this.yyVel = vY;
		this.xxPos = pX;
		this.yyPos = pY;
	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}

}