package r6tk.g6;

import java.awt.Graphics;

import r6tk.r6.R6;

public class G6 {
	private Graphics g;
	private double height;
	private double width;
	private R6 engine;

	public G6(R6 engine,  double width,double height) {
		this.height = height;
		this.engine = engine;
		this.width = width;
	}

	public void render(Graphics g) {
		this.g = g;
		for (Object o : engine.objects) {
			if (o instanceof IRenderable) {
				((IRenderable) o).render(this);
			}
		}
	}

	public void setHeight(double height, double width) {
		this.height = height;
		this.width = width;
	}

	public double height() {
		return height;
	}

	public double width() {
		return width;
	}

	public Graphics getGfx() {
		return g;
	}
}
