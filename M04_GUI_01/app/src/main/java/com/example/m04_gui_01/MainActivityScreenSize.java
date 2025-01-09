package com.example.m04_gui_01;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// RULE 6: The final rule I've made changes to this code to follow is having the author name at the tops of all files (plus a very scary legal warning)!
/**
 * Author: Jeremy Whitenect
 * Year: 2024
 * This code is the intellectual property of Jeremy Whitenect. If this code is stolen, used for monetary gain, or reproduced without permission, you will be prosecuted to the fullest extent of the law.
 */
public class MainActivityScreenSize extends AppCompatActivity {
    private final MusicPlayer musicPlayer = new MusicPlayer();

    // RULE 2: I had a redundant cast here, so me removing it was following the "remove unused code" rule since it was pointless. Same goes with some other scattered code and casts.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_screen_size);
        TextView scrollable = findViewById(R.id.textView);
        scrollable.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_screen_size, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getMyVersion(View view) {

        // Get version data
        String versionNum = Integer.toString(Build.VERSION.SDK_INT);
        boolean afterKitKat = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT);

        // Put it on the screen
        TextView t = (TextView) findViewById(R.id.textView);
        t.setText(" Version Number is " + versionNum);
        t.append("\n afterKitKat = " + Boolean.toString(afterKitKat));
        t.append("\n Build.VERSION.RELEASE = " + Build.VERSION.RELEASE);
        t.append("\n Build.VERSION.INCREMENTAL = " + Build.VERSION.INCREMENTAL);

        // dump build and display metrics
        t.append("\n Build.DISPLAY = " + Build.DISPLAY.toString());
        t.append("\n Screen Size = " + getSizeName(view.getContext()));
        t.append("\n getDisplayMetrics().densityDpi = " + getResources().getDisplayMetrics().densityDpi);
        t.append("\n getDisplayMetrics().density = " + getResources().getDisplayMetrics().density);
        t.append("\n getDisplayMetrics().xdpi = " + getResources().getDisplayMetrics().xdpi);
        t.append("\n getDisplayMetrics().ydpi = " + getResources().getDisplayMetrics().ydpi);
        t.append("\n getDisplayMetrics().heightPixels = " + getResources().getDisplayMetrics().heightPixels);
        t.append("\n getDisplayMetrics().widthPixels = " + getResources().getDisplayMetrics().widthPixels);

        // show what the masks look like for layout size
        t.append("\n\n screenLayout (HEX) = " + (Integer.toHexString(view.getContext().getResources().getConfiguration().screenLayout)));
        t.append("\n SCREENLAYOUT_SIZE_MASK (HEX) = " + (Integer.toHexString(Configuration.SCREENLAYOUT_SIZE_MASK)));
        t.append("\n SCREENLAYOUT_LONG_MASK  (HEX) = " + (Integer.toHexString(Configuration.SCREENLAYOUT_LONG_MASK)));
        t.append("\n SCREENLAYOUT_SIZE_SMALL (HEX) = " + (Integer.toHexString(Configuration.SCREENLAYOUT_SIZE_SMALL)));
        t.append("\n SCREENLAYOUT_SIZE_NORMAL (HEX) = " + (Integer.toHexString(Configuration.SCREENLAYOUT_SIZE_NORMAL)));
        t.append("\n SCREENLAYOUT_SIZE_LARGE (HEX) = " + (Integer.toHexString(Configuration.SCREENLAYOUT_SIZE_LARGE)));

        // Get the layout and mask with size
        int screenLayout = view.getContext().getResources().getConfiguration().screenLayout;
        screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK;
        t.append("\n screenLayout & size mask (HEX) = " + (Integer.toHexString((screenLayout))));

        // Get the layout (again) and mask with long bit of layout
        t.append("\n\n SCREENLAYOUT_LONG_MASK  (HEX) = " + (Integer.toHexString(Configuration.SCREENLAYOUT_LONG_MASK)));
        t.append("\n SCREENLAYOUT_LONG_NO  (HEX) = " + (Integer.toHexString(Configuration.SCREENLAYOUT_LONG_NO)));
        t.append("\n SCREENLAYOUT_LONG_UNDEFINED   (HEX) = " + (Integer.toHexString(Configuration.SCREENLAYOUT_LONG_UNDEFINED)));
        t.append("\n  SCREENLAYOUT_LONG_YES   (HEX) = " + (Integer.toHexString(Configuration.SCREENLAYOUT_LONG_YES)));

        // Now get the layout (again) and mask with size
        screenLayout = view.getContext().getResources().getConfiguration().screenLayout;
        screenLayout &= Configuration.SCREENLAYOUT_LONG_MASK;
        t.append("\n screenLayout & long mask (HEX) = " + (Integer.toHexString((screenLayout))));

    }

    // Use bitmasks to determine screen size
    private static String getSizeName(Context context) {
        int screenLayout = context.getResources().getConfiguration().screenLayout;
        screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenLayout) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                return "small";
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return "normal";
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                return "large";
            case 4: // Configuration.SCREENLAYOUT_SIZE_XLARGE is API >= 9
                return "xlarge";
            default:
                return "undefined";
        }
    }

    // Get the description of the image view, play sound accordingly. Has to have a method for this here, since the onClick in xml has to have the params of the method it's using be a View and that's it.
    public void playImageSound(View view) {
        // This gets the text of the image view that has been clicked
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            CharSequence imageDescription = imageView.getContentDescription();

            String descriptionText = imageDescription != null ? imageDescription.toString() : "";
            Log.d("ImageViewDescription", "Description: " + descriptionText);
            musicPlayer.playImageSound(view, descriptionText);
        }
    }

    // Same concept as the play sound, but for calculating
    public void calculate(View view) {
        Calculator calculator = new Calculator(findViewById(R.id.layout));
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            CharSequence imageDescription = imageView.getContentDescription();

            String descriptionText = imageDescription != null ? imageDescription.toString() : "";
            Log.d("ImageViewDescription", "Description: " + descriptionText);
            calculator.calculate(view, descriptionText);
        }
    }

    // This is to avoid memory leaks
    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicPlayer.getMediaPlayer().release();
    }
}
