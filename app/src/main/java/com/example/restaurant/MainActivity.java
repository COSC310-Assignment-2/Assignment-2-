package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import butterknife.BindView;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.stemmer.PorterStemmer;
import opennlp.tools.tokenize.TokenizerModel;

import opennlp.tools.util.Span;


public class MainActivity extends AppCompatActivity {
    PorterStemmer porterStemmer;
    private List<msg> Msgs=new ArrayList<msg>();
    private NameFinderME nameFinderME;
    private ChatAdapter Adap;
    SimpleDateFormat da=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private RecyclerView chatlist;
    private EditText content1;
    private msg rep;
    private int turns=0;
    private Button send;
    InputStream inputStream ;
    String myname;

    public void chat(msg msg,ChatAdapter adap) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String test = msg.toString();
        String[] respon=new String[1];
        respon[0]=rep.getContent();

        if (turns == 0 && (test.contains("I am") || test.contains("I'm") || test.contains("good") || test.contains("fine"))) {
//            try {
//
//
//
//                inputStream = getAssets ( ).open ("en-ner-person.bin");
//                Log.e("name","1");
//                TokenNameFinderModel tokenModel =
//                        new TokenNameFinderModel(inputStream);
//                Log.e("name","2");
//                nameFinderME = new NameFinderME(tokenModel);
//                Log.e("name","3");
//                Span nameSpans[] = nameFinderME.find(respon);
//                Log.e("name",nameSpans.toString());
//                myname=nameSpans[0].toString();
//                Log.e("nname",nameSpans.toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            adap.addItem(new msg("Ok "+myname+"! How many people are there?" , 0, da.format(new Date())));
            turns++;
        }else if(test.contains("fuck")||test.contains("shit")){
            adap.addItem(new msg(" Please do not say dirty words.",0,da.format(new Date())));
        }else if(test.contains("taxi")||test.contains("bus")){
            adap.addItem(new msg("Please go outside, there are few taxies parking outside of our restaurant.",0,da.format(new Date())));
        }else if(test.contains("enjoy")||test.contains("bar")||test.contains("play")){
            adap.addItem(new msg(" Please go to the inquiry desk, it is just at the right corner of our lobby.",0,da.format(new Date())));
        }else if(test.contains("toilet")||test.contains("washroom")||test.contains("restroom")){
            adap.addItem(new msg(" Please go this way then turn left to the end of the corridor.",0,da.format(new Date())));
        }else if(test.contains("ill")||test.contains("sick")||test.contains("cold")){
            adap.addItem(new msg("Please try some pills, it will help you.",0,da.format(new Date())));

        } else if(turns==1&&(test.contains("1")||test.contains("2")||test.contains("3")||test.contains("4")||test.contains("5")||test.contains("6")||test.contains("7")||test.contains("8")||test.contains("one")||test.contains("two")||test.contains("three")||test.contains("four")||test.contains("five")||test.contains("six")
                    ||test.contains(" seven")||test.contains(" eight")||test.contains("nine")||test.contains("ten")||test.contains("9"))){
                        adap.addItem(new msg(" Is this your first time coming to our restaurant?",0,da.format(new Date())));
                        turns++;
        }
        else if(turns==2&&test.contains("Yes")){
            adap.addItem(new msg(" I am going to suggest our chef special for today.\n They are:\n A\nB\nC\nD",0,da.format(new Date())));
            turns++;


        }else if(turns==2&&test.contains("No")){
            adap.addItem(new msg(" Do you want to order together or separate",0,da.format(new Date())));
            turns++;

        }else if((turns==3)&&(test.contains("Together")||test.contains("Separate")||test.contains("separate")||test.contains("together"))){
            adap.addItem(new msg(" I am going to suggest our chef special for today.\n They are:\n A\nB\nC\nD",0,da.format(new Date())));
        }else if((turns>=3&&turns<5)&&(test.contains("A")||test.contains("B")||test.contains("C")||test.contains("D"))){
            adap.addItem(new msg(" Do you want to order anything else specific? ",0,da.format(new Date())));
        }else if((turns>=3&&turns<5)&&((test.contains("I ")||test.contains("want")||test.contains("take")||test.contains("would")||test.contains("order")))){
            if(test.contains(" soup")){
                adap.addItem(new msg(" Sorry, it is out of order. Suggesting similar stuff: E",0,da.format(new Date())));
                turns++;
            }else{
                adap.addItem(new msg(" Order added. Do you want to drink anything?",0,da.format(new Date())));
                turns++;
            }
        }else if(turns==5){
            if(test.contains("water")){
                adap.addItem(new msg(" You want some lemon? or ice?",0,da.format(new Date())));
            }else if(test.contains("coke")){
                adap.addItem(new msg(" Ice or not?",0,da.format(new Date())));
            }else if(test.contains("wine")){
                adap.addItem(new msg(" Sure, it will be ready soon",0,da.format(new Date())));
                adap.addItem(new msg(" And any appetizer? We suggest:\nA\nB\nC\nD",0,da.format(new Date())));

            }else if(test.contains("coffee")){
                adap.addItem(new msg(" Sure, it will be ready soon",0,da.format(new Date())));
                adap.addItem(new msg(" And any appetizer? We suggest:\nA\nB\nC\nD",0,da.format(new Date())));

            }
            turns++;
        }else if(turns==6){
            adap.addItem(new msg(" Here is the main dishes \n A\n B\n C\n D",0,da.format(new Date())));
            turns++;
        }else if(turns==7&&(test.contains("A")||test.contains("B")||test.contains("C")||test.contains("D"))){
            adap.addItem(new msg(" Alright, your dishes will be ready soon.",0,da.format(new Date())));
            turns++;
        }else if(turns==8&&test.contains("made")){
            adap.addItem(new msg(" It's made by balabala",0,da.format(new Date())));
            turns++;
        }else if(turns==9&&test.contains("organic")){
            adap.addItem(new msg(" Yes, it is",0,da.format(new Date())));
            turns++;
        }else if(turns==10&&test.contains("long")){
            adap.addItem(new msg(" About 20 mins",0,da.format(new Date())));
            adap.addItem(new msg("what the steak size you want? \nSuggestion: ( 6 oz, 8 oz, 10 oz, 12 oz)",0,da.format(new Date())));
            turns++;
        }else if(turns==11&&test.contains("oz")){
            adap.addItem(new msg(" How do you want your steak cooked?",0,da.format(new Date())));
            turns++;
        }else if(turns==12&&test.contains("rare")){
            adap.addItem(new msg(" How do you want your eggs cooked?",0,da.format(new Date())));
            turns++;
        }else if(turns==13&&test.contains("Sunny")){
            adap.addItem(new msg(" Is the side with (original side) ok?",0,da.format(new Date())));
            turns++;
        }else if(turns==14&&test.contains("Yes")){
            adap.addItem(new msg(" Want to upgrade fries to poutine with just \n2\n dollars?",0,da.format(new Date())));
            turns++;
        }else if(turns==15&&test.contains("Yes")){
            adap.addItem(new msg(" Want to order dessert right now or later or no dessert?",0,da.format(new Date())));
            turns++;
        }else if(turns==16){
            adap.addItem(new msg("  Here is the dessert menu \nA \nB \nC \nD",0,da.format(new Date())));
            turns++;
        }else if(turns==17&&test.contains("take")){
            adap.addItem(new msg(" Anything else want to add to order?",0,da.format(new Date())));
            turns++;
        }else if(turns==18){
            adap.addItem(new msg(" Thank you,do you want ot delete anything?",0,da.format(new Date())));
            turns++;
        }else if(turns==19){
            adap.addItem(new msg(" do you want to swap that with similar search items \nA \nB \nC \nD?",0,da.format(new Date())));
            turns++;
        }else if(turns==20){
            adap.addItem(new msg(" Any allergy?",0,da.format(new Date())));
            turns++;
        }else if(turns==21){
            adap.addItem(new msg(" In your order, main dish A has peanut? Do you want to delete that or switch that?",0,da.format(new Date())));
            turns++;
        }else if(turns==22){
            adap.addItem(new msg(" Order completed, total coming time: ",0,da.format(new Date())));
            turns++;
        }else if(turns==23&&test.contains("check")){
            adap.addItem(new msg(" Pay bills by card or cash?",0,da.format(new Date())));
            turns++;
        }else if(turns==24&&test.contains("card")){
            adap.addItem(new msg(" Credit or debit card?",0,da.format(new Date())));
            turns++;
        }else if(turns==25&&test.contains("card")){
            adap.addItem(new msg(" Servers coming over and grab the post machine",0,da.format(new Date())));
            turns++;
        }else if(turns==26){
            adap.addItem(new msg(" Do you want to write the feed back?",0,da.format(new Date())));
            turns++;
        }else if(turns==27){
            adap.addItem(new msg("Thank you! See you next time!",0,da.format(new Date())));
            turns++;
        }
    }






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
        msg start=new msg("Hi, how are you today? May I get your name?", 0,da.format(new Date()));
        Msgs.add(start);

        Adap=new ChatAdapter(this,Msgs);
        chatlist.setAdapter(Adap);
        chatlist.scrollToPosition(Adap.getItemCount()-1);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String content = content1.getText().toString();
                porterStemmer = new PorterStemmer();
                String stem = porterStemmer.stem(content);
                rep=new msg(stem, 1, da.format(new Date()));


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
}
