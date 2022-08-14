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

public class PostAdapter extends ArrayAdapter {

    private Activity context;
    private List<Posts>ilanlarList;


    public PostAdapter(Activity context, List<Posts>ilanlarList){

        super(context, R.layout.activity_post_adapter, ilanlarList);
        this.context=context;
        this.ilanlarList=ilanlarList;
    }




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_post_adapter,null,true);


        TextView textViewcins = listViewItem.findViewById(R.id.textViewcins);
        TextView textViewcinsiyet2 = listViewItem.findViewById(R.id.textViewcinsiyet2);
        TextView textViewsehir = listViewItem.findViewById(R.id.textViewsehir);
        TextView textViewtur2 = listViewItem.findViewById(R.id.textViewtur2);
        TextView textViewyas = listViewItem.findViewById(R.id.textViewyas);
        TextView textViewtel= listViewItem.findViewById(R.id.textviewtel);

        Posts ilan= ilanlarList.get(position);
        textViewcins.setText(ilan.AnimalBreed);
        textViewcinsiyet2.setText(ilan.AnimalGender);
        textViewsehir.setText(ilan.AnimalLocation);
        textViewtur2.setText(ilan.AnimalName);
        textViewyas.setText(ilan.AnimalAge);
        textViewtel.setText(ilan.AnimalNumber);





        return  listViewItem;


    }
}