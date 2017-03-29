package com.sdaacademy.jawny.daniel.listofapplications;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstalledAppsAdapter extends RecyclerView.Adapter<InstalledAppsAdapter.InstallAppsViewHolder> {

    private List<AppInfo> appInfoList = new ArrayList<>();

    public InstalledAppsAdapter(List<AppInfo> appInfoList) {
        if (appInfoList != null) {
            this.appInfoList.addAll(appInfoList);
        }
    }

    @Override
    public InstallAppsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.installed_app_layout, parent, false);
        return new InstallAppsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InstallAppsViewHolder holder, int position) {
        AppInfo appInfo = appInfoList.get(position);
        holder.mName.setText(appInfo.getName());
        holder.mIcon.setImageDrawable(appInfo.getIcon());
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
