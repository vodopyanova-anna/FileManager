//this is readable basics of code to reuse in mvc
package filemanager;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.util.*;

public class GUI{
	private JComboBox gcb, gcb2;
	private JButton helpButton, createFolderButton, copyButton, renameButton, deleteButton, terminalButton, exitButton;
	private JScrollPane scroll, scroll2;
		private JLabel labelDisk, labelDisk2, labelMemory, labelMemory2, labelCommandLine;
	private JTextField commandLine;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
    private JList tempchoice,tempchoice2;
	private int frameSizeX = 800;
	private int frameSizeY = 600;
	private int countButton = 7;


      // This inner class is needed to input icons + text in comboBoxes
        public class IconListRenderer
                extends DefaultListCellRenderer {

                private Map<Object, Icon> icons = null;

                public IconListRenderer(Map<Object, Icon> icons) {
                        this.icons = icons;
                }

                @Override
                public Component getListCellRendererComponent(
                        JList list, Object value, int index,
                        boolean isSelected, boolean cellHasFocus) {

                        // Get the renderer component from parent class

                        JLabel label =
                                (JLabel) super.getListCellRendererComponent(list,
                                        value, index, isSelected, cellHasFocus);

                        // Get icon to use for the list item value

                        Icon icon = icons.get(value);

                        // Set icon to display for value

                        label.setIcon(icon);
                        return label;
                }
        }
        /* Inner Class ends here*/

        // This method fills combobox with disk lettesr and icons
        public void fillingDisksCombos(JComboBox diskComboBox) {
                Map<Object, Icon> icons = new HashMap<Object, Icon>();

                java.util.List<File> files = Arrays.asList(File.listRoots());

                for (File f : files) {
                        icons.put(f.toString(), FileSystemView.getFileSystemView().getSystemIcon(f));
                }

                for (File f : files) {
                        diskComboBox.addItem(f.toString());
                }

                diskComboBox.setRenderer(new IconListRenderer(icons)); // this string is for correct output of combobox
        }
        //This is only for initiaization
        public void fillingDisksNameLabels(JLabel diskLabel,JComboBox diskComboBox){
                File f=new File(diskComboBox.getSelectedItem().toString());
                String [] parts = FileSystemView.getFileSystemView().getSystemDisplayName(f).toString().split(" ");
                diskLabel.setText("["+parts[0]+"]");
        }
        //This is only for initiaization
        public void fillingDisksSpaceLabels(JLabel diskLabel,JComboBox diskComboBox){
            File f=new File(diskComboBox.getSelectedItem().toString());
                long Usable = f.getUsableSpace()/1024; // in kilobytes
                long Total = f.getTotalSpace()/1024; // in kilobytes
                diskLabel.setText(Usable+" kilobytes from "+Total+" kilobytes");
        }

        public void fillingList(JList window,  DefaultListModel listModel,JComboBox diskComboBox) {

            Map<Object, Icon> icons = new HashMap<Object, Icon>();
            File f=new File(diskComboBox.getSelectedItem().toString());

            java.util.List<File> files = Arrays.asList(f.listFiles());// all files eve hidden

            for (File iterator : files) {
              icons.put(FileSystemView.getFileSystemView().getSystemDisplayName(iterator), FileSystemView.getFileSystemView().getSystemIcon(iterator));
            }

            for(File iterator : files) {
                listModel.addElement(FileSystemView.getFileSystemView().getSystemDisplayName(iterator));
            }
           window.setCellRenderer(new IconListRenderer(icons));
                  }
        //useful info to deletion
       /*
       for (File f : files) {
           //System.out.println(FileSystemView.getFileSystemView().getSystemDisplayName(f));
          // System.out.println(FileSystemView.getFileSystemView().getSystemTypeDescription(f));
          // System.out.println(f);
          }*/

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

        gcb = new JComboBox();
        fillingDisksCombos(gcb);
        frame.add(gcb, gbc);


        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 5;
        labelDisk = new JLabel();
                fillingDisksNameLabels(labelDisk,gcb);
        frame.add(labelDisk, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        labelMemory = new JLabel();
                fillingDisksSpaceLabels(labelMemory,gcb);
        frame.add(labelMemory, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.ipadx = 0;
        gcb2 = new JComboBox();
        fillingDisksCombos(gcb2); // here are we filling first combo box with valid info
        frame.add(gcb2, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.ipadx = 5;
        labelDisk2 = new JLabel();
        fillingDisksNameLabels(labelDisk2,gcb2);
        frame.add(labelDisk2, gbc);

        gbc.gridx = 5;
        gbc.gridy = 0;
        labelMemory2 = new JLabel ();

                fillingDisksSpaceLabels(labelMemory2,gcb2);
        frame.add(labelMemory2, gbc); // here are we filling second combo box with valid info

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 380;
        gbc.ipady = 460;
        gbc.gridwidth = 3;
        String[] colHeads = {"Name", "Extension", "Size"};



            DefaultListModel listModel = new DefaultListModel();
        tempchoice = new JList(listModel);

        fillingList(tempchoice,listModel,gcb);
        scroll = new JScrollPane(tempchoice);
        frame.add(scroll, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;

            listModel.clear();
            tempchoice2 = new JList(listModel);
            fillingList(tempchoice2,listModel,gcb2);
            scroll2 = new JScrollPane(tempchoice2);
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

     	SwingUtilities.invokeLater(new Runnable(){
       		public void run(){
       			GUI fmGUI = new GUI();
       			fmGUI.makeGUI();
       		}
       	});

   	}

}


