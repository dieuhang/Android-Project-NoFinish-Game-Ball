package docomogroup.jumpickball.Object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;
import android.view.View;

import docomogroup.jumpickball.Assets.AssetLoader;

/**
 * Created by vuongluis on 16/01/2016.
 */
public class Ball {

    private float x;
    private float y;
    private float radius;
    private float velocityX;
    private float velocityY;
    private float speed;
    private double alpha;
    private Paint paint;
    private Bitmap bitmap;

    public Ball() {
        x = 0;
        y = 0;
        radius = 10;
        velocityX = 5;
        velocityY = 10;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
    }

    public Ball(float x, float y, float radius)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
        speed = 30;
        alpha = Math.PI / 4.0;
        velocityX = (float) (speed * Math.sin(alpha));
        velocityY = (float) (speed * Math.cos(alpha));
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        bitmap = AssetLoader.ball;
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) (radius * 2), (int) (radius * 2), false);

    }

    public void move() {
        x = x + velocityX;
        y = y + velocityY;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }

    public void drawBitmap(Canvas canvas) {
        canvas.drawBitmap(bitmap, x - radius, y - radius, paint);
    }

    public boolean checkCollisionBackground(View view) {
        if (x + radius + velocityX > view.getWidth() || x - radius + velocityX < 0) {
            updateAlpha(-alpha);
            return true;
        } else if (y + radius + velocityY > view.getHeight() || y - radius + velocityY < 0) {
            updateAlpha(Math.PI - alpha);
            return true;
        }
        return false;
    }

    public boolean checkCollisionBrick(Box box) {
        boolean check = false;

        PointF p1 = new PointF(x + velocityX, y + radius + velocityY);
        PointF p2 = new PointF(x + velocityX, y - radius + velocityY);
        if (box.inArea(p1) || box.inArea(p2)) {
            move();
            updateAlpha(Math.PI - alpha);
            check = true;
        }
        p1 = new PointF(x + radius + velocityX, y + velocityY);
        p2 = new PointF(x - radius + velocityX, y + velocityY);
        if (box.inArea(p1) || box.inArea(p2)) {
            move();
            updateAlpha(-alpha);
            check = true;
        }
        return check;

    }

    public boolean checkCollisionMoveBox(MoveBox moveBox, boolean top) {
        boolean check = false;
        PointF p1 = new PointF(x + velocityX, y + radius + velocityY);
        PointF p2 = new PointF(x + velocityX, y - radius + velocityY);
        if (moveBox.inArea(p1) || moveBox.inArea(p2)) {
            move();
            double deltaX = (x - (moveBox.getX() + moveBox.getWidth() / 2));
            double sinAlpha = (deltaX * 1.9) / moveBox.getWidth();
            double changeAlpha = Math.asin(sinAlpha) + Math.PI / 18;
            Log.d("GOC ALPHA", "DeltaX : " + (float) deltaX + "--- Alpha : " + (float) (changeAlpha * 180 / Math.PI));
            if (top) updateAlpha(changeAlpha);
            else updateAlpha(Math.PI - changeAlpha);
            check = true;
        }

        return check;
    }

    private void updateAlpha(double changeAlpha) {
        alpha = changeAlpha;
        velocityX = (float) (speed * Math.sin(alpha));
        velocityY = (float) (speed * Math.cos(alpha));
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setColor(int color) {
        paint.setColor(color);
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

}
