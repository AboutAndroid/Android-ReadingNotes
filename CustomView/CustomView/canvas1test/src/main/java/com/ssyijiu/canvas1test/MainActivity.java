package com.ssyijiu.canvas1test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PieView pieView = (PieView) findViewById(R.id.pieView);
        pieView.setStartAngle(-90);

        List<PieData> pieDatas = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            pieDatas.add(new PieData(i * 10));
        }

        pieView.setData(pieDatas);
    }
}
