package ir.mahoorsoft.app.stationsfanclub;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * Created by RCC1 on 7/28/2018.
 */

public class G extends Application {
    public static SharedPreferences preferences;
    public static Context context;
    public static AppCompatActivity activity;
    public static String Name;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        preferences = context.getSharedPreferences(Name, MODE_PRIVATE);
    }

    /*public static void disableShiftModeNavigation(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShifting(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }*/

    public static void animatingForGone(final View view, float firstAlpha, float lastAlpha, float translationYValue) {
        view.setAlpha(firstAlpha);
        view.animate()
                .translationY(translationYValue)
                .alpha(lastAlpha)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(View.GONE);
                    }
                });
    }

    public static void animatingForVisible(final View view, float firstAlpha, float lastAlpha, float translationYValue) {
        view.setAlpha(firstAlpha);
        view.setVisibility(View.VISIBLE);
        view.animate()
                .translationY(translationYValue)
                .alpha(lastAlpha)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(View.VISIBLE);
                    }
                });
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public static int setListItemsAnimation(View[] fadeIn, View[] fadeLeft, int position, int lastPosition) {
        // If the bound view wasn't previously displayed on screen, it's animated
        Animation animation;
        if (fadeIn != null) {
            for (View aFadeIn : fadeIn) {
                animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
                aFadeIn.startAnimation(animation);
            }
        }
        if (position > lastPosition && fadeLeft != null) {
            for (View aFadeLeft : fadeLeft) {
                animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
                aFadeLeft.startAnimation(animation);
            }
        }
        return position;
    }
}
