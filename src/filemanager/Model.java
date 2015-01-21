package filemanager;

import java.io.File;
import java.util.*;
import java.util.List;


public class Model {

    private File desktopPath = new File(System.getProperty("user.home") + "/Desktop");
    private java.util.List<File> systemDrivers = Arrays.asList(File.listRoots());
    private java.util.List<File> files = Arrays.asList(desktopPath.listFiles());

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
            return files;
        }else
            return null;
    }

}
