package filemanager;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        final Model model = new Model();
        final View view = new View();
        Controller controller = new Controller(model, view);

        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                view.setVisible(true);
            }
        });
    }

}
