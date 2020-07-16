package com.hustar.dgoodpricedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class HomeActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView1, textView2, textView3;
    LinearLayout layout_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        layout_click = findViewById(R.id.layout_click);
        imageView = findViewById(R.id.imageView);

        textView1.setText(Html.fromHtml("<b>D-GoodPrice</b>" + "는 대구시 공공데이터포탈"));
        textView2.setText(Html.fromHtml("D-데이터허브 OpenAPI를 기반으로 하여 제작한 앱입니다"));
        textView3.setText(Html.fromHtml("대구시에서 선정한 " + "<b>착한가격업소</b>" + "의 정보를 제공합니다"));

        ///Animation////
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_anim);
        imageView.startAnimation(anim);

        Animation fadAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_anim);
        Animation fadAnim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_anim);
        Animation fadAnim3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_anim);
        fadAnim.setStartOffset(500);
        fadAnim2.setStartOffset(1000);
        fadAnim3.setStartOffset(1500);
        textView1.startAnimation(fadAnim);
        textView2.startAnimation(fadAnim2);
        textView3.startAnimation(fadAnim3);

        layout_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}