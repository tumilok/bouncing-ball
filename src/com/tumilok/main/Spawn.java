package com.tumilok.main;

import java.awt.Color;

public class Spawn {

    Handler handler;

    private boolean isLevel = true;

    public Spawn(Handler handler) {
        this.handler = handler;
    }

    public void tick() {
        if (HUD.score >= HUD.level * 68) {
            HUD.level++;
            isLevel = true;
        }

        if (isLevel) {
            int size = handler.object.size();
            for (int i = 0; i < size; i++){
                handler.removeObject(handler.object.get(0));
            }

            if (HUD.level == 1) {
                handler.addObject(new Player(Game.WIDTH/2 - 64, Game.HEIGHT - 50, ID.Player, Color.white));
                handler.addObject(new Ball(Game.WIDTH/2 - 6, Game.HEIGHT - 62, ID.Ball, Color.red, handler));
                Game.isStart = false;

                for (int i = 0; i < 10; i++) {
                	for (int j = 0; j < 20; j++) {
                		switch(i) {
                		case 0:
                			if (j == 0 || j == 1 || j == 18 || j == 19)
                				handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick));
                			break;
                		case 1:
                			if (j == 0 || j == 19 || j == 6 || j == 7 || j == 12 || j == 13)
                				handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick));
                			break;
                		case 2:
                			if (j == 3 || j == 7 || j == 8 || j == 11 || j == 12 || j == 16)
                				handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick));
                			break;
                		case 3:
                			if (j == 3 || j == 7 || j == 8 || j == 9 || j == 10 || j == 11 || j == 12 || j == 16)
                				handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick));
                			break;
                		case 4:
                			if (j == 2 || j == 4 || j == 8 || j == 11 || j == 15 || j == 17)
                				handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick));
                			break;
                		case 5:
                			if (j == 2 || j == 4 || j == 8 || j == 11 || j == 15 || j == 17)
                				handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick));
                			break;
                		case 6:
                			if (j == 3 || j == 7 || j == 8 || j == 9 || j == 10 || j == 11 || j == 12 || j == 16)
                				handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick));
                			break;
                		case 7:
                			if (j == 3 || j == 7 || j == 8 || j == 11 || j == 12 || j == 16)
                				handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick));
                			break;
                		case 8:
                			if (j == 0 || j == 19 || j == 6 || j == 7 || j == 12 || j == 13)
                				handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick));
                			break;
                		case 9:
                			if (j == 0 || j == 1 || j == 18 || j == 19)
                				handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick));
                			break;
                		}
                		if (i == 4 || i == 5) {
                			if (j == 3 || j ==9 || j == 10 || j == 16)
                				handler.addObject(new EasyBrick(3 + 51 * j, 100 + 19 * i, ID.Brick));
                		}
                		
                	}
                }
            }
            else if (HUD.level == 2) {
            	handler.addObject(new Player(Game.WIDTH/2 - 64, Game.HEIGHT - 50, ID.Player, Color.white));
                handler.addObject(new Ball(Game.WIDTH/2 - 6, Game.HEIGHT - 62, ID.Ball, Color.red, handler));
                Game.isStart = false;

                for (int i = 0; i < 4; i++) {
                	for (int j = 3 - i; j < 17 + i; j++) {
                		if (j % 2 == 0)
                			handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick));
                		else
                			handler.addObject(new EasyBrick(3 + 51 * j, 100 + 19 * i, ID.Brick));

                	}
                }
                for (int i = 0; i < 4; i++) {
                	for (int j = i; j < 20 - i; j++) {
                		if (i % 2 == 0)
                			handler.addObject(new BasicBrick(3 + 51 * j, 176 + 19 * i, ID.Brick));
                		else
                			handler.addObject(new EasyBrick(3 + 51 * j, 176 + 19 * i, ID.Brick));
                	}
                }
            }
            isLevel = false;
        }
    }
}