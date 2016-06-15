package kinwindtest.com.tablayouttest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabFindFragmenttitle;
    private ViewPager vpFindFragmentpager;

    private FragmentPagerAdapter fAdapter;                               //定义adapter

    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;

    Find_tab_Adapter find_tab_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        toolbar.setTitle("tablayout");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void initialize() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabFindFragmenttitle = (TabLayout) findViewById(R.id.tab_FindFragment_title);
        vpFindFragmentpager = (ViewPager) findViewById(R.id.vp_FindFragment_pager);


        setSupportActionBar(toolbar);
        toolbar.setTitle("tablayout");

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
}
