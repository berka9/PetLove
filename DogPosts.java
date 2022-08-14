package com.example.pet1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DogPosts extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView listViewilandog;
    List<Posts> ilanlarListDog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_posts);
        databaseReference= FirebaseDatabase.getInstance().getReference("Posts").child("dog");

        listViewilandog= findViewById(R.id.listviewilandog);

        ilanlarListDog=new ArrayList<>();


    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.S)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ilanlarListDog.clear();
                for (DataSnapshot ilansnapshot : snapshot.getChildren()){
                    Posts ilan =ilansnapshot.getValue(Posts.class);
                    ilanlarListDog.add(ilan);
                }
                PostAdapterDog adapter = new PostAdapterDog(DogPosts.this,ilanlarListDog);
                listViewilandog.setAdapter((ListAdapter) adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}