package madhukar.com.example.dell.snti;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dell on 5/30/2017.
 */

public class GuideAdapter extends  RecyclerView.Adapter<GuideAdapter.GuideViewHolder>{

    private List<guideData> guideList;
    private Context context;
    private int i=1;

    public GuideAdapter(Context context,List<guideData> guideList)
    {
        this.guideList = guideList;
        this.context = context;
    }

    @Override
    public GuideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.guide_row_layout, parent, false);

        return new GuideAdapter.GuideViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GuideAdapter.GuideViewHolder holder, int position) {
        guideData guide = guideList.get(position);
        holder.pnumber.setText(guide.getPnumber());
        holder.department.setText(guide.getDepartment());
        holder.phone.setText(guide.getPhone());
        holder.email.setText(guide.getEmail());
        holder.image.setImageResource(guide.getImageId());
        holder.guideName.setText(guide.getGuideName());

        RoundedBitmapDrawable circularImage;


            ImageView imageView = (ImageView)holder.image;
            circularImage = makeCircularImage(guide.getImageId());
            imageView.setImageDrawable(circularImage);

            Animation imageanimation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right_dev);
            imageanimation.setStartOffset(i*200);
            imageView.startAnimation(imageanimation);

            TextView textView = (TextView)holder.guideName;
            Animation textanimation = AnimationUtils.loadAnimation(context, R.anim.fade_dev);
            textView.startAnimation(textanimation);
            textanimation.setStartOffset(i*400 + 1400);
            i++;

    }

    @Override
    public int getItemCount() {
        return guideList.size();
    }

    public class GuideViewHolder extends RecyclerView.ViewHolder {
        public TextView guideName,pnumber,department,phone,email;
        public ImageView image;

        public GuideViewHolder(View view) {
            super(view);
            guideName = (TextView) view.findViewById(R.id.guide_name);
            pnumber = (TextView) view.findViewById(R.id.guide_pno);
            department = (TextView) view.findViewById(R.id.guide_department);
            phone = (TextView) view.findViewById(R.id.guide_phone);
            email = (TextView) view.findViewById(R.id.guide_email);
            image = (ImageView)view.findViewById(R.id.photo_guide);
        }
    }

    private RoundedBitmapDrawable makeCircularImage(int drawable) {

        Bitmap imageBitmap = BitmapFactory.decodeResource(context.getResources(), drawable);
        RoundedBitmapDrawable circularImageLocal = RoundedBitmapDrawableFactory.create(context.getResources(),
                imageBitmap);

        circularImageLocal.setCornerRadius(100);
        circularImageLocal.setCircular(true);
        return circularImageLocal;
    }



}
