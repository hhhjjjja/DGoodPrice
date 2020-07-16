package com.hustar.dgoodpricedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class BoardActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    TextView titleTextview;
    TextView nameTextview;
    TextView dateTextview;
    TextView listTextView;
    ListView contentsList;

    Button sidebarBtn;
    Button writeBtn;

    BoardListAdapter bAdapter;

    ArrayList<Data> dataArrayList;
    ArrayList<String> keyArray;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("Data");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        titleTextview = findViewById(R.id.titleTextview);
        nameTextview = findViewById(R.id.nameTextview);
        dateTextview = findViewById(R.id.dateTextview);
        listTextView = findViewById(R.id.listTextView);

        contentsList = findViewById(R.id.contentsList);
        sidebarBtn = findViewById(R.id.sidebarBtn);

        writeBtn = findViewById(R.id.writeBtn);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();

                int id = item.getItemId();

                if(id == R.id.home) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else if(id == R.id.search) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else if(id == R.id.board) {
                    Intent intent = new Intent(getApplicationContext(), BoardActivity.class);
                    intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                return true;
            }
        });

        sidebarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
            }
        });

        dataArrayList = new ArrayList<>();
        keyArray = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.getChildrenCount() == 0) {

                    listTextView.setVisibility(View.VISIBLE);
                    listTextView.setText("작성된 글이 없어요 :(");

                } else {
                    dataArrayList.clear();
                    keyArray.clear();

                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                        listTextView.setVisibility(View.GONE);
                        Data inputData = new Data();

                        String key = postSnapshot.getKey();
                        keyArray.add(key);
                        Log.d("key", key);

                        String title = postSnapshot.child("title").getValue().toString();
                        String name = postSnapshot.child("name").getValue().toString();
                        String date = postSnapshot.child("date").getValue().toString();
                        String contents = postSnapshot.child("contents").getValue().toString();

                        inputData.setTitle(title);
                        inputData.setName(name);
                        inputData.setDate(date);
                        inputData.setContents(contents);

                        dataArrayList.add(inputData);
                    }
                    bAdapter = new BoardListAdapter(dataArrayList);
                    contentsList.setAdapter(bAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        contentsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ContentsActivity.class);
                intent.putExtra("name", dataArrayList.get(i).getName());
                intent.putExtra("title", dataArrayList.get(i).getTitle());
                intent.putExtra("date", dataArrayList.get(i).getDate());
                intent.putExtra("contents", dataArrayList.get(i).getContents());
                intent.putExtra("key", keyArray.get(i));

                startActivity(intent);
            }
        });
    }
}