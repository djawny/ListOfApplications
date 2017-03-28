package com.sdaacademy.jawny.daniel.listofapplications;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppDetailsDialogFragment extends DialogFragment {

    @BindView(R.id.app_uid)
    TextView mUid;

    @BindView(R.id.app_name)
    TextView mName;

    @BindView(R.id.app_icon)
    ImageView mIcon;

    public AppDetailsDialogFragment() {
        // Required empty public constructor
    }

    public static AppDetailsDialogFragment newInstance() {
        return new AppDetailsDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_app_details_dialog, null);
        ButterKnife.bind(this, view);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("App details")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
    }
}
