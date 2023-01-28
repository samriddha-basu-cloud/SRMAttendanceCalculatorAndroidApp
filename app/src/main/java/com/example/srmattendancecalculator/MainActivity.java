package com.example.srmattendancecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private String selectedBranch, selectedDept, selectedYear, selectedSection, selectedSubject, atnd, percnt;
    private final String cnt="Not Possible", fld="Fulfilled";
    private TextView tvBranchSpinner, tvDeptSpinner, tvYearSpinner, tvSectionSpinner, tvSubjectSpinner, tvAbsentHourSpinner;
    private Spinner spinnerbranch, spinnerdept, spinneryear, spinnersection, spinnersubject;
    private ArrayAdapter<CharSequence> adapterbranch, adapterdept, adapteryear, adaptersection, adaptersubject;

    int c=0;int Absent=0;
    double percent=0.0d;
    private int i=0,TotalbfrCT1=0,TotalbfrCT2=0,TotalbfrCT3=0,TotalbfrSem=0,Yetclass=0,criteria75=0,ClassesLeft=0,Attended=0,ToAttend=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText editText=(EditText) findViewById(R.id.editText);



        //Spinner Initialization
        spinnerbranch = findViewById(R.id.spinnerbranch);
//        spinnerdept = findViewById(R.id.spinnerdept);
//        spinneryear = findViewById(R.id.spinneryear);
//        spinnersection = findViewById(R.id.spinnersection);

        //Adapter Initialization
        adapterbranch=ArrayAdapter.createFromResource(this, R.array.array_branch, R.layout.item_file);

        adapterbranch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerbranch.setAdapter(adapterbranch);

        spinnerbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                spinnerdept = findViewById(R.id.spinnerdept);

                selectedBranch = spinnerbranch.getSelectedItem().toString();
                int parentID = adapterView.getId();
                if(parentID == R.id.spinnerbranch)
                {
                    switch(selectedBranch)
                    {
                        case "Select Branch":
                            adapterdept = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_default_dept, R.layout.item_file);
                        break;
                        case "B. Tech.":
                            adapterdept = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_btech_dept, R.layout.item_file);
                            break;
                        case "B. Sc.":
                            adapterdept = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_bsc_dept, R.layout.item_file);
                            break;
                        case "B. B. A.":
                            adapterdept = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_bba_dept, R.layout.item_file);
                            break;
                        case "B. Pharma.":
                            adapterdept = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_bpharma_dept, R.layout.item_file);
                            break;
                        case "M. Tech.":
                            adapterdept = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_mtech_dept, R.layout.item_file);
                            break;

                        default: break;
                    }
                    adapterdept.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerdept.setAdapter(adapterdept);

                    spinnerdept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            spinneryear = findViewById(R.id.spinneryear);
                            selectedDept = spinnerdept.getSelectedItem().toString();

                            int parentID = adapterView.getId();
                            if(parentID == R.id.spinnerdept)
                            {
                                switch(selectedDept)
                                {
                                    case "Select Dept":
                                        adapteryear = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_default_year, R.layout.item_file);
                                        break;
                                    case "CSE":
                                        adapteryear = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_cse_year, R.layout.item_file);
                                        break;
                                    case "CSBS":
                                        adapteryear = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_csbs_year, R.layout.item_file);
                                        break;
                                    case "EEE":
                                        adapteryear = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_eee_year, R.layout.item_file);
                                        break;
                                    case "ECompE":
                                        adapteryear = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_ecompe_year, R.layout.item_file);
                                        break;
                                    case "Civil":
                                        adapteryear = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_civil_year, R.layout.item_file);
                                        break;
                                    case "ECommE":
                                        adapteryear = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_ecomme_year, R.layout.item_file);
                                        break;
                                    case "Mech":
                                        adapteryear = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_mech_year, R.layout.item_file);
                                        break;
                                    case "Auto":
                                        adapteryear = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_auto_year, R.layout.item_file);
                                        break;

                                    default: break;
                                }
                                adapteryear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinneryear.setAdapter(adapteryear);

                                spinneryear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        spinnersection = findViewById(R.id.spinnersection);
                                        selectedYear = spinneryear.getSelectedItem().toString();

                                        int parentID = adapterView.getId();
                                        if(parentID == R.id.spinneryear)
                                        {
                                            switch(selectedYear)
                                            {
                                                case "Select Year":
                                                    adaptersection = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_default_section, R.layout.item_file);
                                                    break;
                                                case "First":
                                                    adaptersection = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_first_year, R.layout.item_file);
                                                    break;
                                                case "Second":
                                                    adaptersection = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_second_year, R.layout.item_file);

                                                    break;
                                                case "Third":
                                                    adaptersection = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_third_year, R.layout.item_file);
                                                    break;
                                                case "Fourth":
                                                    adaptersection = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_fourth_year, R.layout.item_file);
                                                    break;

                                                default: break;
                                            }
                                            adaptersection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                            spinnersection.setAdapter(adaptersection);

                                            spinnersection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                    spinnersubject = findViewById(R.id.spinnersubject);
                                                    selectedSection = spinnersection.getSelectedItem().toString();

                                                    int parentID = adapterView.getId();
                                                    if(parentID == R.id.spinnersection)
                                                    {
                                                        switch (selectedSection)
                                                        {
                                                            case "Select Section":
                                                                adaptersubject = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_default_subject, R.layout.item_file);
                                                                break;
                                                            case "A":
                                                                adaptersubject = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_subject_A, R.layout.item_file);
                                                                break;
                                                            case "B":
                                                                adaptersubject = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_subject_B, R.layout.item_file);

                                                                break;
                                                            case "C":
                                                                adaptersubject = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_subject_C, R.layout.item_file);
                                                                break;
                                                            case "D":
                                                                adaptersubject = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_subject_D, R.layout.item_file);
                                                                break;

                                                            default:
                                                                break;
                                                        }
                                                        adaptersubject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                        spinnersubject.setAdapter(adaptersubject);

                                                        spinnersubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                            @Override
                                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                selectedSubject = spinnersubject.getSelectedItem().toString();
                                                            }

                                                            @Override
                                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                                            }
                                                        });
                                                    }
                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> adapterView) {

                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });







        Button button_submit = (Button)findViewById(R.id.button_submit);
        tvBranchSpinner = findViewById(R.id.textView_branch);
        tvDeptSpinner = findViewById(R.id.textView_dept);
        tvYearSpinner = findViewById(R.id.textView_year);
        tvSectionSpinner = findViewById(R.id.textView_section);



        button_submit.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, outputScreen.class);

            if (selectedBranch.equals("Select Branch")) {
                tvBranchSpinner.setError("Branch Required!");
                tvBranchSpinner.requestFocus();
            }
            else {
//
                tvBranchSpinner.setError(null);


                try {

                    Date date1;
                    Date date2;
                    Date datect1;
                    Date datect2;
                    Date datect3;
                    Date datesem;
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                    String currentDate = sdf.format(new Date());
                    String startDate = "01/04/2023";
                    String CT1Date = "02/02/2023";
                    String CT2Date = "03/13/2023";
                    String CT3Date = "05/08/2023";
                    String semDate = "05/19/2023";
                    date1 = sdf.parse(currentDate);
                    date2 = sdf.parse(startDate);
                    datect1 = sdf.parse(CT1Date);
                    datect2 = sdf.parse(CT2Date);
                    datect3 = sdf.parse(CT3Date);
                    datesem = sdf.parse(semDate);

                    //Difference between current date and today
                    long difference = Math.abs(date1.getTime() - date2.getTime());
                    long differenceDates = difference / (24 * 60 * 60 * 1000);
                    String dayDifference = Long.toString(differenceDates);
                    //Toast.makeText(this, "Date difference between start and today "+dayDifference+" days", Toast.LENGTH_LONG).show();

                    //Difference between current date and CT1
                    long differencect1 = Math.abs(date2.getTime() - datect1.getTime());
                    long differenceDatesct1 = differencect1 / (24 * 60 * 60 * 1000);
                    String dayDifferencect1 = Long.toString(differenceDatesct1);
                    //Toast.makeText(this, "Date difference between start and CT1 "+dayDifferencect1+" days", Toast.LENGTH_LONG).show();

                    //Difference between current date and CT2
                    long differencect2 = Math.abs(date2.getTime() - datect2.getTime());
                    long differenceDatesct2 = differencect2 / (24 * 60 * 60 * 1000);
                    String dayDifferencect2 = Long.toString(differenceDatesct2);
                    //Toast.makeText(this, "Date difference between start and CT2 "+dayDifferencect2+" days", Toast.LENGTH_LONG).show();

                    //Difference between current date and CT3
                    long differencect3 = Math.abs(date2.getTime() - datect3.getTime());
                    long differenceDatesct3 = differencect3 / (24 * 60 * 60 * 1000);
                    String dayDifferencect3 = Long.toString(differenceDatesct3);
                    //Toast.makeText(this, "Date difference between start and Ct3 "+dayDifferencect3+" days", Toast.LENGTH_LONG).show();

                    //Difference between current date and CT1
                    long differencesem = Math.abs(date2.getTime() - datesem.getTime());
                    long differenceDatessem = differencesem / (24 * 60 * 60 * 1000);
                    String dayDifferencesem = Long.toString(differenceDatessem);
                    //Toast.makeText(this, "Date difference between start and Semester 6 "+dayDifferencesem+" days", Toast.LENGTH_LONG).show();

                    String ab = editText.getText().toString();
                    if (!"".equals(ab))
                        Absent = Integer.parseInt(ab);

                    if (selectedBranch.equals("B. Tech.")) {
                        if (selectedDept.equals("CSE")) {
                            if (selectedYear.equals("Third")) {
                                if (selectedSection.equals("D")) {

                                    //Compiler Design
                                    if (selectedSubject.equals("Comp. Des.")) {
                                        for (i = 1; i <= differenceDates + 1; i++) {
                                            if (i % 7 == 1 || i % 7 == 2 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                            if (i % 7 == 3)
                                                c = c + 2;

                                        }
                                        Yetclass = c;
                                        c = 0;
                                        i = 0;
                                        percent = (double) ((Yetclass - Absent) * 100 / Yetclass);
                                        percnt = Double.toString(percent) + "%";
                                        intent.putExtra("PER", percnt);

                                        //CT1

                                        for (i = 1; i <= differenceDatesct1 + 1; i++) {
                                            if (i % 7 == 1 || i % 7 == 2 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                            if (i % 7 == 3)
                                                c = c + 2;

                                        }
                                        TotalbfrCT1 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT1 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT1 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT1", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT1", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT1", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //CT2

                                        for (i = 1; i <= differenceDatesct2 + 1; i++) {
                                            if (i % 7 == 1 || i % 7 == 2 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                            if (i % 7 == 3)
                                                c = c + 2;

                                        }
                                        TotalbfrCT2 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT2 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT2 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT2", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT2", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT2", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //CT3

                                        for (i = 1; i <= differenceDatesct3 + 1; i++) {
                                            if (i % 7 == 1 || i % 7 == 2 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                            if (i % 7 == 3)
                                                c = c + 2;

                                        }
                                        TotalbfrCT3 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT3 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT3 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT3", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT3", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT3", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //EndSem

                                        for (i = 1; i <= differenceDatessem + 1; i++) {
                                            if (i % 7 == 1 || i % 7 == 2 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                            if (i % 7 == 3)
                                                c = c + 2;

                                        }
                                        TotalbfrSem = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrSem * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrSem - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("SEM", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("SEM", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("SEM", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }
                                        Absent=0;

                                        startActivity(intent);
                                    }


                                    //i=0;TotalbfrCT1=0;TotalbfrCT2=0;TotalbfrCT3=0;TotalbfrSem=0;Yetclass=0;criteria75=0;ClassesLeft=0;Attended=0;ToAttend=0;

                                    if (selectedSubject.equals("Web Dev")) {
                                        //Web Desgin
                                        for (i = 1; i <= differenceDates + 1; i++) {
                                            if (i % 7 == 3 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                            if (i % 7 == 2)
                                                c = c + 2;

                                        }

                                        Yetclass = c;
                                        c = 0;
                                        i = 0;
                                        percent = (double) ((Yetclass - Absent) * 100 / Yetclass);
                                        percnt = Double.toString(percent) + "%";
                                        intent.putExtra("PER", percnt);

                                        //CT1

                                        for (i = 1; i <= differenceDatesct1 + 1; i++) {
                                            if (i % 7 == 3 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                            if (i % 7 == 2)
                                                c = c + 2;

                                        }
                                        TotalbfrCT1 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT1 * 0.75);
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT1 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT1", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT1", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT1", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //CT2

                                        for (i = 1; i <= differenceDatesct2 + 1; i++) {
                                            if (i % 7 == 3 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                            if (i % 7 == 2)
                                                c = c + 2;

                                        }
                                        TotalbfrCT2 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT2 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT2 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT2", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT2", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT2", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //CT3

                                        for (i = 1; i <= differenceDatesct3 + 1; i++) {
                                            if (i % 7 == 3 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                            if (i % 7 == 2)
                                                c = c + 2;

                                        }
                                        TotalbfrCT3 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT3 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT3 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT3", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT3", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT3", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //EndSem

                                        for (i = 1; i <= differenceDatessem + 1; i++) {
                                            if (i % 7 == 3 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                            if (i % 7 == 2)
                                                c = c + 2;

                                        }
                                        TotalbfrSem = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrSem * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrSem - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("SEM", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("SEM", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("SEM", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        Absent=0;
                                        startActivity(intent);
                                    }


                                    //i=0;TotalbfrCT1=0;TotalbfrCT2=0;TotalbfrCT3=0;TotalbfrSem=0;Yetclass=0;criteria75=0;ClassesLeft=0;Attended=0;ToAttend=0;


                                    //AI
                                    if (selectedSubject.equals("A.I. n App in CC")) {
                                        for (i = 1; i <= differenceDates + 1; i++) {
                                            if (i % 7 == 3 || i % 7 == 0)
                                                c++;
                                            if (i % 7 == 1 || i % 7 == 2)
                                                c = c + 2;

                                        }

                                        //Toast.makeText(this, "Classes covered for AI "+c+" Hours", Toast.LENGTH_LONG).show();
                                        Yetclass = c;
                                        c = 0;
                                        i = 0;
                                        percent = (double) ((Yetclass - Absent) * 100 / Yetclass);
                                        percnt = Double.toString(percent) + "%";
                                        intent.putExtra("PER", percnt);

                                        //CT1

                                        for (i = 1; i <= differenceDatesct1 + 1; i++) {
                                            if (i % 7 == 3 || i % 7 == 0)
                                                c++;
                                            if (i % 7 == 1 || i % 7 == 2)
                                                c = c + 2;

                                        }
                                        TotalbfrCT1 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT1 * 0.75);
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT1 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT1", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT1", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT1", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //CT2

                                        for (i = 1; i <= differenceDatesct2 + 1; i++) {
                                            if (i % 7 == 3 || i % 7 == 0)
                                                c++;
                                            if (i % 7 == 1 || i % 7 == 2)
                                                c = c + 2;

                                        }
                                        TotalbfrCT2 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT2 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT2 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT2", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT2", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT2", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //CT3

                                        for (i = 1; i <= differenceDatesct3 + 1; i++) {
                                            if (i % 7 == 3 || i % 7 == 0)
                                                c++;
                                            if (i % 7 == 1 || i % 7 == 2)
                                                c = c + 2;

                                        }
                                        TotalbfrCT3 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT3 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT3 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT3", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT3", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT3", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //EndSem

                                        for (i = 1; i <= differenceDatessem + 1; i++) {
                                            if (i % 7 == 3 || i % 7 == 0)
                                                c++;
                                            if (i % 7 == 1 || i % 7 == 2)
                                                c = c + 2;

                                        }
                                        TotalbfrSem = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrSem * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrSem - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("SEM", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("SEM", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("SEM", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }


                                        Absent=0;
                                        startActivity(intent);
                                    }


                                    //i=0;TotalbfrCT1=0;TotalbfrCT2=0;TotalbfrCT3=0;TotalbfrSem=0;Yetclass=0;criteria75=0;ClassesLeft=0;Attended=0;ToAttend=0;

//                                    Big Data
                                    if (selectedSubject.equals("Big Data")) {
                                        for (i = 1; i <= differenceDates + 1; i++) {
                                            if (i % 7 == 1 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                        }

                                        // Toast.makeText(this, "Classes covered for Big Data "+c+" Hours", Toast.LENGTH_LONG).show();

                                        Yetclass = c;
                                        c = 0;
                                        i = 0;
                                        percent = (double) ((Yetclass - Absent) * 100 / Yetclass);
                                        percnt = Double.toString(percent) + "%";
                                        intent.putExtra("PER", percnt);

                                        //CT1
                                        for (i = 1; i <= differenceDatesct1 + 1; i++) {
                                            if (i % 7 == 1 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                        }
                                        TotalbfrCT1 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT1 * 0.75);
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT1 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT1", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT1", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT1", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //CT2

                                        for (i = 1; i <= differenceDatesct2 + 1; i++) {
                                            if (i % 7 == 1 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                        }
                                        TotalbfrCT2 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT2 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT2 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT2", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT2", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT2", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //CT3

                                        for (i = 1; i <= differenceDatesct3 + 1; i++) {
                                            if (i % 7 == 1 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                        }
                                        TotalbfrCT3 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT3 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT3 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT3", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT3", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT3", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //EndSem

                                        for (i = 1; i <= differenceDatessem + 1; i++) {
                                            if (i % 7 == 1 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                        }
                                        TotalbfrSem = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrSem * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrSem - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("SEM", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("SEM", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("SEM", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        Absent=0;
                                        startActivity(intent);
                                    }


                                    //i=0;TotalbfrCT1=0;TotalbfrCT2=0;TotalbfrCT3=0;TotalbfrSem=0;Yetclass=0;criteria75=0;ClassesLeft=0;Attended=0;ToAttend=0;

//                                      Communication System Engineering
                                    if (selectedSubject.equals("Comms. Sys. Engg.")) {
                                        for (i = 1; i <= differenceDates + 1; i++) {
                                            if (i % 7 == 1 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                        }
                                        //Toast.makeText(this, "Classes covered for Communication System Engineering "+c+" Hours", Toast.LENGTH_LONG).show();

                                        Yetclass = c;
                                        c = 0;
                                        i = 0;
                                        percent = (double) ((Yetclass - Absent) * 100 / Yetclass);
                                        percnt = Double.toString(percent) + "%";
                                        intent.putExtra("PER", percnt);

                                        //CT1

                                        for (i = 1; i <= differenceDatesct1 + 1; i++) {
                                            if (i % 7 == 1 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                        }
                                        TotalbfrCT1 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT1 * 0.75);
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT1 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT1", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT1", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT1", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //CT2

                                        for (i = 1; i <= differenceDatesct2 + 1; i++) {
                                            if (i % 7 == 1 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                        }
                                        TotalbfrCT2 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT2 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT2 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT2", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT2", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT2", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //CT3

                                        for (i = 1; i <= differenceDatesct3 + 1; i++) {
                                            if (i % 7 == 1 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                        }
                                        TotalbfrCT3 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT3 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT3 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT3", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT3", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT3", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //EndSem

                                        for (i = 1; i <= differenceDatessem + 1; i++) {
                                            if (i % 7 == 1 || i % 7 == 6 || i % 7 == 0)
                                                c++;
                                        }
                                        TotalbfrSem = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrSem * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrSem - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("SEM", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("SEM", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("SEM", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        Absent=0;
                                        startActivity(intent);
                                    }


                                    //i=0;TotalbfrCT1=0;TotalbfrCT2=0;TotalbfrCT3=0;TotalbfrSem=0;Yetclass=0;criteria75=0;ClassesLeft=0;Attended=0;ToAttend=0;

                                    //MOOC
                                    if (selectedSubject.equals("MOOC")) {
                                        for (i = 1; i <= differenceDates + 1; i++) {
                                            if (i % 7 == 6 || i % 7 == 0)
                                                c++;
                                        }

                                        //Toast.makeText(this, "Classes covered for MOOC "+c+" Hours", Toast.LENGTH_LONG).show();
                                        Yetclass = c;
                                        c = 0;
                                        i = 0;
                                        percent = (double) ((Yetclass - Absent) * 100 / Yetclass);
                                        percnt = Double.toString(percent) + "%";
                                        intent.putExtra("PER", percnt);

                                        //CT1
                                        for (i = 1; i <= differenceDatesct1 + 1; i++) {
                                            if (i % 7 == 6 || i % 7 == 0)
                                                c++;
                                        }
                                        TotalbfrCT1 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT1 * 0.75);
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT1 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT1", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT1", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT1", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //CT2

                                        for (i = 1; i <= differenceDatesct2 + 1; i++) {
                                            if (i % 7 == 6 || i % 7 == 0)
                                                c++;
                                        }
                                        TotalbfrCT2 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT2 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT2 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT2", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT2", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT2", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //CT3

                                        for (i = 1; i <= differenceDatesct3 + 1; i++) {
                                            if (i % 7 == 6 || i % 7 == 0)
                                                c++;
                                        }
                                        TotalbfrCT3 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT3 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT3 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT3", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT3", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT3", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //EndSem

                                        for (i = 1; i <= differenceDatessem + 1; i++) {
                                            if (i % 7 == 6 || i % 7 == 0)
                                                c++;
                                        }
                                        TotalbfrSem = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrSem * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrSem - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("SEM", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("SEM", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("SEM", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        Absent=0;
                                        startActivity(intent);
                                    }


                                    //i=0;TotalbfrCT1=0;TotalbfrCT2=0;TotalbfrCT3=0;TotalbfrSem=0;Yetclass=0;criteria75=0;ClassesLeft=0;Attended=0;ToAttend=0;


                                    //Comprehension
                                    if (selectedSubject.equals("Comprehension")) {
                                        for (i = 1; i <= differenceDates + 1; i++) {
                                            if (i % 7 == 1)
                                                c++;
                                        }

                                        //Toast.makeText(this, "Classes covered for Comprehension "+c+" Hours", Toast.LENGTH_LONG).show();
                                        Yetclass = c;
                                        c = 0;
                                        i = 0;
                                        percent = (double) ((Yetclass - Absent) * 100 / Yetclass);
                                        percnt = Double.toString(percent) + "%";
                                        intent.putExtra("PER", percnt);

                                        //CT1

                                        for (i = 1; i <= differenceDatesct1 + 1; i++) {
                                            if (i % 7 == 1)
                                                c++;
                                        }
                                        TotalbfrCT1 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT1 * 0.75);
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT1 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT1", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT1", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT1", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //CT2

                                        for (i = 1; i <= differenceDatesct2 + 1; i++) {
                                            if (i % 7 == 1)
                                                c++;
                                        }
                                        TotalbfrCT2 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT2 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT2 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT2", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT2", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT2", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //CT3

                                        for (i = 1; i <= differenceDatesct3 + 1; i++) {
                                            if (i % 7 == 1)
                                                c++;
                                        }
                                        TotalbfrCT3 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT3 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT3 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT3", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT3", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT3", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //EndSem

                                        for (i = 1; i <= differenceDatessem + 1; i++) {
                                            if (i % 7 == 1)
                                                c++;
                                        }
                                        TotalbfrSem = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrSem * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrSem - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("SEM", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("SEM", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("SEM", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        Absent=0;
                                        startActivity(intent);
                                    }


                                    //i=0;TotalbfrCT1=0;TotalbfrCT2=0;TotalbfrCT3=0;TotalbfrSem=0;Yetclass=0;criteria75=0;ClassesLeft=0;Attended=0;ToAttend=0;
                                    //Energy Conservation
                                    if (selectedSubject.equals("Energy Cons.")) {
                                        for (i = 1; i <= differenceDates + 1; i++) {
                                            if (i % 7 == 0)
                                                c++;
                                            if (i % 7 == 6)
                                                c = c + 2;

                                        }

                                        //Toast.makeText(this, "Classes covered for Energy Conservation "+c+" Hours", Toast.LENGTH_LONG).show();
                                        Yetclass = c;
                                        c = 0;
                                        i = 0;
                                        percent = (double) ((Yetclass - Absent) * 100 / Yetclass);
                                        percnt = Double.toString(percent) + "%";
                                        intent.putExtra("PER", percnt);

                                        //CT1
                                        for (i = 1; i <= differenceDatesct1 + 1; i++) {
                                            if (i % 7 == 0)
                                                c++;
                                            if (i % 7 == 6)
                                                c = c + 2;

                                        }
                                        TotalbfrCT1 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT1 * 0.75);
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT1 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT1", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT1", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT1", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //CT2

                                        for (i = 1; i <= differenceDatesct2 + 1; i++) {
                                            if (i % 7 == 0)
                                                c++;
                                            if (i % 7 == 6)
                                                c = c + 2;

                                        }
                                        TotalbfrCT2 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT2 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT2 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT2", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT2", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT2", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //CT3

                                        for (i = 1; i <= differenceDatesct3 + 1; i++) {
                                            if (i % 7 == 0)
                                                c++;
                                            if (i % 7 == 6)
                                                c = c + 2;

                                        }
                                        TotalbfrCT3 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT3 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT3 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT3", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT3", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT3", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //EndSem

                                        for (i = 1; i <= differenceDatessem + 1; i++) {
                                            if (i % 7 == 0)
                                                c++;
                                            if (i % 7 == 6)
                                                c = c + 2;

                                        }
                                        TotalbfrSem = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrSem * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrSem - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("SEM", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("SEM", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("SEM", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        Absent=0;
                                        startActivity(intent);
                                    }


                                    //i=0;TotalbfrCT1=0;TotalbfrCT2=0;TotalbfrCT3=0;TotalbfrSem=0;Yetclass=0;criteria75=0;ClassesLeft=0;Attended=0;ToAttend=0;

                                    //CPS III
                                    if (selectedSubject.equals("CPS III")) {
                                        for (i = 1; i <= differenceDates + 1; i++) {
                                            if (i % 7 == 3)
                                                c = c + 2;

                                        }

                                        //Toast.makeText(this, "Classes covered for CPS III "+c+" Hours", Toast.LENGTH_LONG).show();
                                        Yetclass = c;
                                        c = 0;
                                        i = 0;
                                        percent = (double) ((Yetclass - Absent) * 100 / Yetclass);
                                        percnt = Double.toString(percent) + "%";
                                        intent.putExtra("PER", percnt);

                                        //CT1
                                        for (i = 1; i <= differenceDatesct1 + 1; i++) {
                                            if (i % 7 == 3)
                                                c = c + 2;

                                        }
                                        TotalbfrCT1 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT1 * 0.75);
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT1 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT1", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT1", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT1", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //CT2

                                        for (i = 1; i <= differenceDatesct2 + 1; i++) {
                                            if (i % 7 == 3)
                                                c = c + 2;

                                        }
                                        TotalbfrCT2 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT2 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT2 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT2", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT2", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT2", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //CT3

                                        for (i = 1; i <= differenceDatesct3 + 1; i++) {
                                            if (i % 7 == 3)
                                                c = c + 2;

                                        }
                                        TotalbfrCT3 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT3 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT3 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT3", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT3", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT3", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //EndSem

                                        for (i = 1; i <= differenceDatessem + 1; i++) {
                                            if (i % 7 == 3)
                                                c = c + 2;

                                        }
                                        TotalbfrSem = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrSem * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrSem - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("SEM", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("SEM", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("SEM", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        Absent=0;
                                        startActivity(intent);
                                    }


                                    //i=0;TotalbfrCT1=0;TotalbfrCT2=0;TotalbfrCT3=0;TotalbfrSem=0;Yetclass=0;criteria75=0;ClassesLeft=0;Attended=0;ToAttend=0;

                                    //Emp Skills
                                    if (selectedSubject.equals("Emp. Skills")) {
                                        for (i = 1; i <= differenceDates + 1; i++) {
                                            if (i % 7 == 1 || i % 7 == 3)
                                                c++;
                                        }

                                        //Toast.makeText(this, "Classes covered for Employabilty Skills and Practices "+c+" Hours", Toast.LENGTH_LONG).show();
                                        Yetclass = c;
                                        c = 0;
                                        i = 0;
                                        percent = (double) ((Yetclass - Absent) * 100 / Yetclass);
                                        percnt = Double.toString(percent) + "%";
                                        intent.putExtra("PER", percnt);

                                        for (i = 1; i <= differenceDatesct1 + 1; i++) {
                                            if (i % 7 == 1 || i % 7 == 3)
                                                c++;
                                        }
                                        TotalbfrCT1 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT1 * 0.75);
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT1 - Yetclass;
                                        Attended = Yetclass - Absent;

                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT1", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT1", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT1", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }
                                        Absent=0;
                                        startActivity(intent);
                                    }

                                    //i=0;TotalbfrCT1=0;TotalbfrCT2=0;TotalbfrCT3=0;TotalbfrSem=0;Yetclass=0;criteria75=0;ClassesLeft=0;Attended=0;ToAttend=0;

                                    //Indian Art Form
                                    if (selectedSubject.equals("Ind. Art. Form")) {
                                        for (i = 1; i <= differenceDates + 1; i++) {
                                            if (i % 7 == 2)
                                                c=c+2;
                                        }

                                        //Toast.makeText(this, "Classes covered for Indian Art Form "+c+" Hours", Toast.LENGTH_LONG).show();
                                        Yetclass = c;
                                        c = 0;
                                        i = 0;
                                        percent = (double) ((Yetclass - Absent) * 100 / Yetclass);
                                        percnt = Double.toString(percent) + "%";
                                        intent.putExtra("PER", percnt);

                                        //CT1
                                        for (i = 1; i <= differenceDatesct1 + 1; i++) {
                                            if (i % 7 == 2)
                                                c = c + 2;

                                        }
                                        TotalbfrCT1 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT1 * 0.75);
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT1 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT1", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT1", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT1", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }
                                        //CT2

                                        for (i = 1; i <= differenceDatesct2 + 1; i++) {
                                            if (i % 7 == 2)
                                                c = c + 2;

                                        }
                                        TotalbfrCT2 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT2 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT2 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT2", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT2", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT2", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //CT3

                                        for (i = 1; i <= differenceDatesct3 + 1; i++) {
                                            if (i % 7 == 2)
                                                c = c + 2;

                                        }
                                        TotalbfrCT3 = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrCT3 * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrCT3 - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("CT3", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("CT3", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("CT3", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        //EndSem

                                        for (i = 1; i <= differenceDatessem + 1; i++) {
                                            if (i % 7 == 2)
                                                c = c + 2;

                                        }
                                        TotalbfrSem = c;
                                        c = 0;
                                        i = 0;

                                        criteria75 = (int) (TotalbfrSem * 0.75);
                                        //Toast.makeText(this, "Attend "+criteria75+" hours 75% criteria", Toast.LENGTH_LONG).show();
                                        --criteria75;
                                        ClassesLeft = TotalbfrSem - Yetclass;
                                        Attended = Yetclass - Absent;
                                        if (criteria75 <= Attended)
                                            intent.putExtra("SEM", fld);
//                                            Toast.makeText(this, "75% criteria fulfilled", Toast.LENGTH_LONG).show();

                                        else {
                                            ToAttend = criteria75 - Attended;
                                            atnd = String.valueOf(ToAttend);
                                            if (ToAttend > ClassesLeft)
                                                intent.putExtra("SEM", cnt);
                                                // Toast.makeText(this, "75% criteria can't be fulfilled", Toast.LENGTH_LONG).show();
                                            else
                                                intent.putExtra("SEM", atnd);
                                            //Toast.makeText(this, "Attend more " + ToAttend + " to maintain 75% criteria", Toast.LENGTH_LONG).show();
                                        }

                                        Absent=0;
                                        startActivity(intent);
                                    }


                                    //
                                }
                            }
                        }
                    }


            }

                catch (Exception exception) {
                    Toast.makeText(this, "Please rectify the device time", Toast.LENGTH_SHORT).show();
                }


            }


        });


    }
}