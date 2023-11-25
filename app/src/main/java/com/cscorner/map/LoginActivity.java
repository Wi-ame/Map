package com.cscorner.map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {
    ImageView ensa ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ensa= findViewById(R.id.img);
        Drawable d= ContextCompat.getDrawable(this,R.drawable.img);
        if(d!=null){
            d.setBounds(0,0,40,40);

        }
    }
}