package in.zeroonesolutions.questionpool.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import in.zeroonesolutions.questionpool.Material;
import in.zeroonesolutions.questionpool.R;

public class MaterialsAdapterH extends RecyclerView.Adapter<MaterialsAdapterH.MaterialsHolder> {

    Context context;
    List<Material> contactList;
    String viewType;

    public MaterialsAdapterH(Context context, List<Material> contactList,String viewType) {
        this.context = context;
        this.contactList = contactList;
        this.viewType = viewType;
    }

    @Override
    public MaterialsHolder onCreateViewHolder(ViewGroup parent, int type) {
        View view;
        if (viewType.equals("Recents"))
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recent_material_h, parent, false);
        else
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_saved_material_h, parent, false);
        return new MaterialsHolder(view);
    }

    @Override
    public void onBindViewHolder(MaterialsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class MaterialsHolder extends RecyclerView.ViewHolder {

        //TextView txtName, txtStatus;

        public MaterialsHolder(View view) {
            super(view);
//            imgProfile = view.findViewById(R.id.imgProfile);
//            txtName = view.findViewById(R.id.txtName);
//            txtStatus = view.findViewById(R.id.txtStatus);
        }
    }
}

