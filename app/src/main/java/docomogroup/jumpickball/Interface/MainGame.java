package docomogroup.jumpickball.Interface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

import docomogroup.jumpickball.Assets.AssetLoader;
import docomogroup.jumpickball.MainActivity;
import docomogroup.jumpickball.Object.Ball;
import docomogroup.jumpickball.Object.Brick;
import docomogroup.jumpickball.Object.MoveBox;
import docomogroup.jumpickball.Thread.GameThread;

/**
 * Created by vuongluis on 11/03/2016.
 */

public class MainGame extends SurfaceView {

    private SurfaceHolder holder;
    private GameThread gameThread;

    Display display = MainActivity.display;

    Ball ball;
    MoveBox moveBox;

    ArrayList<Brick> brickList1;
    ArrayList<Brick> brickList2;
    ArrayList<Brick> brickList3;
    ArrayList<Brick> brickList4;
    ArrayList<Brick> brickList5;
    ArrayList<Brick> brickList6;
    ArrayList<Brick> brickList7;
    ArrayList<Brick> brickList8;
    ArrayList<Brick> brickList9;

    ArrayList<Brick> collistionBrick;

    private Bitmap background;

    public MainGame(Context context, AttributeSet attributes) {
        super(context, attributes);
        ball = new Ball(50, display.getHeight() - 200, 30);
        ball.setColor(Color.WHITE);
        moveBox = new MoveBox(display.getWidth() / 2, display.getHeight() - 100, 200, 60);
        moveBox.setColor(Color.BLACK);

        brickList1 = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            Brick b = new Brick(i * 50+100, display.getHeight() / 6, 50, 50);
            b.setColor(Color.parseColor("#b1714b"));
            brickList1.add(b);
        }
        brickList2 = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            Brick b = new Brick(i * 50+100, display.getHeight() / 6 + 50, 50, 50);
            b.setColor(Color.parseColor("#b1714b"));
            brickList2.add(b);
        }
        brickList3 = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            Brick b = new Brick(i * 50+100, display.getHeight()/ 6 + 200, 50, 50);
            b.setColor(Color.parseColor("#736f6e"));
            brickList3.add(b);
        }
        brickList4 = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            Brick b = new Brick(i * 50+100, display.getHeight()/ 6 + 250, 50, 50);
            b.setColor(Color.parseColor("#736f6e"));
            brickList4.add(b);
        }
        brickList5 = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            Brick b = new Brick(i * 50+100, display.getHeight()/ 6 + 350, 50, 50);
            b.setColor(Color.parseColor("#f2e8e9"));
            brickList5.add(b);
        }
        brickList6 = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            Brick b = new Brick(i * 50+100, display.getHeight()/ 6 + 400, 50, 50);
            b.setColor(Color.parseColor("#f2e8e9"));
            brickList6.add(b);
        }
        brickList7 = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            Brick b = new Brick(i * 50+100, display.getHeight()/ 6 + 500, 50, 50);
            b.setColor(Color.parseColor("#4b535e"));
            brickList7.add(b);
        }
        brickList8 = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            Brick b = new Brick(i * 50+100, display.getHeight()/ 6 + 550, 50, 50);
            b.setColor(Color.parseColor("#4b535e"));
            brickList7.add(b);
        }
        brickList9 = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            Brick b = new Brick(i * 50+100, display.getHeight()/ 6 + 700, 50, 50);
            b.setColor(Color.parseColor("#becab9"));
            brickList9.add(b);
        }

        collistionBrick = new ArrayList<>();

        gameThread = new GameThread(this);
        holder = this.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameThread.setRunning(true);
                gameThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                gameThread.setRunning(false);
                try {
                    gameThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        background = AssetLoader.background;
        background = Bitmap.createScaledBitmap(background, 1080, 1920, false);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(background, 0, 0, null);

        brickList1.removeAll(collistionBrick);
        brickList2.removeAll(collistionBrick);
        brickList3.removeAll(collistionBrick);
        brickList4.removeAll(collistionBrick);
        brickList5.removeAll(collistionBrick);
        brickList6.removeAll(collistionBrick);
        brickList7.removeAll(collistionBrick);
        brickList8.removeAll(collistionBrick);
        brickList9.removeAll(collistionBrick);

        collistionBrick.clear();
        if(ball.checkCollisionBackground(this)){
            MainActivity.soundManager.playHit();
        }
        moveBox.draw(canvas);
        if (ball.checkCollisionMoveBox(moveBox, false)) {
                MainActivity.soundManager.playHit();
                if (Math.abs(moveBox.getDeltaX()) > Math.abs(ball.getVelocityX())) {
                    ball.setX(ball.getX() + moveBox.getDeltaX());
                }
        }
        ball.move();
        ball.drawBitmap(canvas);
        for (Brick b : brickList1) {
            b.draw(canvas);
            if (ball.checkCollisionBrick(b)) {
                MainActivity.soundBrick.playHitBrick();
                collistionBrick.add(b);
            }
        }
        for (Brick b : brickList2) {
            b.draw(canvas);
            if (ball.checkCollisionBrick(b)) {
                MainActivity.soundBrick.playHitBrick();
                collistionBrick.add(b);
            }
        }
        for (Brick b : brickList3) {
            b.draw(canvas);
            if (ball.checkCollisionBrick(b)) {
                MainActivity.soundBrick.playHitBrick();
                collistionBrick.add(b);
            }
        }
        for (Brick b : brickList4) {
            b.draw(canvas);
            if (ball.checkCollisionBrick(b)) {
                MainActivity.soundBrick.playHitBrick();
                collistionBrick.add(b);
            }
        }
        for (Brick b : brickList5) {
            b.draw(canvas);
            if (ball.checkCollisionBrick(b)) {
                MainActivity.soundBrick.playHitBrick();
                collistionBrick.add(b);
            }
        }
        for (Brick b : brickList6) {
            b.draw(canvas);
            if (ball.checkCollisionBrick(b)) {
                MainActivity.soundBrick.playHitBrick();
                collistionBrick.add(b);
            }
        }
        for (Brick b : brickList7) {
            b.draw(canvas);
            if (ball.checkCollisionBrick(b)) {
                MainActivity.soundBrick.playHitBrick();
                collistionBrick.add(b);
            }
        }
        for (Brick b : brickList8) {
            b.draw(canvas);
            if (ball.checkCollisionBrick(b)) {
                MainActivity.soundBrick.playHitBrick();
                collistionBrick.add(b);
            }
        }
        for (Brick b : brickList9) {
            b.draw(canvas);
            if (ball.checkCollisionBrick(b)) {
                MainActivity.soundBrick.playHitBrick();
                collistionBrick.add(b);
            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        moveBox.onTouchEvent(event);
        return true;
    }
}
