package r6tk.g6;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import r6tk.g6.b6.RenderableLightRay;
import r6tk.g6.b6.RenderablePlaneMirror;
import r6tk.r6.R6;
import r6tk.r6.R6Exception;
import r6tk.r6.geom.Ray;

import java.awt.BorderLayout;
import java.awt.Color;

public class TestWindow {

	private JFrame frame;

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

	private G6 engine;
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		R6 six=new R6();
		
		JPanel panel = new JPanel(){

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
		
		engine=new G6(six,panel.getHeight(), panel.getWidth());
		
		try {
			six.add(new RenderableLightRay(new Ray(1, 100, 100, true), Color.RED));
			six.add(new RenderablePlaneMirror( 50, 100, 0,  Color.blue));
			
		} catch (R6Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
	}

}
