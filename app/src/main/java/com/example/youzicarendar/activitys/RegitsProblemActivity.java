package com.example.youzicarendar.activitys;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.youzicarendar.R;
import com.example.youzicarendar.db.DBProblem;

public class RegitsProblemActivity extends BaseActivity {

    private EditText et1,et2,et3;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regits_problem);
        initView();
        et1 = (EditText) findViewById(R.id.prblemNmae);
        et2 = (EditText) findViewById(R.id.problemContent);
        et3 = (EditText) findViewById(R.id.day);
        button = (Button) findViewById(R.id.btn_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=et1.getText().toString();
                String content =et2.getText().toString();

                ContentValues values = new ContentValues();

                values.put("name",name);
                values.put("content",content);

                DBProblem problem = new DBProblem(getApplicationContext());

                problem.open();

                problem.insert(values);

                Intent intent =new Intent(RegitsProblemActivity.this,MainActivity.class);
                startActivity(intent);
                problem.close();
            }
        });
    }

    private void initView() {
        initNavBar(true,"锻炼计划",false);
    }
}
