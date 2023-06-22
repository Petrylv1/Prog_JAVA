import javax.swing.*;
import java.awt.*;

public class AnimationProgram extends JFrame {
    private AnimationPanel[] animationPanels;

    public AnimationProgram() {
        setTitle("Animacje");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 5));

        animationPanels = new AnimationPanel[10];
        for (int i = 0; i < 10; i++) {
            animationPanels[i] = new AnimationPanel();
            add(animationPanels[i]);
        }

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        AnimationProgram program = new AnimationProgram();
        program.startAnimations();
    }

    public void startAnimations() {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(animationPanels[i]);
            threads[i].setPriority(i + 1);
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }

    private class AnimationPanel extends JPanel implements Runnable {
        private static final int WIDTH = 400;
        private static final int HEIGHT = 300;
        private static final int TARGET_FPS = 30;
        private static final int DELAY = 1000 / TARGET_FPS;
        private static final int MAX_FRAMES = 100;

        private int position;
        private boolean directionRight;
        private int frameCount;
        private double rotationAngle;

        public AnimationPanel() {
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            setBackground(Color.BLACK);
            position = 0;
            directionRight = true;
            frameCount = 0;
            rotationAngle = 0.0;
        }

        @Override
        public void run() {
            while (frameCount < MAX_FRAMES) {
                updateAnimation();
                repaint();
                frameCount++;

                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void updateAnimation() {
            if (directionRight) {
                position += 5;
                if (position >= WIDTH) {
                    position = WIDTH;
                    directionRight = false;
                }
            } else {
                position -= 5;
                if (position <= 0) {
                    position = 0;
                    directionRight = true;
                }
            }

            rotationAngle += Math.toRadians(1);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawFractal(g);
        }

        private void drawFractal(Graphics g) {
            int centerX = position;
            int centerY = HEIGHT / 2;
            int size = 100;

            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.WHITE);

            g2d.translate(centerX, centerY);

            g2d.rotate(rotationAngle);

            drawFractalSegment(g2d, 0, 0, size);
        }

        private void drawFractalSegment(Graphics2D g2d, int x, int y, int size) {
            if (size < 1) {
                return;
            }

            g2d.drawLine(x, y, x + size, y);
            g2d.drawLine(x, y, x - size, y);
            g2d.drawLine(x, y, x, y + size);
            g2d.drawLine(x, y, x, y - size);

            drawFractalSegment(g2d, x + size, y, size / 2);
            drawFractalSegment(g2d, x - size, y, size / 2);
            drawFractalSegment(g2d, x, y + size, size / 2);
            drawFractalSegment(g2d, x, y - size, size / 2);
        }
    }
}
