package com.tumilok.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1622234583316783243L;

    public static final int WIDTH = 1024, HEIGHT = 768;

    public boolean running = false;
    public static boolean isStart;

    private Thread thread;
    private Handler handler;
    private HUD hud;
    private Spawn spawn;
    public static State gameState = State.Menu;
    private Menu menu;

    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        menu = new Menu();
        this.addMouseListener(menu);

        new Window(WIDTH, HEIGHT, "Let's Roll!", this);

        hud = new HUD();
        spawn = new Spawn(handler);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            if(running) render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        if (gameState == State.Game) {
            handler.tick();
            hud.tick();
            spawn.tick();
        }else if(gameState == State.Menu || gameState == State.Help 
        		|| gameState == State.End || gameState == State.Exit) {
            menu.tick();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,  0,  WIDTH, 40);
        
        handler.render(g);
        hud.render(g);
    
        if(gameState != State.Game) {
        	menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max) {
        if (var <= min) return var = min;
        else if (var >= max) return var = max;
        else return var;
    }

    public static void main(String[] args) {
        new Game();
    }
}