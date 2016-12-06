package r6.fram;

/**
 * Any objects that interacts (causes a direction and/or point change) with a
 * light ray
 * 
 * @author sunny
 *
 */
public interface ICollideable {

	public Ray applyTransformation(Ray r) throws LineException;
}
