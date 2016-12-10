package r6tk.r6.geom;

import r6tk.r6.R6;
import r6tk.r6.R6Exception;

public class Arc implements IIntersectable {

	protected final double x;
	protected final double y;
	protected final double r;
	protected final double astart;
	protected final double aend;
	protected final boolean major;

	public Arc(double x, double y, double c, double astart, double aend, boolean major) {
		this.x = x;
		this.y = y;
		this.r = c;
		this.astart = astart;
		this.aend = aend;
		this.major = major;
	}

	@Override
	public double getXInt(Ray r) throws R6Exception {
		if (r.vertical()) {
			double a = r.m() * r.m();
			double b = 2 * r.b() * r.m() - y * r.m();
			double c = x * x + y * y - 2 * y * r.b() + r.b() * r.b() - 2 * x * r.x1() + r.x1() * r.x1();

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
					double opposite = Math.abs(yint1 - y);
					double adjacent = Math.abs(xint1 - x);
					double angle = Math.atan(opposite / adjacent);
					if (R6.inBetween(astart, aend, angle)) {
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
					double opposite = Math.abs(yint2 - y);
					double adjacent = Math.abs(xint2 - x);
					double angle = Math.atan(opposite / adjacent);
					if (R6.inBetween(astart, aend, angle)) {
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
						if (dist1 > dist2)
							return xint1;
						else
							return xint2;
					else
						return xint1;
				else
					return xint2;
			} else {
				throw new R6Exception(R6Error.no_intersections);
			}

		}

		double a = r.m() * r.m() + 1;
		double b = 2 * (r.m() * r.b() - r.m() * y - x);
		double c = y * y - this.r * this.r + x * x - 2 * r.b() * y + r.b() * r.b();

		double i = b * b - 4 * a * c;
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
				double opposite = Math.abs(yint1 - y);
				double adjacent = Math.abs(xint1 - x);
				double angle = Math.atan(opposite / adjacent);
				if (R6.inBetween(astart, aend, angle)) {
					flag1 = true;
					dist1 = Math.sqrt(Math.pow(Math.abs(xint1 - r.x1()), 2) + Math.pow(Math.abs(yint1 - r.y1()), 2));
				}
			} catch (R6Exception e) {
				if (e.e != R6Error.no_outputs)
					throw new R6Exception(R6Error.friendship_is_magic);
			}
			try {
				yint2 = r.y(xint2);
				double opposite = Math.abs(yint2 - y);
				double adjacent = Math.abs(xint2 - x);
				double angle = Math.atan(opposite / adjacent);
				if (R6.inBetween(astart, aend, angle)) {
					dist2 = Math.sqrt(Math.pow(Math.abs(xint2 - r.x1()), 2) + Math.pow(Math.abs(yint2 - r.y1()), 2));
					flag2 = true;
				}
			} catch (R6Exception e) {
				if (e.e != R6Error.no_outputs)
					throw new R6Exception(R6Error.friendship_is_magic);
			}

			if (flag1)
				if (flag2)
					if (dist1 > dist2)
						return yint1;
					else
						return yint2;
				else
					return yint1;
			else
				return yint2;

		} else {
			throw new R6Exception(R6Error.no_intersections);
		}
	}

	@Override
	public double getYInt(Ray r) throws R6Exception {

		if (r.vertical()) {
			double a = r.m() * r.m();
			double b = 2 * r.b() * r.m() - y * r.m();
			double c = x * x + y * y - 2 * y * r.b() + r.b() * r.b() - 2 * x * r.x1() + r.x1() * r.x1();

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
					double opposite = Math.abs(yint1 - y);
					double adjacent = Math.abs(xint1 - x);
					double angle = Math.atan(opposite / adjacent);
					if (R6.inBetween(astart, aend, angle)) {
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
					double opposite = Math.abs(yint2 - y);
					double adjacent = Math.abs(xint2 - x);
					double angle = Math.atan(opposite / adjacent);
					if (R6.inBetween(astart, aend, angle)) {
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
						if (dist1 > dist2)
							return yint1;
						else
							return yint2;
					else
						return yint1;
				else
					return yint2;
			} else {
				throw new R6Exception(R6Error.no_intersections);
			}

		}

		double a = r.m() * r.m() + 1;
		double b = 2 * (r.m() * r.b() - r.m() * y - x);
		double c = y * y - this.r * this.r + x * x - 2 * r.b() * y + r.b() * r.b();

		double i = b * b - 4 * a * c;
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
				double opposite = Math.abs(yint1 - y);
				double adjacent = Math.abs(xint1 - x);
				double angle = Math.atan(opposite / adjacent);
				if (R6.inBetween(astart, aend, angle)) {
					flag1 = true;
					dist1 = Math.sqrt(Math.pow(Math.abs(xint1 - r.x1()), 2) + Math.pow(Math.abs(yint1 - r.y1()), 2));
				}
			} catch (R6Exception e) {
				if (e.e != R6Error.no_outputs)
					throw new R6Exception(R6Error.friendship_is_magic);
			}
			try {
				yint2 = r.y(xint2);
				double opposite = Math.abs(yint2 - y);
				double adjacent = Math.abs(xint2 - x);
				double angle = Math.atan(opposite / adjacent);
				if (R6.inBetween(astart, aend, angle)) {
					dist2 = Math.sqrt(Math.pow(Math.abs(xint2 - r.x1()), 2) + Math.pow(Math.abs(yint2 - r.y1()), 2));
					flag2 = true;
				}
			} catch (R6Exception e) {
				if (e.e != R6Error.no_outputs)
					throw new R6Exception(R6Error.friendship_is_magic);
			}

			if (flag1)
				if (flag2)
					if (dist1 > dist2)
						return yint1;
					else
						return yint2;
				else
					return yint1;
			else
				return yint2;

		} else {
			throw new R6Exception(R6Error.no_intersections);
		}
	}

}
