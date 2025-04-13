import javax.swing.*;
import java.awt.*;

public class DDALineCircleDrawing extends JPanel {

    private int centerX = 250;
    private int centerY = 250;
    private int radius = 100;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLineDDA(g, 10, 10, 80, 100);
        drawCircleDDA(g, centerX, centerY, radius);
    }

    private void drawLineDDA(Graphics g, int x1, int y1, int x2, int y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;

        int steps = Math.max(Math.abs((int)dx), Math.abs((int)dy));

        float xIncrement = dx / steps;
        float yIncrement = dy / steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            g.drawRect(Math.round(x), Math.round(y), 1, 1); // Draw a pixel
            x += xIncrement;
            y += yIncrement;
        }
    }

    private void drawCircleDDA(Graphics g, int xc, int yc, int r) {
        double theta = 0;
        double deltaTheta = 1.0 / r; // step size

        while (theta <= 2 * Math.PI) {
            int x = (int) Math.round(xc + r * Math.cos(theta));
            int y = (int) Math.round(yc + r * Math.sin(theta));
            g.fillRect(x, y, 1, 1); // draw pixel
            theta += deltaTheta;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DDA Line & Circle Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new DDALineCircleDrawing());
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
