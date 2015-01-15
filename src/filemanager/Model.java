package filemanager;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

enum Byte{
    KB("KB", 1024), MB("MB", 1024*1024), GB("GB", 1024*1024*1024);
    private int amount;
    private String name = "bytes";
    Byte(String name, int amount){
        this.name = name;
        this.amount = amount;
    }
    public String getName(){
        return name;
    }
    public int getAmount(){
        return amount;
    }

}

public class Model {
    private Byte bytes = Byte.GB;

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
    public void fillingDisksCombos(JComboBox diskComboBox) {
        try {
            Map<Object, Icon> icons = new HashMap<Object, Icon>();

            java.util.List<File> files = Arrays.asList(File.listRoots());

            for (File f : files) {
                icons.put(f.toString(), FileSystemView.getFileSystemView().getSystemIcon(f));
            }

            for (File f : files) {
                diskComboBox.addItem(f.toString());
            }

            diskComboBox.setRenderer(new IconListRenderer(icons)); // this string is for correct output of combobox
        }catch(Exception e){/***/}      //correct!!!
    }
    //This is only for initiaization
    public void fillingDisksNameLabels(JLabel diskLabel, JComboBox diskComboBox){
        File f = new File(diskComboBox.getSelectedItem().toString());
        String diskName = FileSystemView.getFileSystemView().getSystemDisplayName(f).toString();
        diskLabel.setText("[" + diskName + "]");
    }
    //This is only for initiaization
    public void fillingDisksSpaceLabels(JLabel diskLabel, JComboBox diskComboBox){
        File f = new File(diskComboBox.getSelectedItem().toString());
        long Usable = f.getUsableSpace()/bytes.getAmount();
        long Total = f.getTotalSpace()/bytes.getAmount();
        diskLabel.setText(Usable + " " + bytes.getName() + " from " + Total + " " + bytes.getName());
    }

    public void fillingList(JList window,  DefaultListModel listModel, JComboBox diskComboBox) {
        listModel.clear();
        try {
            Map<Object, Icon> icons = new HashMap<Object, Icon>();
            File f = new File(diskComboBox.getSelectedItem().toString());

            java.util.List<File> files = Arrays.asList(f.listFiles());// all files eve hidden

            for (File iterator : files) {
                icons.put(FileSystemView.getFileSystemView().getSystemDisplayName(iterator), FileSystemView.getFileSystemView().getSystemIcon(iterator));
            }

            for (File iterator : files) {
                listModel.addElement(FileSystemView.getFileSystemView().getSystemDisplayName(iterator));
            }
            window.setCellRenderer(new IconListRenderer(icons));
        } catch (Exception e) {/****/}         //correct!!!
    }

    public void fillingLabelCommandLine(JLabel commandLine, JComboBox diskComboBox){
            commandLine.setText(diskComboBox.getSelectedItem().toString());
    }

    public void fillingLabelCommandLine(JLabel commandLine, JComboBox diskComboBox, ActionEvent e){
        if(e.getSource() == diskComboBox) {
            commandLine.setText(diskComboBox.getSelectedItem().toString());
        }
    }

    public void actionExit(JButton button, ActionEvent e){
        if(e.getSource() == button) {
            System.exit(0);
        }
    }
}
