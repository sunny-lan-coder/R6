package r6tk.g6.b6;

import java.awt.Color;

import r6tk.b6.ConcaveMirror;
import r6tk.g6.G6;
import r6tk.g6.IRenderable;

public class RenderableConcaveMirror extends ConcaveMirror implements IRenderable {
	
	private final Color col;

	public RenderableConcaveMirror(double x, double y, double c, double astart, double aend, boolean major, Color color) {
		super(x, y, c, astart, aend, major);
	col=color;
	}

	@Override
	public void render(G6 engine) {
		
	}

}
