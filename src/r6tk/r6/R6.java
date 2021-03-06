package r6tk.r6;

import java.util.ArrayList;
import java.util.List;

import r6tk.r6.geom.R6Error;

public class R6 {
	List<LightRay> rays;
	List<ICollideable> transforms;
	List<IR6Listener> listeners;
	public final List<Object> objects;
	public static final double epilison = 0.00000001;
	public static final double pi = Math.PI;

	public static boolean e(double a, double b) {
		return Math.abs(a - b) < epilison;
	}

	public static boolean g(double a, double b) {
		return a > b && !e(a, b);
	}

	public static boolean l(double a, double b) {
		return a < b && !e(a, b);
	}

	public static boolean ge(double a, double b) {
		return g(a, b) || e(a, b);
	}

	public static boolean le(double a, double b) {
		return l(a, b) || e(a, b);
	}

	public static final double normalizeAngle(double angle) {
		return Math.atan2(Math.sin(angle), Math.cos(angle));
	}

	public static double ray_m(double x1, double y1, double x2, double y2) {
		return (y2 - y1) / (x2 - x1);
	}

	public static boolean ray_pointsPositive(double x1, double y1, double x2, double y2) throws R6Exception {
		if (e(x2, x1))
			if (e(y2, y1))
				throw new R6Exception(R6Error.friendship_is_magic);
			else
				return g(y2, y1);
		return g(x2, x1);
	}

	public static final double angle(double x1, double y1, double x2, double y2) {
		double opposite = y1 - y2;
		double adjacent = x1 - x2;
		double angle = Math.atan(opposite / adjacent);
		if (adjacent < 0)
			angle += R6.pi;

		if (angle < 0)
			angle += R6.pi * 2;
		
		if(angle > R6.pi*2)
			angle -= R6.pi * 2;

		return angle;
	}

	public static final boolean inBetween(double start, double end, double a) {
		end -= start;
		a -= start;
		if (end < 0)
			end += R6.pi * 2;
		if (a < 0)
			a += R6.pi * 2;
		return a < end;
		
		
	}
//	 public static final boolean inBetween(double max, double max, double targetAngle)
//	 {
//	     double normalisedMin = min > 0 ? min : 2 * Math.PI + min;
//	     double normalisedMax = max > 0 ? max : 2 * Math.PI + max;
//	     double normalisedTarget = targetAngle > 0 ? targetAngle : 2 * Math.PI + targetAngle;
//
//	     return normalisedMin <= normalisedTarget && normalisedTarget <= normalisedMax;
//	 }

	public R6() {
		transforms = new ArrayList<>();
		rays = new ArrayList<>();
		objects = new ArrayList<>();
		listeners = new ArrayList<>();
	}

	public void add(LightRay ray) throws R6Exception {
		rays.add(ray);
		objects.add(ray);
		update();
	}

	public void add(ICollideable transform) throws R6Exception {
		transforms.add(transform);
		objects.add(transform);
		update();
	}

	public void add(IR6Listener listener) {
		listeners.add(listener);
	}

	public void remove(LightRay ray) throws R6Exception {
		rays.remove(ray);
		objects.remove(ray);
		update();
	}

	public void remove(ICollideable transform) throws R6Exception {
		transforms.remove(transform);
		objects.remove(transform);
		update();
	}

	public void remove(IR6Listener listener) {
		listeners.remove(listener);
	}

	public LightRay findReflection(LightRay ray) throws R6Exception {
		ICollideable min = null;
		double mindist = -1;
		for (ICollideable object : transforms) {
			double xint;
			double yint;
			try {
				xint = object.getXInt(ray.head);
				yint = object.getYInt(ray.head);
			} catch (R6Exception e) {
				if (e.e == R6Error.no_intersections)
					continue;
				else
					throw new R6Exception(R6Error.friendship_is_magic, e);
			}
			double dist = Math
					.sqrt(Math.pow(Math.abs(ray.head.x1() - xint), 2) + Math.pow(Math.abs(ray.head.y1() - yint), 2));
			if (e(dist , 0))
				continue;
			if (min == null) {
				min = object;
				mindist = dist;
			} else {
				if (dist < mindist) {
					min = object;
					mindist = dist;
				}
			}
		}
		if (mindist == -1)
			throw new R6Exception(R6Error.no_collision);
		return new LightRay(min.applyTransformation(ray.head));
	}

	public void trace(LightRay ray) throws R6Exception {
		LightRay currentRay = ray;
		outer: while (true) {
			LightRay nextRay;
			try {
				nextRay = findReflection(currentRay);
			} catch (R6Exception e) {
				if (e.e == R6Error.no_collision) {
					break outer;
				} else
					throw new R6Exception(R6Error.friendship_is_magic, e);
			}
			currentRay.bounce = nextRay;
			currentRay = nextRay;
		}
	}

	public void update() throws R6Exception {
		for (LightRay ray : rays) {
			trace(ray);
		}

		for (IR6Listener listener : listeners)
			listener.update();
	}
}
