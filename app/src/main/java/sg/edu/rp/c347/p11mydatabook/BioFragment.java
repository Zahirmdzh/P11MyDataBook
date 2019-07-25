package sg.edu.rp.c347.p11mydatabook;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BioFragment extends Fragment {

    Button btnBio;
    TextView tvBio;


    public BioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_bio, container, false);
        btnBio = view.findViewById(R.id.btnBio);
        tvBio = view.findViewById(R.id.tvBio);

        SharedPreferences pref = getActivity().getSharedPreferences("app", Context.MODE_PRIVATE);
        String bioLoad = pref.getString("bio","");
        tvBio.setText(bioLoad);

        btnBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout layout = new LinearLayout(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                        (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layout.setLayoutParams(params);
                layout.setOrientation(LinearLayout.VERTICAL);


                final EditText et = new EditText(getContext());

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /// code here
                        tvBio.setText(et.getText().toString());
                        SharedPreferences pref = getActivity().getSharedPreferences("app", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();

                        editor.putString("bio",et.getText().toString());
                        editor.commit();




                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });


                et.setGravity(Gravity.CENTER_HORIZONTAL);
                et.setText(tvBio.getText().toString());
                layout.addView(et);

                AlertDialog myDialog = builder.create();

                myDialog.setMessage("Give description of your Biography");
                myDialog.setTitle("Edit Bio");
                myDialog.setView(layout);
                myDialog.show();
            }
        });

        return view;
    }

}
