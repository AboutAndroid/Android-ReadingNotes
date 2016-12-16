package com.ssyijiu.realmdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert();

        User user = getRealm().where(User.class)
                .equalTo("id", "03")
                .findFirst();
        System.out.println(user);

    }

    public Realm getRealm() {
        return Realm.getDefaultInstance();
    }

    public void insert() {

        getRealm().beginTransaction();
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.id = "0" + i;
            user.age = i;
            user.name = "lxm" + i;
            getRealm().copyToRealmOrUpdate(user);
        }
        getRealm().commitTransaction();
    }
}
