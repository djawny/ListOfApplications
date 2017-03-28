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

    private List<AppInfo> mApps = new ArrayList<>();

    public InstalledAppsAdapter(List<AppInfo> mApps) {
        if (mApps != null) {
            this.mApps.addAll(mApps);
        }
    }

    @Override
    public InstallAppsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_layout, parent, false);
        return new InstallAppsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InstallAppsViewHolder holder, int position) {
        AppInfo appInfo = mApps.get(position);
        holder.mName.setText(appInfo.getName());
    }

    @Override
    public int getItemCount() {
        return mApps != null ? mApps.size() : 0;
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
