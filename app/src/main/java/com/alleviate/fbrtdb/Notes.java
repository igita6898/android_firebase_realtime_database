package com.alleviate.fbrtdb;

/**
 * Created by felix on 8/8/16.
 */
public class Notes {

    public String title;
    public String data;
    public String contributer;
    public String logs;

    public Notes(){

    }

    public Notes (String title,String data,String contributer,String logs){

        this.title = title;
        this.data = data;
        this.contributer = contributer;
        this.logs = logs;
    }
}
