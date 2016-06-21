package docomogroup.jumpickball.Object;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by vuongluis on 16/01/2016.
 */
public class Brick extends Box {

    public Brick(float x, float y, float width, float height) {
        super();
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        canvas.drawRect(getX(), getY(), getX() + getWidth(), getY() + getHeight(), paint);
    }
}
