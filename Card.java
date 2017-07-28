package com.example.lenovo.myapplication;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by lenovo on 2017/7/19.
 */

public class Card extends FrameLayout {
    public Card(Context context) {
        super(context);
        label = new TextView(getContext());
        label.setTextSize(32);
        label.setGravity(Gravity.CENTER);
        label.setBackgroundColor(0x33ffffff);

        LayoutParams lp = new LayoutParams(-1,-1);
        lp.setMargins(10,10,0,0);
        addView(label,lp);

        setNum(0);
    }
    private  int num = 0;
    public  int getNum(){
        return  num;
    }

    public void setNum(int num) {
        this.num = num;
        if(num<=0){
            label.setText("");
        }else{
            label.setText(num+"");
        }
        switch (num){
            case 0:
                label.setBackgroundColor(0x33FFFFFF);
                break;
            case 2:
                label.setTextColor(getResources().getColor(R.color.text2));
                label.setBackgroundColor(getResources().getColor(R.color.bg2));
                break;
            case 4:
                label.setTextColor(getResources().getColor(R.color.text4));
                label.setBackgroundColor(getResources().getColor(R.color.bg4));
                break;
            case 8:
                label.setTextColor(getResources().getColor(R.color.text8));
                label.setBackgroundColor(getResources().getColor(R.color.bg8));
                break;
            case 16:
                label.setTextColor(getResources().getColor(R.color.text16));
                label.setBackgroundColor(getResources().getColor(R.color.bg16));
                break;
            case 32:
                label.setTextColor(getResources().getColor(R.color.text32));
                label.setBackgroundColor(getResources().getColor(R.color.bg32));
                break;
            case 64:
                label.setTextColor(getResources().getColor(R.color.text64));
                label.setBackgroundColor(getResources().getColor(R.color.bg64));
                break;
            case 128:
                label.setTextColor(getResources().getColor(R.color.text128));
                label.setBackgroundColor(getResources().getColor(R.color.bg128));
                break;
            case 256:
                label.setTextColor(getResources().getColor(R.color.text256));
                label.setBackgroundColor(getResources().getColor(R.color.bg256));
                break;
            case 512:
                label.setTextColor(getResources().getColor(R.color.text512));
                label.setBackgroundColor(getResources().getColor(R.color.bg512));
                break;
            case 1024:
                label.setTextColor(getResources().getColor(R.color.text1024));
                label.setBackgroundColor(getResources().getColor(R.color.bg1024));
                break;
            case 2048:
                label.setTextColor(getResources().getColor(R.color.text2048));
                label.setBackgroundColor(getResources().getColor(R.color.bg2048));
                break;
            default:
                label.setTextColor(getResources().getColor(R.color.textsuper));
                label.setBackgroundColor(getResources().getColor(R.color.bgsuper));
                break;

        }

    }

    public boolean equals(Card card) {
        return getNum()==card.getNum();
    }

    private TextView label;
}
