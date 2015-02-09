package filemanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.io.IOException;

public class Controller implements ActionListener, MouseListener{
    private Model model;
    private Model model2;
    private View view;
    private boolean activeModel = true;
    private File activeClickFile;

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
        Object source = e.getSource();
        if(source == view.getJcb()){
            view.fillDisksNameLabels(view.getJcb(), view.getLabelDisk());
            view.fillDisksSpaceLabels(view.getJcb(), view.getLabelMemory());
            view.fillList(view.getListModel(), view.getListOfFiles(),
                    model.getFileListByDriveName(view.getJcb().getSelectedIndex()), model.getStackOfFilePath().pop());
            view.fillLabelCommandLine(model.getCurrentActivePath());
            activeModel = true;
        }
        else if(source == view.getJcb2()){
            view.fillPathLabels(view.getJcb2().getSelectedItem().toString(), view.getLabelDisk2());
            view.fillDisksSpaceLabels(view.getJcb2(), view.getLabelMemory2());
            view.fillList(view.getListModel2(), view.getListOfFiles2(),
                    model2.getFileListByDriveName(view.getJcb2().getSelectedIndex()), model2.getStackOfFilePath().pop());
            view.fillLabelCommandLine(model2.getCurrentActivePath());
            activeModel = false;
        }
        else if(source == view.getHelpButton()){

        }
        else if(source == view.getCreateFolderButton()){
            if(activeModel){
                createNewFolder(model, view.getListModel(), view.getListOfFiles());
            } else {
                createNewFolder(model2, view.getListModel2(), view.getListOfFiles2());
            }
        }
        else if(source == view.getCopyButton()){

        }
        else if(source == view.getRenameButton()){

        }
        else if(source == view.getDeleteButton()){

        }
        else if(source == view.getTerminalButton()){

        }
        else if(source == view.getExitButton()){

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == view.getListOfFiles()){
            if(e.getClickCount() == 1){
                activeModel = true;
                int index = ((JList) e.getSource()).locationToIndex(e.getPoint()) - 1;
                if(index>=0)
                    activeClickFile = model.getFiles().get(index);
            }
            if (e.getClickCount() == 2 && !e.isConsumed()) {
                e.consume();
                if(!fillListByDoubleClick(e, model, view.getListModel(), view.getListOfFiles())){
                    openFileByDoubleClick(e, model);
                };
            }
        }else if(e.getSource() == view.getListOfFiles2()) {
            if(e.getClickCount() == 1){
                activeModel = false;
            }
            if (e.getClickCount() == 2 && !e.isConsumed()) {
                e.consume();
                if(!fillListByDoubleClick(e, model2, view.getListModel2(), view.getListOfFiles2())){
                    openFileByDoubleClick(e, model2);
                };
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

    public boolean fillListByDoubleClick(MouseEvent e, Model model, DefaultListModel listModel, JList list){
        int index = ((JList) e.getSource()).locationToIndex(e.getPoint()) - 1; //+1 parent directory
        if (index == -1) {
            if (!model.getStackOfFilePath().empty()) {
                view.fillList(listModel, list,
                            model.getFileListByPath(model.getStackOfFilePath().peek()), model.getStackOfFilePath().pop());
                view.fillLabelCommandLine(model.getCurrentActivePath());
                return true;
            }
        }
        if (index >= 0) {
            if(model.getFiles().get(index).isDirectory()) {
                model.getStackOfFilePath().push(new File(model.getCurrentActivePath()));
                view.fillList(listModel, list,
                        model.getFileListByPath(model.getFiles().get(index)), model.getStackOfFilePath().peek());
                view.fillLabelCommandLine(model.getCurrentActivePath());
                return true;
            }
        }
        return false;
    }
    public boolean openFileByDoubleClick(MouseEvent e, Model model){
        int index = ((JList) e.getSource()).locationToIndex(e.getPoint()) - 1; //+1 parent directory
        if (index >= 0) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(new File(model.getFiles().get(index).getPath()));
                return true;
            } catch (IOException ie) {
                System.out.print(ie);
                return false;
            }
        }
        return false;
    }
    public void createNewFolder(Model model, DefaultListModel listModel, JList list){
        boolean success = (new File((model.getCurrentActivePath() + "/New Folder")).mkdirs());
        view.fillList(listModel, list,
                model.getFileListByPath(new File(model.getCurrentActivePath())), model.getStackOfFilePath().peek());
        if (!success) {
            System.out.println("error");
        }
    }

}
