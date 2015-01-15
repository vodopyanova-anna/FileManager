package filemanager;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class View extends JFrame{

    public JTextField getCommandLine() {
        return commandLine;
    }

    public JButton getCopyButton() {
        return copyButton;
    }

    public JButton getCreateFolderButton() {
        return createFolderButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JComboBox getGcb2() {
        return gcb2;
    }

    public JComboBox getGcb() {
        return gcb;
    }

    public JButton getHelpButton() {
        return helpButton;
    }

    public JLabel getLabelCommandLine() {
        return labelCommandLine;
    }

    public JLabel getLabelDisk2() {
        return labelDisk2;
    }

    public JLabel getLabelDisk() {
        return labelDisk;
    }

    public JLabel getLabelMemory2() {
        return labelMemory2;
    }

    public JLabel getLabelMemory() {
        return labelMemory;
    }

    public DefaultListModel getListModel2() {
        return listModel2;
    }

    public JButton getRenameButton() {
        return renameButton;
    }

    public DefaultListModel getListModel() {
        return listModel;
    }

    public JList getTempChoice2() {
        return tempChoice2;
    }

    public JList getTempChoice() {
        return tempChoice;
    }

    public JButton getTerminalButton() {
        return terminalButton;
    }

    private JComboBox gcb, gcb2;
    private JButton helpButton, createFolderButton, copyButton, renameButton, deleteButton, terminalButton, exitButton;
    private JScrollPane scroll, scroll2;
    private JLabel labelDisk, labelDisk2, labelMemory, labelMemory2, labelCommandLine;
    private JTextField commandLine;
    private DefaultListModel listModel, listModel2;
    private JList tempChoice, tempChoice2;
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

        gcb = new JComboBox();
        this.add(gcb, gbc);

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
        gcb2 = new JComboBox();
        this.add(gcb2, gbc);

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
        tempChoice = new JList(listModel);
        scroll = new JScrollPane(tempChoice);
        this.add(scroll, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;

        listModel2 = new DefaultListModel();
        tempChoice2 = new JList(listModel2);
        scroll2 = new JScrollPane(tempChoice2);
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
            gcb.addActionListener(l);
            gcb2.addActionListener(l);
            helpButton.addActionListener(l);
            createFolderButton.addActionListener(l);
            copyButton.addActionListener(l);
            renameButton.addActionListener(l);
            deleteButton.addActionListener(l);
            terminalButton.addActionListener(l);
            exitButton.addActionListener(l);
            commandLine.addActionListener(l);
        }


}
