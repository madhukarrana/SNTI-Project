package madhukar.com.example.dell.snti;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.clans.fab.FloatingActionMenu;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class FindTrainee extends Fragment implements AdapterView.OnItemSelectedListener {

   private RelativeLayout toolbarOptionLayout;
    private FloatingActionMenu fm;

    private RelativeLayout layoutVt,layoutDetails;
    private Spinner toolbarSpinner;
    private Calendar myCalendar;

    private EditText vtNumber,traineeName,traineeInstitute,traineeBranch,traineeStartDate,traineeEndData;
    private Button vtSubmit,detailsSubmit;
    DatePickerDialog.OnDateSetListener date;
    private int flag=0;
    private TextView toolbarTitle;
    String branch[]={"COMPUTER SCIENCE","MECHANICAL","ELECTRICAL"};
    String institute[]={"NIT JAMSHEDPUR","ITER BHUBANESWAR","KIIIT UNIVERSITY BHUBANESWAR","BIT MESRA"};
    public FindTrainee() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_find_trainee, container, false);
        toolbarTitle =(TextView)getActivity().findViewById(R.id.titleText);
        toolbarTitle.setText("Find");

        toolbarOptionLayout=(RelativeLayout)getActivity().findViewById(R.id.toolbarSpinnerLayout);
        toolbarOptionLayout.setVisibility(View.VISIBLE);
        fm =(FloatingActionMenu)getActivity().findViewById(R.id.floating_contact);
        fm.hideMenu(true);

        layoutVt = (RelativeLayout)v.findViewById(R.id.layoutForSearchById);
        layoutDetails=(RelativeLayout)v.findViewById(R.id.layoutForSearchByDetails);
        toolbarSpinner = (Spinner)getActivity().findViewById(R.id.toolbarSpinner);
        vtNumber =(EditText)v.findViewById(R.id.vtNumber);
        vtSubmit = (Button)v.findViewById(R.id.vtSubmit);
        traineeName=(EditText)v.findViewById(R.id.traineeName);
        traineeInstitute=(EditText)v.findViewById(R.id.traineeInstitute);
        traineeBranch=(EditText)v.findViewById(R.id.traineeBranch);
        traineeStartDate=(EditText)v.findViewById(R.id.traineeStartDate);
        traineeEndData=(EditText)v.findViewById(R.id.traineeEndDate);
        detailsSubmit=(Button)v.findViewById(R.id.detailSubmit);
        toolbarSpinner.setOnItemSelectedListener(this);

        String currentSelection;
        currentSelection = toolbarSpinner.getSelectedItem().toString();
        if(currentSelection.equals("VT NO"))
        {
            layoutDetails.setVisibility(View.INVISIBLE);
            layoutVt.setVisibility(View.VISIBLE);
        }
        else
        {
             layoutVt.setVisibility(View.INVISIBLE);
             layoutDetails.setVisibility(View.VISIBLE);
        }
        traineeBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MaterialDialog.Builder(getActivity())
                        .title("SELECT BRANCH")
                        .items(branch)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                /**
                                 * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                                 * returning false here won't allow the newly selected radio button to actually be selected.
                                 **/
                                if(which!=-1)
                                 traineeBranch.setText(branch[which]);
                                return true;
                            }
                        })
                        .positiveText("Ok")
                .show();
            }
        });


        traineeInstitute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MaterialDialog.Builder(getActivity())
                        .title("SELECT INSTITUTE")
                        .items(institute)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                /**
                                 * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                                 * returning false here won't allow the newly selected radio button to actually be selected.
                                 **/
                                if(which!=-1)
                                    traineeInstitute.setText(institute[which]);
                                return true;
                            }
                        })
                        .positiveText("Ok")
                        .show();
            }
        });


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

        traineeStartDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                    flag=0;
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        traineeEndData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                flag=1;
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        detailsSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              final String name,institute,branch,startDate,endDate;
                name=traineeName.getText().toString();
                institute=traineeInstitute.getText().toString();
                branch=traineeBranch.getText().toString();
                startDate=traineeStartDate.getText().toString();
                endDate=traineeEndData.getText().toString();
                if((name.isEmpty())||(institute.isEmpty())||(branch.isEmpty())||(startDate.isEmpty())||(endDate.isEmpty()))
                {
                    new android.support.v7.app.AlertDialog.Builder(getActivity())
                            .setMessage("Please Enter All Details")
                            .setCancelable(false)
                            .setPositiveButton("Ok",null)
                            .show();

                }
                else
                {

                    if(MainActivity.fetchedData==true)
                    {
                        int index = TraineeDatabse.searchByDetails(name,institute,branch,startDate,endDate);
                        if(index==-1)
                        {
                            new android.support.v7.app.AlertDialog.Builder(getActivity())
                                    .setMessage("Above details not found")
                                    .setCancelable(false)
                                    .setPositiveButton("Ok",null)
                                    .show();

                        }
                        else
                        {
                            Intent i =new Intent(getActivity(),TraineeSearchData.class);
                            i.putExtra("searchResultIndex",index);
                            getActivity().startActivityForResult(i,4);

                        }

                    }
                    else
                    {
                        if(!isNetworkAvailable())
                        {
                            new android.support.v7.app.AlertDialog.Builder(getActivity())
                                    .setMessage("Turn ON Internet to search above details ")
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
                                        int index = TraineeDatabse.searchByDetails(name,institute,branch,startDate,endDate);
                                        if(index==-1)
                                        {
                                            new android.support.v7.app.AlertDialog.Builder(getActivity())
                                                    .setMessage("Above details not found")
                                                    .setCancelable(false)
                                                    .setPositiveButton("Ok",null)
                                                    .show();

                                        }
                                        else
                                        {
                                            Intent i =new Intent(getActivity(),TraineeSearchData.class);
                                            i.putExtra("searchResultIndex",index);
                                            getActivity().startActivityForResult(i,4);

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
        vtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               final String reference = vtNumber.getText().toString();
                if(reference.isEmpty())
                {
                    new android.support.v7.app.AlertDialog.Builder(getActivity())
                            .setMessage("Please Enter Reference Number")
                            .setCancelable(false)
                            .setPositiveButton("Ok",null)
                            .show();

                }
                else {

                    if (MainActivity.fetchedData == true) {
                        int index = TraineeDatabse.searchByReference(reference);
                        if (index == -1) {
                            new android.support.v7.app.AlertDialog.Builder(getActivity())
                                    .setMessage("Reference Number " + reference + " not found ")
                                    .setCancelable(false)
                                    .setPositiveButton("Ok", null)
                                    .show();

                        } else {
                            //  Toast.makeText(getActivity(),"index is "+index,Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getActivity(), TraineeSearchData.class);
                            i.putExtra("searchResultIndex", index);
                            getActivity().startActivityForResult(i, 4);

                        }
                    }
                    else {
                        if (!isNetworkAvailable()) {
                            new android.support.v7.app.AlertDialog.Builder(getActivity())
                                    .setMessage("Turn ON Internet to search Reference Number " +reference)
                                    .setCancelable(false)
                                    .setPositiveButton("Ok", null)
                                    .show();

                        } else {

                            final ProgressDialog progressBar = new ProgressDialog(getActivity());
                            progressBar.setCancelable(false);//you can cancel it by pressing back button
                            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            progressBar.show();//displays the progress bar


                            final Handler handler = new Handler();
                            Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                    if (MainActivity.fetchedData == true) {
                                        progressBar.dismiss();
                                        int index = TraineeDatabse.searchByReference(reference);
                                        if (index == -1) {
                                            new android.support.v7.app.AlertDialog.Builder(getActivity())
                                                    .setMessage("Reference Number " + reference + " not found ")
                                                    .setCancelable(false)
                                                    .setPositiveButton("Ok", null)
                                                    .show();

                                        } else {
                                            //  Toast.makeText(getActivity(),"index is "+index,Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(getActivity(), TraineeSearchData.class);
                                            i.putExtra("searchResultIndex", index);
                                            getActivity().startActivityForResult(i, 4);

                                        }


                                    } else
                                        handler.postDelayed(this, 1000);

                                }
                            };
                            handler.postDelayed(r, 1000);

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
        traineeStartDate.setText(sdf.format(myCalendar.getTime()));
        else
            traineeEndData.setText(sdf.format(myCalendar.getTime()));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(position==0)
        {
            layoutDetails.setVisibility(View.INVISIBLE);
            layoutVt.setVisibility(View.VISIBLE);

        }
        else
        {
            layoutVt.setVisibility(View.INVISIBLE);
            layoutDetails.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
