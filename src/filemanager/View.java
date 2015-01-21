package filemanager;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.util.List;


enum Byte {
    KB("KB", 1024), MB("MB", 1024 * 1024), GB("GB", 1024 * 1024 * 1024);
    private int amount;
    private String name = "bytes";

    Byte(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}

public class View extends JFrame{
    private Byte bytes = Byte.GB;

    private JComboBox jcb, jcb2;
    private JButton helpButton, createFolderButton, copyButton, renameButton, deleteButton, terminalButton, exitButton;
    private JScrollPane scroll, scroll2;
    private JLabel labelDisk, labelDisk2, labelMemory, labelMemory2, labelCommandLine;
    private JTextField commandLine;

    public JComboBox getJcb() {
        return jcb;
    }

    public JComboBox getJcb2() {
        return jcb2;
    }

    public JLabel getLabelDisk() {
        return labelDisk;
    }

    public JLabel getLabelDisk2() {
        return labelDisk2;
    }

    public JLabel getLabelMemory2() {
        return labelMemory2;
    }

    public JLabel getLabelMemory() {
        return labelMemory;
    }

    public DefaultListModel getListModel() {
        return listModel;
    }

    public DefaultListModel getListModel2() {
        return listModel2;
    }

    public JList getListOfFiles() {
        return listOfFiles;
    }

    public JList getListOfFiles2() {
        return listOfFiles2;
    }

    private DefaultListModel listModel, listModel2;
    private JList listOfFiles, listOfFiles2;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private int frameSizeX = 800;
    private int frameSizeY = 600;
    private int countButton = 7;
    public View() {
        this.setTitle("File Manager");
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        this.setLayout(gbl);
        this.setSize(frameSizeX, frameSizeY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void makeGUI(){
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        jcb = new JComboBox();
        this.add(jcb, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 5;
        labelDisk = new JLabel();
        this.add(labelDisk, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        labelMemory = new JLabel();
        this.add(labelMemory, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.ipadx = 0;
        jcb2 = new JComboBox();
        this.add(jcb2, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.ipadx = 5;
        labelDisk2 = new JLabel();
        this.add(labelDisk2, gbc);

        gbc.gridx = 5;
        gbc.gridy = 0;
        labelMemory2 = new JLabel();
        this.add(labelMemory2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 380;
        gbc.ipady = 460;
        gbc.gridwidth = 3;

        listModel = new DefaultListModel();
        listOfFiles = new JList(listModel);
        scroll = new JScrollPane(listOfFiles);
        this.add(scroll, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;

        listModel2 = new DefaultListModel();
        listOfFiles2 = new JList(listModel2);
        scroll2 = new JScrollPane(listOfFiles2);
        this.add(scroll2, gbc);

        gbc.ipady = 15;
        gbc.gridx = 0;
        gbc.gridy = 2;
        labelCommandLine = new JLabel();
        this.add(labelCommandLine, gbc);

        commandLine = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 8;
        gbc.ipadx = 724;
        commandLine = new JTextField();
        this.add(commandLine, gbc);

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
        this.add(buttonPanel, gbc);
        this.setResizable(false);
    }

    public void addActionListener(ActionListener l){
        jcb.addActionListener(l);
        jcb2.addActionListener(l);
        helpButton.addActionListener(l);
        createFolderButton.addActionListener(l);
        copyButton.addActionListener(l);
        renameButton.addActionListener(l);
        deleteButton.addActionListener(l);
        terminalButton.addActionListener(l);
        exitButton.addActionListener(l);
        commandLine.addActionListener(l);
    }

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

    // This method fills combobox with disk letters and icons
    public void fillDisksCombos(JComboBox jcb, List<File> files) {
        try {
            Map<Object, Icon> icons = new HashMap<Object, Icon>();
            for (File f : files) {
                icons.put(f.toString(), FileSystemView.getFileSystemView().getSystemIcon(f));
            }
            for (File f : files) {
                jcb.addItem(f.toString());
            }
            jcb.setRenderer(new IconListRenderer(icons)); // this string is for correct output of combobox
        }catch(Exception e){/***/}      //correct!!!
    }
    //This is only for initiaization
    public void fillDisksNameLabels(JComboBox jcb, JLabel labelDisk){
        File f = new File(jcb.getSelectedItem().toString());
        String diskName = FileSystemView.getFileSystemView().getSystemDisplayName(f).toString();
        labelDisk.setText("[" + diskName + "]");
    }

    public void fillPathLabels(String diskPath, JLabel labelDisk){
        labelDisk.setText("[" + diskPath + "]");
    }

    //This is only for initiaization
    public void fillDisksSpaceLabels(JComboBox jcb, JLabel labelMemory){
        File f = new File(jcb.getSelectedItem().toString());
        long Usable = f.getUsableSpace()/bytes.getAmount();
        long Total = f.getTotalSpace()/bytes.getAmount();
        labelMemory.setText(Usable + " " + bytes.getName() + " from " + Total + " " + bytes.getName());
    }

    public void fillList(DefaultListModel listModel, JList listOfFiles, List<File> files) {
        listModel.clear();
        if(files != null) {
            Map<Object, Icon> icons = new HashMap<Object, Icon>();
            for (File iterator : files) {
                icons.put(FileSystemView.getFileSystemView().getSystemDisplayName(iterator),
                        FileSystemView.getFileSystemView().getSystemIcon(iterator));
            }
            for (File iterator : files) {
                listModel.addElement(FileSystemView.getFileSystemView().getSystemDisplayName(iterator));
            }
            listOfFiles.setCellRenderer(new IconListRenderer(icons));
        }
    }

    public void fillLabelCommandLine(JComboBox jcb){
        labelCommandLine.setText(jcb.getSelectedItem().toString());
    }

    public void actionExit(JButton button, ActionEvent e){
        if(e.getSource() == button) {
            System.exit(0);
        }
    }

   /* public void mouseClicked(MouseEvent mouseEvent) {
        JList theList = (JList) mouseEvent.getSource();
        if (mouseEvent.getClickCount() == 2) {
            int index = theList.locationToIndex(mouseEvent.getPoint());
            if (index >= 0) {
                Object o = theList.getModel().getElementAt(index);
                System.out.println(o.toString());
            }
        }
    }*/

}
