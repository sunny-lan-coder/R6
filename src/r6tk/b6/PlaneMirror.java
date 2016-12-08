package r6tk.b6;

import r6tk.r6.ICollideable;
import r6tk.r6.R6Exception;
import r6tk.r6.geom.Line;
import r6tk.r6.geom.R6Error;
import r6tk.r6.geom.Ray;

public class PlaneMirror extends Line implements ICollideable {

	public PlaneMirror(double x1, double y1, double y2) {
		super(x1, y1, y2);
	}

	public PlaneMirror(double m, double x1, double y1, double x2) throws R6Exception {
		super(m, x1, y1, x2);
	}

	@Override
	public Ray applyTransformation(Ray r) throws R6Exception {
		double xint;
		double yint;
		try {
			yint = getYInt(r);
			xint = getXInt(r);
		} catch (R6Exception e) {
			if (e.e == R6Error.no_intersections)
				throw new R6Exception(R6Error.no_collision);
			else
				throw new R6Exception(R6Error.friendship_is_magic);
		}

		return new Ray(-r.m(), xint, yint, !r.pointsPositive());
	}

}