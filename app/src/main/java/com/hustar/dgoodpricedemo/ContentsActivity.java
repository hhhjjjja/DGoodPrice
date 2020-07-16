package com.hustar.dgoodpricedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ContentsActivity extends AppCompatActivity {

    EditText contentsTitle;
    EditText contents;
    EditText contentsName;
    TextView contentsDate;

    Button deleteContentsBtn;
    Button editContentsBtn;

    String key;
    int flag = 0;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);

        contentsTitle = findViewById(R.id.contentsTitle);
        contents = findViewById(R.id.contents);
        contentsName = findViewById(R.id.contentsName);
        contentsDate = findViewById(R.id.contentsDate);

        contentsTitle.setEnabled(false);
        contents.setEnabled(false);
        contentsName.setEnabled(false);

        deleteContentsBtn = findViewById(R.id.deleteContentsBtn);
        editContentsBtn = findViewById(R.id.editContentsBtn);

        Intent intent = getIntent();

        contentsTitle.setText(intent.getStringExtra("title"));
        contents.setText(intent.getStringExtra("contents"));
        contentsName.setText(intent.getStringExtra("name"));
        contentsDate.setText(intent.getStringExtra("date"));

        key = intent.getStringExtra("key");

        contentsTitle.setTextColor(Color.BLACK);
        contents.setTextColor(Color.BLACK);
        contentsName.setTextColor(Color.BLACK);


        deleteContentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("Data").child(key).removeValue();
                Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        editContentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 0) {
                    contentsTitle.setEnabled(true);
                    contents.setEnabled(true);
                    contentsName.setEnabled(true);

                    flag = 1;

                } else if(flag == 1) {
                    contentsTitle.setEnabled(false);
                    contents.setEnabled(false);
                    contentsName.setEnabled(false);

                    String altTitle = contentsTitle.getText().toString();
                    String altContents = contents.getText().toString();
                    String altName = contentsName.getText().toString();

                    long now = System.currentTimeMillis();
                    Date date = new Date(now);

                    //날짜 포맷설정
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
                    String getTime = dateFormat.format(date);

                    Map<String, Object> altMap = new HashMap<>();
                    altMap.put("name", altName);
                    altMap.put("title", altTitle);
                    altMap.put("contents", altContents);
                    altMap.put("date", getTime);

                    databaseReference.child("Data").child(key).updateChildren(altMap);
                    Toast.makeText(getApplicationContext(), "수정되었습니다.", Toast.LENGTH_SHORT).show();

                    flag = 0;
                    finish();
                }
            }
        });
    }
}