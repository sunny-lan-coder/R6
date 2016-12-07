package r6.fram.testing;

import r6.fram.LineException;
import r6.fram.Ray;

public class Main {

	public static void main(String[] args) throws LineException {
		Ray r1=new Ray(1, 0, 0, true);
		Ray r2=new Ray(1, 0, 0, false);
		
		System.out.println(r1.getXInt(r2));
		System.out.println(r1.getYInt(r2));
	}

}
