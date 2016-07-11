package andrewlt.mystock.utils.StockRequest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import andrewlt.mystock.utils.httpUtils.MyHttpRequestItem;
import andrewlt.mystock.utils.httpUtils.MyHttpUrlConnection;

/**
 * Created by liut1 on 7/2/16.
 */
public class MyStockInfoRequest {
    private String url="http://hq.sinajs.cn/list=";
    private String urlGif="http://image.sinajs.cn/newchart/daily/n/";
    private String info = "";
    private String stockCode = "";
    private MyHttpRequestItem myHttpRequestItem = new MyHttpRequestItem();
    private SinaStockInfo sinaStockInfo;
    public MyStockInfoRequest(String stockCode){
        this.stockCode = stockCode;
        myHttpRequestItem.setUrl(url+resetStockCode(stockCode));
        myHttpRequestItem.setConnectionTimeout(3000);
        myHttpRequestItem.setReadTimeout(4000);
        myHttpRequestItem.setMethod("GET");
        parseStockInfo();
    }
    private String resetStockCode(String stockCode){
        //TODO:find out where is this code from sh, sz or not exist
        if((stockCode.substring(0,1).equals("6"))) {
            return "sh" + stockCode;
        }else if((stockCode.substring(0,1).equals("9"))){//B stock
            return "sh" + stockCode;
        }else if((stockCode.substring(0,1).equals("3"))){
            return "sz" + stockCode;
        }else if((stockCode.substring(0,1).equals("0"))){
            return "sz" + stockCode;
        }else if((stockCode.substring(0,1).equals("2"))){//B stock
            return "sz" + stockCode;
        }
        else {
            return "sh"+stockCode;
        }
    }
    private void parseStockInfo(){
        MyHttpUrlConnection myHttpUrlConnection = new MyHttpUrlConnection(myHttpRequestItem);
        try {
            sinaStockInfo = SinaStockInfo.parseStockInfo(myHttpUrlConnection.GetFromServer());
        } catch (SinaStockInfo.ParseStockInfoException e) {
            e.printStackTrace();
        }
    }
    public Bitmap getStockBitmap(){
        myHttpRequestItem.setUrl(urlGif+resetStockCode(stockCode)+".gif");
        MyHttpUrlConnection myHttpUrlConnection = new MyHttpUrlConnection(myHttpRequestItem);
        return myHttpUrlConnection.GetPictureFromServer();
    }
    /**
     * 获取股票名称
     * @return 股票名称
     */
    public String getName() {
        return sinaStockInfo.getName();
    }
    /**
     * 获取股票id
     * @return 股票编号
     */
    public String getID(){
        return this.stockCode;
    }
    /**
     * 获取今日开盘价
     * @return 今日股票开盘价
     */
    public float getTodayPrice() {
        return sinaStockInfo.getTodayPrice();
    }

    /**
     * 获取昨日收盘价
     * @return 昨日收盘价
     */
    public float getYestodayPrice() {
        return sinaStockInfo.getYestodayPrice();
    }

    /**
     * 获取当前股价
     * @return 当前股价
     */
    public float getNowPrice() {
        return sinaStockInfo.getNowPrice();
    }

    /**
     * 获取今日最高价
     * @return 今日最高价
     */
    public float getHighestPrice() {
        return sinaStockInfo.getHighestPrice();
    }

    /**
     * 获取今日最低价
     * @return 今日最低价
     */
    public float getLowestPrice() {
        return sinaStockInfo.getLowestPrice();
    }


    public long getTradeCount() {
        return sinaStockInfo.getTradeCount();
    }


    public Double getTradeMoney() {

        return sinaStockInfo.getTradeMoney();
    }


    public SinaStockInfo.BuyOrSellInfo[] getBuyInfo() {
        return sinaStockInfo.getBuyInfo();
    }


    public SinaStockInfo.BuyOrSellInfo[] getSellInfo() {
        return sinaStockInfo.getSellInfo();
    }


    public String getDate() {
        return sinaStockInfo.getDate();
    }


    public String getTime() {
        return sinaStockInfo.getTime();
    }
    @Override
    public String toString(){
        return sinaStockInfo.toString();
    }
}
