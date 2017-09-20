package com.bhave.ambuler;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity{

    Spinner gender,bloodGroup;
    EditText name,age,email,address,city,state,pincode;
    private static final int RC_PHOTO_PICKER =  2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ActionBar acb = getSupportActionBar();
        acb.setTitle("Profile");

        age = (EditText) findViewById(R.id.u_Age);
        name = (EditText) findViewById(R.id.u_Name);
        email = (EditText) findViewById(R.id.u_Email);
        address = (EditText) findViewById(R.id.u_Address);
        city = (EditText) findViewById(R.id.u_City);
        state = (EditText) findViewById(R.id.u_State);
        pincode = (EditText) findViewById(R.id.u_PinCode);


        gender = (Spinner) findViewById(R.id.u_Gender);
        bloodGroup = (Spinner) findViewById(R.id.u_BloodGroup);

        List<String> genders = new ArrayList<String>();
        genders.add("Male");
        genders.add("Female");
        genders.add("Others");

        List<String> bgroups = new ArrayList<String>();
        bgroups.add("A+");
        bgroups.add("A-");
        bgroups.add("B+");
        bgroups.add("B-");
        bgroups.add("AB+");
        bgroups.add("AB-");
        bgroups.add("O+");
        bgroups.add("O-");

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genders);
        ArrayAdapter<String> bGroupAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bgroups);

        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        gender.setAdapter(genderAdapter);
        bloodGroup.setAdapter(bGroupAdapter);


       gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

       bloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });


    }

    public void submitProfile(View v){
        //Do Something
    }

    public void choosePic(View v){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_PHOTO_PICKER){
            if(resultCode == RESULT_OK){
                Uri selectedImageUri = data.getData();
                //Something here too
                ImageView i = (ImageView) findViewById(R.id.u_Image);
                i.setImageURI(selectedImageUri);
            }
        }
    }
}
