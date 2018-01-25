package com.example.rajat.locationppicker;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.everseat.textviewlabel.TextViewLabel;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.plus.model.people.Person;

import at.markushi.ui.CircleButton;

public class MainActivity extends AppCompatActivity {
TextView textView,tv;
    int PLACE_PICKER_REQUEST=1;
    CircleButton circleButton;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.textView);
        circleButton= (CircleButton) findViewById(R.id.butt);
        tv= (TextView) findViewById(R.id.textView2);
        imageView= (ImageView) findViewById(R.id.img);


        circleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder=new PlacePicker.IntentBuilder();
                Intent intent;
                try {
                    intent=builder.build(getApplicationContext());
                    startActivityForResult(intent,PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    protected void  onActivityResult(int requestCode,int resultCode,Intent data)
    {
        if(requestCode==PLACE_PICKER_REQUEST)
        {
            if(resultCode==RESULT_OK)
            {
                Place place=PlacePicker.getPlace(data,this);

                String address=String.format("Place: %s",place.getAddress());
                String contact=String.format("Contact: %s",place.getPhoneNumber());
                if(contact==null)
                    contact="Not Found";
                textView.setText(address);
                tv.setText(contact);

            }
        }
    }
}
