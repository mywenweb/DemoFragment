package sakura.com.tw.demofragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private FragmentManager fmgr;
    private Fragment[] pages = new Fragment[7];
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        fmgr = getSupportFragmentManager();

        pages[0] = new Page0();
        pages[1] = new Page1();
        pages[2] = new Page2();
        pages[3] = new Page3();
        pages[4] = new Page4();
        pages[5] = new Page5();
        pages[6] = new Page6();

        //呼叫
        initActionBar();
        //
        MyAdapter myAdapter = new MyAdapter(fmgr);
        viewPager.setAdapter(myAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            //最後選擇的頁面
            //若第一頁也到第二頁...最後一頁也到倒數第二頁
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0){
                    viewPager.setCurrentItem(1);
                }else if (position == 6){
                    viewPager.setCurrentItem(5);
                }else{
                    //頁面滑動上面TAB的指示器會跟著動
                    actionBar.setSelectedNavigationItem(position-1);
                }
            }
        });

        //設定初值（第二頁）
        viewPager.setCurrentItem(1);
    }

    //處理TAB選單
    private void initActionBar(){
        MyTabListner myTabListner = new MyTabListner();
        actionBar = getSupportActionBar();
        //選單的樣式...setText是文字顯示....可以用圖型顯示setIcon
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(
                actionBar.newTab().setText("基本資料").setTabListener(myTabListner)
        );
        actionBar.addTab(
                actionBar.newTab().setText("產品維修").setTabListener(myTabListner)
        );
        actionBar.addTab(
                actionBar.newTab().setText("收款作業").setTabListener(myTabListner)
        );
        actionBar.addTab(
                actionBar.newTab().setText("環境調查").setTabListener(myTabListner)
        );
        actionBar.addTab(
                actionBar.newTab().setText("產品資訊").setTabListener(myTabListner)
        );
    }

    private class MyTabListner implements ActionBar.TabListener{

        //TAB會跟著動
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
             viewPager.setCurrentItem(tab.getPosition()+1);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }
    }

    private class MyAdapter extends FragmentStatePagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return pages[i];
        }

        @Override
        public int getCount() {
            return pages.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page" + position;
        }
    }
}
