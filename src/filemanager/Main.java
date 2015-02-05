package filemanager;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        final Model model = new Model();
        final Model model2 = new Model();
        final View view = new View();
        Controller controller = new Controller(model, model2, view);
        model.addObserver(view);
        model2.addObserver(view);

        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                view.setVisible(true);
            }
        });
    }

}
