package com.example.tugas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    Spinner spinnerText;
    Button submit;
    EditText tanggal;
    EditText jam;
    LinearLayout keterangan;
    String mSpinnerText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Adding View
        spinnerText = findViewById(R.id.Status);
        submit = findViewById(R.id.buttonSubmit);
        tanggal = findViewById(R.id.Date);
        jam = findViewById(R.id.Time);
        keterangan = findViewById(R.id.Keterangan);

        // Making the EditText Uneditable
        tanggal.setKeyListener(null);
        jam.setKeyListener(null);


        // Making spinner & the adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.statuses, android.R.layout.simple_spinner_item);
        spinnerText.setAdapter(adapter);
        if (spinnerText != null){
            spinnerText.setOnItemSelectedListener(this);
        }

        //Making the popup
        tanggal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                showDataPicker();
            }
        });
        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDataPicker();
            }
        });
        jam.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                showTimePicker();
            }
        });
        jam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });

        // Button Submit logic
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        }

        //Alert Dialog
        public void showAlertDialog(){
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
            alertBuilder.setTitle("Konfirmasi");
            alertBuilder.setMessage("Apakah ankamu yakin data yang akan kamu kirim sudah sesuai?");

            alertBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Absen Berhasil", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            });

            alertBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {}
            });
            alertBuilder.show();
        }

        // Showing The fragments
    public void showDataPicker(){
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "date-picker");}
    public void showTimePicker(){
        DialogFragment timeFragment = new TimePickerFragment();
        timeFragment.show(getSupportFragmentManager(), "time-picker");}


    // Adding Text process
    public void processDatePicker(int day, int month, int year){
        String day_string = Integer.toString(day);
        String month_string = Integer.toString(month+1);
        String year_string = Integer.toString(year);
        String dateMessage = day_string + "-" + month_string + "-" + year_string;
        tanggal.setText(dateMessage);
    }
    public void ProcessTimePicker(int hour, int minute){
        String hours = Integer.toString(hour);
        String minutes = Integer.toString(minute);
        String dateMessage = hours + "." + minutes;
        jam.setText(dateMessage);
    }

    //Spinner Logic
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mSpinnerText = adapterView.getItemAtPosition(i).toString();
        if (mSpinnerText.equals("Hadir tepat waktu")){
            keterangan.setVisibility(View.INVISIBLE);
        }
        else keterangan.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        keterangan.setVisibility(View.INVISIBLE);
    }




}