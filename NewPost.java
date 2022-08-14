package com.example.pet1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewPost extends AppCompatActivity {

    EditText editAnimalName,editAnimalAge,editAnimalBreed,editAnimalLocation,editAnimalNumber;
    Button editAnimal;
    RadioGroup radioGroup,radioGroup2;
    RadioButton radioButton,radioButton2;


    DatabaseReference postRef1,postRef2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        editAnimalName = findViewById(R.id.AnimalName);
        editAnimalAge = findViewById(R.id.AnimalAge);
        // editAnimalGender = findViewById(R.id.AnimalGender);
        editAnimalBreed = findViewById(R.id.AnimalBreed);
        editAnimalLocation = findViewById(R.id.AnimalLocation);
        editAnimalNumber = findViewById(R.id.AnimalNumber);

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup2 = findViewById(R.id.radioGroup2);

        editAnimal = (Button) findViewById(R.id.AnimalPost);

        postRef1 = FirebaseDatabase.getInstance().getReference().child("Posts").child("cat");
        postRef2 = FirebaseDatabase.getInstance().getReference().child("Posts").child("dog");

        editAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPostData();

                editAnimalName.getText().clear();
                editAnimalAge.getText().clear();
                editAnimalBreed.getText().clear();
                editAnimalLocation.getText().clear();
                editAnimalNumber.getText().clear();
            }
        });
    }
    public void checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        int radioId2 = radioGroup2.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);
        radioButton2 = findViewById(radioId2);

    }
    private void insertPostData(){

        String name = editAnimalName.getText().toString();
        String age = editAnimalAge.getText().toString();
        String breed = editAnimalBreed.getText().toString();
        String location = editAnimalLocation.getText().toString();
        String number = editAnimalNumber.getText().toString();

        String animal = radioButton.getText().toString();
        String gender = radioButton2.getText().toString();

        Posts posts = new Posts(name,age,gender,breed,location,number,animal);

        if (animal.matches("Kedi")){
            postRef1.push().setValue(posts);
        }else{
            postRef2.push().setValue(posts);

        }


        Toast.makeText(this, "İlan Paylaşıldı", Toast.LENGTH_SHORT).show();


    }

}