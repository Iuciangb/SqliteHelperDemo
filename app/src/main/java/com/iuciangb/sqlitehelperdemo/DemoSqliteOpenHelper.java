package com.iuciangb.sqlitehelperdemo;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

/**
 * @author YY
 * @create 2019/4/3
 * @Describe
 **/
public class DemoSqliteOpenHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "demo.db";
    private final static String CREATE_HISTORY_DB = "create table " + DBConfig.SearchHistory.TABLE_NAME + "(" +
            DBConfig.PRIMARY_ID + " integer primary key autoincrement, " +
            DBConfig.SearchHistory.COLUMN_NUMBER + " integer, " +
            DBConfig.SearchHistory.COLUMN_ACCOUNT + " varchar(100)," +
            DBConfig.SearchHistory.COLUMN_NICKNAME + " varchar(100), " +
            DBConfig.SearchHistory.COLUMN_INTRODUCTION + " varchar(200), " +
            DBConfig.SearchHistory.COLUMN_HOT_MARK + " varchar(100));";
    /**
     * 資料庫version
     */
    private final static int DB_VERSION = 1;
    private static DemoSqliteOpenHelper sDemoSqliteOpenHelper;
    private Context mContext;

    protected static DemoSqliteOpenHelper getInstance(Context context) {
        if (sDemoSqliteOpenHelper != null) {
            return sDemoSqliteOpenHelper;
        }
        synchronized (DemoSqliteOpenHelper.class) {
            if (sDemoSqliteOpenHelper != null) {
                return sDemoSqliteOpenHelper;
            }
            sDemoSqliteOpenHelper = new DemoSqliteOpenHelper(context, DB_VERSION);
        }
        return sDemoSqliteOpenHelper;
    }

    protected DemoSqliteOpenHelper(@Nullable Context context, int version) {
        this(context, DB_NAME, null, version);
    }

    protected DemoSqliteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    protected DemoSqliteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        mContext = context;
    }

    /**
     * 第一次使用getWritableDatabase()或getReadableDatabase()時，
     * 會進onCreate，所以將創建資料表寫在這
     *
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_HISTORY_DB);
    }

    /**
     * 如果版本有升級，會進這裡
     * 這個方式是整個資料表移除重建，
     * 但是如果你需要保留資料，
     * 則需要用AlterTable的方式，且記得判斷新舊版本
     *
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        dropTable(sqLiteDatabase);
        onCreate(sqLiteDatabase);
    }

    public void dropTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBConfig.SearchHistory.TABLE_NAME);
    }

    public void alterTable(SQLiteDatabase sqLiteDatabase, String tableName, String columnName, String columnType) {
        sqLiteDatabase.execSQL("ALTER TABLE '" + tableName + "' ADD COLUMN '" + columnName + "' '" + columnType + "' DEFAULT 0");
    }
}
