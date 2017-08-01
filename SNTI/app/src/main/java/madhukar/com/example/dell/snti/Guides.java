package madhukar.com.example.dell.snti;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Guides extends Fragment {

 /*   int[] imageViews = {R.id.photo_guide1, R.id.photo_guide2, R.id.photo_guide3, R.id.photo_guide4, R.id.photo_guide5};
    int[] images = {R.drawable.snti1, R.drawable.snti1, R.drawable.snti1 , R.drawable.snti1,
            R.drawable.snti1};

    int[] textViews = {R.id.tvt1, R.id.tvt3, R.id.tvt5, R.id.tvt7, R.id.tvt9};

 */
    private RelativeLayout toolbarOptionLayout;

    private List<guideData> guides = new ArrayList<>();
    static RecyclerView guideRecyclerView;
    private GuideAdapter mAdapter;
    private Toolbar fragToolbar;
    private TextView toolbarTitle;

    public Guides() {
        // Required empty public constructor
    }

    private FloatingActionMenu fm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_guides, container, false);
        toolbarTitle =(TextView)getActivity().findViewById(R.id.titleText);
        toolbarTitle.setText("Guides");
        fm =(FloatingActionMenu)getActivity().findViewById(R.id.floating_contact);
        fm.hideMenu(true);
        toolbarOptionLayout=(RelativeLayout)getActivity().findViewById(R.id.toolbarSpinnerLayout);
        toolbarOptionLayout.setVisibility(View.INVISIBLE);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }


        prepareGuideData();
        guideRecyclerView=(RecyclerView)v.findViewById(R.id.guideRecyclerView);
        mAdapter = new GuideAdapter(getActivity(),guides);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        guideRecyclerView.setLayoutManager(mLayoutManager);
        guideRecyclerView.setItemAnimator(new DefaultItemAnimator());
        guideRecyclerView.setAdapter(mAdapter);
        guideRecyclerView.addOnItemTouchListener(new NavigationDrawerFragment.RecylcerTouchListener(getActivity(), guideRecyclerView, new NavigationDrawerFragment.ClickListener() {
            @Override
            public void onClick(View v,final int position) {
               // Toast.makeText(getActivity(),"postion "+position,Toast.LENGTH_SHORT).show();
                if(MainActivity.fetchedData==true)
                {
                    Intent i =new Intent(getActivity(),TraineeUnderGuideList.class);
                    i.putExtra("guideName",guides.get(position).getGuideName());
                    startActivity(i);
                }
                else
                {
                    if(!isNetworkAvailable())
                    {
                        new android.support.v7.app.AlertDialog.Builder(getActivity())
                                .setMessage("Turn ON Internet to see trainees under "+guides.get(position).getGuideName())
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
                                    Intent i =new Intent(getActivity(),TraineeUnderGuideList.class);
                                    i.putExtra("guideName",guides.get(position).getGuideName());
                                    startActivity(i);

                                }

                                else
                                    handler.postDelayed(this,1000);

                            }
                        };
                        handler.postDelayed(r,1000);

               }
                }

            }

            @Override
            public void onLongClick(View v, int position) {

            }
        }));



        return v;
    }


    private void prepareGuideData()
    {
        guideData guide = new guideData("SAKET SINHA","PNUMBER : 164090","DEPART. : ITS","MOB : 9973462375","saket.s88@gmail.com",R.drawable.saket);
        guides.add(guide);

        guide = new guideData("ANIRBAN DUTTA","PNUMBER : 163997","DEPART. : ITS","MOB : 7259162907","saket.s88@gmail.com",R.drawable.anirban);
        guides.add(guide);

        guide = new guideData("VIVEK KUMAR TIWARI","PNUMBER : 164114","DEPART. : ITS","MOB : 8501825589","saket.s88@gmail.com",R.drawable.vivek);
        guides.add(guide);

        guide = new guideData("ANUBHHAV TYAGI","PNUMBER : 158872","DEPART. : ITS","MOB : 7763806919","saket.s88@gmail.com",R.drawable.anubhav);
        guides.add(guide);

        guide = new guideData("ABHIRUP BHATTACHARYA","PNUMBER : 164115","DEPART. : ITS","MOB : 9434887497","saket.s88@gmail.com",R.drawable.abhirup);
        guides.add(guide);

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



}
