package r6tk.r6.geom;

import r6tk.r6.R6Exception;

/**
 * Anything whose point of intersection with a ray can be found
 * @author sunny
 *
 */
public interface IIntersectable {
	public double getXInt(Ray r) throws R6Exception;

	public double getYInt(Ray r) throws R6Exception;
}
