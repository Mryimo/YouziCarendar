package com.example.youzicarendar.activitys;

import android.app.DownloadManager;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SimpleCursorAdapter;

import com.example.youzicarendar.R;
import com.example.youzicarendar.bean.DateBean;
import com.example.youzicarendar.db.DBProblem;
import com.example.youzicarendar.listener.OnSingleChooseListener;
import com.example.youzicarendar.listener.OnPagerChangeListener;
import com.example.youzicarendar.utils.CalendarUtil;
import com.example.youzicarendar.views.CalendarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class MainActivity extends BaseActivity {

    private CalendarView calendarView;
    private TextView chooseDate;
    private ListView listView;
    private Cursor c;

    private int[] cDate = CalendarUtil.getCurrentDate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView title = (TextView) findViewById(R.id.title);
        //当前选中的日期
        chooseDate = findViewById(R.id.choose_date);

        calendarView = (CalendarView) findViewById(R.id.calendar);
        HashMap<String, String> map = new HashMap<>();
        map.put("2017.10.30", "qaz");
        map.put("2017.10.1", "wsx");
        map.put("2017.11.12", "yhn");
        map.put("2017.9.15", "edc");
        map.put("2017.11.6", "rfv");
        map.put("2017.11.11", "tgb");
        calendarView
//                .setSpecifyMap(map)
                .setStartEndDate("2016.1", "2028.12")
                .setDisableStartEndDate("2016.10.10", "2028.10.10")
                .setInitDate(cDate[0] + "." + cDate[1])
                .setSingleDate(cDate[0] + "." + cDate[1] + "." + cDate[2])
                .init();

//       .setOnCalendarViewAdapter(R.layout.item_layout, new CalendarViewAdapter() {
//            @Override
//            public TextView[] convertView(View view, DateBean date) {
//                TextView solarDay = (TextView) view.findViewById(R.id.solar_day);
//                TextView lunarDay = (TextView) view.findViewById(R.id.lunar_day);
//                return new TextView[]{solarDay, lunarDay};
//            }
//        }).init();

        title.setText(cDate[0] + "年" + cDate[1] + "月");
        chooseDate.setText(cDate[0] + "年" + cDate[1] + "月" + cDate[2] + "日");

        calendarView.setOnPagerChangeListener(new OnPagerChangeListener() {
            @Override
            public void onPagerChanged(int[] date) {
                title.setText(date[0] + "年" + date[1] + "月");
            }
        });

        calendarView.setOnSingleChooseListener(new OnSingleChooseListener() {
            @Override
            public void onSingleChoose(View view, DateBean date) {
                title.setText(date.getSolar()[0] + "年" + date.getSolar()[1] + "月");
                if (date.getType() == 1) {
                    chooseDate.setText(date.getSolar()[0] + "年" + date.getSolar()[1] + "月" + date.getSolar()[2] + "日");
                }
            }
        });
        ArrayList list = new ArrayList<>();
        final DBProblem problem = new DBProblem(this);
        problem.open();
        c = problem.query();
        String[] from ={"name","content"};
        int[] to ={R.id.pro_name,R.id.pro_content};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.activity_main,c,from,to,0);
        listView = (ListView) findViewById(R.id.list_problem);
        listView.setAdapter(adapter);
    }

    public void today(View view) {
        calendarView.today();
        chooseDate.setText(cDate[0] + "年" + cDate[1] + "月" + cDate[2] + "日");
    }

    public void someday(View v) {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.input_layout, null);
        final EditText year = (EditText) view.findViewById(R.id.year);
        final EditText month = (EditText) view.findViewById(R.id.month);
        final EditText day = (EditText) view.findViewById(R.id.day);

        new AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (TextUtils.isEmpty(year.getText())
                                || TextUtils.isEmpty(month.getText())
                                || TextUtils.isEmpty(day.getText())) {
                            Toast.makeText(MainActivity.this, "请完善日期！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        boolean result = calendarView.toSpecifyDate(Integer.valueOf(year.getText().toString()),
                                Integer.valueOf(month.getText().toString()),
                                Integer.valueOf(day.getText().toString()));
                        if (!result) {
                            Toast.makeText(MainActivity.this, "日期越界！", Toast.LENGTH_SHORT).show();
                        } else {
                            chooseDate.setText(year.getText() + "年" + month.getText() + "月" + day.getText() + "日");
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", null).show();
    }

    public void lastMonth(View view) {
        calendarView.lastMonth();
    }

    public void nextMonth(View view) {
        calendarView.nextMonth();
    }

    public void start(View view) {
        calendarView.toStart();
    }

    public void end(View view) {
        calendarView.toEnd();
    }


    public void lastYear(View view) {
        calendarView.lastYear();
    }

    public void nextYear(View view) {
        calendarView.nextYear();
    }

    public void registProbelm(View view) {
        Intent intent = new Intent(this,RegitsProblemActivity.class);
        startActivity(intent);
    }



    @Override
    protected void onStop(){
        super.onStop();
    }

}
