package in.zeroonesolutions.questionpool;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectViewHolder> {

    private Context mContext;
    private List<Subject> mSubjectList;

    public SubjectAdapter(Context mContext, List<Subject> mSubjectList) {
        this.mContext = mContext;
        this.mSubjectList = mSubjectList;
    }

    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext)
                .inflate(R.layout.row_subject, parent, false);

        return new SubjectViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder holder, final int position) {
        holder.mImage.setImageResource(mSubjectList.get(position).getIcon());
        holder.mTitle.setText(mSubjectList.get(position).getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(mContext,MaterialsActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("sub_id",mSubjectList.get(position).getId());
                in.putExtra("sub_name",mSubjectList.get(position).getName());
                mContext.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSubjectList.size();
    }

}

class SubjectViewHolder extends RecyclerView.ViewHolder {
    ImageView mImage;
    TextView mTitle;

    SubjectViewHolder(View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.img_icon);
        mTitle = itemView.findViewById(R.id.txt_name);
    }
}

