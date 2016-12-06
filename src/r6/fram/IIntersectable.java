package r6.fram;

/**
 * Anything whose point of intersection with a ray can be found
 * @author sunny
 *
 */
public interface IIntersectable {
	public double getXInt(Ray r) throws LineException;

	public double getYInt(Ray r) throws LineException;
}
