package com.example.rushitaa.photo_app;
/**
 * Activity implemented the Laucher page of the application using ViewPager.
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class LauncherActivity extends ActionBarActivity {


    SectionsPagerAdapter mSectionsPagerAdapter;


    ViewPager mViewPager;
    private static List<Activities> act = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        act.add(new Activities("Badminton | Tennis | BasketBall | Soccer | Football"));
        act.add(new Activities("Guitar | Violin | Piano | Drums | Flute"));
        act.add(new Activities("Aerobics | Contemporary | Fusion | Classical | Salsa"));
        act.add(new Activities("Indian | Italian | Chinese | Mexican | Thai"));

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launcher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_help) {
            Intent HelpIntent = new Intent(LauncherActivity.this, HelpActivity.class);
            startActivity(HelpIntent);
            return true;
        }



        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault(); // adapts based on the country,region selected
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
                case 3:
                    return getString(R.string.title_section4).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView sectionLabelTextView = (TextView) rootView.findViewById(R.id.section_label);
            ImageView image = (ImageView) rootView.findViewById(R.id.imageView);
           // Button register = (Button) rootView.findViewById(R.id.button);
            Button mycontacts = (Button) rootView.findViewById(R.id.button2);

            int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER)-1;
            String name = act.get(sectionNumber).getName();
//             String source = act.get(sectionNumber).getSrc();
            sectionLabelTextView.setText(name);
            if(sectionNumber==0)
            {
                image.setImageResource(R.drawable.balls);
            }
            else if (sectionNumber==1)
            {
                image.setImageResource(R.drawable.music);
            }
            else if (sectionNumber==2)
            {
                image.setImageResource(R.drawable.dance);
            }
            else if(sectionNumber==3)
            {
                image.setImageResource(R.drawable.cuisine);
            }



            mycontacts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent logIntent = new Intent(getActivity(), ViewListActivity.class);
                    startActivity(logIntent);
                }
            });


            return rootView;
        }
    }


}
