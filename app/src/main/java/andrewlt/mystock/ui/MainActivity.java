package andrewlt.mystock.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import andrewlt.mystock.R;
import andrewlt.mystock.ui.TopBar.WidgetTopBar;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    private final String TAG=this.getClass().getName();
    private Intent mHomeIntent;
    private Intent mMarketIntent;
    private Intent mFavoriteIntent;
    private Intent mNewsIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();//高版本可以换成 ActionBar actionBar = getActionBar();
        actionBar.hide();
        this.mHomeIntent = new Intent(this, MainActivity.class);
//        this.mMarketIntent = new Intent(this, SearchSquareActivity.class);
        this.mFavoriteIntent = new Intent(this, FavoriteActivity.class);
//        this.mNewsIntent = new Intent(this, MyInfoActivity.class);
        initTopbar();
        initRadioButton();
    }

    public void initRadioButton(){
        ((RadioButton) findViewById(R.id.radio_button0)).setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button1)).setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button2)).setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button3)).setOnCheckedChangeListener(this);
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
                    startActivity(mFavoriteIntent);
                    break;
                case R.id.radio_button3:
//                    Log.e("======",buttonView.getId() + "is checked 4 ");
                    break;
            }
        }
    }

    private void initTopbar(){
        WidgetTopBar wtbOne = (WidgetTopBar) findViewById(R.id.wdt_main);
        wtbOne.getLeftBtnImage().setOnClickListener(this);
        wtbOne.getRightBtnImage().setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_left_top_bar: {
                Toast.makeText(this, "第二个标题 左边按钮", Toast.LENGTH_SHORT).show();
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
}
