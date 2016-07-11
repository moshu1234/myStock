package andrewlt.mystock.ui.adapters;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by liut1 on 7/3/16.
 */
public class SlidelessViewPager extends ViewPager {
    private Boolean slideEnable = true;
    public SlidelessViewPager(Context context) {
        super(context);
    }

    public SlidelessViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(slideEnable) {
            return super.onTouchEvent(event);
        }else {
            return false;
        }
    }
}
