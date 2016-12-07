package r6tk.r6.testing;

import r6tk.r6.geom.LineException;
import r6tk.r6.geom.Ray;

public class Main {

	public static void main(String[] args) throws LineException {
		Ray r1=new Ray(1, 0, 0, true);
		Ray r2=new Ray(1, 0, 0, false);
		
		System.out.println(r1.getXInt(r2));
		System.out.println(r1.getYInt(r2));
	}

}
