package life;

import javax.swing.*;
import java.awt.*;

public class UniverseMap extends JPanel {
    private int width;

    public UniverseMap(CellState[][] state) {
        this.width = state.length;
        setLayout(new GridLayout(width, width));
        setVisible(true);
        updateMap(state);
    }

    public void updateMap(CellState[][] state) {
        removeAll();
        Rectangle box = new Rectangle(5, 5);
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                JPanel cell = new JPanel();
                cell.setBounds(box);
                if (state[y][x] == CellState.ALIVE) {
                    cell.setBackground(Color.BLACK);
                }
                add(cell);
            }
        }
    }
}