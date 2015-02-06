package filemanager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

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
        view.fillList(view.getListModel(), view.getListOfFiles(), model.getFiles(), new File(model.getCurrentActivePath()));
        view.fillList(view.getListModel2(), view.getListOfFiles2(), model2.getFiles(), new File(model2.getCurrentActivePath()));
        view.addActionListener(this);
        view.addMouseClicked(this);
    }

    public  void actionPerformed(ActionEvent e){
        if(e.getSource() == view.getJcb()){
            view.fillDisksNameLabels(view.getJcb(), view.getLabelDisk());
            view.fillDisksSpaceLabels(view.getJcb(), view.getLabelMemory());
            view.fillList(view.getListModel(), view.getListOfFiles(),
                    model.getFileListByDriveName(view.getJcb().getSelectedIndex()), model.getStackOfFilePath().pop());
            view.fillLabelCommandLine(model.getCurrentActivePath());
        }
        else if(e.getSource() == view.getJcb2()){
            view.fillPathLabels(view.getJcb2().getSelectedItem().toString(), view.getLabelDisk2());
            view.fillDisksSpaceLabels(view.getJcb2(), view.getLabelMemory2());
            view.fillList(view.getListModel2(), view.getListOfFiles2(),
                    model2.getFileListByDriveName(view.getJcb2().getSelectedIndex()), model2.getStackOfFilePath().pop());
            view.fillLabelCommandLine(model2.getCurrentActivePath());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == view.getListOfFiles()){
            if (e.getClickCount() == 2 && !e.isConsumed()) {
                e.consume();
                fillListByDoubleClick(e, model, view.getListModel(), view.getListOfFiles());
            }
        }else if(e.getSource() == view.getListOfFiles2()) {
            if (e.getClickCount() == 2 && !e.isConsumed()) {
                e.consume();
                fillListByDoubleClick(e, model2, view.getListModel2(), view.getListOfFiles2());
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

    public void fillListByDoubleClick(MouseEvent e, Model model, DefaultListModel listModel, JList list){
        int index = ((JList) e.getSource()).locationToIndex(e.getPoint()) - 1; //+1 parent directory
        if (index == -1) {
            if(!model.getStackOfFilePath().empty()){
                view.fillList(listModel, list,
                        model.getFileListByPath(model.getStackOfFilePath().peek()), model.getStackOfFilePath().pop());
                view.fillLabelCommandLine(model.getCurrentActivePath());
            }
        }
        if (index >= 0) {
            model.getStackOfFilePath().push(new File(model.getCurrentActivePath()));
            view.fillList(listModel, list,
                    model.getFileListByPath(model.getFiles().get(index)), model.getStackOfFilePath().peek());
            view.fillLabelCommandLine(model.getCurrentActivePath());

        }
    }
}
