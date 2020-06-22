package com.tmc.tmc_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private TextInputLayout email, password;
    private FloatingActionButton loginbutton;
    private FirebaseAuth Mauth;
    private ProgressDialog Mprogress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Mprogress = new ProgressDialog(Login.this);
        Mauth = FirebaseAuth.getInstance();
        email = findViewById(R.id.EmailID);
        password = findViewById(R.id.PasswordID);
        loginbutton = findViewById(R.id.LoginButtonID);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailaddresstext = email.getEditText().getText().toString();
                String passwordget = password.getEditText().getText().toString();

                if(emailaddresstext.isEmpty()){
                    email.setError("Email require");
                }
                else if(passwordget.isEmpty()){
                    password.setError("Password require");
                }
                else {

                    Mprogress.setTitle("Please wait ...");
                    Mprogress.setMessage("we are login your account");
                    Mprogress.setCanceledOnTouchOutside(false);
                    Mprogress.show();

                    Mauth.signInWithEmailAndPassword(emailaddresstext, passwordget)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Mprogress.dismiss();
                                        Toast.makeText(getApplicationContext(), "success to login account", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else {
                                        Mprogress.dismiss();
                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Mprogress.dismiss();
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }
        });




    }

    @Override
    protected void onStart() {

        FirebaseUser FirebaseUser = Mauth.getCurrentUser();
        if(FirebaseUser != null){
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

        super.onStart();
    }
}