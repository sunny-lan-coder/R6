package r6.fram;

public class LineException extends Exception {
	
	public enum Error{
		infinite_solutions,
		no_solutions
	}
	
	public final Error e;

	/**
	 * 
	 */
	private static final long serialVersionUID = -140111482226803951L;

	public LineException(String message, Error e) {
	
		super(message);
		this.e=e;
	}

}
