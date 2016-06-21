package kinwindtest.com.tablayouttest;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  AppBarLayout.OnOffsetChangedListener {

    private Toolbar toolbar;
    private TabLayout tabFindFragmenttitle;
    private ViewPager vpFindFragmentpager;

    private FragmentPagerAdapter fAdapter;                               //定义adapter

    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;

    Find_tab_Adapter find_tab_Adapter;
    private TextView titletext;
    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();


    }


    @Override
    protected void onResume() {
        super.onResume();
   
        titletext.setVisibility(View.VISIBLE);

        appbar.addOnOffsetChangedListener(this);




    }

    @Override
    protected void onPause() {
        super.onPause();
        appbar.removeOnOffsetChangedListener(this);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        toolbar.setTitle("tablayout");

        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
       // toolbar.setSubtitle("tabyout");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "返回", Toast.LENGTH_SHORT).show();

            }
        });

        setRefreshToolbarEnable(collapsingToolbar,false);


      //  setupCollapsingToolbar();

    }


    

    public static void setRefreshToolbarEnable(CollapsingToolbarLayout collapsingToolbarLayout,
                                               boolean refreshToolbarEnable) {
        try {
            Field field = CollapsingToolbarLayout.class.getDeclaredField("mRefreshToolbar");
            field.setAccessible(true);
            field.setBoolean(collapsingToolbarLayout, refreshToolbarEnable);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

 /*   private void setupCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(
                R.id.collapse_toolbar);

        collapsingToolbar.setTitleEnabled(false);
    }*/
    
    
    
    private void initialize() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabFindFragmenttitle = (TabLayout) findViewById(R.id.tab_FindFragment_title);
        vpFindFragmentpager = (ViewPager) findViewById(R.id.vp_FindFragment_pager);
         appbar=(AppBarLayout)findViewById(R.id.appbar);
        titletext=(TextView)findViewById(R.id.titletext);
      



        toolbar.setTitle("tablayout");
        setSupportActionBar(toolbar);

 /*       CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(
                R.id.collapse_toolbar);
        collapsingToolbar.setTitle("tabyout");
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.transparent));//设置还没收缩时状态下字体颜色
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.white));//设置收缩后Toolbar上字体的*/

         collapsingToolbar = (CollapsingToolbarLayout) findViewById(
                    R.id.collapse_toolbar);

           // collapsingToolbar.setTitleEnabled(false);


        //初始化各fragment
        FindFragment findFragment= FindFragment.newInstance("find");
        SecondeFragment findFragment2= SecondeFragment.newInstance("second");
        thritdFragment findFragment3= thritdFragment.newInstance("third");

        list_fragment = new ArrayList<>();

        list_fragment.add(findFragment);
        list_fragment.add(findFragment2);
        list_fragment.add(findFragment3);

        list_title = new ArrayList<>();
        list_title.add("热门推荐");
        list_title.add("热门收藏");
        list_title.add("本月热榜");

        tabFindFragmenttitle.setTabMode(TabLayout.MODE_FIXED);

        tabFindFragmenttitle.addTab(tabFindFragmenttitle.newTab().setText(list_title.get(0)));
        tabFindFragmenttitle.addTab(tabFindFragmenttitle.newTab().setText(list_title.get(1)));
        tabFindFragmenttitle.addTab(tabFindFragmenttitle.newTab().setText(list_title.get(2)));

        find_tab_Adapter=new Find_tab_Adapter(getSupportFragmentManager(),list_fragment,list_title);

        vpFindFragmentpager.setAdapter(find_tab_Adapter);
        tabFindFragmenttitle.setupWithViewPager(vpFindFragmentpager);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (Math.abs(i) == appbar.getTotalScrollRange()) {

            titletext.setVisibility(View.VISIBLE);
        } else {
            titletext.setVisibility(View.GONE);
        }

    }
}
