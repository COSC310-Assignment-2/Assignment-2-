package com.example.restaurant;

public class msg {
    public static final int type_BLE=0;

    private String content;
    private int type;
    private String time;
    public msg(String content,int type,String time){
        this.content=content;
        this.type=type;
        this.time=time;
    }
    public msg(){

    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String toString(){
        return "Msg{"+"content="+content+'\''+
                ",type=" +type+
                ",time='"+time+'\''+'}';
    }


}
