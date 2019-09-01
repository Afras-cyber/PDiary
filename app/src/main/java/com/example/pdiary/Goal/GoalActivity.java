package com.example.pdiary.Goal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pdiary.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class GoalActivity extends AppCompatActivity {
    private int currentYear = 0;
    private int currentMonth = 0;
    private int currentDay = 0;
    List<String> calanederString;
    private int index=0;
    int[]Days;
    int []Months;
    int []Year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        final CalendarView calendarView=(CalendarView)findViewById(R.id.calender_view_id);
        final TextView selectDay =(TextView)findViewById(R.id.txt_calender);
        final TextView display =(TextView)findViewById(R.id.txt_show_id);
        final EditText textinput=(EditText)findViewById(R.id.textInput);
        final Button saveButton=(Button)findViewById(R.id.saveTextButton);
        calanederString=new ArrayList<>();
        final View daycContent=findViewById(R.id.dayContents);
        Days = new int[30];
        Months=new int[12];
        Year =new int [10];

        readinfo();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayofMonth) {
                selectDay.setText("Day : " + String.valueOf(dayofMonth));
                currentYear = year;
                currentMonth = month;
                currentDay = dayofMonth;
                if (daycContent.getVisibility() == View.GONE) {
                    daycContent.setVisibility(View.VISIBLE);
                }
                for (int h = 0; h < index; h++) {
                    if (Year[h] == currentYear) {
                        for (int i = 0; i < index; i++) {
                            if (Days[i] == currentDay) {
                                for (int j = 0; j < index; j++) {
                                    if (Months[j] == currentMonth && Days[j] == currentDay && Year[h] == currentYear) {
                                        display.setText(calanederString.get(i));
                                        return;
                                    }
                                }
                            }
                        }
                    }
                         /*
                 long savedDate = long.parseLong(calanederString.get(0));
                 if (view.getDate()== savedDate){
                     textinput.setText(calanederString.get(1));
                 }*/
                } display.setText("");textinput.setText("");

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Days[index]=currentDay;
                Months[index]=currentMonth;
                Year[index]=currentYear;
                calanederString.add(index,textinput.getText().toString());
                display.setText("");
                textinput.setText("");
                index++;
                daycContent.setVisibility(View.GONE);
            }
        });
    }
    @Override
    protected void onPause(){
        super.onPause();
        saveInfo();
    }

    private void saveInfo() {
        File file =new File(this.getFilesDir(),"saved");
        File dayFile =new File(this.getFilesDir(),"days");
        File monthFile =new File(this.getFilesDir(),"months");
        File yearFile =new File(this.getFilesDir(),"years");

        try{
            FileOutputStream fOut =new FileOutputStream(file);
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fOut));

            FileOutputStream fOutDays =new FileOutputStream(file);
            BufferedWriter bwDays=new BufferedWriter(new OutputStreamWriter(fOutDays));

            FileOutputStream fOutMonth =new FileOutputStream(file);
            BufferedWriter bwMonth=new BufferedWriter(new OutputStreamWriter(fOutMonth));

            FileOutputStream fOutYear =new FileOutputStream(file);
            BufferedWriter bwYear=new BufferedWriter(new OutputStreamWriter(fOutYear));

            for(int i = 0; i<index;i++){
                bw.write(calanederString.get(i));
                bw.newLine();
                bwDays.write(Days[i]);
                bwMonth.write(Months[i]);
                bwYear.write(Year[i]);
            }
            bw.close();
            fOut.close();
            bwDays.close();
            fOutDays.close();
            fOutMonth.close();
            bwMonth.close();
            fOutYear.close();
            bwYear.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void readinfo() {
        File file =new File(this.getFilesDir(),"saved");
        File dayFile =new File(this.getFilesDir(),"days");
        File monthFile =new File(this.getFilesDir(),"months");
        File yearFile =new File(this.getFilesDir(),"years");

        if(!file.exists()){
            return;
        }
        try{
            FileInputStream is =new FileInputStream(file);
            BufferedReader reader=new BufferedReader(new InputStreamReader(is));

            FileInputStream isDays =new FileInputStream(file);
            BufferedReader readerDays=new BufferedReader(new InputStreamReader(isDays));

            FileInputStream isMonth =new FileInputStream(file);
            BufferedReader readerMonth=new BufferedReader(new InputStreamReader(isMonth));

            FileInputStream isYear =new FileInputStream(file);
            BufferedReader readerYear=new BufferedReader(new InputStreamReader(isYear));

            int i =0;
            String line=reader.readLine();
            while (line!= null){
                calanederString.add(line);
                line=reader.readLine();
                Days[i]=readerDays.read();
                Months[i]=readerMonth.read();
                Year[i]=readerYear.read();
                i++;
            }
            index=i;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

