package madhukar.com.example.dell.snti;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.vision.text.Text;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DrawerLayout drawerLayout;
    NavigationDrawerFragment drawerfragment;
    CoordinatorLayout coordinatorLayout;

    RelativeLayout dimmedBackground;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton fab1, fab2, fab3,fab4,fab5;

    Spinner toolBarSpinner;
    private String toolbarOptions[]={"VT NO","DETAILS"};
    private RelativeLayout toolBarOptionLayout;

    TextView title;

    int searchTrainee_request_code=4;
    private DatabaseReference databaseReference;

    public static boolean fetchedData=false;

     private ArrayList<TraineeDetails> traineeDetailsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Creating the directory
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            File f = new File(Environment.getExternalStorageDirectory(),File.separator+"SNTI");
            f.mkdirs();
            File excel = new File(Environment.getExternalStorageDirectory(),File.separator+"SNTI"+File.separator+"EXCELS");
            excel.mkdir();
        }
        else
        Toast.makeText(this,"Directory is not created",Toast.LENGTH_SHORT).show();


            toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolBarSpinner = (Spinner)findViewById(R.id.toolbarSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,toolbarOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toolBarSpinner.setAdapter(adapter);
        toolBarOptionLayout=(RelativeLayout)findViewById(R.id.toolbarSpinnerLayout);
        title=(TextView)findViewById(R.id.titleText);

        toolBarOptionLayout.setVisibility(View.GONE);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.coordinatorlayout);
        drawerfragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerfragment.setUp(R.id.fragment_navigation_drawer,(DrawerLayout)findViewById(R.id.drawer_layout),toolbar);

        dimmedBackground=(RelativeLayout)findViewById(R.id.dimmedBackground);
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.floating_contact);
        materialDesignFAM.setClosedOnTouchOutside(true);
        fab1 = (FloatingActionButton) findViewById(R.id.floating_email);
        fab2 = (FloatingActionButton) findViewById(R.id.floating_phone);
        materialDesignFAM.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {

                if(opened)
                {
                    materialDesignFAM.setBackgroundColor(Color.parseColor("#99ffffff"));
                    //dimmedBackground.setVisibility(View.VISIBLE);
                }
                else
                {
                    materialDesignFAM.setBackgroundColor(Color.parseColor("#00000000"));
                    // dimmedBackground.setVisibility(View.GONE);
                }

            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String email[]={"snti.vt@tatasteel.com"};
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,email);
                startActivity(emailIntent);
                materialDesignFAM.setBackgroundColor(Color.parseColor("#00000000"));
                materialDesignFAM.close(true);
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:06576644232"));

                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
                materialDesignFAM.setBackgroundColor(Color.parseColor("#00000000"));
                materialDesignFAM.close(true);
            }
        });

        if(isNetworkAvailable())
        {
        }
        else
        {
            Snackbar snackbar=Snackbar.make(coordinatorLayout,"No Internet Connection",Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.RED);
            snackbar.show();
        }




        //Create Databe here by fetching data from firebase
        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://snti-c799a.firebaseio.com/Trainee");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot traineeDetailSnapshot : dataSnapshot.getChildren())
                    {
                        TraineeDetails trainee = traineeDetailSnapshot.getValue(TraineeDetails.class);
                        traineeDetailsList.add(trainee);

                    }
                if(traineeDetailsList!=null) {
                    Toast.makeText(getApplication(), "data fetched", Toast.LENGTH_SHORT).show();
                    fetchedData=true;
                    TraineeDatabse.createDatabase(getApplication(), traineeDetailsList);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        MyAdapter.selected_item = 0;
        NavigationDrawerFragment.recyclerView.getAdapter().notifyDataSetChanged();
//        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameholder,new Home());
        fragmentTransaction.commit();




    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setUpWindowAnimator() {

        Slide slide = new Slide();
        slide.setDuration(1000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(slide);
        }

    }

    @Override
    protected void onRestart() {

        super.onRestart();
    }

    @Override
    public void onBackPressed() {


        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (materialDesignFAM.isOpened()) {
                materialDesignFAM.setBackgroundColor(Color.parseColor("#00000000"));
                materialDesignFAM.close(true);
            } else {


                if (MyAdapter.selected_item == 0) {

                    new AlertDialog.Builder(this)
                            .setMessage("Are you sure you want to exit?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    MainActivity.this.finish();
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                    return;
                }
                else if((Heads.unfoldableView != null)
                        && (Heads.unfoldableView.isUnfolded() || Heads.unfoldableView.isUnfolding()))
                {
                    Heads.unfoldableView.foldBack();
                }
                else {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameholder, new Home());
                    fragmentTransaction.commit();
                    MyAdapter.selected_item = 0;
                    NavigationDrawerFragment.recyclerView.getAdapter().notifyDataSetChanged();
                }

                //Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();


            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==searchTrainee_request_code)

             {
                 MyAdapter.selected_item = 4;
                 NavigationDrawerFragment.recyclerView.getAdapter().notifyDataSetChanged();
                 fragmentTransaction = fragmentManager.beginTransaction();
                 fragmentTransaction.replace(R.id.frameholder,new FindTrainee());
                 fragmentTransaction.commit();

             }
        else if(requestCode==5)
        {
            MyAdapter.selected_item = 5;
            NavigationDrawerFragment.recyclerView.getAdapter().notifyDataSetChanged();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameholder,new Report());
            fragmentTransaction.commit();

        }
        else if(requestCode==6)
        {
            MyAdapter.selected_item = 6;
            NavigationDrawerFragment.recyclerView.getAdapter().notifyDataSetChanged();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameholder,new Report());
            fragmentTransaction.commit();

        }


    }

    @Override
    protected void onDestroy() {
        TraineeDatabse.ClearDatabase();
        super.onDestroy();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
