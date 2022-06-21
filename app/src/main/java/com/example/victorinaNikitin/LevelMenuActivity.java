package com.example.victorinaNikitin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


public class LevelMenuActivity extends AppCompatActivity {

    int savelevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_menu);

        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        savelevel = save.getInt("Level", 1);

        if(savelevel == 6){
            savelevel = 5;
        }

        int[] btnLevels = {
                R.id.level1,
                R.id.level2,
                R.id.level3,
                R.id.level4,
                R.id.level5,
        };
        for(int i=0; i<savelevel; i++){
            TextView tv = findViewById(btnLevels[i]);
            int x = i+1;
            tv.setText(""+x);
        }

        //развернуть игру на весь экран и скрыть панель состояния
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW);

    }

    public void btnBackClick(View view){
        Intent mainActiv = new Intent(this, MainActivity.class );
        startActivity(mainActiv);
        this.finish();
    }

    //обработчик нажатия кнопки back
    @Override
    public void onBackPressed() {
        btnBackClick(getCurrentFocus());
        super.onBackPressed();
    }

    //обработчик нажатия кнопок выбора уровня
    public void startLevelClick(View view){
        Intent activityLevelStarted = new Intent(this, Level1.class);
        int levelId = 1;
        TextView lvlClick = (TextView) view;
        String x1 = lvlClick.getText().toString();
        if(x1.equals("Х")) {
            Toast.makeText(this, getString(R.string.complete_previous_level), Toast.LENGTH_SHORT).show();
        }else{
            levelId = Integer.parseInt(x1);
            if(levelId<=5) {
                activityLevelStarted.putExtra("levelId", levelId);
                startActivity(activityLevelStarted);
                this.finish();
            }
        }
    }

}