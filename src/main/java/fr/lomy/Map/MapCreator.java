package fr.lomy.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MapCreator {
    private JPanel creatorPanel;
    private JButton routeButton;
    private JButton voitureButton;
    private JList list1;

    public MapCreator() {

        JFrame frame = new JFrame("MapCreator");
        this.creatorPanel = new JPanel();

        frame.setContentPane(this.creatorPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);

        routeButton = new JButton();
        voitureButton = new JButton();
        list1 = new JList();

        routeButton.setText("Route");
        voitureButton.setText("Voiture");
        list1.setVisible(false);

        creatorPanel.add(routeButton);
        creatorPanel.add(voitureButton);
        creatorPanel.add(list1);




        routeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Route button clicked");
                list1.setVisible(!list1.isVisible());
            }
        });
    }
}
