package sg.edu.rp.c347.p11mydatabook;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Navigation> drawerItems;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    NavAdapter aa;
    String currentTitle;
    ActionBar ab;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.drawer_layout);
        drawerList = findViewById(R.id.left_drawer);

//        drawerItems = new String[]{"Bio", "Vaccination", "Anniversary", "About Us"};
//
//        Navigation drawerItems = new Navigation("Bio");

        drawerItems = new ArrayList<>();
        String bio = "Bio";
        String vac = "Vaccination";
        String ann = "Anniversary";
        String about = "About Us";

        drawerItems.add(new Navigation(bio));
        drawerItems.add(new Navigation(vac));
        drawerItems.add(new Navigation(ann));
        drawerItems.add(new Navigation(about));
        aa = new NavAdapter(this, R.layout.navigation_row, drawerItems);


        ab = getSupportActionBar();
        drawerList.setAdapter(aa);
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 3) {

                    startActivity(new Intent(getApplicationContext(), AboutActivity.class));

                    return;
                }


                Fragment fragment = null;

                if (position == 0)
                    fragment = new BioFragment();
                else if (position == 1)
                    fragment = new VaccinationFragment();
                else if (position == 2)
                    fragment = new AnniversaryFragment();


                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.content_frame, fragment);
                transaction.commit();

                drawerList.setItemChecked(position, true);
                currentTitle = drawerItems.get(position).getTitle(); //title of the current position
                ab.setTitle(currentTitle);
                drawerLayout.closeDrawer(drawerList); // close the drawer go back to left side

            }
        });

        currentTitle = this.getTitle().toString();

        drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                ab.setTitle(currentTitle);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ab.setTitle("Make a selection");
            }
        };
        // Set the drawer toggle as the DrawerListener
        drawerLayout.addDrawerListener(drawerToggle);
        ab.setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync toggle state so the indicator is shown properly.
        //  Have to call in onPostCreate()
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
