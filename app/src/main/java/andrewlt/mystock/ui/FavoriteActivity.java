package andrewlt.mystock.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import andrewlt.mystock.R;
import andrewlt.mystock.data.GreenDaoFavoriteList;
import andrewlt.mystock.ui.FavoriteFragments.FavoriteFragmentsSearch;
import andrewlt.mystock.ui.FavoriteFragments.FavoriteFragmentsSelected;
import andrewlt.mystock.ui.TopBar.WidgetTopBar;
import andrewlt.mystock.ui.adapters.FavoriteSearchAdapter;
import andrewlt.mystock.ui.adapters.SlidelessViewPager;


/**
 * Created by liut1 on 7/3/16.
 */
public class FavoriteActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Intent mHomeIntent;
    private Intent mMarketIntent;
    private Intent mFavoriteIntent;
    private Intent mNewsIntent;

    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private SlidelessViewPager mViewPager;
    FragmentStatePagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        ActionBar actionBar = getSupportActionBar();//高版本可以换成 ActionBar actionBar = getActionBar();
        actionBar.hide();
        initGreenDao();
        initFragments();
        Log.e("========","get into favorite activity");
        this.mHomeIntent = new Intent(this, MainActivity.class);
//        this.mMarketIntent = new Intent(this, SearchSquareActivity.class);
        this.mFavoriteIntent = new Intent(this, FavoriteActivity.class);
//        this.mNewsIntent = new Intent(this, MyInfoActivity.class);
        initTopbar();
        initRadioButton();
    }
    private void initTopbar(){
        WidgetTopBar wtbOne = (WidgetTopBar) findViewById(R.id.favorite_list_topbar);
        wtbOne.getLeftBtnImage().setOnClickListener(this);
        wtbOne.getRightBtnImage().setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_left_top_bar: {
                Toast.makeText(this, "第二个标题 左边按钮", Toast.LENGTH_SHORT).show();
                if(mViewPager.getCurrentItem() == 0)
                    mViewPager.setCurrentItem(1);
                else
                    mViewPager.setCurrentItem(0);
                break;
            }
            case R.id.ib_right_top_bar: {
                Toast.makeText(this, "第一个标题 右边按钮", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_left_top_bar: {
                Toast.makeText(this, "第一个标题 左边按钮", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_right_top_bar: {
                Toast.makeText(this, "第二个标题 右边按钮", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    public void initRadioButton(){
        ((RadioButton) findViewById(R.id.radio_button0)).setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button1)).setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button2)).setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button3)).setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button2)).setChecked(true);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            switch (buttonView.getId()) {
                case R.id.radio_button0:
//                    Log.e("======",buttonView.getId() + "is checked 1 ");
                    break;
                case R.id.radio_button1:
//                    Log.e("======",buttonView.getId() + "is checked 2 ");
                    break;
                case R.id.radio_button2:
//                    Log.e("======",buttonView.getId() + "is checked 3 ");
//                    startActivity(mFavoriteIntent);
                    break;
                case R.id.radio_button3:
//                    Log.e("======",buttonView.getId() + "is checked 4 ");
                    break;
            }
        }
    }
    public void initFragments(){
        mViewPager = (SlidelessViewPager)findViewById(R.id.favorite_list_viewpager);
        FavoriteFragmentsSelected mFavoriteFragmentsSelected = new FavoriteFragmentsSelected();
        FavoriteFragmentsSearch mFavoriteFragmentsSearch = new FavoriteFragmentsSearch();
        mFragments.add(mFavoriteFragmentsSelected);
        mFragments.add(mFavoriteFragmentsSearch);

        mAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mViewPager.setAdapter(mAdapter);
        mFavoriteFragmentsSearch.setHandler(mFavoriteFragmentsSelected.getFavoriteListHandler());
    }

    public void initGreenDao(){
        GreenDaoFavoriteList greenDaoFavoriteList = new GreenDaoFavoriteList().getInstance();
        greenDaoFavoriteList.initGreenDao(this,"favorite_list");

    }
}
