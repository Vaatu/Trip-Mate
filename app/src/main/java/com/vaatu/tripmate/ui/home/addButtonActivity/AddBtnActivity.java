package com.vaatu.tripmate.ui.home.addButtonActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vaatu.tripmate.R;
import com.vaatu.tripmate.utils.dateTimePicker.DatePickerFragment;
import com.vaatu.tripmate.utils.dateTimePicker.TimePickerFragment;

import android.app.DatePickerDialog;


import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddBtnActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    private static final String LOG_TAG = "HERE Auto Suggest";


    @BindView(R.id.add_trip_btn)
    Button addTripBtn;
    @BindView(R.id.repeat_spinner)
    Spinner repeatSpinner;
    @BindView(R.id.trip_way_spinner)
    Spinner tripWaySpinner;
    @BindView(R.id.repeat_spin_linearlayout)
    LinearLayout repeatSpinLinearlayout;
    @BindView(R.id.add_note_btn)
    ImageButton addNoteBtn;
    @BindView(R.id.note_text_field)
    TextInputLayout noteTextField;
    @BindView(R.id.notes_linearLayout)
    LinearLayout notesLinearLayout;
    @BindView(R.id.dateTextField)
    TextInputEditText dateTextField;
    @BindView(R.id.timeTextField)
    TextInputEditText timeTextField;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.trip_name_text_field)
    TextInputLayout tripNameTextField;
    AutocompleteSupportFragment placeStartPointAutoComplete;
    AutocompleteSupportFragment placeDestPointAutoComplete;
    PlacesClient mPlacesClient;
    List<Place.Field> placeField = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS);
    int hour;
    int minutes;
    int increasedID = 0;
    ArrayAdapter<CharSequence> adapterTripDirectionSpin;
    ArrayAdapter<CharSequence> adapterTripRepeatSpin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_btn);
        ButterKnife.bind(this);
        //Auto Complete Google
        setUpAutoComplete();

        //Spinner init
        spinnerInit();
    }

    private void setUpAutoComplete() {
        if (!Places.isInitialized()) {
            // @TODO Get Places API key
            Places.initialize(getApplicationContext(), "AIzaSyDbQxlvW4q0t1rhRHicRHVDzWbDP8y1Hlc");
            //Init Frags
            placeStartPointAutoComplete = (AutocompleteSupportFragment)
                    getSupportFragmentManager().findFragmentById(R.id.start_autoComplete_Frag);
            placeStartPointAutoComplete.setPlaceFields(placeField);

            placeDestPointAutoComplete = (AutocompleteSupportFragment)
                    getSupportFragmentManager().findFragmentById(R.id.dest_autoComplete_Frag);
            placeDestPointAutoComplete.setPlaceFields(placeField);

            placeStartPointAutoComplete.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                @Override
                public void onPlaceSelected(Place place) {
                    // TODO: Get info about the selected place.
                    Log.i("Places", "Place: " + place.getAddress() + ", " + place.getId());
                }

                @Override
                public void onError(Status status) {
                    // TODO: Handle the error.
                    Log.i("Places", "An error occurred: " + status);
                }
            });
            placeDestPointAutoComplete.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                @Override
                public void onPlaceSelected(Place place) {
                    // TODO: Get info about the selected place.
                    Log.i("Places", "Place: " + place.getAddress() + ", " + place.getId());
                }

                @Override
                public void onError(Status status) {
                    // TODO: Handle the error.
                    Log.i("Places", "An error occurred: " + status);
                }
            });


        }

    }

    @OnClick({R.id.add_trip_btn, R.id.add_note_btn, R.id.notes_linearLayout, R.id.dateTextField, R.id.timeTextField})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_trip_btn:
                Toast.makeText(this, "Hello add_trip_btn :)", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_note_btn:
//                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.notes_linearLayout);
//                linearLayout.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
//                linearLayout.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
//                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
//                linearLayout.setId(R.id.notes_linearLayout + increasedID);
//
//                View v = getLayoutInflater().inflate(R.layout.activity_add_btn, null);
//
//                ImageButton imgBtn = v.findViewById(R.id.add_note_btn);
//                imgBtn.setId(R.id.add_note_btn + increasedID);
//
//                TextInputLayout noteText = v.findViewById(R.id.note_text_field);
//                noteText.setId(R.id.add_note_btn + increasedID);
//
//                LinearLayout parent = findViewById(R.id.notes_parent_linear_Layout);
//                parent.addView(linearLayout);
//
//
//                increasedID++;
                break;
            case R.id.notes_linearLayout:
                break;
            case R.id.dateTextField:
                DialogFragment datepicker = new DatePickerFragment();
                datepicker.show(getSupportFragmentManager(), "date");

                break;
            case R.id.timeTextField:
                DialogFragment timepicker = new TimePickerFragment();
                timepicker.show(getSupportFragmentManager(), "time");

                break;
        }
    }

    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, i);
        c.set(Calendar.MONTH, i1);
        c.set(Calendar.DAY_OF_MONTH, i2);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        Log.i("Date Time Picker", currentDateString);
        dateTextField.setText(currentDateString);

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

        hour = i;
        minutes = i1;
        String timeSet = "";
        if (hour > 12) {
            hour -= 12;
            timeSet = "PM";
        } else if (hour == 0) {
            hour += 12;
            timeSet = "AM";
        } else if (hour == 12) {
            timeSet = "PM";
        } else {
            timeSet = "AM";
        }

        String min = "";
        if (minutes < 10)
            min = "0" + minutes;
        else
            min = String.valueOf(minutes);

        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hour).append(':')
                .append(min).append(" ").append(timeSet).toString();
        timeTextField.setText(aTime);
    }

    private void spinnerInit() {
        //Trip Direction Spinner
        adapterTripDirectionSpin = ArrayAdapter.createFromResource(this,
                R.array.trip_direction_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterTripDirectionSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        tripWaySpinner.setAdapter(adapterTripDirectionSpin);
        tripWaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                Log.i("Spinner", adapterView.getItemAtPosition(pos).toString() + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Trip Repeat Spinner
        adapterTripRepeatSpin = ArrayAdapter.createFromResource(this,
                R.array.times_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterTripRepeatSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        repeatSpinner.setAdapter(adapterTripRepeatSpin);
        repeatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                Log.i("Spinner", adapterView.getItemAtPosition(pos).toString() + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
