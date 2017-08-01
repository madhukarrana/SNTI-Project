package madhukar.com.example.dell.snti;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.github.clans.fab.FloatingActionMenu;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    private ViewFlipper viewFlipper;
    private float lastX;
    private TextView title,description;
    private String images[];
    private Context context;
    private FloatingActionMenu fm;
    private RelativeLayout toolbarOptionLayout;
    private Toolbar fragToolbar;

    private TextView toolbarTitle;
    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        toolbarTitle =(TextView)getActivity().findViewById(R.id.titleText);
        toolbarTitle.setText("Home");
        context=getActivity();
        fm =(FloatingActionMenu)getActivity().findViewById(R.id.floating_contact);
        fm.showMenu(true);
        toolbarOptionLayout=(RelativeLayout)getActivity().findViewById(R.id.toolbarSpinnerLayout);
        toolbarOptionLayout.setVisibility(View.INVISIBLE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
        viewFlipper=(ViewFlipper)v.findViewById(R.id.viewflipper);
        title=(TextView)v.findViewById(R.id.homeContentTitle);

        // set typeface for home title here

        description=(TextView)v.findViewById(R.id.homeContent);
        String content = getHomeCOntent();
        description.setText(content);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/ExoBold.ttf");
        description.setTypeface(typeface);

        //set typeface for home content here

        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));

        return v;
    }

    private String getHomeCOntent()
    {
        String str= "SNTI- Shavak Nanavati Technical Institute, the erstwhile Jamshedpur Technical Institute, was estabilished in " +
                "the year 1921 to provide the technically qualified human resource for tata steel. " +
                "Today SNTI forms an integral part of " +
                "HR management division of TATA STEEL.It has rendered commendable service in nation development of technical manpower " +
                "not only for the TATA STEEL, but also for the steel plants in the public secctor and other manufacturing industries." +
                "\n" +
                "SERVICES AND PRODUCTS" +
                "\n" +
                "1. Pre-employment - graduate engineering trainee, system trainee, junior engineer& trade apprentices" +
                "\n" +
                "2. psot-employment- for the employees of the company" +
                "\n" +
                "3. External programmes- the pg, MCA, MBA students are provided six month project work and vocational training are given in the month of May-June every year";
        return str;
    }

}
