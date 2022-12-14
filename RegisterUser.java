package com.example.pet1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private TextView  registerUser;
    private EditText editTextfullName,editTextage,editTextemail,editTextpassword,editNumber;
    private ProgressBar progressBar;
    private ImageView banner;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();
        banner = (ImageView)findViewById(R.id.banner);
        //  banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        editTextfullName = (EditText) findViewById(R.id.fullName);
        editTextage = (EditText) findViewById(R.id.age);
        editTextemail = (EditText) findViewById(R.id.email);
        editTextpassword = (EditText) findViewById(R.id.password);
        editNumber = (EditText) findViewById(R.id.number);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.registerUser:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String email = editTextemail.getText().toString().trim();
        String password= editTextpassword.getText().toString().trim();
        String fullName= editTextfullName.getText().toString().trim();
        String age= editTextage.getText().toString().trim();
        String number=editNumber.getText().toString().trim();

        if(fullName.isEmpty()){
            editTextfullName.setError("??sim/Soyisim doldurulmak zorundad??r!");
            editTextfullName.requestFocus();
            return;
        }

        if(age.isEmpty()){
            editTextage.setError("Ya?? alan??n?? doldurunuz!");
            editTextage.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextemail.setError("Email Alan??n?? Doldurunuz!");
            editTextemail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextemail.setError("Do??ru bir email giriniz!");
            editTextemail.requestFocus();
            return;

        }

        if(password.isEmpty()){
            editTextpassword.setError("??ifre alan??n?? doldurunuz!");
            editTextpassword.requestFocus();
            return;
        }

        if(password.length() < 6 ){
            editTextpassword.setError("??ifreniz en az 6 karakterden olu??mal??d??r!");
            editTextpassword.requestFocus();
            return;

        }


        if(number.isEmpty()){
            editNumber.setError("Numara alan??n?? doldurunuz!");
            editNumber.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(fullName,age,email,number);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()) {
                                        Toast.makeText(RegisterUser.this, "Ba??ar??yla kay??t yap??lm????t??r!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                    else{
                                        Toast.makeText(RegisterUser.this, "Kay??t yap??lamad??, tekrar deneyin!    ", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(RegisterUser.this, "Kay??t yap??lamad??, tekrar deneyin!    ", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }
}