package r6tk.g6.b6;

import java.awt.Color;

import r6tk.b6.PlaneMirror;
import r6tk.g6.G6Engine;
import r6tk.g6.IRenderable;
import r6tk.r6.R6Exception;

public class RenderablePlaneMirror extends PlaneMirror implements IRenderable {

	private final Color c;

	public RenderablePlaneMirror(double m, double x1, double y1, double x2, Color c) throws R6Exception {
		super(m, x1, y1, x2);
		this.c = c;
	}

	public RenderablePlaneMirror(double x1, double y1, double y2, Color c) {
		super(x1, y1, y2);
		this.c = c;
	}

	@Override
	public void render(G6Engine engine) {
		engine.getGfx().setColor(c);
		engine.getGfx().drawLine((int) x1, (int) y1, (int) x2, (int) y2);
	}

}
