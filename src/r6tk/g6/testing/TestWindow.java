package r6tk.g6.testing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

import r6tk.g6.G6;
import r6tk.g6.b6.RenderableConcaveMirror;
import r6tk.g6.b6.RenderableLightRay;
import r6tk.g6.b6.RenderablePlaneMirror;
import r6tk.r6.LightRay;
import r6tk.r6.R6;
import r6tk.r6.R6Exception;
import r6tk.r6.geom.Ray;

public class TestWindow {

	private JFrame frame;
	private static JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestWindow window = new TestWindow();
					window.frame.setVisible(true);
					// RenderableLightRay r;
					// for(int i=0;i<360;i++){
					// r=new RenderableLightRay(new Ray(Math.tan(i), 100, 200,
					// false), Color.RED);
					// six.add(r);
					// panel.repaint();
					// try {
					// Thread.sleep(100);
					// } catch (InterruptedException e) {
					// e.printStackTrace();
					// }
					// six.remove(r);
					// }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestWindow() {
		initialize();
	}

	private G6 engine;
	private static R6 six;
	private static LightRay r1;
	private static LightRay r2;
	private static LightRay r3;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		RenderableConcaveMirror mycon = new RenderableConcaveMirror(400, 300, 100, R6.pi, R6.pi * 2, Color.black);
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		six = new R6();

		engine = new G6(six, 640, 480);

		panel = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6677520948178100771L;

			@Override
			public void paint(Graphics g) {
				g.clearRect(0, 0, getWidth(), getHeight());
				g.drawString(panel.getMousePosition().toString(), 0, 10);

				g.drawString("" + Math.toDegrees(R6.angle(panel.getMousePosition().getX(),
						panel.getMousePosition().getY(), mycon.x(), mycon.y())), 0, getHeight() - 10);
				engine.render(g);
			}
		};
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				try {
					if (r2 != null) {
						six.remove(r1);
						six.remove(r2);
						six.remove(r3);
						r1 = null;
						r2 = null;
						r3 = null;
					}
					double x1 = panel.getMousePosition().getX();
					double y1 = panel.getMousePosition().getY();
					double x2 = mycon.fX();
					double y2 = mycon.fY();
					// System.out.println("m="+R6.ray_m(x1, y1, x2, y2));
					// System.out.println("ppap="+R6.ray_pointsPositive(x1, y1,
					// x2, y2));
					r1 = new RenderableLightRay(
							new Ray(R6.ray_m(x1, y1, x2, y2), x1, y1, R6.ray_pointsPositive(x1, y1, x2, y2)),
							Color.red);
					r2 = new RenderableLightRay(new Ray(x1, y1, true), Color.red);
					x2 = mycon.x();
					y2 = mycon.y();
					r3 = new RenderableLightRay(
							new Ray(R6.ray_m(x1, y1, x2, y2), x1, y1, R6.ray_pointsPositive(x1, y1, x2, y2)),
							Color.red);
					six.add(r1);
					six.add(r2);
//					six.add(r3);
				} catch (R6Exception e) {
					e.printStackTrace();
				}
				panel.repaint();
			}
		});

		panel.setBackground(Color.black);

		// six.add(new IR6Listener() {
		//
		// @Override
		// public void update() {
		// panel.repaint();
		// }
		// });

		frame.getContentPane().add(panel, BorderLayout.CENTER);

		try {
			// for(int i=280;i<=360;i+=10){
			// double a=-10;
			// six.add(new RenderableLightRay(new Ray(50, 150, true),
			// Color.RED));
			//// }
			six.add(new RenderablePlaneMirror(9, 10, 0, 50, Color.blue));
			// six.add(new RenderablePlaneMirror(0,0,300,400, Color.GREEN));
			// six.add(new RenderablePlaneMirror(200, 0, 400, Color.blue));
			//
			six.add(mycon);

		} catch (R6Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
