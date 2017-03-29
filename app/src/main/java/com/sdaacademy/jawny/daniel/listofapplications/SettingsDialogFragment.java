package com.sdaacademy.jawny.daniel.listofapplications;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsDialogFragment extends DialogFragment {

    public static final String TAG = SettingsDialogFragment.class.getSimpleName();
    public static final String CHECK_BOX = "check_box";

    @BindView(R.id.show_app_check_box)
    CheckBox mShowAppCheckBox;

    public SettingsDialogFragment() {
        // Required empty public constructor
    }

    public static SettingsDialogFragment newInstance() {
        return new SettingsDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_settings_dialog, null);
        ButterKnife.bind(this, view);
        mShowAppCheckBox.setChecked(true);
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Settings")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mShowAppCheckBox.isChecked()) {
                            Log.d(TAG, "checked");

                        } else {
                            Log.d(TAG, "unchecked");

                        }
                    }
                })
                .create();
    }
}
