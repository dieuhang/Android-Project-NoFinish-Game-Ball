package docomogroup.jumpickball.Interface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;

import docomogroup.jumpickball.Assets.AssetLoader;

/**
 * Created by vuongluis on 1/03/2016.
 */
public class MenuView extends View {

    Display display;
    Bitmap menu, play;
    Paint paint;

    public MenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        display = AssetLoader.display;
        menu = Bitmap.createScaledBitmap(AssetLoader.menu, display.getWidth(), display.getHeight(), false);
        play = AssetLoader.play;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(menu, 0, 0, paint);
        canvas.drawBitmap(play, (display.getWidth() - play.getWidth()) / 2, (display.getHeight() - play.getHeight()) / 2, paint);
    }
}
