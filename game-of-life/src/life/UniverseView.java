package life;

import javax.swing.*;
import java.awt.*;

public class UniverseView extends JFrame {

    private JLabel infoLabel;

    public UniverseView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        this.infoLabel = new JLabel("Generation #" + Universe.generation + ", Alive: " + Universe.numOfAlive);
        setLayout(new BorderLayout());
        add(infoLabel, BorderLayout.NORTH);
    }

    public void updateView(CellState[][] state) {
        infoLabel.setText("Generation #" + Universe.generation + ", Alive: " + Universe.numOfAlive);
        add(BorderLayout.CENTER, new UniverseMap(state));
    }
}
