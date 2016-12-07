package r6tk.g6;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class G6Engine {
	public final List<IRenderable> objects;
	private Graphics g;
	private double height;
	private double width;

	public G6Engine(double height, double width) {
		objects = new ArrayList<>();
		this.height = height;
		this.width = width;
	}

	public void render(Graphics g) {
		this.g = g;
		for (IRenderable object : objects)
			object.render(this);
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
