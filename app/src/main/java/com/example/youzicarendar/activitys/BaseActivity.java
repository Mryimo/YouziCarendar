package com.example.youzicarendar.activitys;

import android.app.Activity;
import android.app.ListActivity;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youzicarendar.R;

public class BaseActivity extends Activity {

    private ImageView mIvBack, mIvMe;
    private TextView mTvTitle;

    protected <T extends View> T fd (@IdRes int id) {
        return findViewById(id);
    }

    protected void initNavBar(boolean isShowBack, String Title, boolean isShowMe) {
        mIvBack = fd(R.id.iv_back);
        mTvTitle = fd(R.id.tv_title);
        mIvMe = fd(R.id.iv_me);

        mIvBack.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
        mIvMe.setVisibility(isShowMe ? View.VISIBLE : View.GONE);
        mTvTitle.setText(Title);

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
