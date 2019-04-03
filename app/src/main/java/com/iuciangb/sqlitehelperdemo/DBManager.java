package com.iuciangb.sqlitehelperdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YY
 * @create 2019/4/3
 * @Describe
 **/
public class DBManager {
    private static DBManager sDBManager;
    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    public static DBManager getInstance(Context context) {
        if (sDBManager != null) {
            return sDBManager;
        }
        synchronized (DBManager.class) {
            if (sDBManager != null) {
                return sDBManager;
            }
            sDBManager = new DBManager(context);
        }
        return sDBManager;
    }

    private DBManager(Context context) {
        mContext = context;
    }

    public void addSearchHistory(SearchAnchorData searchAnchorData) {
        if (hasThisSearchHistory(searchAnchorData.getNo())) {
            deleteSearchHistory(searchAnchorData.getNo());
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConfig.SearchHistory.COLUMN_NUMBER, searchAnchorData.getNo());
        contentValues.put(DBConfig.SearchHistory.COLUMN_ACCOUNT, searchAnchorData.getAccount());
        contentValues.put(DBConfig.SearchHistory.COLUMN_NICKNAME, searchAnchorData.getNickname());
        contentValues.put(DBConfig.SearchHistory.COLUMN_INTRODUCTION, searchAnchorData.getIntroduction());
        contentValues.put(DBConfig.SearchHistory.COLUMN_HOT_MARK, searchAnchorData.getHotMark());

        writeDB().insert(TableList.SEARCH_HISTORY.getTableName(), null, contentValues);
        closeDB();
    }

    public List<SearchAnchorData> querySearchHistory() {
        Cursor cursor = readDB().rawQuery("select * from " + TableList.SEARCH_HISTORY.getTableName() + " order by " + DBConfig.PRIMARY_ID + " desc", null);
        int numberIndex = cursor.getColumnIndex(DBConfig.SearchHistory.COLUMN_NUMBER);
        int accountIndex = cursor.getColumnIndex(DBConfig.SearchHistory.COLUMN_ACCOUNT);
        int nicknameIndex = cursor.getColumnIndex(DBConfig.SearchHistory.COLUMN_NICKNAME);
        int introductionIndex = cursor.getColumnIndex(DBConfig.SearchHistory.COLUMN_INTRODUCTION);
        int hotMarkIndex = cursor.getColumnIndex(DBConfig.SearchHistory.COLUMN_HOT_MARK);

        List<SearchAnchorData> searchHistoryList = new ArrayList<>();
        while (cursor.moveToNext()) {
            SearchAnchorData searchAnchorData = new SearchAnchorData(
                    cursor.getInt(numberIndex),
                    cursor.getString(accountIndex),
                    cursor.getString(nicknameIndex),
                    cursor.getString(introductionIndex),
                    cursor.getString(hotMarkIndex));

            searchHistoryList.add(searchAnchorData);
        }
        cursor.close();
        closeDB();
        return searchHistoryList;
    }

    public void dropTable(TableList tableList) {
        writeDB().execSQL("drop table if exists '" + tableList.getTableName() + "'");
        closeDB();
    }

    public void deleteDataFromTable(TableList tableList) {
        writeDB().execSQL("delete from " + tableList.getTableName());
    }

    private void deleteSearchHistory(int number) {
        readDB().execSQL("delete from " + TableList.SEARCH_HISTORY.getTableName() + " where " + DBConfig.SearchHistory.COLUMN_NUMBER + "=" + number);
        closeDB();
    }

    private boolean hasThisSearchHistory(int number) {
        boolean hasHistory = false;

        Cursor cursor = readDB().rawQuery("select * from " + TableList.SEARCH_HISTORY.getTableName() + " where " + DBConfig.SearchHistory.COLUMN_NUMBER + "=" + number, null);
        if (cursor.moveToNext()) {
            hasHistory = true;
        }
        cursor.close();
        return hasHistory;
    }

    private SQLiteDatabase readDB() {
        mSQLiteDatabase = DemoSqliteOpenHelper.getInstance(mContext).getWritableDatabase();
        return mSQLiteDatabase;
    }

    private SQLiteDatabase writeDB() {
        mSQLiteDatabase = DemoSqliteOpenHelper.getInstance(mContext).getReadableDatabase();
        return mSQLiteDatabase;
    }

    private void closeDB() {
        mSQLiteDatabase.close();
    }

    public enum TableList {
        SEARCH_HISTORY(DBConfig.SearchHistory.TABLE_NAME);

        private String mTableName;

        TableList(String tableName) {
            mTableName = tableName;
        }

        public String getTableName() {
            return mTableName;
        }
    }
}