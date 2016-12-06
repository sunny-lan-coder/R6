package r6.fram;

public class Ray {
	private final double m;
	private final double x1;
	private final double y1;
	private final boolean vertical;

	public Ray(int m, double x1, double y1) {
		this.m = m;
		this.x1 = x1;
		this.y1 = y1;
		vertical = false;
	}

	public Ray(int x1) {
		this.x1 = x1;
		vertical = true;
		
		//filler
		m=0;
		y1=0;
	}

	public double b(){
		try {
			return y(0);
		} catch (LineException e) {
			
		}
	}
	
	public double y(double x)throws LineException{
		if(vertical)
			if(x==x1)
				throw new LineException("Infinite solutions");
			else
				throw new LineException("No solution");
		return m*(x-x1)+y1;
	}

}
