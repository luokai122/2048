package com.example.lenovo.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/7/17.
 */

public class GameView extends GridLayout {
    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initGameView();
    }
    public GameView(Context context, AttributeSet attrs) {
       super(context, attrs);

        initGameView();
    }
    public GameView(Context context) {
        super(context);

        initGameView();
    }

    private void initGameView(){
        setColumnCount(4);

        setBackgroundColor(0xffbbada0);

        setOnTouchListener(new OnTouchListener() {
            private float startX ,startY,offsetX,offsetY;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN :
                            startX = event.getX();
                            startY = event.getY();
                            break;
                        case MotionEvent.ACTION_UP :
                            offsetX = event.getX()-startX;
                            offsetY = event.getY()-startY;

                            if(Math.abs(offsetX)>Math.abs(offsetY)){
                                if(offsetX<-5){
                                    swipeLeft();
                                    System.out.println("left");
                                }else if(offsetX>5){
                                    swipeRight();
                                    System.out.println("right");
                                }
                            }else {
                                if (offsetY<-5){
                                    swipeUp();
                                    System.out.println("up");
                                }else if(offsetY>5){
                                    swipeDown();
                                    System.out.println("down");
                                }
                            }
                            break;
                    }

                return true;
            }

        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int cardWidth  = (Math.min(w,h)-10)/4;

        addCard(cardWidth,cardWidth);

        startGame();
    }
    private void addCard(int cardWidth,int cardHeight){
        Card c ;
        for (int y = 0;y<4;y++){
          for(int x = 0;x<4;x++){
              c = new Card(getContext());
              c.setNum(0);
              addView(c,cardWidth,cardHeight);

              cardsMap[x][y] = c;
          }
        }
    }

    private  void startGame(){
        for (int y =0;y<4;y++){
            for (int x =0;x<4;x++){
                cardsMap[x][y].setNum(0);
            }
        }

        addRandomNum();
        addRandomNum();


    }


    private void addRandomNum(){

        emptyPoint.clear();

        for (int y =0;y<4;y++){
            for (int x =0;x<4;x++){
                if(cardsMap[x][y].getNum()<=0){
                    emptyPoint.add(new Point(x,y));
                }
            }
        }

        Point p = emptyPoint.remove((int)(Math.random()*emptyPoint.size()));
        cardsMap[p.x][p.y].setNum(Math.random()>0.1?2:4);
    }


    private void swipeLeft(){

        boolean marge = false;
        for (int y=0;y<4;y++){
            for (int x =0;x<4;x++){
                for(int x1 =x+1;x1<4;x1++){
                    if(cardsMap[x1][y].getNum()>0){
                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);
                            x--;
                            marge = true;
                        }else if(cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);
                            marge = true;
                        }
                        break;
                    }
                }
            }
        }
       if(marge){
           addRandomNum();
           checkComplete();
       }
    }
    private void swipeRight(){
        boolean marge = false;
        for (int y=0;y<4;y++){
            for (int x =3;x>=0;x--){
                for(int x1 =x-1;x1>=0;x1--){
                    if(cardsMap[x1][y].getNum()>0){
                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);

                            x++;
                            marge = true;
                        }else if(cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);
                            marge = true;
                        }
                        break;
                    }
                }
            }
        }
        if(marge){
            addRandomNum();
            checkComplete();
        }
    }
    private void swipeUp(){
        boolean marge = false;
        for (int x=0;x<4;x++){
            for (int y =0;y<4;y++){
                for(int y1 =y+1;y1<4;y1++){
                    if(cardsMap[x][y1].getNum()>0){
                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);
                            y--;
                            marge = true;
                        }else if(cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x][y1].setNum(0);
                            marge = true;
                        }
                        break;
                    }
                }
            }
        }
        if(marge){
            addRandomNum();
            checkComplete();
        }
    }
    private void swipeDown(){
        boolean marge = false;
        for (int x=0;x<4;x++){
            for (int y =3;y>=0;y--){
                for(int y1 =y-1;y1>=0;y1--){
                    if(cardsMap[x][y1].getNum()>0){
                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);

                            y++;
                            marge = true;
                        }else if(cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x][y1].setNum(0);
                            marge = true;
                        }
                        break;
                    }
                }
            }
        }
        if(marge){
            addRandomNum();
            checkComplete();
        }
    }

    private void checkComplete(){
        boolean complete = true;
        ALL:
        for (int y=0;y<4;y++){
            for (int x=0;x<4;x++){
                if(cardsMap[x][y].getNum()==0||
                        (x>0&&cardsMap[x][y].equals(cardsMap[x-1][y]))||
                        (x<3&&cardsMap[x][y].equals(cardsMap[x+1][y]))||
                        (y>0&&cardsMap[x][y].equals(cardsMap[x][y-1]))||
                        (y<3&&cardsMap[x][y].equals(cardsMap[x][y+1]))){
                        complete = false;
                        break ALL;
                }
            }

            }
        if(complete){
            new AlertDialog.Builder(getContext()).setTitle("您好").setMessage("游戏结束").setPositiveButton("重来",new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startGame();
                }
            }).show();
        }


    }

    private  Card[][] cardsMap = new Card[4][4];
    private List<Point> emptyPoint = new ArrayList();
}
