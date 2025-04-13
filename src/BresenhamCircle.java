import javax.swing.*;
import java.awt.*;

public class BresenhamCircle extends JPanel {

    private int centerX = 250;
    private int centerY = 250;
    private int radius = 100;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCircleBresenham(g, centerX, centerY, radius);
    }

    private void drawCircleBresenham(Graphics g, int xc, int yc, int r) {
        int x = 0;
        int y = r;
        int d = 3 - 2 * r;

        drawCirclePoints(g, xc, yc, x, y);

        while (y >= x) {
            x++;

            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else {
                d = d + 4 * x + 6;
            }

            drawCirclePoints(g, xc, yc, x, y);
        }
    }

    private void drawCirclePoints(Graphics g, int xc, int yc, int x, int y) {
        // Draw all 8 symmetric points
        g.fillRect(xc + x, yc + y, 1, 1);
        g.fillRect(xc - x, yc + y, 1, 1);
        g.fillRect(xc + x, yc - y, 1, 1);
        g.fillRect(xc - x, yc - y, 1, 1);
        g.fillRect(xc + y, yc + x, 1, 1);
        g.fillRect(xc - y, yc + x, 1, 1);
        g.fillRect(xc + y, yc - x, 1, 1);
        g.fillRect(xc - y, yc - x, 1, 1);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bresenham Circle Drawing");
        BresenhamCircle panel = new BresenhamCircle();
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
