package com.haysplit.user.gamemath;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class secondScreen extends Activity {
    private int actual_ans;
    private int ans;
    private boolean check;
    private Button correct;
    private TextView countdown;
    private MediaPlayer ding;
    private TextView equation;
    private Button incorrect;
    private int max;
    private boolean over;
    private int player_score;
    private TextView score;
    private int time;
    private MediaPlayer zing;

    class C01852 implements OnClickListener {
        C01852() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Intent goingback = new Intent();
            goingback.putExtra("score", secondScreen.this.player_score);
            secondScreen.this.setResult(-1, goingback);
            secondScreen.this.finish();
        }
    }

    class C01863 implements OnClickListener {
        C01863() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Intent goingback = new Intent();
            goingback.putExtra("score", secondScreen.this.player_score);
            secondScreen.this.setResult(-1, goingback);
            secondScreen.this.finish();
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.second_layout);
        Intent tent = getIntent();
        this.max = tent.getIntExtra("difficulty", 10);
        this.time = tent.getIntExtra("timediff", 30) * 1000;
        this.correct = (Button) findViewById(C0182R.id.correct);
        this.incorrect = (Button) findViewById(C0182R.id.incorrect);
        this.equation = (TextView) findViewById(C0182R.id.equation);
        this.score = (TextView) findViewById(C0182R.id.scoreview);
        this.countdown = (TextView) findViewById(C0182R.id.countdown);
        this.ding = MediaPlayer.create(this, C0182R.raw.dingf);
        this.zing = MediaPlayer.create(this, C0182R.raw.buzz2);
        new_question();
        new CountDownTimer((long) this.time, 1000) {

            class C01831 implements OnClickListener {
                C01831() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    Intent goingback = new Intent();
                    goingback.putExtra("score", secondScreen.this.player_score);
                    secondScreen.this.setResult(-1, goingback);
                    secondScreen.this.finish();
                }
            }

            public void onTick(long millisUntilFinished) {
                secondScreen.this.countdown.setText("Countdown: " + (millisUntilFinished / 1000));
                if (secondScreen.this.over) {
                    cancel();
                }
            }

            public void onFinish() {
                secondScreen.this.zing.start();
                secondScreen.this.correct.setClickable(false);
                secondScreen.this.incorrect.setClickable(false);
                AlertDialog alertDialog = new Builder(secondScreen.this).create();
                alertDialog.setCancelable(false);
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.setTitle("Game Over!");
                alertDialog.setMessage("Your final score is : " + secondScreen.this.player_score);
                alertDialog.setButton(-3, "OK", new C01831());
                alertDialog.show();
            }
        }.start();
    }

    private int[] new_question() {
        String[] signs = new String[]{"+", "/", "-", "*"};
        int sign_array = new Random().nextInt(3) + 0;
        int val_one = new Random().nextInt(10) + 1;
        Random valtwo = new Random();
        int val_two = valtwo.nextInt(this.max) + 1;
        if (val_two == val_one) {
            while (val_two == val_one) {
                val_two = valtwo.nextInt(this.max) + 1;
            }
        }
        Random sign_choice = new Random();
        int choice = sign_choice.nextInt(20) + 0;
        int ratio = 10;
        if (this.max == 20) {
            ratio = sign_choice.nextInt(10) + 5;
        }
        if (this.max == 50) {
            ratio = sign_choice.nextInt(5) + 0;
        }
        if (choice <= ratio) {
            this.check = true;
            correct_answer(sign_array, val_one, val_two);
        } else {
            this.check = false;
            wrong_answer(sign_array, val_one, val_two);
        }
        return null;
    }

    private int wrong_answer(int sign_val, int val_one, int val_two) {
        int ran_num = new Random().nextInt(5) + 0;
        if (sign_val == 0) {
            this.ans = (val_one + val_two) + ran_num;
            this.actual_ans = val_one + val_two;
            this.equation.setText(String.valueOf(val_one) + "  +  " + String.valueOf(val_two) + " = " + String.valueOf(this.ans));
        } else if (sign_val == 2) {
            this.ans = (val_one - val_two) + ran_num;
            this.actual_ans = val_one - val_two;
            this.equation.setText(String.valueOf(val_one) + "  -  " + String.valueOf(val_two) + " = " + String.valueOf(this.ans));
        } else if (sign_val == 3 || sign_val == 1) {
            this.ans = (val_one * val_two) + ran_num;
            this.actual_ans = val_one * val_two;
            this.equation.setText(String.valueOf(val_one) + "  *  " + String.valueOf(val_two) + " = " + String.valueOf(this.ans));
        }
        return this.ans;
    }

    private int correct_answer(int sign_val, int val_one, int val_two) {
        if (sign_val == 0) {
            this.ans = val_one + val_two;
            this.equation.setText(String.valueOf(val_one) + "  +  " + String.valueOf(val_two) + " = " + String.valueOf(this.ans));
        } else if (sign_val == 2) {
            this.ans = val_one - val_two;
            this.equation.setText(String.valueOf(val_one) + "  -  " + String.valueOf(val_two) + " = " + String.valueOf(this.ans));
        } else if (sign_val == 3 || sign_val == 1) {
            this.ans = val_one * val_two;
            this.equation.setText(String.valueOf(val_one) + "  *  " + String.valueOf(val_two) + " = " + String.valueOf(this.ans));
        }
        this.actual_ans = this.ans;
        return this.ans;
    }

    public void check_correct(View view) {
        if (this.ans == this.actual_ans) {
            this.player_score++;
            this.ding.start();
            this.score.setText("Score: " + this.player_score);
            new_question();
            return;
        }
        this.zing.start();
        this.over = true;
        this.correct.setClickable(false);
        this.incorrect.setClickable(false);
        AlertDialog alertDialog = new Builder(this).create();
        alertDialog.setCancelable(false);
        alertDialog.setTitle("Game Over!");
        alertDialog.setMessage("Your final score is : " + this.player_score);
        alertDialog.setButton(-3, "OK", new C01852());
        alertDialog.show();
    }

    public void check_incorrect(View view) {
        if (this.actual_ans != this.ans) {
            this.ding.start();
            this.player_score++;
            new_question();
            this.score.setText("Score: " + this.player_score);
            return;
        }
        this.zing.start();
        this.over = true;
        this.correct.setClickable(false);
        this.incorrect.setClickable(false);
        AlertDialog alertDialog = new Builder(this).create();
        alertDialog.setCancelable(false);
        alertDialog.setTitle("Game Over!");
        alertDialog.setMessage("Your final score is : " + this.player_score);
        alertDialog.setButton(-3, "OK", new C01863());
        alertDialog.show();
    }

    public void onBackPressed() {
    }
}
