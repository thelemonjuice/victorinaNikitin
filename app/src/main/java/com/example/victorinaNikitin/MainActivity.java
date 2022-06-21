package com.example.victorinaNikitin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Toast toast1;
    private Long backClickTime = Long.parseLong("0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //развернуть игру на весь экран и скрыть панель состояния
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW);
    }

    public void btnStartClick(View view){
        Intent levelMenu = new Intent(this, LevelMenuActivity.class );
        startActivity(levelMenu);
        this.finish();
    }


    //обработчик нажатия кнопки back
    @Override
    public void onBackPressed() {
        if(backClickTime+2000 > System.currentTimeMillis()){
            toast1.cancel();
            super.onBackPressed();
        }else{
            toast1 = Toast.makeText(this, getString(R.string.click_again_to_exit), Toast.LENGTH_SHORT);
            toast1.show();
            backClickTime = System.currentTimeMillis();
        }
    }


}