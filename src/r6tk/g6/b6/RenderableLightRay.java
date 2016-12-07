package r6tk.g6.b6;

import java.awt.Color;

import r6tk.g6.G6;
import r6tk.g6.IRenderable;
import r6tk.r6.LightRay;
import r6tk.r6.R6Exception;
import r6tk.r6.geom.R6Error;
import r6tk.r6.geom.Ray;

public class RenderableLightRay extends LightRay implements IRenderable {

	final Color c;

	public RenderableLightRay(Ray head, Color c) {
		super(head);
		this.c = c;
	}

	@Override
	public void render(G6 engine) {

		engine.getGfx().setColor(c);
		LightRay subray = this;
		while (subray != null) {
			Ray head = subray.head;

			double xend = -1;
			double yend = -1;
			double xstart = head.x1();
			double ystart = head.y1();

			if (bounce == null) {

				// left side
				try {
					double ytmp = head.y(0);
					if (ytmp >= 0 && ytmp <= engine.height())
						yend = ytmp;
				} catch (R6Exception e) {
					if (e.e != R6Error.not_on_line)
						e.printStackTrace();
				}

				// right side
				try {
					double ytmp = head.y(engine.width());
					if (ytmp >= 0 && ytmp <= engine.height())
						yend = ytmp;
				} catch (R6Exception e) {
					if (e.e != R6Error.not_on_line)
						e.printStackTrace();
				}

				// left side
				try {
					double xtmp = head.x(0);
					if (xtmp >= 0 && xtmp <= engine.width())
						xend = xtmp;
				} catch (R6Exception e) {
					if (e.e != R6Error.not_on_line)
						e.printStackTrace();
				}

				// right side
				try {
					double xtmp = head.x(engine.height());
					if (xtmp >= 0 && xtmp <= engine.width())
						xend = xtmp;
				} catch (R6Exception e) {
					if (e.e != R6Error.not_on_line)
						e.printStackTrace();
				}

			} else {
				xend = bounce.head.x1();
				yend = bounce.head.y1();

			}
			System.out.println(yend);
			engine.getGfx().drawLine((int) xstart, (int) ystart, (int) xend, (int) yend);
			subray = subray.bounce;
		}
	}

}
