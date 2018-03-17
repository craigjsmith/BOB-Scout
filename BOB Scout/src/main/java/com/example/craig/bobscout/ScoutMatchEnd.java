package com.example.craig.bobscout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ScoutMatchEnd extends AppCompatActivity {

    private ToggleButton messedUp;
    private ToggleButton unusualMatch;
    private ToggleButton robotTipped;
    private ToggleButton damagedLift;
    private ToggleButton damagedDrivetrain;
    private ToggleButton damagedIntake;
    private ToggleButton playedDefense;
    private ToggleButton pushBot;
    private RadioGroup climbSelf;
    private RadioGroup climbSelfOther;
    private RadioGroup climbOther;
    private RadioButton selfSucc;
    private RadioButton selfFail;
    private RadioButton selfOtherSucc;
    private RadioButton selfOtherFail;
    private RadioButton otherSucc;
    private RadioButton otherFail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_match_end);

        messedUp = findViewById(R.id.messedUp);
        unusualMatch = findViewById(R.id.unusualMatch);
        robotTipped = findViewById(R.id.robotTipped);
        damagedDrivetrain = findViewById(R.id.damagedDrivetrain);
        damagedIntake = findViewById(R.id.damagedIntake);
        damagedLift = findViewById(R.id.damagedLift);
        playedDefense = findViewById(R.id.playedDefense);
        pushBot = findViewById(R.id.pushBot);
        climbSelf = findViewById(R.id.climbSelf);
        climbSelfOther = findViewById(R.id.climbSelfOther);
        climbOther = findViewById(R.id.climbOther);
        selfSucc = findViewById(R.id.selfSucc);
        selfFail = findViewById(R.id.selfFail);
        selfOtherSucc = findViewById(R.id.selfOtherSucc);
        selfOtherFail = findViewById(R.id.selfOtherFail);
        otherSucc = findViewById(R.id.otherSucc);
        otherFail = findViewById(R.id.otherFail);
    }

    public void submit(View v) {
        Intent intent = new Intent(this, SubmitMatch.class);
        Bundle extras = getIntent().getExtras();
        String data = extras.getString("DATA");
        String match = extras.getString("MATCH");
        String team = extras.getString("TEAM");
        String aStart = extras.getString("AUTO_START");
        boolean aCross = extras.getBoolean("AUTO_CROSS");
        boolean aSwitch = extras.getBoolean("AUTO_SWITCH");
        boolean aScale = extras.getBoolean("AUTO_SCALE");
        boolean aPickup = extras.getBoolean("AUTO_PICKUP");

        boolean discard = messedUp.isChecked();
        boolean unusual = unusualMatch.isChecked();
        boolean tipped = robotTipped.isChecked();
        boolean damDrive = damagedDrivetrain.isChecked();
        boolean damIntake = damagedIntake.isChecked();
        boolean damLift = damagedLift.isChecked();
        boolean def = playedDefense.isChecked();
        boolean push = pushBot.isChecked();
        boolean selfClimb = false;
        boolean otherClimb = false;

        if(climbSelf.getCheckedRadioButtonId() == selfSucc.getId()) {
            selfClimb = true;
        } else if(climbSelfOther.getCheckedRadioButtonId() == selfOtherSucc.getId()) {
            selfClimb = true;
            otherClimb = true;
        } else if(climbOther.getCheckedRadioButtonId() == otherSucc.getId()) {
            selfClimb = true;
        }

        data += match + "," + team + "," + aStart + "," + aCross + "," + aSwitch + "," + aScale + "," + aPickup;
        data += "," + null + "," + null + ",";
        data += discard + "," + unusual + "," + tipped + "," + damDrive + "," + damIntake + "," + damLift + "," + selfClimb + "," + otherClimb + "," + def + "," + push;
        data += "\n";

        extras.putString("END", data);

        intent.putExtras(extras);
        startActivity(intent);
    }

    public void climbRadioLogic(View v){
        if (climbSelf.getCheckedRadioButtonId() == v.getId()) {
            climbSelfOther.clearCheck();
            climbOther.clearCheck();
        } else if(climbSelfOther.getCheckedRadioButtonId() == v.getId()) {
            climbSelf.clearCheck();
            climbOther.clearCheck();
        } else if(climbOther.getCheckedRadioButtonId() == v.getId()) {
            climbSelf.clearCheck();
            climbSelfOther.clearCheck();
        }
    }


}
