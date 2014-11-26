package filemanager;

import javax.swing.*;

public class Main {
	JLabel label;
	Main(){    
        JFrame frame = new JFrame("File Manager");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        frame.setVisible(true);
	}
        public static void main(String[] args) {
        	SwingUtilities.invokeLater(new Runnable(){
        		public void run(){
        			new Main();
        		}
        	});
    }
}
