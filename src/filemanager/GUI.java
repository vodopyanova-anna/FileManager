package filemanager;

import javax.swing.*;
import java.awt.*;

public class GUI{
	private JComboBox<String> gcb, gcb2;
	private JButton helpButton, createFolderButton, copyButton, renameButton, deleteButton, terminalButton, exitButton;
	private JTable table, table2;
	private JScrollPane scroll, scroll2;
	private JLabel labelDisk, labelDisk2, labelMemory, labelMemory2, labelCommandLine;
	private JTextField commandLine;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;	
	private int frameSizeX = 800;
	private int frameSizeY = 600;
	private int countButton = 7;
	public void makeGUI(){ 		
        JFrame frame = new JFrame("File Manager");    
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        frame.setLayout(gbl);
        frame.setSize(frameSizeX, frameSizeY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        gbc.weightx = 0.5;
        gbc.weighty = 0.5; 
        
        gbc.anchor = GridBagConstraints.WEST;        
        gbc.gridx = 0;
        gbc.gridy = 0;  
        String disks [] = {"c", "d", "flash"};
        gcb = new JComboBox<String> (disks);         
        frame.add(gcb, gbc); 
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 5;
        labelDisk = new JLabel ("[work]");
        frame.add(labelDisk, gbc);  
        
        gbc.gridx = 2;
        gbc.gridy = 0;        
        labelMemory = new JLabel ("100 000 000 �� �������� �� 250 000 000 ��");
        frame.add(labelMemory, gbc);  
        
        gbc.gridx = 3;
        gbc.gridy = 0; 
        gbc.ipadx = 0;
        gcb2 = new JComboBox<String> (disks);
        frame.add(gcb2, gbc); 
        
        gbc.gridx = 4;
        gbc.gridy = 0; 
        gbc.ipadx = 5;
        labelDisk2 = new JLabel ("[reserv]");
        frame.add(labelDisk2, gbc);  
        
        gbc.gridx = 5;
        gbc.gridy = 0;        
        labelMemory2 = new JLabel ("125 545 000 �� �������� �� 454 545 000 ��");
        frame.add(labelMemory2, gbc); 
        
        gbc.gridx = 0;
        gbc.gridy = 1;        
        gbc.ipadx = 380;
        gbc.ipady = 460; 
        gbc.gridwidth = 3;
        String[] colHeads = {"Name", "Extension", "Size"};
        Object [][] data = {
        		{"My folder", "", "1234"},
        		{"My folder", "", "12345"},
        		{"My file", "txt", "146"},
        		{"My file2", "doc", "1234"},
        		{"My photo", "jpeg", "454545"},
        };
        table = new JTable(data, colHeads);
        scroll = new JScrollPane(table);        
        frame.add(scroll, gbc); 
		
        gbc.gridx = 3;
        gbc.gridy = 1;
        Object [][] data2 = {
        		{"folder", "", "1234"},
        		{"folder", "", "12345"},
        		{"file", "txt", "146"},        		
        };
        table2 = new JTable(data2, colHeads);
        scroll2 = new JScrollPane(table2);       
        frame.add(scroll2, gbc);  
                    
        gbc.ipady = 15; 
        gbc.gridx = 0;
        gbc.gridy = 2; 
        labelCommandLine = new JLabel("C:\\>");
        frame.add(labelCommandLine, gbc);
        
        commandLine = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2; 
        gbc.gridwidth = 8;  
        gbc.ipadx = 724;
        commandLine = new JTextField();
        frame.add(commandLine, gbc);
        
        helpButton = new JButton("F1-Help");
        createFolderButton = new JButton("F2-New folder");        
        copyButton = new JButton("F5-Copy");
        renameButton = new JButton("F6-Rename");        
        deleteButton = new JButton("F8-Delete");        
        terminalButton = new JButton("F9-Terminal");        
        exitButton = new JButton("F10-Exit");
        
        JPanel buttonPanel = new JPanel();  
        buttonPanel.setLayout(new GridLayout(1, countButton));
        buttonPanel.add(helpButton);        
        buttonPanel.add(createFolderButton);        
        buttonPanel.add(copyButton);        
        buttonPanel.add(renameButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(terminalButton);
        buttonPanel.add(exitButton);
        gbc.gridx = 0;
        gbc.gridy = 3;        
        gbc.gridwidth = GridBagConstraints.REMAINDER;        
        frame.add(buttonPanel, gbc); 
        
        frame.setResizable(false);
        frame.setVisible(true);        
	} 
    
	
    public static void main(String[] args) {
            //test push
      /*	SwingUtilities.invokeLater(new Runnable(){
       		public void run(){
       			GUI fmGUI = new GUI();    
       			fmGUI.makeGUI();
       		}
       	});*/
            GUI2 GUI_2 = new GUI2();
            GUI_2.Test();


   	}

}

