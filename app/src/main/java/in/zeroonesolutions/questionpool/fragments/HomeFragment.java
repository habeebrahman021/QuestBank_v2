package in.zeroonesolutions.questionpool.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.zeroonesolutions.questionpool.Material;
import in.zeroonesolutions.questionpool.R;
import in.zeroonesolutions.questionpool.Subject;
import in.zeroonesolutions.questionpool.SubjectAdapter;
import in.zeroonesolutions.questionpool.adapter.MaterialsAdapterH;
import in.zeroonesolutions.questionpool.adapter.SubjectsAdapterH;

public class HomeFragment extends Fragment {
    RecyclerView rvRecents, rvSaved, rvSubjects;
    MaterialsAdapterH materialsAdapter;
    SubjectsAdapterH subjectsAdapter;
    List<Material> materialList;
    List<Subject> subjectList;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvRecents = view.findViewById(R.id.rvRecents);
        rvSaved = view.findViewById(R.id.rvFavourites);
        rvSubjects = view.findViewById(R.id.rvSubjects);


        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvRecents.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        materialList = new ArrayList<>();
        materialList.add(new Material());
        materialList.add(new Material());
        materialList.add(new Material());
        materialsAdapter = new MaterialsAdapterH(getActivity(),materialList,"Recents");
        rvRecents.setAdapter(materialsAdapter);

        rvSaved.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        materialsAdapter = new MaterialsAdapterH(getActivity(),materialList,"Saved");
        rvSaved.setAdapter(materialsAdapter);

        rvSubjects.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        subjectList = new ArrayList<>();
        subjectList.add(new Subject());
        subjectList.add(new Subject());
        subjectList.add(new Subject());
        subjectList.add(new Subject());
        subjectsAdapter = new SubjectsAdapterH(getActivity(),subjectList);
        rvSubjects.setAdapter(subjectsAdapter);



//        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 3);
//        rvSubjects.setLayoutManager(mGridLayoutManager);
//
//        List<Subject> mSubjectList = new ArrayList<>();
//        mSubjectList.add(new Subject(1,"Chemistry",R.drawable.chemistry));
//        mSubjectList.add(new Subject(2,"Physics",R.drawable.physics));
//        mSubjectList.add(new Subject(3,"Biology",R.drawable.biology));
//        mSubjectList.add(new Subject(4,"Maths",R.drawable.maths));
//        mSubjectList.add(new Subject(5,"IT",R.drawable.computer));
//        mSubjectList.add(new Subject(6,"Social Science",R.drawable.social));
////        mSubjectList.add(new Subject(7,"English",R.drawable.english));
////        mSubjectList.add(new Subject(8,"Malayalam",R.drawable.malayalam));
////        mSubjectList.add(new Subject(9,"Hindi",R.drawable.hindi));
////        mSubjectList.add(new Subject(10,"Arabic",R.drawable.arabic));
////        mSubjectList.add(new Subject(11,"Urudu",R.drawable.urudu));
////        mSubjectList.add(new Subject(12,"Sanskrit",R.drawable.sanscrit));
//
//        SubjectAdapter mAdapter = new SubjectAdapter(getActivity(),mSubjectList);
//        rvSubjects.setAdapter(mAdapter);
    }
}
