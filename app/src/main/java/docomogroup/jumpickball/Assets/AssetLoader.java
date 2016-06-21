package docomogroup.jumpickball.Assets;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Display;

import docomogroup.jumpickball.R;
/**
 * Created by vuongluis on 03/03/2016.
 */
public class AssetLoader {

    public static Display display;
    public static Resources resources;

    public static Bitmap background, menu;
    public static Bitmap ball, movebox;
    public static Bitmap play;

    public static void load(Display dis, Resources res){
        display = dis;
        resources = res;
        ball = BitmapFactory.decodeResource(resources, R.drawable.ball);
        movebox = BitmapFactory.decodeResource(resources, R.drawable.movebox);
        background = BitmapFactory.decodeResource(resources, R.drawable.background);
        menu = BitmapFactory.decodeResource(resources, R.drawable.menugame);
        play = BitmapFactory.decodeResource(resources, R.drawable.play);
    }
}
