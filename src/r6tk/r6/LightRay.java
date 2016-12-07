package r6tk.r6;

import java.util.ArrayList;
import java.util.List;

import r6tk.r6.geom.Ray;

public class LightRay {
	public final List<Ray> rays;
	public final List<ICollideable> bounces;
	public Ray head;

	public LightRay(Ray head) {
		rays = new ArrayList<>();
		bounces = new ArrayList<>();
		this.head = head;
		rays.add(head);
	}
}
