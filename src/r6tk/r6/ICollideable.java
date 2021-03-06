package r6tk.r6;

import r6tk.r6.geom.IIntersectable;
import r6tk.r6.geom.Ray;

/**
 * Any objects that interacts (causes a direction and/or point change) with a
 * light ray
 * 
 * @author sunny
 *
 */
public interface ICollideable extends IIntersectable{

	public Ray applyTransformation(Ray r) throws R6Exception;
}
