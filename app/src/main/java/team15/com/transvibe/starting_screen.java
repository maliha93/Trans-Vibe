package team15.com.transvibe;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.os.Handler;
import java.util.Timer;
import android.app.Activity;
import android.util.Log;
import android.os.Message;
import java.util.TimerTask;
import android.content.Intent;
public class starting_screen extends AppCompatActivity {
    TextView tv=null;
    boolean done=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);
        tv= (TextView)findViewById(R.id.trans);
        final Typeface face=Typeface.createFromAsset(getAssets(), "Chunkfive.otf");
        tv.setTypeface(face);
        setText("  TRANS");
        int secondsDelayed = 3;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                tv = (TextView) findViewById(R.id.vibe);
                tv.setTypeface(face);
                setText("VIBE");
            }
        }, secondsDelayed * 1000);
        int secondsDelayed3 = 4;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Animation translatebu = AnimationUtils.loadAnimation(starting_screen.this, R.anim.starting);
                TextView tv2 = (TextView) findViewById(R.id.tilde);
                tv2.setText("~");
                tv2.startAnimation(translatebu);
            }
        }, secondsDelayed3 * 1000);



        int secondsDelayed2 = 6;
        new Handler().postDelayed(new Runnable() {
            public void run() {

                Intent intent = new Intent(starting_screen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, secondsDelayed2 * 1000);

    }
    @Override
    public void onBackPressed() {
        finish();
    }
    public void setText(final String s)
    {
        done=false;
        final int[] i = new int[1];
        i[0] = 0;
        final int length = s.length();
        final Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                char c= s.charAt(i[0]);
                Log.d("Strange",""+c);
                tv.append(String.valueOf(c));
                i[0]++;
            }
        };

        final Timer timer = new Timer();
        TimerTask taskEverySplitSecond = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
                if (i[0] == length - 1) {
                    timer.cancel();
                    done=true;
                }
            }
        };
        timer.schedule(taskEverySplitSecond, 1, 400);

    }

}
