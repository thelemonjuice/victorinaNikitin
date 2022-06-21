package com.example.victorinaNikitin;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;


public class Level1 extends AppCompatActivity {

    int selectedLevel = 0;
    Dialog dialog;
    Dialog dialogEnd;
    int numLeft;
    int numRight;
    ArrayLevels arrayLevels; //подключение своего класса ArrayLevels
    int[] levelImages;
    int[] levelTexts;
    Random random = new Random(); //генератор случайных чисел
    int count = 0; //счётчик правильных ответов
    ImageView imgLeft;
    ImageView imgRight;
    TextView textLeft;
    TextView textRight;
    CardView cardLeft;
    CardView cardRight;
    ProgressBar levelProgressBar;
    Animation animation;
    long startTime;
    long lastRecordTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal_levels);
        //развернуть игру на весь экран и скрыть панель состояния
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW);
        cardLeft = findViewById(R.id.card_view_left);
        cardRight = findViewById(R.id.card_view_right);
        imgLeft = findViewById(R.id.img_left);
        imgRight = findViewById(R.id.img_right);
        textLeft = findViewById(R.id.text_left);
        textRight = findViewById(R.id.text_right);
        levelProgressBar = findViewById(R.id.levelCompleteProgressBar);
        animation = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);

        //получение id уровня переданного в предыдущем activity
        String s1 = getIntent().getExtras().get("levelId").toString();
        selectedLevel = Integer.parseInt(s1);
        //устанавливаем текст с номером уровня
        TextView currentLevel = findViewById(R.id.currentLevelNameTextView);
        currentLevel.setText(getString(R.string.level_name)+" "+selectedLevel);

        arrayLevels = new ArrayLevels(selectedLevel);

        SharedPreferences savetime = getSharedPreferences("Save", MODE_PRIVATE);
        try {
            lastRecordTime = savetime.getLong("time" + selectedLevel, 0);
        }catch (Exception ex){
            lastRecordTime = 0;
        }

        //создание начального диалогового окна
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрывает стандартный заголовок
        dialog.setContentView(R.layout.preview_dialog); //выбираем свой макет диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //делаем прозрачный фон
        dialog.setCancelable(false); //отменяет закрытие окна на нажатие кнопки back
        TextView startDialogText = dialog.findViewById(R.id.textdescription); //достаёт textview стартовоого диалогового окна
        ImageView startDialogImg = dialog.findViewById(R.id.preview_img); //достает imageview стартовоого диалогового окна
        startDialogImg.setImageResource(arrayLevels.startDialogImg[selectedLevel-1]); //устанавливает картинку в стартовом диалоговом окне
        startDialogText.setText(arrayLevels.startDialogText[selectedLevel-1]); //устанавливает текст в стартовом диалоговом окне

        //кнопка закрытия стартового диалогового окна
        TextView btnClose = dialog.findViewById(R.id.btn_close_dialog);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //обработчик нажатия на кнопку закртия диалогового окна
                try {
                    Intent intent1 = new Intent(Level1.this, LevelMenuActivity.class);
                    startActivity(intent1);
                    finish();
                }catch (Exception ex){
                    Toast.makeText(Level1.this, "Ошибка: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss(); //закрытие диалогового окна
            }
        });
        //кнопка "продолжить" в стартовом диалоговом окне
        Button btnContinue = dialog.findViewById(R.id.btn_continue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //обработчик нажатия на кнопку "продолжить" в диалоговом окне
                startTime = System.currentTimeMillis();
                dialog.dismiss(); //закрытие диалогового окна
            }
        });
        dialog.show(); //показать начальное диалоговое окно

        //создание конечного диалогового окна
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрывает стандартный заголовок
        dialogEnd.setContentView(R.layout.dialog_end); //выбираем свой макет диалогового окна
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //делаем прозрачный фон
        dialogEnd.setCancelable(false); //отменяет закрытие окна на нажатие кнопки back

        //кнопка закрытия конечного диалогового окна
        TextView btnClose2 = dialogEnd.findViewById(R.id.btn_close_dialog);
        btnClose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //обработчик нажатия на кнопку закртия диалогового окна
                try {
                    Intent intent1 = new Intent(Level1.this, LevelMenuActivity.class);
                    startActivity(intent1);
                    finish();
                }catch (Exception ex){
                    Toast.makeText(Level1.this, "Ошибка: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                dialogEnd.dismiss(); //закрытие диалогового окна
            }
        });

        //кнопка "продолжить" в конечном диалоговом окне
        Button btnContinue2 = dialogEnd.findViewById(R.id.btn_continue);
        btnContinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //обработчик нажатия на кнопку "продолжить" в диалоговом окне
                try{
                    Intent intent;
                    if(selectedLevel<5) {
                        intent = new Intent(Level1.this, Level1.class);
                        intent.putExtra("levelId", selectedLevel+1);
                    }else{
                        intent = new Intent(Level1.this, LevelMenuActivity.class);
                    }
                    startActivity(intent);
                    finish();
                }catch (Exception ex){
                    Toast.makeText(Level1.this, "Error: "+ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                dialogEnd.dismiss(); //закрытие конечного диалогового окна
            }
        });

        startLevel();

        //обработчик нажатия на левую картинку
        imgLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    //когда нажали на картинку
                    imgRight.setEnabled(false); //блокирует правую картинку
                    if(numLeft > numRight){
                        //когда левая картинка больше правой устанавливается картинка правильного ответа
                        imgLeft.setImageResource(R.drawable.img_true);
                        //показ правильных ответов
                        if(selectedLevel==2) {
                            textLeft.setText(arrayLevels.texts2desc[numLeft]);
                            textRight.setText(arrayLevels.texts2desc[numRight]);
                        }
                        if(selectedLevel==5) {
                            textLeft.setText(arrayLevels.texts5desc[numLeft]);
                            textRight.setText(arrayLevels.texts5desc[numRight]);
                        }

                    }else{
                        //когда левая картинка меньше правой устанавливается картинка неправильного ответа
                        imgLeft.setImageResource(R.drawable.img_false);
                        //показ правильных ответов
                        if(selectedLevel==2) {
                            textLeft.setText(arrayLevels.texts2desc[numLeft]);
                            textRight.setText(arrayLevels.texts2desc[numRight]);
                        }
                        if(selectedLevel==5) {
                            textLeft.setText(arrayLevels.texts5desc[numLeft]);
                            textRight.setText(arrayLevels.texts5desc[numRight]);
                        }
                    }
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    //когда убрали палец с картинки
                    if(numLeft>numRight){
                        //если правая картинка больше  левой
                        if(count<20){
                            count++;
                        }
                        levelProgressBar.setProgress(count);
                    }else{
                        //если правая картинка меньше левой
                        if(count>0){
                            if(count==1){
                                count=0;
                            }else{
                                count = count - 2;
                            }
                        }
                        levelProgressBar.setProgress(count);
                    }

                    //выход из уровня
                    if(count==20){
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int savelevel = save.getInt("Level", 1);
                        if(selectedLevel<savelevel){

                        }else {
                            SharedPreferences.Editor editor = save.edit();
                            int y = selectedLevel + 1;
                            editor.putInt("Level", y);
                            editor.commit();
                        }

                        long currentTimeComplete = System.currentTimeMillis() - startTime;
                        TextView endTime = dialogEnd.findViewById(R.id.endTimeText);
                        if (lastRecordTime==0){
                            lastRecordTime = System.currentTimeMillis()*2;
                        }
                        if(currentTimeComplete<lastRecordTime){
                            SharedPreferences.Editor editor = save.edit();
                            editor.putLong("time"+selectedLevel, currentTimeComplete);
                            editor.commit();
                            endTime.setText(getString(R.string.transit_time_record)+"\n" + currentTimeComplete/1000 + " сек.");
                        }else{
                            endTime.setText(getString(R.string.transit_time)+"\n" + currentTimeComplete/1000 + " сек.");
                        }
                        dialogEnd.show(); //показать конечное диалоговое окно
                    }else{
                        startLevel();
                        imgRight.setEnabled(true); //включает левую картинку
                    }

                }

                return true;
            }
        });
        //конец обработчика нажатия на левую картинку

        //обработчик нажатия на правую картинку
        imgRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    //когда нажали на картинку
                    imgLeft.setEnabled(false); //блокирует левую картинку
                    if(numLeft < numRight){
                        //когда левая картинка больше правой устанавливается картинка правильного ответа
                        imgRight.setImageResource(R.drawable.img_true);
                        //показ правильных ответов
                        if(selectedLevel==2) {
                            textLeft.setText(arrayLevels.texts2desc[numLeft]);
                            textRight.setText(arrayLevels.texts2desc[numRight]);
                        }
                        if(selectedLevel==5) {
                            textLeft.setText(arrayLevels.texts5desc[numLeft]);
                            textRight.setText(arrayLevels.texts5desc[numRight]);
                        }

                    }else{
                        //когда левая картинка меньше правой устанавливается картинка неправильного ответа
                        imgRight.setImageResource(R.drawable.img_false);
                        //показ правильных ответов
                        if(selectedLevel==2) {
                            textLeft.setText(arrayLevels.texts2desc[numLeft]);
                            textRight.setText(arrayLevels.texts2desc[numRight]);
                        }
                        if(selectedLevel==5) {
                            textLeft.setText(arrayLevels.texts5desc[numLeft]);
                            textRight.setText(arrayLevels.texts5desc[numRight]);
                        }
                    }
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    //когда убрали палец с картинки
                    if(numLeft<numRight){
                        //если правая картинка больше  левой
                        if(count<20){
                            count++;
                        }
                        levelProgressBar.setProgress(count);
                    }else{
                        //если правая картинка меньше левой
                        if(count>0){
                            if(count==1){
                                count=0;
                            }else{
                                count = count - 2;
                            }
                        }
                        levelProgressBar.setProgress(count);
                    }

                    //выход из уровня
                    if(count==20){
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int savelevel = save.getInt("Level", 1);
                        if(selectedLevel<savelevel){

                        }else {
                            SharedPreferences.Editor editor = save.edit();
                            int y = selectedLevel + 1;
                            editor.putInt("Level", y);
                            editor.commit();
                        }

                        long currentTimeComplete = System.currentTimeMillis() - startTime;
                        TextView endTime = dialogEnd.findViewById(R.id.endTimeText);
                        if (lastRecordTime==0){
                            lastRecordTime = System.currentTimeMillis()*2;
                        }
                        if(currentTimeComplete<lastRecordTime){
                            SharedPreferences.Editor editor = save.edit();
                            editor.putLong("time"+selectedLevel, currentTimeComplete);
                            editor.commit();
                            endTime.setText(getString(R.string.transit_time_record)+"\n" + currentTimeComplete/1000 + "\nсек.");
                        }else{
                            endTime.setText(getString(R.string.transit_time)+"\n" + currentTimeComplete/1000 + "\nсек.");
                        }
                        dialogEnd.show(); //показать конечное диалоговое окно
                    }else{
                        startLevel();
                        imgLeft.setEnabled(true); //включает левую картинку
                    }
                }

                return true;
            }
        });
        //конец обработчика нажатия на правую картинку

    }

    //функция запуска уровня
    private void startLevel(){
        try {
            numLeft = random.nextInt(arrayLevels.img.size()); //генерация случайного числа
            imgLeft.setImageResource(arrayLevels.img.get(numLeft)); //устанавливает левую картинку
            imgLeft.startAnimation(animation);
            textLeft.setText(arrayLevels.text.get(numLeft)); //устанавливает левую надпись
            //генерация нового правого случайного числа, если левое число совпало с правым
            do {
                numRight = random.nextInt(arrayLevels.img.size());
            } while (numLeft == numRight);
            imgRight.setImageResource(arrayLevels.img.get(numRight)); //устанавливает правую картинку
            imgRight.startAnimation(animation);
            textRight.setText(arrayLevels.text.get(numRight)); //устанавливает правую надпись
        }catch (Exception ex){
            Toast.makeText(this, "Error: "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //обработчик нажатия системной кнопки back
    @Override
    public void onBackPressed() {
        backButtonClick(getCurrentFocus());
        super.onBackPressed();
    }

    public void backButtonClick(View view){
        Intent levelMenu = new Intent(this, LevelMenuActivity.class );
        startActivity(levelMenu);
        this.finish();
    }

}