package com.shian.shianlife.activity.sendorders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;

public class SendOrderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_order);

        String title = getIntent().getStringExtra("TitleName");
        setTitle(title);
    }
}
