package madhukar.com.example.dell.snti;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.clans.fab.FloatingActionMenu;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;


public class Report extends Fragment {

    private EditText startDate,endDate,institute,branch,guides;
    private Button submitButton;
    private FloatingActionMenu fm;

    private TextView toolbarTitle;
    private RelativeLayout toolbarOptionLayout;
    private Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    private int flag=0;
    public static String[] instituteList={"NIT JAMSHEDPUR","ITER BHUBANESWAR","KIIT UNIVERSITY BHUBANESWAR","BIT MESRA"};
    public static String[] branchList = {"COMPUTER SCIENCE","MECHANICAL","ELECTRICAL"};
    public static String[] guideList  ={"SAKET SINHA","ANIRBAN DUTTA","VIVEK KUMAR TIWARI","ANUBHHAV TYAGI","ABHIRUP BHATTACHARYA"};
    public static Integer[] selectedInstituteList=new Integer[0];
    public static Integer[] selectedBranchList=new Integer[0];
    public static Integer[] selectedGuideList=new Integer[0];

    public Report() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_report, container, false);
        toolbarTitle =(TextView)getActivity().findViewById(R.id.titleText);
        if(MyAdapter.selected_item==5)
        toolbarTitle.setText("Report");
        else if(MyAdapter.selected_item==6)
            toolbarTitle.setText("Statistics");

        toolbarOptionLayout=(RelativeLayout)getActivity().findViewById(R.id.toolbarSpinnerLayout);
        toolbarOptionLayout.setVisibility(View.INVISIBLE);


        fm =(FloatingActionMenu)getActivity().findViewById(R.id.floating_contact);
        fm.hideMenu(true);

        startDate = (EditText)v.findViewById(R.id.reportStartDate);
        endDate = (EditText)v.findViewById(R.id.reportEndDate);
        institute=(EditText)v.findViewById(R.id.reportInstitute);
        branch = (EditText)v.findViewById(R.id.reportBranch);
        submitButton=(Button)v.findViewById(R.id.reportSubmit);
        guides = (EditText)v.findViewById(R.id.reportGuide);
        myCalendar = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };



        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=0;
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=1;
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        institute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getActivity())
                        .title("Choose Institutes")
                        .items(instituteList)
                        .itemsCallbackMultiChoice(selectedInstituteList, new MaterialDialog.ListCallbackMultiChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                                institute.setText("");
                                selectedInstituteList=which;
                                if(which.length>0)
                                {
                                    String inst=instituteList[which[0]];
                                    int i;
                                    for(i=1;i<which.length;i++)
                                        inst+=","+instituteList[which[i]];
                                    institute.setText(inst);
                                }
                                return true;
                            }
                        })
                        .positiveText("Choose")
                        .show();
            }
        });
        branch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MaterialDialog.Builder(getActivity())
                        .title("Choose Branch")
                        .items(branchList)
                        .itemsCallbackMultiChoice(selectedBranchList, new MaterialDialog.ListCallbackMultiChoice() {

                            @Override
                            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                                branch.setText("");
                                selectedBranchList=which;
                                if(which.length>0)
                                {

                                    String inst=branchList[which[0]];
                                    int i;
                                    for(i=1;i<which.length;i++)
                                        inst+=","+instituteList[which[i]];
                                    branch.setText(inst);
                                }
                                return true;
                            }
                        })
                        .positiveText("Choose")
                        .show();

            }
        });

        guides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getActivity())
                        .title("Choose Guides")
                        .items(guideList)
                        .itemsCallbackMultiChoice(selectedGuideList, new MaterialDialog.ListCallbackMultiChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                                guides.setText("");
                                selectedGuideList=which;
                                if(which.length>0)
                                {
                                    String inst=guideList[which[0]];
                                    int i;
                                    for(i=1;i<which.length;i++)
                                        inst+=","+guideList[which[i]];
                                    guides.setText(inst);
                                }
                                return true;
                            }
                        })
                        .positiveText("Choose")
                        .show();

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String start,end;

                start=startDate.getText().toString();
                end=endDate.getText().toString();
                if((start.isEmpty())||(end.isEmpty())||(selectedInstituteList.length==0)||(selectedBranchList.length==0)||(selectedGuideList.length==0))
                {
                    new android.support.v7.app.AlertDialog.Builder(getActivity())
                            .setMessage("Please Enter Details")
                            .setCancelable(false)
                            .setPositiveButton("Ok",null)
                            .show();

                }
                else
                {
                    if(MainActivity.fetchedData==true)
                    {

                        if(MyAdapter.selected_item==5)
                        {
                            Intent i =new Intent(getActivity(),DataReport.class);
                            i.putExtra("startDate",start);
                            i.putExtra("endDate",end);
                            getActivity().startActivityForResult(i, 5);

                        }
                        else if(MyAdapter.selected_item==6)
                        {
                            Intent i =new Intent(getActivity(),ChartRepresentation.class);
                            i.putExtra("startDate",start);
                            i.putExtra("endDate",end);
                            getActivity().startActivityForResult(i, 6);
                        }

                    }
                    else
                    {
                        if(!isNetworkAvailable())
                        {
                            new android.support.v7.app.AlertDialog.Builder(getActivity())
                                    .setMessage("Turn ON Internet to see the Report ")
                                    .setCancelable(false)
                                    .setPositiveButton("Ok",null)
                                    .show();

                        }
                        else
                        {

                            final ProgressDialog progressBar = new ProgressDialog(getActivity());
                            progressBar.setCancelable(false);//you can cancel it by pressing back button
                            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            progressBar.show();//displays the progress bar


                            final Handler handler=new Handler();
                            Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                    if(MainActivity.fetchedData==true)
                                    {
                                        progressBar.dismiss();
                                        if(MyAdapter.selected_item==5)
                                        {
                                            Intent i =new Intent(getActivity(),DataReport.class);
                                            i.putExtra("startDate",start);
                                            i.putExtra("endDate",end);
                                            getActivity().startActivityForResult(i, 5);

                                        }
                                        else if(MyAdapter.selected_item==6)
                                        {
                                            Intent i =new Intent(getActivity(),ChartRepresentation.class);
                                            i.putExtra("startDate",start);
                                            i.putExtra("endDate",end);
                                            getActivity().startActivityForResult(i, 6);
                                        }

                                    }

                                    else
                                        handler.postDelayed(this,1000);

                                }
                            };
                            handler.postDelayed(r,1000);

                        }


                    }

                }
            }
        });

        return v;
    }

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        if(flag==0)
            startDate.setText(sdf.format(myCalendar.getTime()));
        else
            endDate.setText(sdf.format(myCalendar.getTime()));

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
