package r6tk.r6.geom;

import java.io.PrintStream;
import java.io.PrintWriter;

import r6tk.r6.Error;

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

	@Override
	public void printStackTrace() {
		System.err.println("LineError exception: " + e.toString());
		super.printStackTrace();
	}

	@Override
	public void printStackTrace(PrintWriter w) {
		System.err.println("LineError exception: " + e.toString());
		super.printStackTrace(w);
	}

	@Override
	public void printStackTrace(PrintStream s) {
		System.err.println("LineError exception: " + e.toString());
		super.printStackTrace(s);
	}

}
