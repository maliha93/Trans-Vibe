package team15.com.transvibe;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.Spinner;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView.OnItemSelectedListener;

public class input extends AppCompatActivity implements OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        spinner.setOnItemSelectedListener(this);
        TextView tv[]= new TextView [3];
        tv[0]=(TextView)findViewById(R.id.id1);
        tv[1]=(TextView)findViewById(R.id.id2);
        tv[2]=(TextView)findViewById(R.id.id3);
        Typeface face=Typeface.createFromAsset(getAssets(), "Chunkfive.otf");
        for(int i=0;i<3;i++)
            tv[i].setTypeface(face);
        Button b=(Button)findViewById(R.id.submit);
        b.setTypeface(face);



    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        String selected= (String) spinner.getSelectedItem().toString();
        TextView tv[]= new TextView [2];
        tv[0]=(TextView)findViewById(R.id.start);
        tv[1]=(TextView)findViewById(R.id.end);
        if(selected.equals("Meter")){
            tv[0].setText("0.1<");
            tv[1].setText("<0.5");
        }
        if(selected.equals("Centimeter")){
            tv[0].setText("10<");
            tv[1].setText("<50");
        }
        if(selected.equals("Millimeter")){
            tv[0].setText("100<");
            tv[1].setText("<500");
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    public void onClickSubmit(View view){


        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.button_anim);
        Button btnAlpha = (Button)findViewById(R.id.submit);
        view.startAnimation(animAlpha);
        Intent intent = new Intent(this, WaveActivity.class);
        EditText mass = (EditText) findViewById(R.id.mass);
        String temp = mass.getText().toString();
        if(TextUtils.isEmpty(temp)) {
            mass.setError("Empty field!");
            return;
        }

        EditText length = (EditText) findViewById(R.id.length);
        temp = length.getText().toString();
        if(TextUtils.isEmpty(temp)) {
            length.setError("Empty field!");
            return;
        }
        EditText tension = (EditText) findViewById(R.id.tension);
        temp = tension.getText().toString();
        if(TextUtils.isEmpty(temp)) {
            tension.setError("Empty field!");
            return;
        }
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        Double m=Double.parseDouble(mass.getText().toString());
        Double l=Double.parseDouble(length.getText().toString());

        Double t=Double.parseDouble(tension.getText().toString());
        Spinner s = (Spinner) findViewById(R.id.spinner1);
        Spinner ss = (Spinner) findViewById(R.id.spinner2);
        String massUnit=String.valueOf(s.getSelectedItem());
        String lenUnit=String.valueOf(ss.getSelectedItem());
        if(lenUnit.equals("Meter") && (l<0.1 || l> 0.5)){
            length.setError("Out of Range!");
            return;
        }
        if(lenUnit.equals("Centimeter") && (l<10 || l> 50)){
            length.setError("Out of Range!");
            return;
        }
        if(lenUnit.equals("Millimeter") && (l<100 || l> 500)){
            length.setError("Out of Range!");
            return;
        }

        if(lenUnit.equals("Meter"))
            l=l*1000;
        else if(lenUnit.equals("Centimeter"))
            l=l*10;
        if(massUnit.equals("Gram/Centimemter"))
            m=m/10;
        intent.putExtra("mass", m);
        intent.putExtra("length", l);
        intent.putExtra("tension", t);
        startActivity(intent);
    }

}
