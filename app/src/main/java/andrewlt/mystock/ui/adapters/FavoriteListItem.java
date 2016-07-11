package andrewlt.mystock.ui.adapters;

import android.graphics.Bitmap;

/**
 * Created by liut1 on 7/3/16.
 */
public class FavoriteListItem {
    private String name;
    private String id;
    private String nowPrice;
    private String openToday;
    private String tradeCount;
    private String nowScale;
    private Bitmap bitmap;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setNowPrice(String nowPrice){
        this.nowPrice = nowPrice;
    }
    public String getNowPrice(){
        return this.nowPrice;
    }
    public void setOpenToday(String openToday){
        this.openToday = openToday;
    }
    public String getOpenToday(){
        return this.openToday;
    }
    public void setTradeCount(String tradeCount){
        this.tradeCount = tradeCount;
    }
    public String getTradeCount(){
        return this.tradeCount;
    }
    public void setNowScale(String nowScale){
        this.nowScale = nowScale;
    }
    public String getNowScale(){
        return this.nowScale;
    }
    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }
    public Bitmap getBitmap(){
        return this.bitmap;
    }
}
