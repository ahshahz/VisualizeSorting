
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.awt.Graphics;


public class View extends JComponent {
    private int height;
    private int width;
    private JFrame aJFrame; //main window or JFrame
    private JFrame pJFrame; //Controls user input
    private JFrame eJFrame; //error jframe
    private JFrame hJFrame; //help jframe


    private JMenuBar menBar; //UI element for menu bar
    private JButton menButton;
    private JButton help;


    private int length;

    public View(int height, int width) { //initializes JFrame and associated elements
        this.height = height;
        this.width = width;
        this.length = height * width;
        aJFrame = new JFrame("Sorting Visualizer");
        aJFrame.setSize(this.width, this.height);
        menBar = new JMenuBar();
        menButton = new JButton("Press for Menu");
        help = new JButton("Help");
        aJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void drawPoint(int x, int y, int value) {  //draws values on the panel
        Graphics g = this.getGraphics();
        Color c = new Color(0, 0, 0);
        if (value < 0) {

            c = new Color(Math.abs(Math.max(-255, value)), 0, 0);

        }
        if (value == 0) {
            c = new Color(0, 0, 0);

        }
        if (value > 0) {
            c = new Color(0, Math.min(255, value), 0);
        }
        g.setColor(c);
        g.drawOval(x, y, 1, 1);
        g.dispose();
    }

    public void clear() {   //clears the window
        Graphics g = this.getGraphics();
        Color c = new Color(255, 255, 255);
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                g.setColor(c);
                g.drawRect(x, y, 100, 100);
            }
        }
        g.dispose();
    }

    public void addActionLisetner(ActionListener m) { //adds listening component
        menButton.addActionListener(m);
        menBar.add(menButton);

        help.addActionListener(m);
        menBar.add(help);

        aJFrame.setJMenuBar(menBar);
        aJFrame.setVisible(true);
        aJFrame.add(this);

    }

    public void popMenu(ActionListener m) { //Control Panel for user input
        JPanel panel = new JPanel(null);
        JButton start = new JButton("Start");
        JButton clear = new JButton("Clear");
        JButton sortColumn = new JButton("Sort by Column");
        JButton sort_by_rows = new JButton("Sort by Rows");
        JButton sort_by_rows_columns = new JButton("Sort Rows then Columns");
        JButton sort_by_columns_rows = new JButton("Sort Columns then Rows");
        JButton QuickSort = new JButton("QuickSort");

        //start button
        start.addActionListener(m);
        start.setBounds(0, 0, 200, 20);
        panel.add(start);

        //clear button
        clear.addActionListener(m);
        clear.setBounds(0, 30, 200, 20);
        panel.add(clear);

        //sort column button
        sortColumn.addActionListener(m);
        sortColumn.setBounds(0, 60, 200, 20);
        panel.add(sortColumn);

        //sorting type control
        sort_by_columns_rows.addActionListener(m);
        sort_by_columns_rows.setBounds(0, 90, 200, 20);
        panel.add(sort_by_columns_rows);

        //sorting type control
        sort_by_rows.addActionListener(m);
        sort_by_rows.setBounds(0, 120, 200, 20);
        panel.add(sort_by_rows);

        //sorting type control
        sort_by_rows_columns.addActionListener(m);
        sort_by_rows_columns.setBounds(0, 150, 200, 20);
        panel.add(sort_by_rows_columns);

        //sorting type control
        QuickSort.addActionListener(m);
        QuickSort.setBounds(0, 180, 200, 20);
        panel.add(QuickSort);

        pJFrame = new JFrame("Sorting Control");
        pJFrame.add(panel);
        pJFrame.setSize(400, 400);
        pJFrame.setVisible(true);
    }

    public void eMenu(ActionListener m) { //error menu
        JPanel panel = new JPanel(null);

        JLabel emessage = new JLabel("Not randomized!");
        emessage.setBounds(0, 0, 200, 20);
        panel.add(emessage);

        JLabel emessage_two = new JLabel("Please see help for further information");
        emessage_two.setBounds(0, 20, 300, 20);
        panel.add(emessage_two);

        eJFrame = new JFrame("ERROR!");
        eJFrame.add(panel);
        eJFrame.setSize(400, 170);
        eJFrame.setVisible(false);
    }

    public void hMenu(ActionListener m) { //help menu
        JPanel panel = new JPanel(null);

        JLabel hmessage = new JLabel("Step One: Press start to randomize array (Use Press for Menu)");
        hmessage.setBounds(0, 0, 400, 20);
        panel.add(hmessage);

        JLabel hmessage_two = new JLabel("Please wait until button is clickable");
        hmessage_two.setBounds(0, 30, 300, 20);
        panel.add(hmessage_two);

        JLabel hmessage_three = new JLabel("Step Two: Press select desired sorting");
        hmessage_three.setBounds(0, 60, 300, 20);
        panel.add(hmessage_three);

        JLabel hmessage_four = new JLabel("Intense red represents values <= -255");
        hmessage_four.setBounds(0, 90, 300, 20);
        panel.add(hmessage_four);

        JLabel h_five = new JLabel("Red to black represents values <= -1");
        h_five.setBounds(0, 120, 300, 20);
        panel.add(h_five);

        JLabel h_six = new JLabel("The color black is the value 0");
        h_six.setBounds(0, 150, 300, 20);
        panel.add(h_six);

        JLabel h_seven = new JLabel("The values from 1 to 255 are shown as the color increases in intensity from black to green");
        h_seven.setBounds(0, 180, 600, 20);
        panel.add(h_seven);

        JLabel h_eight = new JLabel("The value from 256 and above are represented with the color green");
        h_eight.setBounds(0, 210, 400, 20);
        panel.add(h_eight);

        hJFrame = new JFrame("Help Menu");
        hJFrame.add(panel);
        hJFrame.setSize(800, 400);
        hJFrame.setVisible(false); //set to false so that the window doesn't appear on startup
    }

    public void showMenu() {
        pJFrame.setVisible(true); //called by controller
    }

    public void showError() {
        eJFrame.setVisible(true);
    }

    public void showHelp() {
        hJFrame.setVisible(true);
    }

}
