package r6tk.r6.geom;

import r6tk.r6.R6;
import r6tk.r6.R6Exception;

public class Arc implements IIntersectable {

	protected final double x;
	protected final double y;
	protected final double r;
	protected final double astart;
	protected final double aend;

	public Arc(double x, double y, double r, double astart, double aend) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.astart = astart;
		this.aend = aend;
	}

	public double x() {
		return x;
	}

	public double y() {
		return y;
	}

	public double astart() {
		return astart;
	}

	public double aend() {
		return aend;
	}

	public double r() {
		return r;
	}

	@Override
	public double getXInt(Ray r) throws R6Exception {
		if (r.vertical()) {
			double a = 1;
			double b = -2 * y;
			double c = r.x1() * r.x1() - 2 * r.x1() * x + x * x + y * y - this.r * this.r;

			double i = b * b - 4 * a * c;
			double yint1 = (-b + Math.sqrt(i)) / (2 * a);
			double yint2 = (-b - Math.sqrt(i)) / (2 * a);

			if (i > 0) {
				boolean flag1 = false;
				boolean flag2 = false;
				double dist1 = 0;
				double dist2 = 0;
				double xint2 = 0;
				double xint1 = 0;
				try {
					xint1 = r.x(yint1);
//					System.out.println("angle 1:"+Math.toDegrees(R6.angle(x,y,xint1,yint1)));
					if (R6.inBetween(astart, aend, R6.angle(x,y,xint1,yint1))) {
						flag1 = true;
						dist1 = Math
								.sqrt(Math.pow(Math.abs(xint1 - r.x1()), 2) + Math.pow(Math.abs(yint1 - r.y1()), 2));
					}
				} catch (R6Exception e) {
					if (e.e != R6Error.no_outputs)
						throw new R6Exception(R6Error.friendship_is_magic);
				}
				try {
					xint2 = r.x(yint2);
//					System.out.println("angle 2:"+Math.toDegrees(R6.angle(x,y,xint2,yint2)));
					if (R6.inBetween(astart, aend, R6.angle(x,y,xint2,yint2))) {
						dist2 = Math
								.sqrt(Math.pow(Math.abs(xint2 - r.x1()), 2) + Math.pow(Math.abs(yint2 - r.y1()), 2));
						flag2 = true;
					}
				} catch (R6Exception e) {
					if (e.e != R6Error.no_outputs)
						throw new R6Exception(R6Error.friendship_is_magic);
				}

				if (flag1)
					if (flag2)
						if (dist1 < dist2)
							return xint1;
						else
							return xint2;
					else
						return xint1;
				else {
					if (flag2)
						return xint2;
					else
						throw new R6Exception(R6Error.no_intersections);
				}
			} else {
				throw new R6Exception(R6Error.no_intersections);
			}

		}

		double a = (r.m() * r.m()) + 1;
		double b = 2 * ((r.m() * r.b()) - (r.m() * y) - x);
		double c = (y * y) - (this.r * this.r) + (x * x) - (2 * r.b() * y) + (r.b() * r.b());

		double i = (b * b) - (4 * a * c);
		double xint1 = (-b + Math.sqrt(i)) / (2 * a);
		double xint2 = (-b - Math.sqrt(i)) / (2 * a);

		if (i > 0) {
			boolean flag1 = false;
			boolean flag2 = false;
			double dist1 = 0;
			double dist2 = 0;
			double yint1 = 0;
			double yint2 = 0;
			try {
				yint1 = r.y(xint1);
				if (R6.inBetween(astart, aend, R6.angle(x,y,xint1,yint1))) {
					flag1 = true;
					dist1 = Math.sqrt(Math.pow(Math.abs(xint1 - r.x1()), 2) + Math.pow(Math.abs(yint1 - r.y1()), 2));
				}
			} catch (R6Exception e) {
				if (e.e != R6Error.no_outputs)
					throw new R6Exception(R6Error.friendship_is_magic);
			}
			try {
				yint2 = r.y(xint2);
				if (R6.inBetween(astart, aend, R6.angle(x,y,xint2,yint2))) {
					dist2 = Math.sqrt(Math.pow(Math.abs(xint2 - r.x1()), 2) + Math.pow(Math.abs(yint2 - r.y1()), 2));
					flag2 = true;
				}
			} catch (R6Exception e) {
				if (e.e != R6Error.no_outputs)
					throw new R6Exception(R6Error.friendship_is_magic);
			}

			if (flag1)
				if (flag2)
					if (dist1 < dist2)
						return xint1;
					else
						return xint2;
				else
					return xint1;
			else {
				if (flag2)
					return xint2;
				else
					throw new R6Exception(R6Error.no_intersections);
			}

		} else {
			throw new R6Exception(R6Error.no_intersections);
		}
	}

	@Override
	public double getYInt(Ray r) throws R6Exception {
		if (r.vertical()) {
			double a = 1;
			double b = -2 * y;
			double c = r.x1() * r.x1() - 2 * r.x1() * x + x * x + y * y - this.r * this.r;

			double i = b * b - 4 * a * c;
			double yint1 = (-b + Math.sqrt(i)) / (2 * a);
			double yint2 = (-b - Math.sqrt(i)) / (2 * a);

			if (i > 0) {
				boolean flag1 = false;
				boolean flag2 = false;
				double dist1 = 0;
				double dist2 = 0;
				double xint2 = 0;
				double xint1 = 0;
				try {
					xint1 = r.x(yint1);
//					System.out.println("angle 1:"+Math.toDegrees(R6.angle(x,y,xint1,yint1)));
					if (R6.inBetween(astart, aend, R6.angle(x,y,xint1,yint1))) {
						flag1 = true;
						dist1 = Math
								.sqrt(Math.pow(Math.abs(xint1 - r.x1()), 2) + Math.pow(Math.abs(yint1 - r.y1()), 2));
					}
				} catch (R6Exception e) {
					if (e.e != R6Error.no_outputs)
						throw new R6Exception(R6Error.friendship_is_magic);
				}
				try {
					xint2 = r.x(yint2);
//					System.out.println("angle 2:"+Math.toDegrees(R6.angle(x,y,xint2,yint2)));
					if (R6.inBetween(astart, aend, R6.angle(x,y,xint2,yint2))) {
						dist2 = Math
								.sqrt(Math.pow(Math.abs(xint2 - r.x1()), 2) + Math.pow(Math.abs(yint2 - r.y1()), 2));
						flag2 = true;
					}
				} catch (R6Exception e) {
					if (e.e != R6Error.no_outputs)
						throw new R6Exception(R6Error.friendship_is_magic);
				}

				if (flag1)
					if (flag2)
						if (dist1 < dist2)
							return yint1;
						else
							return yint2;
					else
						return yint1;
				else {
					if (flag2)
						return yint2;
					else
						throw new R6Exception(R6Error.no_intersections);
				}
			} else {
				throw new R6Exception(R6Error.no_intersections);
			}

		}

		double a = (r.m() * r.m()) + 1;
		double b = 2 * ((r.m() * r.b()) - (r.m() * y) - x);
		double c = (y * y) - (this.r * this.r) + (x * x) - (2 * r.b() * y) + (r.b() * r.b());

		double i = (b * b) - (4 * a * c);
		double xint1 = (-b + Math.sqrt(i)) / (2 * a);
		double xint2 = (-b - Math.sqrt(i)) / (2 * a);

		if (i > 0) {
			boolean flag1 = false;
			boolean flag2 = false;
			double dist1 = 0;
			double dist2 = 0;
			double yint1 = 0;
			double yint2 = 0;
			try {
				yint1 = r.y(xint1);
				if (R6.inBetween(astart, aend, R6.angle(x,y,xint1,yint1))) {
					flag1 = true;
					dist1 = Math.sqrt(Math.pow(Math.abs(xint1 - r.x1()), 2) + Math.pow(Math.abs(yint1 - r.y1()), 2));
				}
			} catch (R6Exception e) {
				if (e.e != R6Error.no_outputs)
					throw new R6Exception(R6Error.friendship_is_magic);
			}
			try {
				yint2 = r.y(xint2);
				if (R6.inBetween(astart, aend, R6.angle(x,y,xint2,yint2))) {
					dist2 = Math.sqrt(Math.pow(Math.abs(xint2 - r.x1()), 2) + Math.pow(Math.abs(yint2 - r.y1()), 2));
					flag2 = true;
				}
			} catch (R6Exception e) {
				if (e.e != R6Error.no_outputs)
					throw new R6Exception(R6Error.friendship_is_magic);
			}

			if (flag1)
				if (flag2)
					if (dist1 < dist2)
						return yint1;
					else
						return yint2;
				else
					return yint1;
			else {
				if (flag2)
					return yint2;
				else
					throw new R6Exception(R6Error.no_intersections);
			}

		} else {
			throw new R6Exception(R6Error.no_intersections);
		}
	}

}