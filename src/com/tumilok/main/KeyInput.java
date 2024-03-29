package com.tumilok.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Handler handler;
    private boolean keyDown[] = new boolean[4];

    public KeyInput(Handler handler) {
        this.handler = handler;

        for (int i = 0; i < 4; i++) keyDown[i] = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Player) {
                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) { tempObject.setVelX(-5); keyDown[0] = true; }
                if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) { tempObject.setVelX(5); keyDown[1] = true; }
                if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) { tempObject.setVelY(-2); keyDown[2] = true; }
                if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) { tempObject.setVelY(2); keyDown[3] = true; }
            }
        }

        if (key == KeyEvent.VK_SPACE) {
        	if(Game.gameState == State.Game){
        		Game.isStart = true;
        	}
        }
        if (key == KeyEvent.VK_ESCAPE){
            if (Game.gameState == State.Game) {
            	Game.gameState = State.Pause;
            	AudioPlayer.getSound("backgroundmusic").loop(1000);
            }
            else if (Game.gameState == State.MenuHelp) Game.gameState = State.Menu;
            else if (Game.gameState == State.PauseHelp) Game.gameState = State.Pause;
            else if (Game.gameState == State.Menu) Game.gameState = State.Exit;
            else if (Game.gameState == State.Pause) {
            	Game.gameState = State.Game;
            	AudioPlayer.getSound("backgroundmusic").stop();
            }
            else if (Game.gameState == State.Quit) Game.gameState = State.Pause;
            else if (Game.gameState == State.Exit) Game.gameState = State.Menu;
        }
        if (key == KeyEvent.VK_ENTER) {
        	if (Game.gameState == State.Exit) System.exit(0);
        	else if (Game.gameState == State.Quit) {
        		Game.gameState = State.Menu;
        		Game.newGame = true;
        	}
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Player) {
                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) keyDown[0] = false;
                if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) keyDown[1] = false;
                if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) keyDown[2] = false;
                if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) keyDown[3] = false;

                if (!keyDown[2] && !keyDown[3]) tempObject.setVelY(0);
                if (!keyDown[0] && !keyDown[1]) tempObject.setVelX(0);
            }
        }
    }

}
