package com.ut3.bbt.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread{

    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private CaptorActivity captorActivity;
    private boolean running;
    private Canvas canvas;

    public GameThread(SurfaceHolder surfaceHolder, GameView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
        this.captorActivity.setUpSensors(gameView.getContext());
    }

    public void setRunning(boolean isRunning) {
        running = isRunning;
    }

    @Override
    public void run() {
        while (running) {
            canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized(surfaceHolder) {
                    this.gameView.draw(canvas);
                    this.gameView.update();
                }
            } catch (Exception e) {}
            finally {
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
