package com.example.pet1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PostAdapterDog extends ArrayAdapter {

    private Activity context;
    private List<Posts>ilanlarListDog;


    public PostAdapterDog(Activity context, List<Posts>ilanlarListDog){

        super(context, R.layout.activity_post_adapter_dog, ilanlarListDog);
        this.context=context;
        this.ilanlarListDog=ilanlarListDog;
    }




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_post_adapter_dog,null,true);


        TextView textViewcins = listViewItem.findViewById(R.id.textViewcinsdog);
        TextView textViewcinsiyet2 = listViewItem.findViewById(R.id.textViewcinsiyet2dog);
        TextView textViewsehir = listViewItem.findViewById(R.id.textViewsehirdog);
        TextView textViewtur2 = listViewItem.findViewById(R.id.textViewtur2dog);
        TextView textViewyas = listViewItem.findViewById(R.id.textViewyasdog);
        TextView textViewtel= listViewItem.findViewById(R.id.textviewteldog);

        Posts ilan= ilanlarListDog.get(position);
        textViewcins.setText(ilan.AnimalBreed);
        textViewcinsiyet2.setText(ilan.AnimalGender);
        textViewsehir.setText(ilan.AnimalLocation);
        textViewtur2.setText(ilan.AnimalName);
        textViewyas.setText(ilan.AnimalAge);
        textViewtel.setText(ilan.AnimalNumber);


        return  listViewItem;


    }
}