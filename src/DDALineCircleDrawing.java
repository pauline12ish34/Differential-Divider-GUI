import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class DDALineCircleDrawing extends JPanel {

    private int centerX = 250;
    private int centerY = 250;
    private int radius = -1;

    private int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
    private boolean drawLine = false;
    private boolean drawCircle = false;

    public DDALineCircleDrawing(int choice, Scanner scanner) {
        if (choice == 1) {
            drawCircle = true;
            System.out.print("Enter radius of the circle: ");
            radius = scanner.nextInt();
        } else if (choice == 2) {
            drawLine = true;
            System.out.print("Enter x1: ");
            x1 = scanner.nextInt();
            System.out.print("Enter y1: ");
            y1 = scanner.nextInt();
            System.out.print("Enter x2: ");
            x2 = scanner.nextInt();
            System.out.print("Enter y2: ");
            y2 = scanner.nextInt();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (drawLine) {
            drawLineDDA(g, x1, y1, x2, y2);
        } else if (drawCircle) {
            drawCircleDDA(g, centerX, centerY, radius);
        }
    }

    private void drawLineDDA(Graphics g, int x1, int y1, int x2, int y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;

        int steps = Math.max(Math.abs((int) dx), Math.abs((int) dy));

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
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Draw Circle");
            System.out.println("2. Draw Line");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            if (choice == 3) {
                System.out.println("Exiting...");
                break;
            }

            JFrame frame = new JFrame("DDA Line & Circle Drawing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new DDALineCircleDrawing(choice, scanner));
            frame.setSize(600, 600);
            frame.setVisible(true);
        }

        scanner.close();
    }
}
