package com.example.pet1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register, forgotPassword;
    private EditText editTextemail, editTextpassword;
    private Button signIn;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.signIN);
        signIn.setOnClickListener(this);

        editTextemail = (EditText) findViewById(R.id.email);
        editTextpassword = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth =FirebaseAuth.getInstance();

        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this, RegisterUser.class));
                break;

            case R.id.signIN:
                userLogin();
                break;

            case R.id.forgotPassword:
                startActivity(new Intent(this,ForgotPassword.class));
                break;


        }

    }

    private void userLogin(){
        String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextemail.setError("Email gereklidir!");
            editTextemail.requestFocus();
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextemail.setError(("Doğru bir email. giriniz!"));
            editTextemail.requestFocus();
            return;

        }
        if(password.isEmpty()){
            editTextpassword.setError("Şifre alanını doldurunuz!");
            editTextpassword.requestFocus();
            return;

        }
        if(password.length() < 6 ){
            editTextpassword.setError("Şifreniz en az 6 karakterden oluşmalıdır!");
            editTextpassword.requestFocus();
            return;

        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()) {
                        startActivity(new Intent(MainActivity.this, HomePage.class));
                    }else {
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this, "Hesabınızı emailinizden doğrulayınız!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Giriş yapılamamıştır! Lütfen bilgilerinizi doğru giriniz!", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }
}