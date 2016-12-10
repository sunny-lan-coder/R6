package r6tk.r6.geom;

import r6tk.r6.R6;
import r6tk.r6.R6Exception;

/**
 * A mathematical ray
 * 
 * @author sunny
 *
 */
public class Ray implements IIntersectable {
	protected final double m;
	protected final double x1;
	protected final double y1;
	protected final boolean vertical;
	protected final boolean pointsPositive;

	/**
	 * Construct a non vertical ray
	 * 
	 * @param m
	 *            slope of ray
	 * @param x1
	 *            x component of ray starting point
	 * @param y1
	 *            y component of ray starting point
	 * @param pointsPositive
	 *            direction of ray
	 */
	public Ray(double m, double x1, double y1, boolean pointsPositive) {
		this.m = m;
		this.x1 = x1;
		this.y1 = y1;

		this.pointsPositive = pointsPositive;
		if (m == Double.POSITIVE_INFINITY) {
			vertical = true;
			pointsPositive = true;
		} else if (m == Double.NEGATIVE_INFINITY) {
			vertical = true;
			pointsPositive = false;
		} else {
			vertical = false;
		}

	}
	
	public double angle(){
		double theta = Math.atan(m);

		if (!pointsPositive)
			theta += R6.pi;

		theta = R6.normalizeAngle(theta);
		return theta;
	}

	public double angle() {
		double theta = Math.atan(m);

		if (!pointsPositive)
			theta += R6.pi;

		theta = R6.normalizeAngle(theta);
		return theta;
	}

	/**
	 * Construct vertical ray
	 * 
	 * @param x1
	 *            x component of ray starting point
	 * @param y1
	 *            y component of ray starting point
	 * @param pointsPositive
	 *            direction of ray
	 */
	public Ray(double x1, double y1, boolean pointsPositive) {
		this.x1 = x1;
		this.y1 = y1;
		vertical = true;
		this.pointsPositive = pointsPositive;
		if (pointsPositive)
			m = Double.POSITIVE_INFINITY;
		else
			m = Double.NEGATIVE_INFINITY;
	}

	/**
	 * 
	 * @return y-intercept of ray
	 */
	public double b() {
		return m*(0-x1)+y1;
	}

	/**
	 * 
	 * @return slope of ray, {@link Double.POSITIVE_INFINITY} or
	 *         {@link Double.NEGATIVE_INFINITY} if ray is vertical
	 */
	public double m() {
		return m;
	}

	/**
	 * 
	 * @return True if ray points in positive direction, false if ray points in
	 *         negative direction
	 */
	public boolean pointsPositive() {
		return pointsPositive;
	}

	/**
	 * 
	 * @return x component of ray starting point
	 */
	public double x1() {
		return x1;
	}

	/**
	 * 
	 * @return y component of ray starting point
	 */
	public double y1() {
		return y1;
	}

	/**
	 * Finds solution to ray for a given y
	 * 
	 * @param y
	 *            input y value
	 * @return x value that satisfies the equation for a given y
	 * @throws R6Exception
	 */
	public double x(double y) throws R6Exception {
		if (m == 0)
			if (y == y1)
				throw new R6Exception(R6Error.infinite_outputs);
			else
				throw new R6Exception(R6Error.no_outputs);
		if (pointsPositive ^ (m < 0)) {
			if (y < y1)
				throw new R6Exception(R6Error.no_outputs);
		} else {
			if (y > y1)
				throw new R6Exception(R6Error.no_outputs);
		}
		if (vertical)
			return x1;

		return (y - y1) / m + x1;
	}

	/**
	 * Finds solution to ray for a given x
	 * 
	 * @param x
	 *            input x value
	 * @return y value that satisfies the equation for a given x
	 * @throws R6Exception
	 */
	public double y(double x) throws R6Exception {
		if (vertical)
			if (x == x1)
				throw new R6Exception(R6Error.infinite_outputs);
			else
				throw new R6Exception(R6Error.no_outputs);

		if (pointsPositive) {
			if (x < x1)
				throw new R6Exception(R6Error.no_outputs);
		} else {
			if (x > x1)
				throw new R6Exception(R6Error.no_outputs);
		}

		return m * (x - x1) + y1;
	}

	public boolean vertical() {
		return vertical;
	}

