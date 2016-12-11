package r6tk.r6.testing;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import r6tk.g6.G6;
import r6tk.g6.b6.RenderableLightRay;
import r6tk.g6.b6.RenderablePlaneMirror;
import r6tk.r6.IR6Listener;
import r6tk.r6.R6;
import r6tk.r6.R6Exception;
import r6tk.r6.geom.Arc;
import r6tk.r6.geom.R6Error;
import r6tk.r6.geom.Ray;

import java.awt.BorderLayout;
import java.awt.Color;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Arc myarc = new Arc(120, 300, 100, 0, R6.pi);
		Ray r = new Ray(3,50, 100, true);

		panel = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6677520948178100771L;

			@Override
			public void paint(Graphics g) {
				g.drawArc((int) (myarc.x()-myarc.r()), (int) (myarc.y()-myarc.r()), (int) myarc.r() * 2, (int) myarc.r() * 2,
						(int) Math.toDegrees(myarc.aend()-R6.pi/2), (int) Math.toDegrees(myarc.aend() - myarc.astart()));
				render(g, r);
				double xint=0;
				double yint=0;
				try {
					xint = myarc.getXInt(r);
					yint = myarc.getYInt(r);
					g.fillRect((int) xint , (int) yint, 3, 3);
				} catch (R6Exception e) {
				
				}
			}
		};

		panel.setBackground(Color.black);

		frame.getContentPane().add(panel, BorderLayout.CENTER);

	}

	static void render(Graphics g, Ray head) {
		double xend = -1;
		double yend = -1;
		double xstart = head.x1();
		double ystart = head.y1();

		g.fillRect((int) xstart - 1, (int) ystart - 1, 3, 3);

		// System.out.println("side");

		// left side
		try {
			double ytmp = head.y(0);

			if (ytmp >= 0 && ytmp <= panel.getHeight()) {
				// System.out.println("left");
				yend = ytmp;
				xend = 0;
			}
			// System.out.println(ytmp);
		} catch (R6Exception e) {
			if (e.e != R6Error.no_outputs)
				e.printStackTrace();
		}

		// right side
		try {
			double ytmp = head.y(panel.getWidth());
			if (ytmp >= 0 && ytmp <= panel.getHeight()) {
				yend = ytmp;
				xend = panel.getWidth();
				// System.out.println("right");
			}
			// System.out.println(ytmp);
		} catch (R6Exception e) {
			if (e.e != R6Error.no_outputs)
				e.printStackTrace();
		}

		// top side
		try {
			double xtmp = head.x(0);
			if (xtmp >= 0 && xtmp <= panel.getWidth()) {
				xend = xtmp;
				yend = 0;

				// System.out.println("top");
			}
			// System.out.println(xtmp);
		} catch (R6Exception e) {
			if (e.e != R6Error.no_outputs)
				e.printStackTrace();
		}

		// bottom side
		try {
			double xtmp = head.x(panel.getHeight());
			if (xtmp >= 0 && xtmp <= panel.getWidth()) {
				xend = xtmp;
				yend = panel.getHeight();
				// System.out.println("bottom");
			}
			// System.out.println(xtmp);
		} catch (R6Exception e) {
			if (e.e != R6Error.no_outputs)
				e.printStackTrace();
		}

		g.drawLine((int) xstart, (int) ystart, (int) xend, (int) yend);
	}

}
