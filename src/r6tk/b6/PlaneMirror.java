package r6tk.b6;

import r6tk.r6.ICollideable;
import r6tk.r6.R6;
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

		double incidentRayAngle = Math.atan(r.m());

		if (!r.pointsPositive())
			incidentRayAngle += R6.pi;

		double normal = Math.atan(m) + R6.pi / 2;

		if (!r.pointsPositive())
			normal += R6.pi;
		
		double incidentAngle = normal - incidentRayAngle;

		double reflectedRayAngle = incidentAngle * 2 + incidentRayAngle;

		System.out.println("incident ray angle:" + Math.toDegrees(incidentRayAngle));
		System.out.println("normal angle:" + Math.toDegrees(normal));
		System.out.println("incident angle:" + Math.toDegrees(incidentAngle));
		System.out.println("reflected ray angle:" + Math.toDegrees(reflectedRayAngle));

		if (!r.pointsPositive())
			reflectedRayAngle += R6.pi;

		return new Ray(Math.tan(reflectedRayAngle), xint, yint, reflectedRayAngle < R6.pi);
	}

}