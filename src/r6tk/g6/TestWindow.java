package r6tk.g6;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

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

	private G6Engine engine=new G6Engine(450, 300);
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
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
		
		engine=new G6Engine(panel.getHeight(), panel.getWidth());
		
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
	}

}
