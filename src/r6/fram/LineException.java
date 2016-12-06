package r6.fram;

public class LineException extends Exception {

	public final Error e;
	public final Exception source;

	/**
	 * 
	 */
	private static final long serialVersionUID = -140111482226803951L;

	public LineException(Error e, Exception source) {
		this.source = source;
		this.e = e;
	}

	public LineException(Error e) {
		this.e = e;
		this.source = null;
	}

}
