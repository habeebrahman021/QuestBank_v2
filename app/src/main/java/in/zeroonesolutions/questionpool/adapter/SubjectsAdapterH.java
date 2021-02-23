package in.zeroonesolutions.questionpool.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.zeroonesolutions.questionpool.Subject;
import in.zeroonesolutions.questionpool.R;

public class SubjectsAdapterH extends RecyclerView.Adapter<SubjectsAdapterH.SubjectsHolder> {

    Context context;
    List<Subject> contactList;

    public SubjectsAdapterH(Context context, List<Subject> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @Override
    public SubjectsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_subject_v, parent, false);
        return new SubjectsHolder(view);
    }

    @Override
    public void onBindViewHolder(SubjectsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class SubjectsHolder extends RecyclerView.ViewHolder {

        //TextView txtName, txtStatus;

        public SubjectsHolder(View view) {
            super(view);
//            imgProfile = view.findViewById(R.id.imgProfile);
//            txtName = view.findViewById(R.id.txtName);
//            txtStatus = view.findViewById(R.id.txtStatus);
        }
    }
}

