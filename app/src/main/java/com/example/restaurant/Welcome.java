package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity {
    private Button en;
    private Button leaving;
    private Button enh;
    private Button chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        en=findViewById(R.id.bt1);
        leaving=findViewById(R.id.bt2);
        enh=findViewById(R.id.bt3);
        chat=findViewById(R.id.bt4);
        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Welcome.this,MainActivity.class);
                startActivity(it);


            }
        });
        enh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Welcome.this,SubActivity.class);
                startActivity(it);


            }
        });
        leaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Welcome.this,chatroom.class);
                startActivity(it);

            }
        });

    }
}
