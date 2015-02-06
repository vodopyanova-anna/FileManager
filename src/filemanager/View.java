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

public class View extends JFrame implements Observer{
    private Byte bytes = Byte.GB;
    private JComboBox jcb, jcb2;
    private JButton helpButton, createFolderButton, copyButton, renameButton, deleteButton, terminalButton, exitButton;
    private JLabel labelDisk, labelDisk2, labelMemory, labelMemory2, labelCommandLine;
    private JTextField commandLine;
    private DefaultListModel listModel, listModel2;
    private JList listOfFiles, listOfFiles2;
    private int frameSizeX = 800;
    private int frameSizeY = 600;
    private int countButton = 7;

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

    public View() {
        this.setTitle("File Manager");
        this.setLayout(new GridBagLayout());
        this.setSize(frameSizeX, frameSizeY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void makeGUI(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 10;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        JPanel leftTopPanel = new JPanel();
        leftTopPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        jcb = new JComboBox();
        leftTopPanel.add(jcb);
        labelDisk = new JLabel();
        leftTopPanel.add(labelDisk);
        labelMemory = new JLabel();
        leftTopPanel.add(labelMemory);

        JPanel rightTopPanel = new JPanel();
        rightTopPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        jcb2 = new JComboBox();
        rightTopPanel.add(jcb2);
        labelDisk2 = new JLabel();
        rightTopPanel.add(labelDisk2);
        labelMemory2 = new JLabel();
        rightTopPanel.add(labelMemory2);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
        topPanel.add(leftTopPanel);
        topPanel.add(rightTopPanel);
        this.add(topPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 470;
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(1, 2));
        listModel = new DefaultListModel();
        listOfFiles = new JList(listModel);
        JScrollPane scroll = new JScrollPane(listOfFiles);
        listPanel.add(scroll);

        listModel2 = new DefaultListModel();
        listOfFiles2 = new JList(listModel2);
        JScrollPane scroll2 = new JScrollPane(listOfFiles2);
        listPanel.add(scroll2);
        this.add(listPanel, gbc);

        gbc.ipady = 20;
        gbc.gridy = 2;
        JPanel commandLinePanel = new JPanel();
        commandLinePanel.setLayout(new GridLayout(1, 2));
        labelCommandLine = new JLabel();
        commandLinePanel.add(labelCommandLine);
        commandLine = new JTextField(50);
        commandLinePanel.add(commandLine);

        this.add(commandLinePanel, gbc);

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

        gbc.ipady = 20;
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

    public void fillList(DefaultListModel listModel, JList listOfFiles, List<File> files, File parentDirectory) {
        listModel.clear();
        if(files != null) {
            Map<Object, Icon> icons = new HashMap<Object, Icon>();
            for (File iterator : files) {
                icons.put(FileSystemView.getFileSystemView().getSystemDisplayName(iterator),
                        FileSystemView.getFileSystemView().getSystemIcon(iterator));
            }
            icons.put(parentDirectory, FileSystemView.getFileSystemView().getSystemIcon(parentDirectory));
            for (File iterator : files) {
                listModel.addElement(FileSystemView.getFileSystemView().getSystemDisplayName(iterator));
            }
            listModel.add(0, parentDirectory);
            listOfFiles.setCellRenderer(new IconListRenderer(icons));
        }
    }

    public void fillLabelCommandLine(String labelText){
        labelCommandLine.setText(labelText);
    }

    public void actionExit(JButton button, ActionEvent e){
        if(e.getSource() == button) {
            System.exit(0);
        }
    }

    public void addMouseClicked(MouseListener l) {
        listOfFiles.addMouseListener(l);
        listOfFiles2.addMouseListener(l);
    }


    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Model){
            Model model = (Model) o;

        }
    }
}
