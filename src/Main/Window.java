package Main;

import Assets.Assets;
import Input.Keyboard;
import States.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

public class Window extends JFrame implements Runnable {

    public static final int WIDTH = 800, HEIGHT = 600;

    private final Canvas canvas;
    private final int FPS = 60;
    private final Keyboard keyboard;
    private Thread thread;
    private boolean running = false;
    private double delta = 0;
    private int AVERAGEFPS = FPS;
    private GameState gameState;

    public Window() {
        setTitle("Asteroids Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);

        canvas = new Canvas();
        canvas.setBackground(Color.BLACK);
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setFocusable(true);

        add(canvas);

        keyboard = new Keyboard();
        canvas.addKeyListener(keyboard);
    }

    private void update() {
        keyboard.update();
        gameState.update();
    }

    private void draw() {
        BufferStrategy bs = canvas.getBufferStrategy();

        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //Limpiamos ventana
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);


        //--------Zona de Dibujado---------
        gameState.draw(g);

        g.setColor(Color.WHITE);
        g.drawString("FPS: " + AVERAGEFPS, 5, 15);
        //---------------------------------


        g.dispose();
        bs.show();
    }


    @Override
    public void run() {

        long now = 0;
        long lastTime = System.nanoTime();
        int frames = 0;
        long time = 0;
        double TARGETTIME = 1000000000.0 / FPS;

        try {
            init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / TARGETTIME;
            time += (now - lastTime);
            lastTime = now;
            if (delta >= 1) {
                update();
                draw();
                delta--;
                frames++;
            }

            if (time >= 1000000000) {
                AVERAGEFPS = frames;
                frames = 0;
                time = 0;
            }
        }

        stop();
    }

    private void init() throws IOException {
        Assets.init();

        gameState = new GameState();
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
