package com.example.zjy.eye;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.zjy.fragment.feed.FeedFragment;
import com.example.zjy.niklauslibrary.base.BaseActivity;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.radio_group)
    RadioGroup radioGroup;
    @Bind(R.id.rb_profile)
    RadioButton rb_profile;
    @Override
    public int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_feed:
                        showFragment(R.id.fl_placeholder,new FeedFragment());
                        break;
                    case R.id.rb_category:
                        break;
                    case R.id.rb_pgc:
                        break;
                    case R.id.rb_profile:
                        break;
                }
            }
        });
        radioGroup.getChildAt(0).performClick();
    }
}
