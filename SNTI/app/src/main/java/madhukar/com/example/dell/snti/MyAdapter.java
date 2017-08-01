package madhukar.com.example.dell.snti;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Aditya on 07-09-2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    public static int selected_item=0;
    int selectedPos,selectedPosition;
  //  private ClickListener clickListener;
    List<Information> data= Collections.emptyList();
    MyAdapter(Context context, List<Information> data)
    {
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.custom_row, parent,false);
        MyViewHolder holder=new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            Information current=data.get(position);
            holder.title.setText(current.title);
        if(position == selected_item)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#2000bcd4"));
            holder.title.setTextColor(Color.parseColor("#FF5683"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#00000000"));
            holder.title.setTextColor(Color.BLACK);
        }
//        holder.itemView.setSelected(selectedPos == position);

        holder.icon.setImageResource(current.iconId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

  /*  public void setClickListener(ClickListener clickListener)
    {
        this.clickListener=clickListener;
    }
*/
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView icon;
        TextView title;
        ImageView bg;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            bg= (ImageView) itemView.findViewById(R.id.bg);
            title= (TextView) itemView.findViewById(R.id.title);
            icon= (ImageView) itemView.findViewById(R.id.icon);

        }

        @Override
        public void onClick(View v) {
          /*  context.startActivity(new Intent(context,SubActivity.class));
            if(clickListener!=null)
            {
                clickListener.itemClicked(v,getPosition());
            }
*/
            notifyItemChanged(selectedPos);
            selectedPosition = getLayoutPosition();
            notifyItemChanged(selectedPos);

        }
    }

    /*public interface ClickListener{
        public void itemClicked(View v,int position);

    }
        */
}
