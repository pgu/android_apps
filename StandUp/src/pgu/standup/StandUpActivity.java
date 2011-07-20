package pgu.standup;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class StandUpActivity extends Activity {

    // private final static String TAG = "X";

    private static final int CYCLE_TEST = 21 * 1000;
    private static final int CYCLE_COUNTDOWN = 1 * 1000;

    private TextViewCountdown countdown;
    private final List<TextViewState> texts = new ArrayList<TextViewState>();
    private final Timer textTimer = new Timer();
    private final Timer countdownTimer = new Timer();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle(R.string.title);

        countdown = new TextViewCountdown(R.id.countdown, this);
        texts.add(new TextViewState(R.id.qYesterday, this));
        texts.add(new TextViewState(R.id.qToday, this));
        texts.add(new TextViewState(R.id.qWay, this));

        findViewById(R.id.reset).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                resetApp();
            }
        });

        findViewById(R.id.go).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                final TextViewState text = getActiveQuestion();
                resetApp();

                if (null != text) { // stand up est en cours
                // Log.i(TAG, "click-text " + text.getTextView().toString());
                    int idx = texts.indexOf(text);
                    idx++; // go to next
                    if (idx == texts.size()) {
                        // Log.i(TAG, "click-return");
                        return;
                    }
                    if (idx < texts.size()) {
                        // Log.i(TAG, "click-set textstart");
                        textStart = texts.get(idx);
                    }
                }

                // Log.i(TAG, "click-schedule texttask");
                textTask = getTextTask();
                textTimer.schedule(textTask, 0, CYCLE_TEST);

            }
        });

    }

    private TextViewState textStart;

    public void resetApp() {
        // Log.i(TAG, "resetapp");
        textStart = null;
        if (null != textTask) {
            textTask.cancel();
        }
        if (null != countdownTask) {
            countdownTask.cancel();
        }

        countdown.reset();
        resetTexts();
    }

    public TimerTask getCountdownTask() {
        return new TimerTask() {

            @Override
            public void run() {
                // Log.i(TAG, "run-countdowntask");
                runOnUiThread(runCountdownTask);
            }

        };
    }

    public TimerTask getTextTask() {
        return new TimerTask() {

            @Override
            public void run() {
                // Log.i(TAG, "run-texttask");
                runOnUiThread(runTextTask);
            }

        };
    }

    private TimerTask countdownTask;
    private TimerTask textTask;

    private final Runnable runCountdownTask = new Runnable() {

        @Override
        public void run() {
            // Log.i(TAG, "run-runcountdowntask");
            Integer seconds = Integer.parseInt(countdown.getTextView().getText().toString().replace("s", ""));
            if (0 < seconds) {
                seconds--;
                countdown.update(seconds);
            } else {
                countdown.reset();
                countdownTask.cancel();
            }
        }

    };

    private final Runnable runTextTask = new Runnable() {

        @Override
        public void run() {
            // Log.i(TAG, "run-runtexttask");
            TextViewState textActive = getActiveQuestion();
            int location;
            if (null == textActive) {
                if (null == textStart) {
                    location = 0; // on est au début
                } else {
                    location = texts.indexOf(textStart); // ou on est passe directement a la question suivante
                }
            } else {
                location = texts.indexOf(textActive);
                location++; // get current text, pass to the next one
            }

            if (location == texts.size()) { // on est a la fin, reset
                resetApp();
                return;
            }

            textActive = texts.get(location);
            highlight(textActive);
            // Log.i(TAG, "countdowntime-schedule");
            countdownTask = getCountdownTask();
            countdownTimer.schedule(countdownTask, 0, CYCLE_COUNTDOWN);
        }
    };

    private void resetTexts() {
        for (final TextViewState text : texts) {
            text.reset();
        }
    }

    private void highlight(final TextViewState text) {
        resetTexts();
        text.highlight();
    }

    private TextViewState getActiveQuestion() {
        for (final TextViewState text : texts) {
            if (text.isActive()) {
                return text;
            }
        }
        return null;
    }
}