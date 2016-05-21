package team15.com.transvibe;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    double length[] = {500, 250 , 250 , 500 , 500 };
    double mass[] = {0.02 , 0.018 , 0.0049, 0.0245, 0.016};
    double tension[] = {50, 58.8 , 49 , 98 , 78.4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button n[]=new Button [4];
        n[0]=(Button) findViewById(R.id.button1);
        n[1]=(Button) findViewById(R.id.button2);
        n[2]=(Button) findViewById(R.id.button3);
        n[3]=(Button) findViewById(R.id.button4);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Chunkfive.otf");
        for(int i=0;i<4;i++)
            n[i].setTypeface(typeface);

    }

    public void clickedStarter(View view) {
        Intent intent = new Intent(MainActivity.this, WaveActivity.class);
        startActivity(intent);
    }
    public void onClickInput(View view){
        Intent intent = new Intent(this, input.class);
        startActivity(intent);
    }

    public void onClickTheory(View view){
        Intent intent = new Intent(this, Theory.class);
        startActivity(intent);
    }

    public void onClickCredits(View view){
        Intent intent = new Intent(this, credits.class);
        startActivity(intent);
    }

    public void onClickRandom(View view) {
        Intent intent = new Intent(this, WaveActivity.class);
        Random r = new Random();
        int randomNumber = r.nextInt(5);
        intent.putExtra("mass", mass[randomNumber]);
        intent.putExtra("length", length[randomNumber]);
        intent.putExtra("tension", tension[randomNumber]);
        startActivity(intent);
    }
}
