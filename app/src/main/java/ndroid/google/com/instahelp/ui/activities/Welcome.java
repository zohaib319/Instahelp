package ndroid.google.com.instahelp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import ndroid.google.com.instahelp.MainActivity;
import ndroid.google.com.instahelp.R;

public class Welcome extends AppCompatActivity {

    Button signup_using_email;
    LinearLayout goto_login;
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        goto_login = (LinearLayout)findViewById(R.id.go_to_signin);
        goto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent a=new Intent(Welcome.this,LoginActivity.class);
                startActivity(a);

            }
        });

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        layouts = new int[]{
                R.layout.slide1,
                R.layout.slide1,
                R.layout.slide1};

        // adding bottom dots
        addBottomDots(0);

        signup_using_email=(Button) findViewById(R.id.signup_using_email);
        signup_using_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Welcome.this,RegisterActivity.class);
                startActivity(intent);

            }
        });

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.setOffscreenPageLimit(-1);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);


    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            Log.d("log","dots");
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        Log.d("length",dotsLayout.getChildCount()+"");

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }


    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

              }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };



    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            final View view = layoutInflater.inflate(layouts[position], container, false);

            final EditText preview1=(EditText) view.findViewById(R.id.preview1);
            preview1.setRawInputType(InputType.TYPE_CLASS_TEXT);

            if(position!=0)
            {
                if(position==1)
                    preview1.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                else
                    preview1.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        final String arr = getResources().getStringArray(R.array.welcom_strings)[position];

                        new Timer().scheduleAtFixedRate(new TimerTask() {
                            int count = 0;

                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (count < arr.length()) {
                                            if(count==0)
                                                preview1.setText("");
                                            preview1.setSelection(preview1.getText().length());
                                            preview1.append(arr.charAt(count) + "");
                                            count++;
                                        }else {
                                        }
                                    }
                                });
                            }
                        }, 10, 100);
                    }
                }, 4000);

            }else  {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        final String arr = getResources().getStringArray(R.array.welcom_strings)[position];

                        new Timer().scheduleAtFixedRate(new TimerTask() {
                            int count = 0;

                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (count < arr.length()) {
                                            if(count==0)
                                                preview1.setText("");
                                            preview1.setSelection(preview1.getText().length());
                                            preview1.append(arr.charAt(count) + "");
                                            count++;
                                        }else{

                                        }
                                    }
                                });
                            }
                        }, 10, 100);
                    }
                }, 200);
            }


            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
