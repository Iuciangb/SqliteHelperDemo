package com.iuciangb.sqlitehelperdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SearchAnchorData searchAnchorData = new SearchAnchorData(100, "abcabc", "HelloWorld", "This is sqlite helper Demo", "");
//        DBManager.getInstance(this).addSearchHistory(searchAnchorData);
//        List<SearchAnchorData> searchAnchorDataList = DBManager.getInstance(this).querySearchHistory();

    }
}
