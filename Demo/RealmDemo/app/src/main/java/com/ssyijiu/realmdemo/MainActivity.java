package com.ssyijiu.realmdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert();

        User user = getRealm().where(User.class)
                .equalTo("id", "2017")
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
            user.id = "2017";
            user.age = i;
            user.name = "lxm" + i;
            getRealm().copyToRealmOrUpdate(user);
        }
        getRealm().commitTransaction();
    }
}
