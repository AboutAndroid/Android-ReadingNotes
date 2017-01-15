package com.ssyijiu.mvpdemo2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ssyijiu.mvpdemo2.ui.UserInfoActivity;

public class MainActivity extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        findViewById(R.id.button).setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
            intent.putExtra("name","ssyijiu");
            startActivity(intent);
        });
    }
}
