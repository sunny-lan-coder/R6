package r6tk.r6.testing;

import r6tk.r6.R6Exception;
import r6tk.r6.geom.Line;
import r6tk.r6.geom.Ray;

public class Main {

	public static void main(String[] args) throws R6Exception {
		Ray r1=new Ray(0, 0, 0, true);
		Ray r2=new Ray(1, 1, 0,false);
		Line l1=new Line(5, 0, 10);
		
		System.out.println(l1.getXInt(r1));
		System.out.println(l1.getYInt(r1));
		
		System.out.println(Math.atan(Double.POSITIVE_INFINITY));
	}

}
