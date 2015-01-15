package filemanager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{
    private Model model;
    private View view;

    public Controller(Model m, View v) {
        this.model = m;
        this.view = v;
        view.makeGUI();
        model.fillingDisksCombos(view.getGcb());
        model.fillingDisksCombos(view.getGcb2());
        model.fillingList(view.getTempChoice(), view.getListModel(), view.getGcb());
        model.fillingList(view.getTempChoice2(), view.getListModel2(), view.getGcb2());
        model.fillingDisksNameLabels(view.getLabelDisk(), view.getGcb());
        model.fillingDisksNameLabels(view.getLabelDisk2(), view.getGcb2());
        model.fillingDisksSpaceLabels(view.getLabelMemory(), view.getGcb());
        model.fillingDisksSpaceLabels(view.getLabelMemory2(), view.getGcb2());
        model.fillingLabelCommandLine(view.getLabelCommandLine(), view.getGcb());
        this.view.addActionListener(this);
    }
    public  void actionPerformed(ActionEvent e){
        model.fillingList(view.getTempChoice(), view.getListModel(), view.getGcb());
        model.fillingList(view.getTempChoice2(), view.getListModel2(), view.getGcb2());
        model.fillingDisksNameLabels(view.getLabelDisk(), view.getGcb());
        model.fillingDisksNameLabels(view.getLabelDisk2(), view.getGcb2());
        model.fillingDisksSpaceLabels(view.getLabelMemory(), view.getGcb());
        model.fillingDisksSpaceLabels(view.getLabelMemory2(), view.getGcb2());
        model.fillingLabelCommandLine(view.getLabelCommandLine(), view.getGcb(), e);
        model.fillingLabelCommandLine(view.getLabelCommandLine(), view.getGcb2(), e);
        model.actionExit(view.getExitButton(), e);
    }
}
