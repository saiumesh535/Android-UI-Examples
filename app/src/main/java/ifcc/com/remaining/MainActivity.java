package ifcc.com.remaining;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    Spinner spinner;
    AutoCompleteTextView autoCompleteTextView;
    Button showDateDialoge,showTimePicker,startService;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // git has been added

        spinner                 =   (Spinner)findViewById(R.id.spinner);
        autoCompleteTextView    =   (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        showDateDialoge         =   (Button)findViewById(R.id.showDateDialoge);
        showTimePicker          =   (Button)findViewById(R.id.showTimePicker);
        progressBar             =   (ProgressBar)findViewById(R.id.progressBar);
        startService            =   (Button)findViewById(R.id.startService);

        final String [] names = new String[]{"c","c++","android"};

        // array adpter helps us to hold the data
        ArrayAdapter<String> spinner_data = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,names);

        spinner.setAdapter(spinner_data);

        // when user selects any option
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,names[position],Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this,"nothing being selected",Toast.LENGTH_LONG).show();
            }
        });

        // setting up the adapter for the auto complete textView
        ArrayAdapter<String> autotext_adapter = new ArrayAdapter<>(this,android.R.layout.select_dialog_item,names);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(autotext_adapter);

        // open date picker when user clicks on button
        showDateDialoge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Toast.makeText(MainActivity.this,dayOfMonth+"/"+monthOfYear+"/"+year,Toast.LENGTH_LONG).show();
                    }
                },mYear,mMonth,mDay);

                // show date dialoge when user clicks on button
                datePickerDialog.setTitle("Select Date");
                datePickerDialog.show();
            }
        });

        // show time picker when user clicks on button
        showTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                final int min = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(MainActivity.this, hourOfDay + ":" + min, Toast.LENGTH_LONG).show();
                    }
                }, hour, min, true); // true for 24 hours format
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });
        // styling the progress bar
        progressBar.setProgress(30);
        Toast.makeText(MainActivity.this,"Progress bar is at "+progressBar.getProgress(),Toast.LENGTH_LONG).show();

        // starting the service when user clicks on start service button
        // Please remember to define service in manifest file
        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this,AndroidService.class));
                Toast.makeText(MainActivity.this,"Sevice started",Toast.LENGTH_LONG).show();
            }
        });
    }
}
