package filemanager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller implements ActionListener, MouseListener{
    private Model model;
    private Model model2;
    private View view;

    public Controller(Model m, Model m2, View v) {
        model = m;
        model2 = m2;
        view = v;
        model.addObserver(view);
        model2.addObserver(view);
        view.makeGUI();
        view.fillDisksCombos(view.getJcb(), model.getSystemDrivers());
        view.fillDisksCombos(view.getJcb2(), model2.getSystemDrivers());
        view.fillDisksNameLabels(view.getJcb(), view.getLabelDisk());
        //view.fillDisksNameLabels(view.getJcb2(), view.getLabelDisk2());
        view.fillPathLabels(model2.getDesktopPath().toString(), view.getLabelDisk2()); //just as a variant
        view.fillDisksSpaceLabels(view.getJcb(), view.getLabelMemory());
        view.fillDisksSpaceLabels(view.getJcb2(), view.getLabelMemory2());
        view.fillLabelCommandLine(model.getCurrentActivePath());
        view.fillList(view.getListModel(), view.getListOfFiles(), model.getFiles());
        view.fillList(view.getListModel2(), view.getListOfFiles2(), model2.getFiles());
        view.addActionListener(this);
        view.addMouseClicked(this);
    }

    public  void actionPerformed(ActionEvent e){
        if(e.getSource() == view.getJcb()){
            view.fillDisksNameLabels(view.getJcb(), view.getLabelDisk());
            view.fillDisksSpaceLabels(view.getJcb(), view.getLabelMemory());
            view.fillList(view.getListModel(), view.getListOfFiles(),
                    model.getFileListByDriveName(view.getJcb().getSelectedIndex()));
            view.fillLabelCommandLine(model.getCurrentActivePath());
        }
        else if(e.getSource() == view.getJcb2()){
            view.fillPathLabels(view.getJcb2().getSelectedItem().toString(), view.getLabelDisk2());
            view.fillDisksSpaceLabels(view.getJcb2(), view.getLabelMemory2());
            view.fillList(view.getListModel2(), view.getListOfFiles2(),
                    model2.getFileListByDriveName(view.getJcb2().getSelectedIndex()));
            view.fillLabelCommandLine(model2.getCurrentActivePath());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == view.getListOfFiles()){
            if (e.getClickCount() == 2 && !e.isConsumed()) {
                e.consume();
                int index = ((JList)e.getSource()).locationToIndex(e.getPoint());

                if (index >= 0) {
                    view.fillList(view.getListModel(), view.getListOfFiles(),
                                  model.getFileListByPath(model.getFiles().get(index)));
                    view.fillLabelCommandLine(model.getCurrentActivePath());

                }
            }
        }else if(e.getSource() == view.getListOfFiles2()) {
            if (e.getClickCount() == 2 && !e.isConsumed()) {
                e.consume();
                int index = ((JList) e.getSource()).locationToIndex(e.getPoint());
                if (index >= 0) {
                    view.fillList(view.getListModel2(), view.getListOfFiles2(),
                            model2.getFileListByPath(model2.getFiles().get(index)));
                    view.fillPathLabels(model2.getCurrentActivePath(), view.getLabelDisk2());
                    view.fillLabelCommandLine(model2.getCurrentActivePath());
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
