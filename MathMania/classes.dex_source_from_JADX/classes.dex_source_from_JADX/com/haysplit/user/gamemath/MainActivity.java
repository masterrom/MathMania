package com.haysplit.user.gamemath;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private int besteasy;
    private int besthrd;
    private int bestmed;
    SharedPreferences bestscores;
    private RadioButton easy;
    private TextView eazy;
    private RadioButton hard;
    private MediaPlayer highscore;
    private TextView hrd;
    private TextView med;
    private RadioButton medium;
    private int num;
    private int score;
    private int time;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(C0182R.layout.activity_main);
        this.num = 0;
        this.time = 0;
        this.highscore = MediaPlayer.create(this, C0182R.raw.high);
        this.eazy = (TextView) findViewById(C0182R.id.besteasy);
        this.med = (TextView) findViewById(C0182R.id.bestmedium);
        this.hrd = (TextView) findViewById(C0182R.id.besthard);
        this.easy = (RadioButton) findViewById(C0182R.id.easy);
        this.medium = (RadioButton) findViewById(C0182R.id.medium);
        this.hard = (RadioButton) findViewById(C0182R.id.hard);
        this.bestscores = getSharedPreferences("scores", 0);
        int x = this.bestscores.getInt("Easyscore", 0);
        int y = this.bestscores.getInt("Mediumscore", 0);
        int z = this.bestscores.getInt("Hardscore", 0);
        this.eazy.setText("Easy: " + x);
        this.med.setText("Medium: " + y);
        this.hrd.setText("Hard: " + z);
    }

    public void game_screen(View view) {
        Intent get_game_screen = new Intent(this, secondScreen.class);
        if (this.num == 0 || this.time == 0) {
            Toast.makeText(getApplicationContext(), "Please select the time and game difficulty", 0).show();
            return;
        }
        get_game_screen.putExtra("difficulty", this.num);
        get_game_screen.putExtra("timediff", this.time);
        startActivityForResult(get_game_screen, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.score = data.getIntExtra("score", 0);
        if (this.num == 10 && this.score > this.besteasy) {
            this.highscore.start();
            this.eazy.setText("Easy: " + String.valueOf(this.score));
            this.besteasy = this.score;
            this.bestscores = getSharedPreferences("scores", 0);
            Editor edit = this.bestscores.edit();
            edit.putInt("Easyscore", this.besteasy);
            edit.apply();
        }
        if (this.num == 20 && this.score > this.bestmed) {
            this.highscore.start();
            this.med.setText("Medium: " + String.valueOf(this.score));
            this.bestmed = this.score;
            this.bestscores = getSharedPreferences("scores", 0);
            edit = this.bestscores.edit();
            edit.putInt("Mediumscore", this.bestmed);
            edit.apply();
        }
        if (this.num == 50 && this.score > this.besthrd) {
            this.highscore.start();
            this.hrd.setText("Hard: " + String.valueOf(this.score));
            this.besthrd = this.score;
            this.bestscores = getSharedPreferences("scores", 0);
            edit = this.bestscores.edit();
            edit.putInt("Hardscore", this.besthrd);
            edit.apply();
        }
    }

    public void radiobutton(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case C0182R.id.easy /*2131492952*/:
                if (checked) {
                    this.num = 10;
                    return;
                }
                return;
            case C0182R.id.medium /*2131492953*/:
                if (checked) {
                    this.num = 20;
                    return;
                }
                return;
            case C0182R.id.hard /*2131492954*/:
                if (checked) {
                    this.num = 50;
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void timediff(View view) {
        boolean timecheck = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case C0182R.id.second30 /*2131492955*/:
                if (timecheck) {
                    this.time = 30;
                    return;
                }
                return;
            case C0182R.id.second20 /*2131492956*/:
                if (timecheck) {
                    this.time = 20;
                    return;
                }
                return;
            case C0182R.id.second10 /*2131492957*/:
                if (timecheck) {
                    this.time = 10;
                    return;
                }
                return;
            default:
                return;
        }
    }
}
