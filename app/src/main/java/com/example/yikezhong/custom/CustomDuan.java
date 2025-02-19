package com.example.yikezhong.custom;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yikezhong.R;

/**
 * 段子页面 自定义加号view
 *
 * 点击加号按钮,加号隐藏,减号旋转显示,另外4张图片也旋转一定角度显示
 *
 * 点击减号按钮,减号隐藏,加号旋转显示,另外4张图片也旋转一定角度隐藏
 */
public class CustomDuan extends RelativeLayout {
    private ImageView image_show;
    private ImageView image_jian;
    private LinearLayout image_report;
    private LinearLayout image_copy;
    private LinearLayout image_pingbi;
    private TextView reportText;
    private TextView copyText;
    private TextView shiledText;

    public CustomDuan(Context context) {
        super(context);
    }

    public CustomDuan(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_duan, this, false);
        image_show = view.findViewById(R.id.image_show);
        image_jian = view.findViewById(R.id.image_jian);
        image_report = view.findViewById(R.id.image_report);
        image_copy = view.findViewById(R.id.image_copy);
        image_pingbi = view.findViewById(R.id.image_pingbi);
        shiledText = view.findViewById(R.id.shiledText);
        copyText = view.findViewById(R.id.copyText);
        reportText = view.findViewById(R.id.reportText);

        //加号按钮的点击事件,展示另外三张图片 , 展示 图片下面的文本
        image_show.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                image_jian.setVisibility(View.VISIBLE);
                image_show.setVisibility(View.GONE);
                shiledText.setVisibility(View.VISIBLE);
                copyText.setVisibility(View.VISIBLE);
                reportText.setVisibility(View.VISIBLE);
                showMenu();
            }
        });

        //减号的点击事件,隐藏另外三张图片 , 隐藏 图片下面的文本
        image_jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                image_jian.setVisibility(View.GONE);
                image_show.setVisibility(View.VISIBLE);
                shiledText.setVisibility(View.GONE);
                copyText.setVisibility(View.GONE);
                reportText.setVisibility(View.GONE);
                hideMenu();
            }
        });

        addView(view);
    }

    public CustomDuan(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    //属性动画,,展示出来
    public void showMenu(){
        //三个平移动画 平移出来
        ObjectAnimator firstAnimator = ObjectAnimator.ofFloat(image_pingbi
                ,"translationX",0,-65*3);
        ObjectAnimator secondAnimator = ObjectAnimator.ofFloat(image_copy
                ,"translationX",0,-65*2);
        ObjectAnimator thirdAnimator = ObjectAnimator.ofFloat(image_report
                ,"translationX",0,-65*1);

        //旋转动画
        ObjectAnimator rotation1 = ObjectAnimator.ofFloat(image_jian, "rotation", 0, 135, 0);
        ObjectAnimator rotation2 = ObjectAnimator.ofFloat(image_report, "rotation", 0, 180, 0);
        ObjectAnimator rotation3 = ObjectAnimator.ofFloat(image_copy, "rotation", 0, 180, 0);
        ObjectAnimator rotation4 = ObjectAnimator.ofFloat(image_pingbi, "rotation", 0, 180, 0);

        //组合动画
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(800);//动画时长
        animatorSet.setInterpolator(new OvershootInterpolator());
        //设置动画一起播放
        animatorSet.playTogether(rotation1,rotation2,rotation3,rotation4,firstAnimator,secondAnimator,thirdAnimator);

        animatorSet.start();

    }

    public void hideMenu(){
        //三个平移回去
        ObjectAnimator firstAnimator = ObjectAnimator.ofFloat(image_pingbi
                , "translationX", image_report.getTranslationX(), 0);
        ObjectAnimator secondAnimator = ObjectAnimator.ofFloat(image_copy
                , "translationX", image_copy.getTranslationX(), 0);
        ObjectAnimator thirdAnimator = ObjectAnimator.ofFloat(image_report
                , "translationX", image_pingbi.getTranslationX(), 0);
        ObjectAnimator rotation1 = ObjectAnimator.ofFloat(image_show, "rotation", 0, 135, 0);
        ObjectAnimator rotation2 = ObjectAnimator.ofFloat(image_copy, "rotation", 0, 180, 0);
        ObjectAnimator rotation3 = ObjectAnimator.ofFloat(image_pingbi, "rotation", 0, 180, 0);
        ObjectAnimator rotation4 = ObjectAnimator.ofFloat(image_report, "rotation", 0, 180, 0);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(800);
        animatorSet.setInterpolator(new OvershootInterpolator());
        animatorSet.playTogether(rotation1,rotation2,rotation3,rotation4,firstAnimator,secondAnimator,thirdAnimator);

        animatorSet.start();
    }

}