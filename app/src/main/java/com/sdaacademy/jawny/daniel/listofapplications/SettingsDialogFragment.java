package com.sdaacademy.jawny.daniel.listofapplications;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsDialogFragment extends DialogFragment {

    public static final String TAG = SettingsDialogFragment.class.getSimpleName();

    @BindView(R.id.show_app_check_box)
    CheckBox mShowAppCheckBox;
    private MainActivity mainActivity;

    public SettingsDialogFragment() {
        // Required empty public constructor
    }

    public static SettingsDialogFragment newInstance() {
        return new SettingsDialogFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
        } else {
            Log.e(TAG, "Invalid activity type");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_settings_dialog, null);
        ButterKnife.bind(this, view);
        mShowAppCheckBox.setChecked(true);
        mShowAppCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mainActivity.showAllApps();
                } else {
                    mainActivity.showUserApps();
                }
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Settings")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
    }
}
