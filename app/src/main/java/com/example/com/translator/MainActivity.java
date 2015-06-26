package com.example.com.translator;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends Activity {

    private String[][] phases;
    static private final int ALL_OTHER_LANGUAGES = 3;
    static private final int NUM_OF_LANGUAGES = 3;

    private OnClickListener from_buttons_listener = new OnClickListener() {
        public void onClick(View v) {
            ((ToggleButton)findViewById(R.id.from_button0)).setChecked(false);
            ((ToggleButton)findViewById(R.id.from_button1)).setChecked(false);
            ((ToggleButton)findViewById(R.id.from_button2)).setChecked(false);
            ((ToggleButton)v).setChecked(true);
            updatePhase();
            update();
        }
    };

    private OnClickListener to_buttons_listener = new OnClickListener() {
        public void onClick(View v) {
            ((ToggleButton)findViewById(R.id.to_button0)).setChecked(false);
            ((ToggleButton)findViewById(R.id.to_button1)).setChecked(false);
            ((ToggleButton)findViewById(R.id.to_button2)).setChecked(false);
            ((ToggleButton)findViewById(R.id.to_button3)).setChecked(false);
            ((ToggleButton)v).setChecked(true);
            update();
        }
    };

    private OnClickListener phase_buttons_listener = new OnClickListener() {
        public void onClick(View v) {
            ((ToggleButton)findViewById(R.id.phase_button0)).setChecked(false);
            ((ToggleButton)findViewById(R.id.phase_button1)).setChecked(false);
            ((ToggleButton)findViewById(R.id.phase_button2)).setChecked(false);
            ((ToggleButton)findViewById(R.id.phase_button3)).setChecked(false);
            ((ToggleButton)findViewById(R.id.phase_button4)).setChecked(false);
            ((ToggleButton)v).setChecked(true);
            update();
        }
    };

    private int getFromId() {
        if(((ToggleButton)findViewById(R.id.from_button0)).isChecked()) {
            return 0;
        } else if (((ToggleButton)findViewById(R.id.from_button1)).isChecked()) {
            return 1;
        } else if (((ToggleButton)findViewById(R.id.from_button2)).isChecked()) {
            return 2;
        } else {
            return -1;
        }
    }

    private int getToId() {
        if(((ToggleButton)findViewById(R.id.to_button0)).isChecked()) {
            return 0;
        } else if (((ToggleButton)findViewById(R.id.to_button1)).isChecked()) {
            return 1;
        } else if (((ToggleButton)findViewById(R.id.to_button2)).isChecked()) {
            return 2;
        } else if (((ToggleButton)findViewById(R.id.to_button3)).isChecked()) {
            return 3;
        } else {
            return -1;
        }
    }

    private void updatePhase() {
        int fromId = getFromId();
        ((ToggleButton)findViewById(R.id.phase_button0)).setText(phases[fromId][0]);
        ((ToggleButton)findViewById(R.id.phase_button0)).setTextOn(phases[fromId][0]);
        ((ToggleButton)findViewById(R.id.phase_button0)).setTextOff(phases[fromId][0]);
        ((ToggleButton)findViewById(R.id.phase_button1)).setText(phases[fromId][1]);
        ((ToggleButton)findViewById(R.id.phase_button1)).setTextOn(phases[fromId][1]);
        ((ToggleButton)findViewById(R.id.phase_button1)).setTextOff(phases[fromId][1]);
        ((ToggleButton)findViewById(R.id.phase_button2)).setText(phases[fromId][2]);
        ((ToggleButton)findViewById(R.id.phase_button2)).setTextOn(phases[fromId][2]);
        ((ToggleButton)findViewById(R.id.phase_button2)).setTextOff(phases[fromId][2]);
        ((ToggleButton)findViewById(R.id.phase_button3)).setText(phases[fromId][3]);
        ((ToggleButton)findViewById(R.id.phase_button3)).setTextOn(phases[fromId][3]);
        ((ToggleButton)findViewById(R.id.phase_button3)).setTextOff(phases[fromId][3]);
        ((ToggleButton)findViewById(R.id.phase_button4)).setText(phases[fromId][4]);
        ((ToggleButton)findViewById(R.id.phase_button4)).setTextOn(phases[fromId][4]);
        ((ToggleButton)findViewById(R.id.phase_button4)).setTextOff(phases[fromId][4]);
    }

    private void update() {
        TextView resultText0 = (TextView)findViewById(R.id.result_text0);

        int fromId = getFromId();
        int toId = getToId();
        int phaseId = getPhaseId();
        if (toId != ALL_OTHER_LANGUAGES) {
            resultText0.setText(phases[toId][phaseId]);
        }
        else {
            String result = "";
            for (int i = 0; i < NUM_OF_LANGUAGES; i++) {
                if (i != fromId) {
                    result += int2Language(i) + ":\n   " + phases[i][phaseId] + "\n";
                }
            }
            result = result.trim();
            resultText0.setText(result);
        }
    }

    private String int2Language(int language) {
        switch(language) {
            case 0: return "English";
            case 1: return "Spanish";
            case 2: return "Chinese";
            default: return null;
        }
    }

    private int getPhaseId() {
        if(((ToggleButton)findViewById(R.id.phase_button0)).isChecked()) {
            return 0;
        } else if (((ToggleButton)findViewById(R.id.phase_button1)).isChecked()) {
            return 1;
        } else if (((ToggleButton)findViewById(R.id.phase_button2)).isChecked()) {
            return 2;
        } else if (((ToggleButton)findViewById(R.id.phase_button3)).isChecked()) {
            return 3;
        }  else if (((ToggleButton)findViewById(R.id.phase_button4)).isChecked()) {
            return 4;
        } else {
            return -1;
        }
    }

    private CompoundButton.OnCheckedChangeListener orientationSwitchLinstener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            update();
            if (isChecked) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            }
            update();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch orientationSwitch = (Switch) findViewById(R.id.orientation_switch);
        orientationSwitch.setOnCheckedChangeListener(orientationSwitchLinstener);

        phases = new String[][]{
                getResources().getStringArray(R.array.phase0),
                getResources().getStringArray(R.array.phase1),
                getResources().getStringArray(R.array.phase2)};

        ((ToggleButton) findViewById(R.id.from_button0)).setChecked(true);
        ((ToggleButton) findViewById(R.id.to_button0)).setChecked(true);
        ((ToggleButton) findViewById(R.id.phase_button0)).setChecked(true);

        findViewById(R.id.from_button0).setOnClickListener(from_buttons_listener);
        findViewById(R.id.from_button1).setOnClickListener(from_buttons_listener);
        findViewById(R.id.from_button2).setOnClickListener(from_buttons_listener);

        findViewById(R.id.to_button0).setOnClickListener(to_buttons_listener);
        findViewById(R.id.to_button1).setOnClickListener(to_buttons_listener);
        findViewById(R.id.to_button2).setOnClickListener(to_buttons_listener);
        findViewById(R.id.to_button3).setOnClickListener(to_buttons_listener);

        findViewById(R.id.phase_button0).setOnClickListener(phase_buttons_listener);
        findViewById(R.id.phase_button1).setOnClickListener(phase_buttons_listener);
        findViewById(R.id.phase_button2).setOnClickListener(phase_buttons_listener);
        findViewById(R.id.phase_button3).setOnClickListener(phase_buttons_listener);
        findViewById(R.id.phase_button4).setOnClickListener(phase_buttons_listener);

        update();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
