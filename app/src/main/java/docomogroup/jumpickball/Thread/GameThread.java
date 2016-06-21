package docomogroup.jumpickball.Thread;

import android.graphics.Canvas;

import docomogroup.jumpickball.Interface.MainGame;

public class GameThread extends Thread {

    private int fps = 40;
    private MainGame view;
    private boolean running;

    public GameThread(MainGame view) {
        this.view = view;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while (running) {
            Canvas c = null;
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    if (c != null) view.draw(c);
                }

            } finally {
                if (c != null) view.getHolder().unlockCanvasAndPost(c);
            }

            try {
                this.sleep(1000 / fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
