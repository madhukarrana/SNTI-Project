package madhukar.com.example.dell.snti;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 5/30/2017.
 */

public class TraineeUnderGuideAdapter extends  RecyclerView.Adapter<TraineeUnderGuideAdapter.TraineeUnderGuideViewHolder>
{

    List<TraineeUnderGuideData> traineeUnderGuideDataList =new ArrayList<>();

    public TraineeUnderGuideAdapter(List<TraineeUnderGuideData> traineeUnderGuideDataList) {
        this.traineeUnderGuideDataList = traineeUnderGuideDataList;
    }

    @Override
    public TraineeUnderGuideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trainee_under_guide_layout, parent, false);

        return new TraineeUnderGuideAdapter.TraineeUnderGuideViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(TraineeUnderGuideViewHolder holder, int position) {

        TraineeUnderGuideData traineeData = traineeUnderGuideDataList.get(position);
        holder.reference.setText(traineeData.getReference());
        holder.name.setText(traineeData.getName());
        holder.institute.setText(traineeData.getInstitute());
        holder.branch.setText(traineeData.getBranch());
        holder.startDate.setText("Start Date :"+traineeData.getStartDate());
        holder.endDate.setText("End Date :"+traineeData.getEndDate());

    }

    @Override
    public int getItemCount() {
        return traineeUnderGuideDataList.size();
    }

    public class TraineeUnderGuideViewHolder extends RecyclerView.ViewHolder {
        public TextView reference,name,institute,branch,startDate,endDate;

        public TraineeUnderGuideViewHolder(View view) {
            super(view);
            reference = (TextView) view.findViewById(R.id.guide_trainee_vt);
            name = (TextView) view.findViewById(R.id.guide_trainee_name);
            institute = (TextView) view.findViewById(R.id.guide_trainee_institute);
            branch = (TextView) view.findViewById(R.id.guide_trainee_branch);
            startDate=(TextView)view.findViewById(R.id.guide_trainee_startDate);
            endDate = (TextView)view.findViewById(R.id.guide_trainee_endDate);
        }
    }

}
