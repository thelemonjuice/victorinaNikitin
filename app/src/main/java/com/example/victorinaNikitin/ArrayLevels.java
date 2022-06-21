package com.example.victorinaNikitin;


import java.util.ArrayList;

public class ArrayLevels {
    public ArrayList<Integer> img =new ArrayList<>();
    public ArrayList<Integer> text =new ArrayList<>();

    public ArrayLevels(int selectedLevel)
    {
        switch(selectedLevel){
            case 1:
                img.add(R.drawable.level1_bit);
                img.add(R.drawable.level1_byte);
                img.add(R.drawable.level1_kb);
                img.add(R.drawable.level1_mb);
                img.add(R.drawable.level1_gb);
                img.add(R.drawable.level1_tb);
                img.add(R.drawable.level1_eb);
                img.add(R.drawable.level1_zb);
                img.add(R.drawable.level1_yb);
                text.add(R.string.level1_text1);
                text.add(R.string.level1_text2);
                text.add(R.string.level1_text3);
                text.add(R.string.level1_text4);
                text.add(R.string.level1_text5);
                text.add(R.string.level1_text6);
                text.add(R.string.level1_text7);
                text.add(R.string.level1_text8);
                text.add(R.string.level1_text9);
                break;
            case 2:
                img.add(R.drawable.level2_3_0);
                img.add(R.drawable.level2_3_1);
                img.add(R.drawable.level2_95);
                img.add(R.drawable.level2_98);
                img.add(R.drawable.level2_2000);
                img.add(R.drawable.level2_xp);
                img.add(R.drawable.level2_7);
                img.add(R.drawable.level2_10);
                img.add(R.drawable.level2_11);
                text.add(R.string.level2_text0);
                text.add(R.string.level2_text1);
                text.add(R.string.level2_text2);
                text.add(R.string.level2_text3);
                text.add(R.string.level2_text4);
                text.add(R.string.level2_text5);
                text.add(R.string.level2_text6);
                text.add(R.string.level2_text7);
                text.add(R.string.level2_text8);
                break;
            case 3:
                img.add(R.drawable.level3_bioshock_infinite);
                img.add(R.drawable.level3_gta_5);
                img.add(R.drawable.level3_the_witcher_3);
                img.add(R.drawable.level3_dark_souls_3);
                img.add(R.drawable.level3_overwatch);
                img.add(R.drawable.level3_god_of_war);
                img.add(R.drawable.level3_rdr2);
                img.add(R.drawable.level3_sekiro);
                img.add(R.drawable.level3_doom_eternal);
                img.add(R.drawable.level3_cyberpunk_2077);
                text.add(R.string.level3_text0);
                text.add(R.string.level3_text1);
                text.add(R.string.level3_text2);
                text.add(R.string.level3_text3);
                text.add(R.string.level3_text4);
                text.add(R.string.level3_text5);
                text.add(R.string.level3_text6);
                text.add(R.string.level3_text7);
                text.add(R.string.level3_text8);
                text.add(R.string.level3_text9);
                break;
            case 4:
                img.add(R.drawable.level4_ruby);
                img.add(R.drawable.level4_go);
                img.add(R.drawable.level4_cplusplus);
                img.add(R.drawable.level4_swift);
                img.add(R.drawable.level4_kotlin);
                img.add(R.drawable.level4_typescript);
                img.add(R.drawable.level4_php);
                img.add(R.drawable.level4_python);
                img.add(R.drawable.level4_java);
                img.add(R.drawable.level4_csharp);
                img.add(R.drawable.level4_javascript);
                text.add(R.string.level4_text0);
                text.add(R.string.level4_text1);
                text.add(R.string.level4_text2);
                text.add(R.string.level4_text3);
                text.add(R.string.level4_text4);
                text.add(R.string.level4_text5);
                text.add(R.string.level4_text6);
                text.add(R.string.level4_text7);
                text.add(R.string.level4_text8);
                text.add(R.string.level4_text9);
                text.add(R.string.level4_text10);
                break;
            case 5:
                img.add(R.drawable.level5_playstation);
                img.add(R.drawable.level5_xbox);
                img.add(R.drawable.level5_chromeos);
                img.add(R.drawable.level5_linux);
                img.add(R.drawable.level5_osx);
                img.add(R.drawable.level5_ios);
                img.add(R.drawable.level5_windows);
                img.add(R.drawable.level5_android);
                text.add(R.string.level5_text0);
                text.add(R.string.level5_text1);
                text.add(R.string.level5_text2);
                text.add(R.string.level5_text3);
                text.add(R.string.level5_text4);
                text.add(R.string.level5_text5);
                text.add(R.string.level5_text6);
                text.add(R.string.level5_text7);
                break;
        }
    }
    //картинки начального диалога
    final int[] startDialogImg = {
            R.drawable.preview_level1,
            R.drawable.preview_level2,
            R.drawable.preview_level3,
            R.drawable.preview_level4,
            R.drawable.preview_level5,
    };
    //текст начального диалога
    final int[] startDialogText = {
            R.string.level1,
            R.string.level2,
            R.string.level3,
            R.string.level4,
            R.string.level5,
    };



    //ответы 2 уровня
    final int[] texts2desc = {
            R.string.level2_text_win3_0,
            R.string.level2_text_win3_1,
            R.string.level2_text_win95,
            R.string.level2_text_win98,
            R.string.level2_text_win2000,
            R.string.level2_text_winxp,
            R.string.level2_text_win7,
            R.string.level2_text_win10,
            R.string.level2_text_win11,
    };

    //ответы 5 уровня
    final int[] texts5desc = {
            R.string.level5_text0_ps,
            R.string.level5_text1_xbox,
            R.string.level5_text2_chrome,
            R.string.level5_text3_linux,
            R.string.level5_text4_osx,
            R.string.level5_text5_ios,
            R.string.level5_text6_windows,
            R.string.level5_text7_android,
    };
}
