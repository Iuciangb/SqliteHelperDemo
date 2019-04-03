package com.iuciangb.sqlitehelperdemo;

/**
 * @author YY
 * @create 2019/4/2
 * @Describe
 **/
public class DBConfig {
    protected final static String PRIMARY_ID = "_id";

    protected static class SearchHistory {
        public final static String TABLE_NAME = "SearchHistory";
        public final static String COLUMN_NUMBER = "number";
        public final static String COLUMN_ACCOUNT = "account";
        public final static String COLUMN_NICKNAME = "nickname";
        public final static String COLUMN_INTRODUCTION = "introduction";
        public final static String COLUMN_HOT_MARK = "hot_mark";
    }
}
