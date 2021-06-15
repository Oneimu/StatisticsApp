package com.example.statistics;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Character.isDigit;

public class Statistics {
    private String mode, range, maximum, minimum, sum, count;
    private String mean, median;
    private List<Integer> list_num;


    @RequiresApi(api = Build.VERSION_CODES.N)

    // this method takes in a string of numbers; each unique number are seperated with commas, so the method cast the string numbers back to numbers and save a sorted form into an array
    public void stringNumbers(String items) {
        List numbers = new ArrayList<Integer>();
        String num ="";

        for (int i = 0; i < items.length(); i++) {

            try{

                if (' ' != items.charAt(i)){
                    num += items.charAt(i);

                }else {
                    numbers.add(Integer.parseInt(num));
                    num = "";
                }

                if (i == items.length()-1){
                    numbers.add(Integer.parseInt(num));
                }
            }catch (NumberFormatException e){

            }

        }

        Collections.sort(numbers);
        list_num = numbers;
    }

    // the methods below this line are used to send the statistic attributes of the code, this includes method to find the Mode, Mean, Median, etc

    @RequiresApi(api = Build.VERSION_CODES.N)
    public  String setMode() {


        HashMap<Integer, Integer>  diction = new HashMap<>();
        int max = 0;

        for (int num: list_num){
            if (diction.containsKey(num)){
                int incre = diction.get(num) + 1;
                if (incre > max){
                    max = incre;
                }
                diction.put(num, incre);
            }else{
                diction.put(num, 1);
            }

        }

        if (max == 0){
            return "0";
        }

        int record = 0;
        int val = 0;

        for (int num: diction.keySet()){
            if (max == diction.get(num)){
                record +=1;
                val = num;
            }
        }

        if (record > 1){
            return "0";
        }
        return val +"";

    }


    public  String setMaximum(){
        return list_num.get(list_num.size()-1)+"";
    }
    public String setMinimum(){
        return list_num.get(0)+"";
    }

    public String setRange(){
        int r = list_num.get(list_num.size()-1) - list_num.get(0) ;
        return r+"";
    }

    public  String setSum(){
        int sum_num = 0;
        for (int i: list_num){
            sum_num += i;
        }
        return sum_num +"";
    }
    public String setCount(){
        return list_num.size()+"";
    }

    public String  setMedian(){

        double median_num;

        int len_numbers = list_num.size();
        if ( len_numbers %2 == 0){
            int num1 = list_num.get(len_numbers/2 - 1);
            int num2 = list_num.get(len_numbers/2);
            double avg = (double)(num1 + num2)/2;
            return avg+"";

        }else{
            return list_num.get(len_numbers/2) + "";
        }

    }

    public String setMean(){
        int sum_n = Integer.parseInt(setSum());
        double mean_numbers = (double) sum_n/list_num.size();
        double m = Math.round(mean_numbers*10)/10.0;
        return m +"";
    }

    // return values
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getResult(String type){


        if (list_num.size() == 0){
            return "N/A";
        }

        if (type.equals("Median")){
            return  setMedian();
        }else if(type.equals("Mode")){
            return setMode();
        }else if (type.equals("Mean")){
            return setMean();
        }else if(type.equals("Sum")){
            return setSum();
        }else if(type.equals("Count")){
            return setCount();
        }else if(type.equals("Maximum")){
            return setMaximum();
        }else if(type.equals("Minimum")) {
            return setMinimum();
        }else{
            return setRange();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getMessage(String type){


        if (type.equals("Median")){
            return 4;
        }else if(type.equals("Mode")){
            return 0;
        }else if (type.equals("Mean")){
            return 3;
        }else if(type.equals("Sum")){
            return 7;
        }else if(type.equals("Count")){
            return 6;
        }else if(type.equals("Maximum")){
            return 1;
        }else if(type.equals("Minimum")) {
            return 2;
        }else{
            return 5;
        }
    }


}