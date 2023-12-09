package com.cscorner.map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        Button btnSignIn = findViewById(R.id.btnSignInWithEmail);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Récupérer les valeurs saisies par l'utilisateur
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                checkCredentials( email, password);
            }
        });
    }

    private void checkCredentials(String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        startActivity(new Intent(LoginActivity.this, SecondActivity.class));

                        finish();
                    } else {

                        Toast.makeText(LoginActivity.this, "Informations incorrectes", Toast.LENGTH_SHORT).show();
                    }


                    editTextEmail.setText("");
                    editTextPassword.setText("");
                });
    }


}