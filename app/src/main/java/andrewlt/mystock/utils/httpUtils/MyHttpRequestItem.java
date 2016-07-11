package andrewlt.mystock.utils.httpUtils;

/**
 * Created by liut1 on 7/2/16.
 */
public class MyHttpRequestItem {
    private String url;
    private Integer connectionTimeout;
    private Integer readTimeout;
    private String method;//get or post

    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setConnectionTimeout(Integer connectionTimeout){
        this.connectionTimeout = connectionTimeout;
    }
    public Integer getConnectionTimeout(){
        return this.connectionTimeout;
    }
    public void setReadTimeout(Integer readTimeout){
        this.readTimeout = readTimeout;
    }
    public Integer getReadTimeout(){
        return this.readTimeout;
    }
    public void setMethod(String method){
        this.method = method;
    }
    public String getMethod(){
        return this.method;
    }
}
