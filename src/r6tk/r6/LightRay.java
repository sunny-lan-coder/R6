package r6tk.r6;

import r6tk.r6.geom.Ray;

public class LightRay {
	
	public final Ray head;
	public LightRay bounce;

	public LightRay(Ray head) {
		this.head=head;
	}
}
