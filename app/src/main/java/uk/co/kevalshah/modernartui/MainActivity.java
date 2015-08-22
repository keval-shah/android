package uk.co.kevalshah.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private static final String MOMA_URL = "http://www.moma.org/m";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onStartTrackingTouch(final SeekBar seekBar) {

    }

    @Override
    public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
        alterColour(findViewById(R.id.blueBox), progress, R.color.blue, R.color.khaki);
        alterColour(findViewById(R.id.purpleBox), progress, R.color.purple, R.color.gold);
        alterColour(findViewById(R.id.redBox), progress, R.color.red, R.color.orange);
        alterColour(findViewById(R.id.darkBlueBox), progress, R.color.darkBlue, R.color.olive);
    }

    private void alterColour(final View view, final int percentage, final int originalColourResourceId,
                             final int targetColourResourceId) {
        final int originalColour = getResources().getColor(originalColourResourceId);
        final int targetColour = getResources().getColor(targetColourResourceId);
        view.setBackgroundColor(Color.rgb(
                calcNewValue(Color.red(originalColour), Color.red(targetColour), percentage),
                calcNewValue(Color.green(originalColour), Color.green(targetColour), percentage),
                calcNewValue(Color.blue(originalColour), Color.blue(targetColour), percentage)));
    }

    private int calcNewValue(final int start, final int stop, final int percentage) {
        final int increment = (int) ((stop - start) * percentage/100f);
        return start + increment;
    }

    @Override
    public void onStopTrackingTouch(final SeekBar seekBar) {

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_more_information) {
            final DialogFragment dialog = AlertDialogFragment.newInstance();
            dialog.show(getFragmentManager(), getString(R.string.action_more_information));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class AlertDialogFragment extends DialogFragment {

        public static AlertDialogFragment newInstance() {
            return new AlertDialogFragment();
        }

        @Override
        public Dialog onCreateDialog(final Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.dialog_text)
                    .setCancelable(true)
                    .setNegativeButton(R.string.button_not_now, null)
                    .setPositiveButton(R.string.button_visit_moma, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(MOMA_URL));
                            startActivity(browserIntent);
                        }
                    })
                    .create();
        }
    }
}
