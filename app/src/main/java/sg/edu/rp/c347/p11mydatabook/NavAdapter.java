package sg.edu.rp.c347.p11mydatabook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NavAdapter extends ArrayAdapter {


    private ArrayList<Navigation> navItems;
    private Context context;
    TextView tvTitle;
    ImageView ivIcon;

    public NavAdapter(Context context, int resource, ArrayList<Navigation> objects) {
        super(context, resource, objects);
     navItems = objects;
     this.context = context;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = layoutInflater.inflate(R.layout.navigation_row,parent,false);

        tvTitle = rowView.findViewById(R.id.tvTitle);
        ivIcon = rowView.findViewById(R.id.ivIcon);

        Navigation itemAtpos = navItems.get(position);


        if (itemAtpos.getTitle().equalsIgnoreCase("Bio")) {
            tvTitle.setText("Bio");
            ivIcon.setImageResource(android.R.drawable.ic_dialog_info);

        } else if (itemAtpos.getTitle().equalsIgnoreCase("Vaccination")) {
            tvTitle.setText("Vaccination");
            ivIcon.setImageResource(android.R.drawable.ic_menu_edit);

        } else if (itemAtpos.getTitle().equalsIgnoreCase("Anniversary")) {
            tvTitle.setText("Anniversary");
            ivIcon.setImageResource(android.R.drawable.ic_menu_my_calendar);

        } else if (itemAtpos.getTitle().equalsIgnoreCase("About Us")) {
            tvTitle.setText("About Us");
            ivIcon.setImageResource(android.R.drawable.star_big_on);
        }




        return rowView;
    }
}
