package r6tk.g6.b6;

import java.awt.Color;

import r6tk.b6.ConcaveMirror;
import r6tk.g6.G6;
import r6tk.g6.IRenderable;
import r6tk.r6.R6;

public class RenderableConcaveMirror extends ConcaveMirror implements IRenderable {

	private final Color col;

	public RenderableConcaveMirror(double x, double y, double c, double astart, double aend, Color color) {
		super(x, y, c, astart, aend);
		col = color;
	}

	@Override
	public void render(G6 engine) {
		engine.getGfx().setColor(col);
		engine.getGfx().fillRect((int)x-1, (int)y-1, 3, 3);
//		engine.getGfx().fillRect((int)fx, (int)fy, 3, 3);
		engine.getGfx().drawArc((int) (x() - r()), (int) (y() - r()), (int) r() * 2, (int) r() * 2,
				(int) Math.toDegrees(aend() - R6.pi), (int) Math.toDegrees(aend() - astart()));
	}

}
