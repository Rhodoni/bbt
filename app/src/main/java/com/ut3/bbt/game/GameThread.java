package com.ut3.bbt.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread{

    private final SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    private Canvas canvas;


    private long lastTime;
    private final long framerate = 30;

    public GameThread(SurfaceHolder surfaceHolder, GameView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;

        lastTime = System.currentTimeMillis();
    }

    public void setRunning(boolean isRunning) {
        running = isRunning;
    }

    @Override
    public void run() {
        while (running) {
            long currentTime = System.currentTimeMillis();

            if (currentTime - lastTime > 1000/framerate) {
                lastTime = currentTime;

                canvas = null;
                try {
                    canvas = this.surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        this.gameView.draw(canvas);
                        this.gameView.update();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null) {
                        try {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
