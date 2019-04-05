package com.example.javamail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private ArrayList<HashMap<String, String>> getDatalist;
    private RecyclerViewAdapter mAdapter;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_PHONE_STATE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        Constants.MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);

            }
        }

        random = new Random();

        getDatalist = new ArrayList<>();
        for (int aind = 0; aind < 20; aind++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("KEY_EMAIL", "android" + aind + "@gmail.com");
            map.put("KEY_PHONE", phoneNumberGenerating());
            getDatalist.add(map);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));

        mAdapter = new RecyclerViewAdapter(MainActivity.this, getDatalist, recyclerView);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(HashMap<String, String> item) {
                String mEmail = "";
                String mPhone = "";

                try {
                    mEmail = item.get("KEY_EMAIL");
                    mPhone = item.get("KEY_PHONE");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "Clicked row: \nEmail: " + mEmail + ", Phone: " + mPhone, Toast.LENGTH_LONG).show();
            }
        });

        mAdapter.setOnLoadMoreListener(new RecyclerViewAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (getDatalist.size() <= 40) {
                    getDatalist.add(null);
                    mAdapter.notifyItemInserted(getDatalist.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getDatalist.remove(getDatalist.size() - 1);
                            mAdapter.notifyItemRemoved(getDatalist.size());

                            int index = getDatalist.size();
                            int end = index + 20;
                            for (int i = index; i < end; i++) {
                                HashMap<String, String> map = new HashMap<>();
                                map.put("KEY_EMAIL", "android" + i + "@gmail.com");
                                map.put("KEY_PHONE", phoneNumberGenerating());
                                getDatalist.add(map);
                            }
                            mAdapter.notifyDataSetChanged();
                            mAdapter.setLoaded();
                        }
                    }, 5000);
                } else {
                    Toast.makeText(MainActivity.this, "Loading data completed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private String phoneNumberGenerating() {
        int low = 100000000;
        int high = 999999999;
        int randomNumber = random.nextInt(high - low) + low;

        return "0" + randomNumber;
    }
}

