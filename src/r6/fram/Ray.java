package r6.fram;

/**
 * A mathematical ray
 * 
 * @author sunny
 *
 */
public class Ray implements IIntersectable{
	private final double m;
	private final double x1;
	private final double y1;
	private final boolean vertical;
	private final boolean pointsPositive;

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
		vertical = false;
		this.pointsPositive = pointsPositive;
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
	 * @throws LineException
	 */
	public double b() throws LineException {
		try {
			return y(0);
		} catch (LineException e) {
			if (e.e == Error.infinite_solutions)
				throw new LineException(Error.infinite_intercepts, e);
			else if (e.e == Error.behind_line)
				throw new LineException(Error.no_intercepts, e);
			else if (e.e == Error.no_solutions)
				throw new LineException(Error.no_intercepts, e);
			else
				throw e;
		}
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
	 * @throws LineException
	 */
	public double x(double y) throws LineException {
		if (pointsPositive) {
			if (y < y1)
				throw new LineException(Error.behind_line);
		} else {
			if (y > y1)
				throw new LineException(Error.behind_line);
		}
		if (vertical)
			return x1;
		if (m == 0)
			if (y == y1)
				throw new LineException(Error.infinite_solutions);
			else
				throw new LineException(Error.no_solutions);
		return (y - y1) / m + x1;
	}

	/**
	 * Finds solution to ray for a given x
	 * 
	 * @param x
	 *            input x value
	 * @return y value that satisfies the equation for a given x
	 * @throws LineException
	 */
	public double y(double x) throws LineException {
		if (pointsPositive) {
			if (x < x1)
				throw new LineException(Error.behind_line);
		} else {
			if (x > x1)
				throw new LineException(Error.behind_line);
		}
		if (vertical)
			if (x == x1)
				throw new LineException(Error.infinite_solutions);
			else
				throw new LineException(Error.no_solutions);
		return m * (x - x1) + y1;
	}

	@Override
	public double getXInt(Ray r) throws LineException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getYInt(Ray r) throws LineException {
		// TODO Auto-generated method stub
		return 0;
	}

}
