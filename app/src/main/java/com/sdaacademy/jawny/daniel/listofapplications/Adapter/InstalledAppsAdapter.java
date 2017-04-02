package com.sdaacademy.jawny.daniel.listofapplications.Adapter;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdaacademy.jawny.daniel.listofapplications.View.AppDetailsDialogFragment;
import com.sdaacademy.jawny.daniel.listofapplications.Model.AppInfo;
import com.sdaacademy.jawny.daniel.listofapplications.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstalledAppsAdapter extends RecyclerView.Adapter<InstalledAppsAdapter.InstallAppsViewHolder> {

    private List<AppInfo> appInfoList = new ArrayList<>();
    private FragmentManager fragmentManager;

    public InstalledAppsAdapter(List<AppInfo> appInfoList, FragmentManager fragmentManager) {
        if (appInfoList != null) {
            this.appInfoList.addAll(appInfoList);
        }
        this.fragmentManager = fragmentManager;
    }

    @Override
    public InstallAppsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.installed_app_layout, parent, false);
        return new InstallAppsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InstallAppsViewHolder holder, final int position) {
        final AppInfo appInfo = appInfoList.get(position);
        holder.mName.setText(appInfo.getName());
        holder.mIcon.setImageDrawable(appInfo.getIcon());

//        if (position % 2 == 0) {
//            holder.itemView.setBackgroundColor(Color.BLACK);
//            holder.itemView.getBackground().setAlpha(10);
//        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDetailsDialogFragment appDetailsDialogFragment = AppDetailsDialogFragment.newInstance(appInfo);
                appDetailsDialogFragment.setCancelable(false);
                appDetailsDialogFragment.show(fragmentManager, "");
            }
        });
    }

    @Override
    public int getItemCount() {
        return appInfoList != null ? appInfoList.size() : 0;
    }

    static class InstallAppsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.installed_app_icon)
        ImageView mIcon;

        @BindView(R.id.installed_app_name)
        TextView mName;

        public InstallAppsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
