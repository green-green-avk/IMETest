package green_green_avk.imetest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        final InputView wInput = findViewById(R.id.input);
        final TextView wOutput = findViewById(R.id.output);
        final RadioGroup wMode = findViewById(R.id.mode);
        wInput.cookedIME = wMode.getCheckedRadioButtonId() == R.id.cooked;
        wMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final RadioGroup group, final int checkedId) {
                wInput.cookedIME = checkedId == R.id.cooked;
                recreate();
            }
        });
        wInput.output = new InputView.Output() {
            @Override
            public void print(@NonNull final String v) {
                wOutput.append(v);
            }
        };
    }
}
