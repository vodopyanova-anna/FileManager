package filemanager;

import java.io.File;
import java.util.*;
import java.util.List;

public class Model extends Observable{

    private File desktopPath = new File(System.getProperty("user.home") + "/Desktop");
    private List<File> systemDrivers = new ArrayList(Arrays.asList(File.listRoots()));
    private List<File> files = new ArrayList(Arrays.asList(desktopPath.listFiles()));
    private String currentActivePath = desktopPath.getPath();

    public String getCurrentActivePath() {
        return currentActivePath;
    }

    public void setCurrentActivePath(String currentActivePath) {
        this.currentActivePath = currentActivePath;
    }

    public File getDesktopPath() {
        return desktopPath;
    }

    public List<File> getSystemDrivers() {
        return systemDrivers;
    }

    public List<File> getFiles() {
        return files;
    }

    public List<File> getFileListByDriveName(int drive_id){
        if(systemDrivers.get(drive_id).listFiles() != null) {
            files = Arrays.asList(systemDrivers.get(drive_id).listFiles());
            currentActivePath = systemDrivers.get(drive_id).getPath();
            return files;
        }else
            return null;
    }

    public List<File> getFileListByPath(File filePath){
        if(filePath.listFiles() != null) {
            files = Arrays.asList(filePath.listFiles());
            currentActivePath = filePath.getPath();
            return files;
        }else
            return null;
    }
    @Override
    public synchronized void addObserver(Observer o){
        super.addObserver(o);
    }
    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }
    @Override
    public void notifyObservers(){
        super.notifyObservers();
    }
    public void setChanged(){
        notifyObservers();
    }
    public void setFiles(List<File> files){
        this.files = files;
        setChanged();
    }

    public void fileChanged(){
        setChanged();
        notifyObservers();
    }

}
