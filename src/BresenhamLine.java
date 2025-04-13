import javax.swing.*;
import java.awt.*;

public class BresenhamLine extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Example: draw a line from (50, 50) to (300, 200)
        drawLineBresenham(g, 50, 50, 300, 200);
    }

    private void drawLineBresenham(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;

        boolean isSteep = dy > dx;

        if (isSteep) {
            // Swap dx and dy
            int temp = dx;
            dx = dy;
            dy = temp;
        }

        int d = 2 * dy - dx;
        int x = x1;
        int y = y1;

        for (int i = 0; i <= dx; i++) {
            g.fillRect(x, y, 1, 1);

            if (d > 0) {
                if (isSteep)
                    x += sx;
                else
                    y += sy;
                d = d - 2 * dx;
            }

            if (isSteep)
                y += sy;
            else
                x += sx;

            d = d + 2 * dy;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bresenham Line Drawing");
        BresenhamLine panel = new BresenhamLine();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
