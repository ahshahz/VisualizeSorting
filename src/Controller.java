
import java.util.Observer;
import java.util.Observable;
import java.awt.event.*;

public class Controller implements Observer {
    private Model model;
    private View view;

    public Controller(Model model, View view) { //adds appropriate dependencies

        this.model = model;
        this.view = view;
        this.view.addActionLisetner(new Listener());
        this.view.popMenu(new Listener());
        this.view.eMenu(new Listener());
        this.view.hMenu(new Listener());

        this.model.addObserver(this);
    }

    public void update(Observable o, Object arg) {  //updates user interface
        int[][] array = model.getArray();
        //System.out.println("update called");
        int value;
        int x = 0;
        int y = 0;
        for (int r = 0; r < array.length; r++) {
            for (int c = 0; c < array[r].length; c++) {
                value = array[r][c];
                view.drawPoint(c, r, value);
            }
        }

    }

    class Listener implements ActionListener {
        private boolean randomized = false;

        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            System.out.println("actions in controller " + s);
            if (s.equals("Start")) {
                model.randomize();
                randomized = true;
            } else if (s.equals("Clear")) {
                view.clear();
                randomized = false;
            } else if (s.equals("Press for Menu")) {
                view.showMenu();
            } else if (s.equals("Sort Columns then Rows") && randomized) {
                model.sortArray1();
            } else if (s.equals("Sort Rows then Columns") && randomized) {
                model.sortArray2();
            } else if (s.equals("Sort by Column") && randomized) {
                model.sortColumn();
            } else if (s.equals("Sort by Rows") && randomized) {
                model.sortRows();
            } else if (s.equals("QuickSort") && randomized) {
                model.quicksortControl();
            } else if (s.equals("Help")) {
                view.showHelp();
            } else {
                view.showError();
            }


        }

    }


    public static void main(String[] args) {
        Model model = new Model(720, 1280);
        View view = new View(720, 1280);//caution width and height backwards
        Controller start = new Controller(model, view);
        while (true) ;
       /*
        System.out.println("before random 1");
        model.randomize();
        model.sortArray1();
        model.sortArray2();
        System.out.println("clearing screen");
        view.clear();
       */
    }
}