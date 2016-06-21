package docomogroup.jumpickball.Object;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.Display;
import android.view.MotionEvent;

import docomogroup.jumpickball.MainActivity;
import docomogroup.jumpickball.R;

/**
 * Created by vuongluis on 16/01/2016.
 */
public class MoveBox{

    Display display = MainActivity.display;
    private float x;
    private float y;
    private float width;
    private float height;
    private Paint paint;
    private float previousX;
    private float deltaX = 0;
    private Bitmap bitmap;

    public MoveBox(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.RED);
        previousX = x;
        bitmap = BitmapFactory.decodeResource(MainActivity.resources, R.drawable.movebox);
        bitmap = Bitmap.createScaledBitmap(bitmap, (int)getWidth(), (int)getHeight(), true);
    }

    public MoveBox(MoveBox moveBox) {
        x = moveBox.getX();
        y = moveBox.getY();
        width = moveBox.getWidth();
        height = moveBox.getHeight();
    }

    public void onTouchEvent(MotionEvent event) {
        float currentX = event.getX();
        float currentY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                deltaX = currentX - previousX;
                if (getX() + getWidth() + deltaX < display.getWidth() && getX() + deltaX > 0)
                    setX(getX() + deltaX);
                break;
        }
        previousX = currentX;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, getX(), getY(), getPaint());

    }

    public boolean inArea(PointF p) {
        if (p.x >= x && p.x <= x + width && p.y >= y && p.y <= y + height) {
            return true;
        }
        return false;
    }

    public void setColor(int color) {
        paint.setColor(color);
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(float deltaX) {
        this.deltaX = deltaX;
    }
}
