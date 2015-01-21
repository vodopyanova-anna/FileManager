//template here will be another UI
package filemanager;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FMView extends JFrame{

    private JComboBox DiskNamesComboboxLeft, DiskNamesComboboxRight;
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

    public FMView()
    {
           
        this.setName("File Manager");

        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        this.setLayout(gbl);
        this.setSize(frameSizeX, frameSizeY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        gbc.weightx = 0.5;
        gbc.weighty = 0.5;

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        DiskNamesComboboxLeft = new JComboBox();
        //fillingDisksCombos(DiskNamesComboboxLeft);
        this.add(DiskNamesComboboxLeft, gbc);


        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 5;
        labelDisk = new JLabel();
        //fillingDisksNameLabels(labelDisk,DiskNamesComboboxLeft);
        this.add(labelDisk, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        labelMemory = new JLabel();
        //fillingDisksSpaceLabels(labelMemory,DiskNamesComboboxLeft);
        this.add(labelMemory, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.ipadx = 0;
        DiskNamesComboboxRight = new JComboBox();
        //fillingDisksCombos(DiskNamesComboboxRight); // here are we filling first combo box with valid info
        this.add(DiskNamesComboboxRight, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.ipadx = 5;
        labelDisk2 = new JLabel();
    //    fillingDisksNameLabels(labelDisk2,DiskNamesComboboxRight);
        this.add(labelDisk2, gbc);

        gbc.gridx = 5;
        gbc.gridy = 0;
        labelMemory2 = new JLabel ();

      //  fillingDisksSpaceLabels(labelMemory2,DiskNamesComboboxRight);
        this.add(labelMemory2, gbc); // here are we filling second combo box with valid info

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 380;
        gbc.ipady = 460;
        gbc.gridwidth = 3;

        DefaultListModel listModel = new DefaultListModel();
        tempchoice = new JList(listModel);

        //fillingList(tempchoice,listModel,DiskNamesComboboxLeft);
        scroll = new JScrollPane(tempchoice);
        this.add(scroll, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;

        listModel.clear();
        tempchoice2 = new JList(listModel);
        //fillingList(tempchoice2,listModel,DiskNamesComboboxRight);
        scroll2 = new JScrollPane(tempchoice2);
        this.add(scroll2, gbc);

        gbc.ipady = 15;
        gbc.gridx = 0;
        gbc.gridy = 2;
        labelCommandLine = new JLabel("C:\\>");
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
        // Inner Class ends here


    // This method fills combobox with disk lettesr and icons

    public JComboBox  fillingDiskNamesComboboxes(java.util.List<File> SystemDisks){
        JComboBox diskComboBox = new JComboBox();
        Map<Object, Icon> icons = new HashMap<Object, Icon>();

                for (File f : SystemDisks) {
                        icons.put(f.toString(), FileSystemView.getFileSystemView().getSystemIcon(f));
                }

                for (File f : SystemDisks) {
                        diskComboBox.addItem(f.toString());
                }

        diskComboBox.setRenderer(new IconListRenderer(icons)); // this string is for correct output of combobox
        return diskComboBox;
    };

    public JComboBox getDiskNamesComboboxLeft() {
        return DiskNamesComboboxLeft;
    }

    public void setDiskNamesComboboxLeft(JComboBox diskNamesComboboxLeft) {
        DiskNamesComboboxLeft = diskNamesComboboxLeft;
    }

    public JComboBox getDiskNamesComboboxRight() {
        return DiskNamesComboboxRight;
    }

    public void setDiskNamesComboboxRight(JComboBox diskNamesComboboxRight) {
        DiskNamesComboboxRight = diskNamesComboboxRight;
    }

    void addExitBListener(ActionListener ExitButtonListener){
        exitButton.addActionListener(ExitButtonListener);
    }
}
