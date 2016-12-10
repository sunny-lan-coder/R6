package r6tk.b6;

import r6tk.r6.ICollideable;
import r6tk.r6.R6;
import r6tk.r6.R6Exception;
import r6tk.r6.geom.Arc;
import r6tk.r6.geom.R6Error;
import r6tk.r6.geom.Ray;

public class ConcaveMirror extends Arc implements ICollideable {
	public ConcaveMirror(double x, double y, double c, double astart, double aend) {
		super(x, y, c, astart, aend);
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
//		
//		System.out.println(xint+","+yint);

		double opposite = Math.abs(r.x1() - y);
		double adjacent = Math.abs(r.y1() - x);
		double normal = R6.angle(x, y, xint, yint);

		double dist = Math.sqrt(opposite * opposite + adjacent * adjacent);
		if (R6.g(this.r, dist))
			// { System.out.println("side 1");
			normal += R6.pi;
		else if (R6.e(this.r, dist))
			throw new R6Exception(R6Error.no_collision);
		// } else {
		//// System.out.println("side 2");
		// }

		double incidentRayAngle = r.angle();

		if (!(normal > 90 && normal < 270))
			incidentRayAngle += R6.pi;

		double incidentAngle = Math.abs(normal - incidentRayAngle);
		if(R6.e(incidentAngle, 90))
			throw new R6Exception(R6Error.no_collision);

		if (incidentRayAngle > normal)
			incidentAngle = -incidentAngle;

		double reflectedRayAngle = incidentAngle + normal;

		// reflectedRayAngle = R6.normalizeAngle(reflectedRayAngle);
		if (reflectedRayAngle < 0)
			reflectedRayAngle += R6.pi * 2;
		if (reflectedRayAngle > R6.pi * 2)
			reflectedRayAngle -= R6.pi * 2;

		// System.out.println("incident ray angle:" +
		// Math.toDegrees(incidentRayAngle));
//		 System.out.println("normal angle:" + Math.toDegrees(normal));
		// System.out.println("incident angle:" +
		// Math.toDegrees(incidentAngle));
		// System.out.println("reflected ray angle:" +
		// Math.toDegrees(reflectedRayAngle));
		// System.out.println("slope:" + Math.tan(reflectedRayAngle));
		// System.out
		// .println("reflected side:" + ((reflectedRayAngle >= R6.pi / 2 + R6.pi
		// && reflectedRayAngle < R6.pi * 2)
		// || (reflectedRayAngle >= 0 && reflectedRayAngle <= R6.pi / 2)));

		return new Ray(Math.tan(reflectedRayAngle), xint, yint,
				(reflectedRayAngle >= R6.pi / 2 + R6.pi && reflectedRayAngle < R6.pi * 2)
						|| (reflectedRayAngle >= 0 && reflectedRayAngle <= R6.pi / 2));

	}
}