	@Override
	public double getXInt(Ray r) throws R6Exception {

		double xint;

		// if parallel
		if (r.vertical() && vertical) {
			if (r.x1() == x1) {
				if (pointsPositive) {
					if (r.pointsPositive())
						throw new R6Exception(R6Error.infinite_intersections);

					if (r.y1() > y1)
						throw new R6Exception(R6Error.infinite_intersections);
					else if (r.y1() < y1)
						throw new R6Exception(R6Error.no_intersections);

					return x1;

				} else {
					if (!r.pointsPositive())
						throw new R6Exception(R6Error.infinite_intersections);

					if (r.y1() < y1)
						throw new R6Exception(R6Error.infinite_intersections);
					else if (r.y1() > y1)
						throw new R6Exception(R6Error.no_intersections);

					return x1;
				}
			} else
				throw new R6Exception(R6Error.no_intersections);
		}
		if (r.m() == m) {
			if (r.b() == b())
				if (pointsPositive) {
					if (r.pointsPositive())
						throw new R6Exception(R6Error.infinite_intersections);

					if (r.x1() > x1)
						throw new R6Exception(R6Error.infinite_intersections);
					else if (r.x1() < x1)
						throw new R6Exception(R6Error.no_intersections);

					return x1;

				} else {
					if (!r.pointsPositive())
						throw new R6Exception(R6Error.infinite_intersections);

					if (r.x1() < x1)
						throw new R6Exception(R6Error.infinite_intersections);
					else if (r.x1() > x1)
						throw new R6Exception(R6Error.no_intersections);

					return x1;
				}
			else
				throw new R6Exception(R6Error.no_intersections);
		}

		if (vertical) {
			xint = x1;
		} else if (r.vertical()) {
			xint = r.x1;
		} else {
			xint = (-m * x1 + y1 + r.m() * r.x1() - r.y1()) / (r.m() - m);
		}

		double yinta = 0;
		double yintb = 0;
		boolean aflag = false;
		boolean bflag = false;

		try {
			yinta = y(xint);
			aflag = true;
		} catch (R6Exception e) {
			if (e.e == R6Error.no_outputs)
				throw new R6Exception(R6Error.no_intersections, e);
		}
		try {
			yintb = r.y(xint);
			bflag = true;
		} catch (R6Exception e) {
			if (e.e == R6Error.no_outputs)
				throw new R6Exception(R6Error.no_intersections, e);
		}

		if (aflag && bflag)
			if (Math.abs(yinta - yintb) > R6.epilison)
				throw new R6Exception(R6Error.friendship_is_magic);

		if (aflag)
			return xint;
		if (bflag)
			return xint;
		else
			throw new R6Exception(R6Error.friendship_is_magic);
	}

	@Override
	public double getYInt(Ray r) throws R6Exception {

		double xint;

		// if parallel
		if (r.vertical() && vertical) {
			if (r.x1() == x1) {
				if (pointsPositive) {
					if (r.pointsPositive())
						throw new R6Exception(R6Error.infinite_intersections);

					if (r.y1() > y1)
						throw new R6Exception(R6Error.infinite_intersections);
					else if (r.y1() < y1)
						throw new R6Exception(R6Error.no_intersections);

					return y1;

				} else {
					if (!r.pointsPositive())
						throw new R6Exception(R6Error.infinite_intersections);

					if (r.y1() < y1)
						throw new R6Exception(R6Error.infinite_intersections);
					else if (r.y1() > y1)
						throw new R6Exception(R6Error.no_intersections);

					return y1;
				}
			} else
				throw new R6Exception(R6Error.no_intersections);
		}
		if (r.m() == m) {
			if (r.b() == b())
				if (pointsPositive) {
					if (r.pointsPositive())
						throw new R6Exception(R6Error.infinite_intersections);

					if (r.x1() > x1)
						throw new R6Exception(R6Error.infinite_intersections);
					else if (r.x1() < x1)
						throw new R6Exception(R6Error.no_intersections);

					return y1;

				} else {
					if (!r.pointsPositive())
						throw new R6Exception(R6Error.infinite_intersections);

					if (r.x1() < x1)
						throw new R6Exception(R6Error.infinite_intersections);
					else if (r.x1() > x1)
						throw new R6Exception(R6Error.no_intersections);

					return y1;
				}
			else
				throw new R6Exception(R6Error.no_intersections);
		}

		if (vertical) {
			xint = x1;
		} else if (r.vertical()) {
			xint = r.x1;
		} else {
			xint = (-m * x1 + y1 + r.m() * r.x1() - r.y1()) / (r.m() - m);
		}

		double yinta = 0;
		double yintb = 0;
		boolean aflag = false;
		boolean bflag = false;

		try {
			yinta = y(xint);
			aflag = true;
		} catch (R6Exception e) {
			if (e.e == R6Error.no_outputs)
				throw new R6Exception(R6Error.no_intersections, e);
		}
		try {
			yintb = r.y(xint);
			bflag = true;
		} catch (R6Exception e) {
			if (e.e == R6Error.no_outputs)
				throw new R6Exception(R6Error.no_intersections, e);
		}

		if (aflag && bflag)
			if (Math.abs(yinta - yintb) > R6.epilison)
				throw new R6Exception(R6Error.friendship_is_magic);

		if (aflag)
			return yinta;
		if (bflag)
			return yintb;
		else
			throw new R6Exception(R6Error.friendship_is_magic);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Ray) {
			Ray r = (Ray) o;
			if (r.m == m && r.x1 == x1 && r.y1 == y1 && r.pointsPositive == pointsPositive) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object clone() {
		if (vertical)
			return new Ray(x1, y1, pointsPositive);
		return new Ray(m, x1, y1, pointsPositive);
	}

}
