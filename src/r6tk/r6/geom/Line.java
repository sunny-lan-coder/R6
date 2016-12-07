package r6tk.r6.geom;

import r6tk.r6.R6Exception;

public class Line implements IIntersectable {

	protected final double m;
	protected final double x1;
	protected final double y1;
	protected final double x2;
	protected final double y2;
	protected final boolean vertical;

	/**
	 * Construct a non vertical line
	 * 
	 * @param m
	 *            slope of line
	 * @param x1
	 *            x component of line starting point
	 * @param y1
	 *            y component of line starting point
	 * @param pointsPositive
	 *            direction of line
	 * @throws R6Exception
	 */
	public Line(double m, double x1, double y1, double x2) throws R6Exception {
		this.m = m;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		vertical = false;
		try {
			this.y2 = y(x2);
		} catch (R6Exception e) {
			throw new R6Exception(R6Error.friendship_is_magic, e);
		}
	}

	/**
	 * Construct vertical line
	 * 
	 * @param x1
	 *            x component of line starting point
	 * @param y1
	 *            y component of line starting point
	 * @param pointsPositive
	 *            direction of line
	 */
	public Line(double x1, double y1, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.y2 = y2;
		this.x2 = x1;
		vertical = true;
		m = Double.POSITIVE_INFINITY;
	}

	/**
	 * 
	 * @return y-intercept of line
	 * @throws R6Exception
	 */
	public double b() throws R6Exception {
		try {
			return y(0);
		} catch (R6Exception e) {
			if (e.e == R6Error.infinite_solutions)
				throw new R6Exception(R6Error.infinite_intercepts, e);
			else if (e.e == R6Error.not_on_line)
				throw new R6Exception(R6Error.no_intercepts, e);
			else if (e.e == R6Error.no_solutions)
				throw new R6Exception(R6Error.no_intercepts, e);
			else
				throw e;
		}
	}

	/**
	 * 
	 * @return slope of line, {@link Double.POSITIVE_INFINITY} or
	 *         {@link Double.NEGATIVE_INFINITY} if line is vertical
	 */
	public double m() {
		return m;
	}

	/**
	 * 
	 * @return x component of line starting point
	 */
	public double x1() {
		return x1;
	}

	/**
	 * 
	 * @return y component of line starting point
	 */
	public double y1() {
		return y1;
	}

	/**
	 * 
	 * @return x component of line ending point
	 */
	public double x2() {
		return x2;
	}

	/**
	 * 
	 * @return y component of line ending point
	 */
	public double y2() {
		return y2;
	}

	/**
	 * Finds solution to line for a given y
	 * 
	 * @param y
	 *            input y value
	 * @return x value that satisfies the equation for a given y
	 * @throws R6Exception
	 */
	public double x(double y) throws R6Exception {

		if (y < Math.min(y1, y2))
			throw new R6Exception(R6Error.not_on_line);

		if (y > Math.max(y1, y2))
			throw new R6Exception(R6Error.not_on_line);

		if (vertical)
			return x1;
		if (m == 0)
			if (y == y1)
				throw new R6Exception(R6Error.infinite_solutions);
			else
				throw new R6Exception(R6Error.no_solutions);
		return (y - y1) / m + x1;
	}

	/**
	 * Finds solution to line for a given x
	 * 
	 * @param x
	 *            input x value
	 * @return y value that satisfies the equation for a given x
	 * @throws R6Exception
	 */
	public double y(double x) throws R6Exception {
		if (x < Math.min(x1, x2))
			throw new R6Exception(R6Error.not_on_line);

		if (x > Math.max(x1, x2))
			throw new R6Exception(R6Error.not_on_line);

		if (vertical)
			if (x == x1)
				throw new R6Exception(R6Error.infinite_solutions);
			else
				throw new R6Exception(R6Error.no_solutions);

		return m * (x - x1) + y1;
	}

