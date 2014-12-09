package filemanager;

import javax.swing.*;
import java.awt.*;

public class GUI{
	private JComboBox<String> gcb, gcb2;
	private JButton helpButton, createFolderButton, copyButton, renameButton, deleteButton, terminalButton, exitButton;
	private JTable table, table2;
	private JScrollPane scroll, scroll2;	
	private GridBagLayout gbl;
	private GridBagConstraints gbc;	
	GUI(){ 		
        JFrame frame = new JFrame("File Manager");    
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        frame.setLayout(gbl);
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;  
        String disks [] = {"c", "d", "flash"};
        gcb = new JComboBox<String> (disks);         
        frame.add(gcb, gbc); 
        
        gbc.gridx = 1;
        gbc.gridy = 0;        
        gcb2 = new JComboBox<String> (disks);
        frame.add(gcb2, gbc);  
        
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; 
        gbc.gridx = 0;
        gbc.gridy = 1;        
        gbc.ipadx = 420;
        gbc.ipady = 500;               
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
		
        gbc.gridx = 1;
        gbc.gridy = 1;
        Object [][] data2 = {
        		{"folder", "", "1234"},
        		{"folder", "", "12345"},
        		{"file", "txt", "146"},
        		{"file2", "doc", "1234"},
        		{"photo", "jpeg", "454545"},
        		{"folder", "", "1234"},
        		{"folder", "", "12345"},
        		{"file", "txt", "146"},
        		{"file2", "doc", "1234"},
        		{"photo", "jpeg", "454545"},
        		{"folder", "", "1234"},
        		{"folder", "", "12345"},
        		{"file", "txt", "146"},
        		{"file2", "doc", "1234"},
        		{"photo", "jpeg", "454545"},
        		{"folder", "", "1234"},
        		{"folder", "", "12345"},
        		{"file", "txt", "146"},
        		{"file2", "doc", "1234"},
        		{"photo", "jpeg", "454545"},
        		{"folder", "", "1234"},
        		{"folder", "", "12345"},
        		{"file", "txt", "146"},
        		{"file2", "doc", "1234"},
        		{"photo", "jpeg", "454545"},
        		{"folder", "", "1234"},
        		{"folder", "", "12345"},
        		{"file", "txt", "146"},
        		{"file2", "doc", "1234"},
        		{"photo", "jpeg", "454545"},
        		{"folder", "", "1234"},
        		{"folder", "", "12345"},
        		{"file", "txt", "146"},
        		{"file2", "doc", "1234"},
        		{"photo", "jpeg", "454545"},
        		{"folder", "", "1234"},
        		{"folder", "", "12345"},
        		{"file", "txt", "146"},
        		{"file2", "doc", "1234"},
        		{"photo", "jpeg", "454545"},
        };
        table2 = new JTable(data2, colHeads);
        scroll2 = new JScrollPane(table2);       
        frame.add(scroll2, gbc);  
                        
        helpButton = new JButton("F1 - Help");
        helpButton.setActionCommand("helpButton was pressed");         
        
        createFolderButton = new JButton("F2 - New folder");
        createFolderButton.setActionCommand("createFolderButton was pressed");         
       
        copyButton = new JButton("F5 - Copy");
        copyButton.setActionCommand("copyButton was pressed");        
                
        renameButton = new JButton("F6 - Rename");
        renameButton.setActionCommand("renameButton was pressed");        
        
        deleteButton = new JButton("F8 - Delete");
        deleteButton.setActionCommand("deleteButton was pressed");        
       
        terminalButton = new JButton("F9 - Terminal");
        terminalButton.setActionCommand("terminalButton was pressed");        
        
        exitButton = new JButton("F10 - Exit");
        exitButton.setActionCommand("exitButton was pressed");
        
        JPanel buttonPanel = new JPanel();        
        buttonPanel.add(helpButton);        
        buttonPanel.add(createFolderButton);        
        buttonPanel.add(copyButton);        
        buttonPanel.add(renameButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(terminalButton);
        buttonPanel.add(exitButton);
        gbc.gridx = 0;
        gbc.gridy = 2;        
        gbc.ipady = 70;   
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.SOUTH;
        frame.add(buttonPanel, gbc);          
        frame.setVisible(true);     
	} 
    
	
    public static void main(String[] args) {
      	SwingUtilities.invokeLater(new Runnable(){
       		public void run(){
       			new GUI();    
       			
       		}
       	});
   	}

}

