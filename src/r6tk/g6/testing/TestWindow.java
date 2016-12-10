package r6tk.g6.testing;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import r6tk.g6.G6;
import r6tk.g6.b6.RenderableConcaveMirror;
import r6tk.g6.b6.RenderableLightRay;
import r6tk.g6.b6.RenderablePlaneMirror;
import r6tk.r6.IR6Listener;
import r6tk.r6.R6;
import r6tk.r6.R6Exception;
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
//					RenderableLightRay r;
//					for(int i=0;i<360;i++){
//						r=new RenderableLightRay(new Ray(Math.tan(i), 100, 200, false), Color.RED);
//						six.add(r);
//						panel.repaint();
//						try {
//							Thread.sleep(100);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//						six.remove(r);
//					}
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
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		six=new R6();

		engine=new G6(six,640, 480);
		
		 panel = new JPanel(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 6677520948178100771L;
			
			@Override
			public void paint(Graphics g){
			
				engine.render(g);
			}
		};
		
		
		panel.setBackground(Color.black);
		
		six.add(new IR6Listener() {
			
			@Override
			public void update() {
				panel.repaint();
			}
		});

		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		try {
//		for(int i=280;i<=360;i+=10){
//			double a=-10;
//			six.add(new RenderableLightRay(new Ray(1,50.0, 150.0,true), Color.RED));
//		}
						six.add(new RenderablePlaneMirror(-0.5,0,150,200,  Color.blue));
						six.add(new RenderablePlaneMirror(0,0,300,400, Color.GREEN));
						six.add(new RenderablePlaneMirror(200, 0, 400, Color.blue));
						
//						for(int i=0;i<360;i+=10){
							RenderableLightRay r=new RenderableLightRay(new Ray(Math.tan(100), 300, 200, false), Color.RED);
				six.add(r);
							 r=new RenderableLightRay(new Ray(Math.tan(100), 110, 200, true), Color.RED);
							six.add(r);
//						}
			
		} catch (R6Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