	public boolean vertical() {
		return vertical;
	}

	@Override
	public double getXInt(Ray r) throws R6Exception {

		double xint;

		// if parallel
		if (r.m() == m) {
			if (r.b() == b()) {
				if (Math.min(y1, y2) < r.y1() && Math.max(y1, y2) > r.y1())
					throw new R6Exception(R6Error.infinite_solutions);
				if (Math.min(y1, y2) > r.y1() && Math.max(y1, y2) < r.y1())
					throw new R6Exception(R6Error.no_solutions);
				return x1;
			} else
				throw new R6Exception(R6Error.no_solutions);
		}
		if (r.vertical() && vertical) {
			if (r.x1() == x1) {
				if (Math.min(y1, y2) < r.y1() && Math.max(y1, y2) > r.y1())
					throw new R6Exception(R6Error.infinite_solutions);
				if (Math.min(y1, y2) > r.y1() && Math.max(y1, y2) < r.y1())
					throw new R6Exception(R6Error.no_solutions);
				return x1;
			} else
				throw new R6Exception(R6Error.no_solutions);
		}

		if (vertical) {
			xint = x1;
		} else if (r.vertical()) {
			xint = r.x1();
		} else {
			xint = (m * x1 + y1 + r.m() * r.x1() - r.y1()) / (r.m() - m);
		}

		double yinta = 0;
		double yintb = 0;
		boolean aflag = false;
		boolean bflag = false;

		try {
			yinta = y(xint);
			aflag = true;
		} catch (R6Exception e) {
			if (e.e == R6Error.not_on_line)
				throw new R6Exception(R6Error.no_solutions, e);
		}
		try {
			yintb = r.y(xint);
			bflag = true;
		} catch (R6Exception e) {
			if (e.e == R6Error.not_on_line)
				throw new R6Exception(R6Error.no_solutions, e);
		}

		if (aflag && bflag)
			if (yinta != yintb)
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
		if (r.m() == m) {
			if (r.b() == b()) {
				if (Math.min(y1, y2) < r.y1() && Math.max(y1, y2) > r.y1())
					throw new R6Exception(R6Error.infinite_solutions);
				if (Math.min(y1, y2) > r.y1() && Math.max(y1, y2) < r.y1())
					throw new R6Exception(R6Error.no_solutions);
				return y1;
			} else
				throw new R6Exception(R6Error.no_solutions);
		}
		if (r.vertical() && vertical) {
			if (r.x1() == x1) {
				if (Math.min(y1, y2) < r.y1() && Math.max(y1, y2) > r.y1())
					throw new R6Exception(R6Error.infinite_solutions);
				if (Math.min(y1, y2) > r.y1() && Math.max(y1, y2) < r.y1())
					throw new R6Exception(R6Error.no_solutions);
				return y1;
			} else
				throw new R6Exception(R6Error.no_solutions);
		}

		if (vertical) {
			xint = x1;
		} else if (r.vertical()) {
			xint = r.x1();
		} else {
			xint = (m * x1 + y1 + r.m() * r.x1() - r.y1()) / (r.m() - m);
		}

		double yinta = 0;
		double yintb = 0;
		boolean aflag = false;
		boolean bflag = false;

		try {
			yinta = y(xint);
			aflag = true;
		} catch (R6Exception e) {
			if (e.e == R6Error.not_on_line)
				throw new R6Exception(R6Error.no_solutions, e);
		}
		try {
			yintb = r.y(xint);
			bflag = true;
		} catch (R6Exception e) {
			if (e.e == R6Error.not_on_line)
				throw new R6Exception(R6Error.no_solutions, e);
		}

		if (aflag && bflag)
			if (yinta != yintb)
				throw new R6Exception(R6Error.friendship_is_magic);

		if (aflag)
			return yinta;
		if (bflag)
			return yintb;
		else
			throw new R6Exception(R6Error.friendship_is_magic);
	}

}
