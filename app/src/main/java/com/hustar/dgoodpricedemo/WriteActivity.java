package com.hustar.dgoodpricedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteActivity extends AppCompatActivity {

    EditText editName, editTitle, editContents;
    Button button;
    Data data;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        editName = findViewById(R.id.editText);
        editTitle = findViewById(R.id.editText2);
        editContents = findViewById(R.id.editText3);
        button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //현재 날짜
                long now = System.currentTimeMillis();
                Date date = new Date(now);

                //날짜 포맷설정
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
                String getTime = dateFormat.format(date);

                String name = editName.getText().toString();
                String title = editTitle.getText().toString();
                String contents = editContents.getText().toString();

                data = new Data(name, title, contents, getTime);
                databaseReference.child("Data").push().setValue(data);

                Toast.makeText(getApplicationContext(), "Save", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return false;
    }
}