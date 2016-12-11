package r6tk.b6;

import r6tk.r6.ICollideable;
import r6tk.r6.R6;
import r6tk.r6.R6Exception;
import r6tk.r6.geom.Arc;
import r6tk.r6.geom.R6Error;
import r6tk.r6.geom.Ray;

public class ConcaveMirror extends Arc implements ICollideable {
	protected final double fx;
	protected final double fy;

	public ConcaveMirror(double x, double y, double c, double astart, double aend) {
		super(x, y, c, astart, aend);
		double middleAngle = ((aend - astart) / 2) + astart + R6.pi / 2;
		fx = Math.sin(middleAngle) * c / 2 + x;
		fy = Math.cos(middleAngle) * c / 2 + y;
	}

	public double fX() {
		return fx;
	}

	public double fY() {
		return fy;
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
		// System.out.println(xint+","+yint);

//		double opposite = Math.abs(r.y1() - y);
//		double adjacent = Math.abs(r.x1() - x);
		double normal = R6.angle(x, y, xint, yint);

//		double dist = Math.sqrt(opposite * opposite + adjacent * adjacent);
//		if (R6.ge(this.r, dist))
//			 { System.out.println("side 1");
//			normal += R6.pi;
//			 } else if (R6.e(this.r, dist)){
//			throw new R6Exception(R6Error.no_collision);
//		 } else{
//
//			 System.out.println("side 2");
//		 }

		double incidentRayAngle = R6.angle(xint, yint, r.x1(), r.y1());

//		if ((R6.g(normal , R6.pi/2) && R6.l(normal , R6.pi/2+R6.pi)))
			incidentRayAngle += R6.pi;

		double incidentAngle = Math.abs(normal - incidentRayAngle);
		if (R6.e(incidentAngle, R6.pi/2))
			throw new R6Exception(R6Error.no_collision);

		if (incidentRayAngle > normal)
			incidentAngle = -incidentAngle;

		double reflectedRayAngle = incidentAngle + normal;

		// reflectedRayAngle = R6.normalizeAngle(reflectedRayAngle);
		if (reflectedRayAngle < 0)
			reflectedRayAngle += R6.pi * 2;
		if (reflectedRayAngle > R6.pi * 2)
			reflectedRayAngle -= R6.pi * 2;
		
		boolean reflectedSide=(reflectedRayAngle >= R6.pi / 2 + R6.pi && reflectedRayAngle < R6.pi * 2)
				|| (reflectedRayAngle >= 0 && reflectedRayAngle <= R6.pi / 2);

//		System.out.println("incident ray angle:" + Math.toDegrees(incidentRayAngle));
//		System.out.println("normal angle:" + Math.toDegrees(normal));
//		System.out.println("incident angle:" + Math.toDegrees(incidentAngle));
//		System.out.println("reflected ray angle:" + Math.toDegrees(reflectedRayAngle));
//		System.out.println("slope:" + Math.tan(reflectedRayAngle));
//		System.out
//				.println("reflected side:" + reflectedSide);

		if(R6.e(reflectedRayAngle,R6.pi/2+R6.pi)){
//			System.out.println("vertical up");
			return new Ray(xint, yint, false);
		}
		
		if(R6.e(reflectedRayAngle,R6.pi/2)){
//			System.out.println("vertical down");
			return new Ray(xint, yint, true);
		}
		
		return new Ray(Math.tan(reflectedRayAngle), xint, yint,
			reflectedSide);

	}
}
