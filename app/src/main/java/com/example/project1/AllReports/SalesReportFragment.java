package com.example.project1.AllReports;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.project1.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SalesReportFragment extends Fragment {
    TextView datePick;
    DatePickerDialog.OnDateSetListener dateSetListener;

    public SalesReportFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sales_report, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        datePick = view.findViewById(R.id.datePick_id);

        Calendar calendar= Calendar.getInstance();

        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);


        datePick.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), (datePicker, year1, month1, day1) -> {
                            month1 = month1 + 1;
                            String m,d;
                            if(month1 <10) m="0"+ month1;else m= String.valueOf(month1);
                            if(day1 <10) d="0"+ day1;else d= String.valueOf(day1);
                            String date = year1 + "-" + m + "-" + d;
                            @SuppressLint("SimpleDateFormat")
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date date1 = format.parse(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            datePick.setText(date);
                        },year,month,day);
                datePickerDialog.show();
            }
        });
    }
}