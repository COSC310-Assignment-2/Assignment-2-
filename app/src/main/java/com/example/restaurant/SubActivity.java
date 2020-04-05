package com.example.restaurant;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubActivity extends AppCompatActivity {
    private List<msg> Msgs=new ArrayList<msg>();
    private ChatAdapter Adap;
    private RecyclerView chatlist;
    private EditText content1;
    private msg rep;
    private int turns=0;
    private Button send;
    SimpleDateFormat da=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatlist=findViewById(R.id.rv_chatList);
        content1=findViewById(R.id.et_content);
        send=findViewById(R.id.bt_send);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        chatlist.setLayoutManager(linearLayoutManager);
        Date curDate =  new Date(System.currentTimeMillis());
        msg start=new msg("Hi, how are you today?", 0,da.format(new Date()));
        Msgs.add(start);

        Adap=new ChatAdapter(this,Msgs);
        chatlist.setAdapter(Adap);
        chatlist.scrollToPosition(Adap.getItemCount()-1);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg respon;
                String content = content1.getText().toString();
                rep=new msg(content, 1, da.format(new Date()));

                Adap.addItem(rep);
                chatlist.scrollToPosition(Adap.getItemCount()-1);
                content1.setText("");
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        chat(rep,Adap);
                        chatlist.scrollToPosition(Adap.getItemCount()-1);//execute the task
                    }
                }, 500);

            }
        });
        chatlist.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < -10) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(content1.getWindowToken(), 0);
                }
            }
        });

    }
    public void chat(msg msg,ChatAdapter adap) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String test = msg.toString();
        if (turns == 0 && (test.contains("I am") || test.contains("I'm") || test.contains("good") || test.contains("fine"))) {
            adap.addItem(new msg(" How many people are there?", 0, da.format(new Date())));
            turns++;
        } else if (turns == 1 && (test.contains("1") || test.contains("2") || test.contains("3") || test.contains("4") || test.contains("5") || test.contains("6") || test.contains("7") || test.contains("8") || test.contains("one") || test.contains("two") || test.contains("three") || test.contains("four") || test.contains("five") || test.contains("six")
                || test.contains(" seven") || test.contains(" eight") || test.contains("nine") || test.contains("ten") || test.contains("9") || test.contains("sorry"))) {
            adap.addItem(new msg(" Is this your first time coming to our hotel?", 0, da.format(new Date())));
            turns++;
        } else if (turns == 2 && test.contains("yes")) {
            adap.addItem(new msg(" Here is our recommendation room type: suite on 20% off.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 2 && test.contains("no")) {
            adap.addItem(new msg(" How many people are there?", 0, da.format(new Date())));
            turns++;

        } else if (turns == 3 && test.contains("no")) {
            adap.addItem(new msg(" Here is our recommendation room type: suite on 20% off. ", 0, da.format(new Date())));
            turns++;
        } else if (turns == 3 && (test.contains("yes") || test.contains("sorry"))) {
            adap.addItem(new msg(" What room type you want?\n Pls select your desired room type. Here is our available room type: \n single room\n double room\n triple room\n suite", 0, da.format(new Date())));
            turns++;
        } else if (turns == 3 && test.contains("sorry")) {
            adap.addItem(new msg("wait", 0, da.format(new Date())));
            turns++;
        } else if (turns == 4 && test.contains("sorry")) {
            adap.addItem(new msg("Please wait for a second.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 5 && test.contains("sorry")) {
            adap.addItem(new msg("Do you have the reservation?", 0, da.format(new Date())));
            turns++;
        } else if (turns == 6 && test.contains("yes") || test.contains("sorry")) {
            adap.addItem(new msg("Give us one second, we are checking the data. Pls confirm your reservation: one double room.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 6 && (test.contains("no") || test.contains("sorry"))) {
            adap.addItem(new msg("Please wait for a second.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 7 && (test.contains("confirm") || test.contains("sorry"))) {
            adap.addItem(new msg("Please wait for a second.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 7 && (test.contains("wrong room"))) {
            adap.addItem(new msg("Enter the code that we gave you on the reservation email.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 8 && test.contains("111")) {
            adap.addItem(new msg("Check and proceed", 0, da.format(new Date())));
            turns++;
        } else if (turns == 8 && !test.contains("111")) {
            adap.addItem(new msg("Enter again or go back to last loop", 0, da.format(new Date())));
            turns++;
        } else if (turns == 9) {
            adap.addItem(new msg("Do you want to live near the lake?", 0, da.format(new Date())));
            turns++;
        } else if (turns == 10 && (test.contains("no") || test.contains("yes"))) {
            adap.addItem(new msg("Do you have the car?", 0, da.format(new Date())));
            turns++;
        } else if (turns == 11 && (test.contains("no") || test.contains("yes"))) {
            adap.addItem(new msg("Do you usually hang out at night?", 0, da.format(new Date())));
            turns++;
        } else if (turns == 12 && (test.contains("no") || test.contains("yes"))) {
            adap.addItem(new msg("Do you like to live close to elevator or not?", 0, da.format(new Date())));
            turns++;
        } else if (turns == 13 && (test.contains("no") || test.contains("yes"))) {
            adap.addItem(new msg("Do you usually go to the gym?", 0, da.format(new Date())));
            turns++;
        } else if (turns == 14 && (test.contains("no") || test.contains("yes"))) {
            adap.addItem(new msg("Do you want to go to the game room?", 0, da.format(new Date())));
            turns++;
        } else if (turns == 15 && (test.contains("no") || test.contains("yes"))) {
            adap.addItem(new msg("Do you want a drink in the night?", 0, da.format(new Date())));
            turns++;
        } else if (turns == 16 && (test.contains("no") || test.contains("yes"))) {
            adap.addItem(new msg("Do you need laundry service?", 0, da.format(new Date())));
            turns++;
        } else if (turns == 17 && (test.contains("no") || test.contains("yes"))) {
            adap.addItem(new msg("Do you like swimming or sauna?", 0, da.format(new Date())));
            turns++;
        } else if (turns == 18 && (test.contains("no") || test.contains("yes"))) {
            adap.addItem(new msg("According to your submitting data, we suggest that you should live in the room 405. And here is our hotel service, answer yes to proceed.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 19 && test.contains("yes")) {
            adap.addItem(new msg("When you look outside of your window, you can see the lake view, especially perfect when the sun rises.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 20 && test.contains("yes")) {
            adap.addItem(new msg("The parking lot is in the P2 and P3.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 21 && test.contains("yes")) {
            adap.addItem(new msg("If you want to hang out at night, you are free to do so, because all the people that choose to live closer to you is having the hang out habit.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 22 && test.contains("yes")) {
            adap.addItem(new msg("Elevator is on the left side of your door for about 100 meters.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 23 && test.contains("yes")) {
            adap.addItem(new msg("Gym is on the 10th floor, along with games room.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 24 && test.contains("yes")) {
            adap.addItem(new msg("Bar is at P1 floor along with a big casino.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 25 && test.contains("yes")) {
            adap.addItem(new msg("Laundry service is at 1st floor besides reception. If you want to call them, call the phone * 009", 0, da.format(new Date())));
            turns++;
        } else if (turns == 26 && test.contains("yes")) {
            adap.addItem(new msg("Swimming pool and sauna is on the 15th floor, and swimming pool is a outdoor swimming pool.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 27 && test.contains("yes")) {
            adap.addItem(new msg("We have 3 restaurants at 2nd floor, and they provide chinses food, Indian food, western food, Thai food, sushi, BBQ. You are welcome to enjoy them from 7 am to 12pm.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 28 && test.contains("yes")) {
            adap.addItem(new msg("The chambermaid usually come at 12 pm, if you don’t want them to come in, you can put don’t bother on the doorknob.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 29 && test.contains("yes")) {
            adap.addItem(new msg("In your room, Wi-Fi is provided. The instructions are on the nightstand besides your bed.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 30 && test.contains("yes")) {
            adap.addItem(new msg("The emergency exit is on the right of your door", 0, da.format(new Date())));
            turns++;
        } else if (turns == 31 && test.contains("yes")) {
            adap.addItem(new msg("The check out time is 11 am, if you want to extend the living time, you can call * 003 for service.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 32 && test.contains("yes")) {
            adap.addItem(new msg("If you want any additional food delivered to your room, you can call* 006 for it.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 33 && test.contains("yes")) {
            adap.addItem(new msg("Thank you for your patience, you are free to go to your room. If you have questions, answer yes, our porter will come to you in a second.", 0, da.format(new Date())));
            turns++;
        } else if (turns == 34 && test.contains("yes")) {
            adap.addItem(new msg("He is coming to you.", 0, da.format(new Date())));
            turns++;
        } else if (test.contains("fuck") || test.contains("shit")) {
            adap.addItem(new msg(" Please do not say dirty words.", 0, da.format(new Date())));
        } else if (test.contains("taxi") || test.contains("bus")) {
            adap.addItem(new msg("Please go outside, there are few taxies parking outside of our restaurant.", 0, da.format(new Date())));
        } else if (test.contains("enjoy") || test.contains("bar") || test.contains("play")) {
            adap.addItem(new msg(" Please go to the inquiry desk, it is just at the right corner of our lobby.", 0, da.format(new Date())));
        } else if (test.contains("toilet") || test.contains("washroom") || test.contains("restroom")) {
            adap.addItem(new msg(" Please go this way then turn left to the end of the corridor.", 0, da.format(new Date())));
        } else if (test.contains("ill") || test.contains("sick") || test.contains("cold")) {
            adap.addItem(new msg("Please try some pills, it will help you.", 0, da.format(new Date())));
        }


    }
}
