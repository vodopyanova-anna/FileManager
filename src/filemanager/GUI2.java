package filemanager;


import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class GUI2 {
    JFrame frame = new JFrame("File Manager");
    JPanel panel = new JPanel(new MigLayout());


    public void Test() {
        JLabel disName1Label = new JLabel();
        JLabel disName2Label =new JLabel();
        JComboBox diskName1=new JComboBox();
        JComboBox diskName2=new JComboBox();

        fillingDisksCombos(diskName1,disName1Label);

    panel.add(disName1Label);
    panel.add(diskName1, "wrap");
    panel.add(disName2Label);
    panel.add(diskName2);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
}

    public void fillingDisksCombos(JComboBox diskComboBox,JLabel diskLabel) {
        List<File> files = Arrays.asList(File.listRoots());
        for (File f : files) {
           System.out.println(FileSystemView.getFileSystemView().getSystemDisplayName(f));
           System.out.println(FileSystemView.getFileSystemView().getSystemTypeDescription(f));
           System.out.println(f);
            //следующие 2 строчки забагованы) нужны переписать ListCellRenderer и установить сет рендер
           diskComboBox.addItem(FileSystemView.getFileSystemView().getSystemIcon(f));
           diskComboBox.addItem( FileSystemView.getFileSystemView().getSystemTypeDescription(f));

          //  diskComboBox.addItem(FileSystemView.getFileSystemView().getSystemDisplayName(f));

          //  diskLabel.setText(FileSystemView.getFileSystemView().getSystemTypeDescription(f));
          }
    }
}
