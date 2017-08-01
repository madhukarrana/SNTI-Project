package madhukar.com.example.dell.snti;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alexvasilkov.android.commons.texts.SpannableBuilder;
import com.alexvasilkov.android.commons.utils.Views;
import com.alexvasilkov.foldablelayout.UnfoldableView;
import com.alexvasilkov.foldablelayout.shading.GlanceFoldShading;
import com.github.clans.fab.FloatingActionMenu;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class Heads extends Fragment {

    private RelativeLayout toolbarOptionLayout;
    private FloatingActionMenu fm;

    private View listTouchInterceptor;
    private View detailsLayout;
    public static UnfoldableView unfoldableView;
    private TextView toolbarTitle;

    public Heads() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RelativeLayout v = new RelativeLayout(getActivity()); // for example
        inflater.inflate(R.layout.fragment_heads,v,true);
        toolbarTitle =(TextView)getActivity().findViewById(R.id.titleText);
        toolbarTitle.setText("Heads");
        toolbarOptionLayout=(RelativeLayout)getActivity().findViewById(R.id.toolbarSpinnerLayout);
        toolbarOptionLayout.setVisibility(View.INVISIBLE);

        fm =(FloatingActionMenu)getActivity().findViewById(R.id.floating_contact);
        fm.hideMenu(true);

        ListView listView = (ListView)v.findViewById(R.id.list_view);
        listView.setAdapter(new PaintingsAdapter(getActivity(),Heads.this));

        listTouchInterceptor = Views.find(v, R.id.touch_interceptor_view);
        listTouchInterceptor.setClickable(false);
        detailsLayout = Views.find(v, R.id.details_layout);
        detailsLayout.setVisibility(View.INVISIBLE);

        unfoldableView = Views.find(v, R.id.unfoldable_view);
        Bitmap glance = BitmapFactory.decodeResource(getResources(), R.drawable.unfold_glance);
        unfoldableView.setFoldShading(new GlanceFoldShading(glance));

        unfoldableView.setOnFoldingListener(new UnfoldableView.SimpleFoldingListener() {
            @Override
            public void onUnfolding(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(true);
                detailsLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onUnfolded(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(false);
            }

            @Override
            public void onFoldingBack(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(true);
            }

            @Override
            public void onFoldedBack(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(false);
                detailsLayout.setVisibility(View.INVISIBLE);
            }
        });

        return v;
    }


    public  void openDetails(View coverView, Painting painting) {
        final ImageView image = Views.find(detailsLayout, R.id.details_image);
        final TextView title = Views.find(detailsLayout, R.id.details_title);
        final TextView description = Views.find(detailsLayout, R.id.details_text);

        GlideHelper.loadPaintingImage(image, painting);
        title.setText(painting.getTitle());

        SpannableBuilder builder = new SpannableBuilder(getActivity());
        builder
                .createStyle().setFont(Typeface.DEFAULT_BOLD).setSize(16).apply()
                .append(R.string.designation).append(": ")
                .clearStyle()
                .append(painting.getDesignation()).append("\n")
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append(R.string.contact).append(": ")
                .clearStyle()
                .append(painting.getContact()).append("\n")
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append(R.string.email).append(": ")
                .clearStyle()
                .append(painting.getEmail()).append("\n")
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append(R.string.description).append(": ")
                .clearStyle()
                .append(painting.getDescription());
        description.setText(builder.build());

        unfoldableView.unfold(coverView, detailsLayout);
    }

}
