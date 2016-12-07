package r6.g6.testing;

import java.awt.Color;

import r6.fram.Ray;
import r6.g6.G6Engine;
import r6.g6.IRenderable;

public class TestRenderableRay extends Ray implements IRenderable{
	private final Color col;

	public TestRenderableRay(double x1, double y1, boolean pointsPositive, Color col) {
		super(x1, y1, pointsPositive);
		this.col=col;
	}

	public TestRenderableRay(double m, double x1, double y1, boolean pointsPositive, Color col) {
		super(m, x1, y1, pointsPositive);
		this.col=col;
	}

	@Override
	public void render(G6Engine engine) {
		
	} 

}
