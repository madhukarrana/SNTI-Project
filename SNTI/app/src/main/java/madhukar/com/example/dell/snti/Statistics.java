package madhukar.com.example.dell.snti;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Statistics extends Fragment {

    private TextView toolbarTitle;
    private RelativeLayout toolbarOptionLayout;
    public Statistics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_statistics, container, false);
        toolbarTitle =(TextView)getActivity().findViewById(R.id.titleText);
        toolbarTitle.setText("Statistics");

        toolbarOptionLayout=(RelativeLayout)getActivity().findViewById(R.id.toolbarSpinnerLayout);
        toolbarOptionLayout.setVisibility(View.INVISIBLE);
        return v;

    }

}
