import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class BresenhamLine extends JPanel {

    private int x1, y1, x2, y2;

    public BresenhamLine(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLineBresenham(g, x1, y1, x2, y2);
    }

    private void drawLineBresenham(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;

        boolean isSteep = dy > dx;

        if (isSteep) {
            int temp = x1;
            x1 = y1;
            y1 = temp;

            temp = x2;
            x2 = y2;
            y2 = temp;

            dx = Math.abs(x2 - x1);
            dy = Math.abs(y2 - y1);

            sx = x1 < x2 ? 1 : -1;
            sy = y1 < y2 ? 1 : -1;
        }

        int d = 2 * dy - dx;
        int x = x1;
        int y = y1;

        for (int i = 0; i <= dx; i++) {
            if (isSteep) {
                g.fillRect(y, x, 1, 1); // swap back for steep case
            } else {
                g.fillRect(x, y, 1, 1);
            }

            if (d > 0) {
                y += sy;
                d -= 2 * dx;
            }
            x += sx;
            d += 2 * dy;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nBresenham Line Drawer");
            System.out.println("1. Draw a line");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 2) {
                System.out.println("Exiting...");
                break;
            }

            System.out.print("Enter starting x1: ");
            int x1 = scanner.nextInt();
            System.out.print("Enter starting y1: ");
            int y1 = scanner.nextInt();
            System.out.print("Enter ending x2: ");
            int x2 = scanner.nextInt();
            System.out.print("Enter ending y2: ");
            int y2 = scanner.nextInt();

            JFrame frame = new JFrame("Bresenham Line Drawing");
            BresenhamLine panel = new BresenhamLine(x1, y1, x2, y2);
            frame.add(panel);
            frame.setSize(500, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }

        scanner.close();
    }
}
