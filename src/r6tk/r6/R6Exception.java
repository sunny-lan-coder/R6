package r6tk.r6;

import java.io.PrintStream;
import java.io.PrintWriter;

import r6tk.r6.geom.R6Error;

public class R6Exception extends Exception {

	public final R6Error e;
	public final Exception source;

	/**
	 * 
	 */
	private static final long serialVersionUID = -140111482226803951L;

	public R6Exception(R6Error e, Exception source) {
		this.source = source;
		this.e = e;
	}

	public R6Exception(R6Error e) {
		this.e = e;
		this.source = null;
	}

	@Override
	public void printStackTrace() {
		System.err.println("LineError exception: " + e.toString() + ", subexception:");
		System.err.print(" ");
		if (source == null)
			System.err.println("none");
		else
			source.printStackTrace();
		super.printStackTrace();
	}

	@Override
	public void printStackTrace(PrintWriter w) {
		w.println("LineError exception: " + e.toString() + ", subexception:");
		w.print(" ");
		if (source == null)
			w.println("none");
		else
			source.printStackTrace(w);
		super.printStackTrace(w);
	}

	@Override
	public void printStackTrace(PrintStream s) {
		s.println("LineError exception: " + e.toString() + ", subexception:");
		s.print(" ");
		if (source == null)
			s.println("none");
		else
			source.printStackTrace(s);
		super.printStackTrace(s);
	}

}
