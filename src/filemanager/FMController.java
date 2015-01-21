package filemanager;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FMController {
    private FMView theView;
    private FMModel theModel;

    public FMController(FMView theView, FMModel theModel)
    {
        this.theView=theView;
        this.theModel=theModel;

        this.theView.addExitBListener(new ExitButtonListener());

        //mb not working to think how it is hould be done
        this.theView.setDiskNamesComboboxLeft(this.theView.fillingDiskNamesComboboxes(theModel.getSystemDisks()));
        this.theView.setDiskNamesComboboxRight(this.theView.fillingDiskNamesComboboxes(theModel.getSystemDisks()));

    }

    class ExitButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            theView.dispose();
        }
    }
}
