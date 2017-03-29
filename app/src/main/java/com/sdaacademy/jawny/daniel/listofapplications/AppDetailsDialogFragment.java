package com.sdaacademy.jawny.daniel.listofapplications;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppDetailsDialogFragment extends DialogFragment {

    public static final String TAG_APP_INFO = "tag_app_info";

    @BindView(R.id.app_uid)
    TextView mUid;

    @BindView(R.id.app_name)
    TextView mName;

    @BindView(R.id.app_icon)
    ImageView mIcon;

    private AppInfo appInfo;

    public AppDetailsDialogFragment() {
        // Required empty public constructor
    }

    public static AppDetailsDialogFragment newInstance(Parcelable appInfo) {
        Bundle args = new Bundle();
        args.putParcelable(TAG_APP_INFO, appInfo);
        AppDetailsDialogFragment appDetailsDialogFragment = new AppDetailsDialogFragment();
        appDetailsDialogFragment.setArguments(args);
        return appDetailsDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        appInfo = arguments.getParcelable(TAG_APP_INFO);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_app_details_dialog, null);
        ButterKnife.bind(this, view);

        setViews();

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Application details:")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
    }

    private void setViews() {
        mUid.setText(String.format("UID: %s", String.valueOf(appInfo.getUid())));
        mName.setText(String.format("Name: %s", appInfo.getName()));
        mIcon.setImageDrawable(appInfo.getIcon());
    }
}
