package filemanager;

//https://www.youtube.com/watch?v=dTVVa2gfht8 <= tutorial to mvc

public class MVCFM {
    public static void main(String[] args) {
        FMView theView = new FMView();
        FMModel theModel = new FMModel();

        FMController theController = new FMController(theView,theModel);

        theView.setVisible(true);
    }
}
