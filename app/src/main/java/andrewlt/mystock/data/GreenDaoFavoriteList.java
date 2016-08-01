package andrewlt.mystock.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import me.mystock.greendao.DaoMaster;
import me.mystock.greendao.DaoSession;
import me.mystock.greendao.FavoriteList;
import me.mystock.greendao.FavoriteListDao;

/**
 * Created by liut1 on 7/12/16.
 */
public class GreenDaoFavoriteList {
    private static GreenDaoFavoriteList instance = null;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private FavoriteListDao dao;
    public GreenDaoFavoriteList getInstance(){
        if(instance == null){
            synchronized (GreenDaoFavoriteList.class){
                if(instance == null){
                    instance = new GreenDaoFavoriteList();
                }
            }
        }
        return instance;
    }
    public void initGreenDao(Context context, String dbName){

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, dbName, null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        dao = daoSession.getFavoriteListDao();
    }
    public FavoriteListDao getNoteDao() {
        return dao;
    }
    public void addNote(FavoriteList note){
        dao.insert(note);
    }
    public void delNote(FavoriteList note){
        dao.delete(note);
    }
    public void searchNote(){

    }
}
