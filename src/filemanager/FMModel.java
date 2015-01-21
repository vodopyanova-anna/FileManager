// here will be all data managment
package filemanager;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FMModel {
private java.util.List<File> SystemDisks;

    public List<File> getSystemDisks() {
        return SystemDisks;
    }

    public void setSystemDisks(List<File> systemDisks) {
        SystemDisks = systemDisks;
    }

    public FMModel(){
        setSystemDisks(Arrays.asList(File.listRoots()));
    }


//        //This is only for initiaization
//        public void fillingDisksNameLabels(JLabel diskLabel,JComboBox diskComboBox){
//                File f=new File(diskComboBox.getSelectedItem().toString());
//                String [] parts = FileSystemView.getFileSystemView().getSystemDisplayName(f).toString().split(" ");
//                diskLabel.setText("["+parts[0]+"]");
//        }
//        //This is only for initiaization
//        public void fillingDisksSpaceLabels(JLabel diskLabel,JComboBox diskComboBox){
//            File f=new File(diskComboBox.getSelectedItem().toString());
//                long Usable = f.getUsableSpace()/1024; // in kilobytes
//                long Total = f.getTotalSpace()/1024; // in kilobytes
//                diskLabel.setText(Usable+" kilobytes from "+Total+" kilobytes");
//        }
//
//        public void fillingList(JList window,  DefaultListModel listModel,JComboBox diskComboBox) {
//
//            Map<Object, Icon> icons = new HashMap<Object, Icon>();
//            File f=new File(diskComboBox.getSelectedItem().toString());
//
//            java.util.List<File> files = Arrays.asList(f.listFiles());// all files eve hidden
//
//            for (File iterator : files) {
//              icons.put(FileSystemView.getFileSystemView().getSystemDisplayName(iterator), FileSystemView.getFileSystemView().getSystemIcon(iterator));
//            }
//
//            for(File iterator : files) {
//                listModel.addElement(FileSystemView.getFileSystemView().getSystemDisplayName(iterator));
//            }
//           window.setCellRenderer(new IconListRenderer(icons));
//                  }
//        //useful info to deletion
//       /*
//       for (File f : files) {
//           //System.out.println(FileSystemView.getFileSystemView().getSystemDisplayName(f));
//          // System.out.println(FileSystemView.getFileSystemView().getSystemTypeDescription(f));
//          // System.out.println(f);
//          }*/

}
