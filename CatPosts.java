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

public class CatPosts extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView listViewilann;
    List<Posts> ilanlarList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_posts);
        databaseReference= FirebaseDatabase.getInstance().getReference("Posts").child("cat");

        listViewilann= findViewById(R.id.listviewilann);
        ilanlarList=new ArrayList<>();
    }



    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.S)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ilanlarList.clear();

                for (DataSnapshot ilansnapshot : snapshot.getChildren()){
                    Posts ilan =ilansnapshot.getValue(Posts.class);


                    ilanlarList.add(ilan);

                }



                PostAdapter adapter = new PostAdapter(CatPosts.this,ilanlarList);
                listViewilann.setAdapter((ListAdapter) adapter);

          /*      if (textViewcins.getText().toString().equals("kedi")){
                    imageviewico.getBackground().toString()=getDrawable("giris");

                }*/

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}