package uk.co.kevalshah.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final String MOMA_URL = "http://www.moma.org/m";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
