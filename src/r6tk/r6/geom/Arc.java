package r6tk.r6.geom;

import r6tk.r6.R6Exception;

public class Arc implements IIntersectable{
	
	protected final double x;
	protected final double y;
	protected final double r;
	protected final double a1;
	protected final double a2;
	
	public Arc(double x, double y, double c, double astart, double aend) {
		this.x=x;
		this.y=y;
		this.r=c;
		a1=Math.min(astart, aend);
		a2=Math.max(astart, aend);
	}
	
	@Override
	public double getXInt(Ray r) throws R6Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getYInt(Ray r) throws R6Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
