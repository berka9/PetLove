package com.example.pet1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomePage extends AppCompatActivity {
    // public ImageButton postbtn;
    public Button postbtn, dogbtn,btncat2;
    public ImageView imageuser;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        postbtn=findViewById(R.id.postbtn);
        btncat2=findViewById(R.id.btncat2);
        dogbtn=findViewById(R.id.dogbtn);
        imageuser=(findViewById(R.id.imageuser));
        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent post2 = new Intent(HomePage.this,NewPost.class);
                startActivity(post2);

            }
        });

        dogbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dog1 = new Intent(HomePage.this,DogPosts.class);
                startActivity(dog1);
            }
        });

        imageuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent user = new Intent(HomePage.this,ProfileActivity.class);
                startActivity(user);
            }
        });


       btncat2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent cat1 = new Intent(HomePage.this,CatPosts.class);
               startActivity(cat1);
           }
       });


    }



}