package filemanager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller implements ActionListener{
    private Model model;
    private View view;

    public Controller(Model m, View v) {
        this.model = m;
        this.view = v;
        view.makeGUI();
        view.fillDisksCombos(view.getJcb(), model.getSystemDrivers());
        view.fillDisksCombos(view.getJcb2(), model.getSystemDrivers());
        view.fillDisksNameLabels(view.getJcb(), view.getLabelDisk());
        //view.fillDisksNameLabels(view.getJcb2(), view.getLabelDisk2());
        view.fillPathLabels(model.getDesktopPath().toString(), view.getLabelDisk2()); //just as a variant
        view.fillDisksSpaceLabels(view.getJcb(), view.getLabelMemory());
        view.fillDisksSpaceLabels(view.getJcb2(), view.getLabelMemory2());
        view.fillLabelCommandLine(view.getJcb());
        view.fillList(view.getListModel(), view.getListOfFiles(), model.getFiles());
        view.fillList(view.getListModel2(), view.getListOfFiles2(), model.getFiles());
        view.addActionListener(this);
    }
    public  void actionPerformed(ActionEvent e){
        if(e.getSource() == view.getJcb()){
            view.fillDisksNameLabels(view.getJcb(), view.getLabelDisk());
            view.fillDisksSpaceLabels(view.getJcb(), view.getLabelMemory());
            view.fillLabelCommandLine(view.getJcb());
            view.fillList(view.getListModel(), view.getListOfFiles(),
                    model.getFileListByDriveName(view.getJcb().getSelectedIndex()));
        }
        else if(e.getSource() == view.getJcb2()){
            view.fillPathLabels(view.getJcb2().getSelectedItem().toString(), view.getLabelDisk2());
            view.fillDisksSpaceLabels(view.getJcb2(), view.getLabelMemory2());
            view.fillLabelCommandLine(view.getJcb2());
            view.fillList(view.getListModel2(), view.getListOfFiles2(),
                    model.getFileListByDriveName(view.getJcb2().getSelectedIndex()));
        }


    }

}
