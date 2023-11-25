package com.cscorner.map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;




public class MainActivity extends AppCompatActivity {
   GoogleSignInOptions gso ;
   GoogleSignInClient gsc;
   Button email ;
    Button googleBtn ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);
       gso =new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
       gsc =  GoogleSignIn.getClient(this,gso);
        googleBtn = findViewById(R.id.btnSignIn);
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        email =findViewById(R.id.btnSignInWithEmail);
        Button Facebook = findViewById(R.id.facebook_logo);
 email.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         Intent intent =  new Intent(MainActivity.this, LoginActivity.class);
         startActivity(intent);
     }
 });

        Drawable Drawable = ContextCompat.getDrawable(this, R.drawable.facebook_logo);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_google_logo);
        if (drawable != null) {
            drawable.setBounds(0, 0,100, 100);
            googleBtn.setCompoundDrawables(drawable, null, null, null);
        }
        if (Drawable != null) {
            Drawable.setBounds(0, 0,200, 200);
            Facebook.setCompoundDrawables(Drawable, null, null, null);
        }

    }
    private  void signIn() {
        Intent signINIntent =gsc.getSignInIntent();
        startActivityForResult(signINIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // La connexion avec Google a réussi
                GoogleSignInAccount account = task.getResult(ApiException.class);
                // Vous pouvez maintenant utiliser 'account' pour obtenir des informations sur l'utilisateur
                navigatetoSecondActivity();
            } catch (ApiException e) {
                // La connexion avec Google a échoué
                Toast.makeText(getApplicationContext(), "Connexion avec Google échouée", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void navigatetoSecondActivity(){
        gsc.signOut();
        finish();
        Intent intent =new Intent(MainActivity.this ,SecondActivity.class);
        startActivity(intent);
    }
}