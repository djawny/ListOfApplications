package com.sdaacademy.jawny.daniel.listofapplications;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SettingsDialogFragment.OnSettingsListener {

    @BindView(R.id.main_activity_layout)
    CoordinatorLayout mMainLayout;

    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    private PackageManager packageManager;
    private InstalledAppsAsyncTask installedAppsAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayApps(true);
    }

    private void showSnackBar(String message) {
        Snackbar.make(mMainLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_settings:
                openSettings();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openSettings() {
        SettingsDialogFragment settingsDialogFragment = SettingsDialogFragment.newInstance();
        settingsDialogFragment.setCancelable(false);
        settingsDialogFragment.show(getSupportFragmentManager(), "");
    }

    @Override
    public void onCheckChange(boolean isChecked) {
        displayApps(isChecked);
    }

    private void displayApps(boolean isChecked) {
        installedAppsAsyncTask = new InstalledAppsAsyncTask();
        installedAppsAsyncTask.execute(isChecked);
    }

    private class InstalledAppsAsyncTask extends AsyncTask<Boolean, Void, InstalledAppsAdapter> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }

        @Override
        protected InstalledAppsAdapter doInBackground(Boolean... params) {
            List<AppInfo> appInfos = getAppInfos(params[0]);
            InstalledAppsAdapter adapter = new InstalledAppsAdapter(appInfos, getSupportFragmentManager());
            return adapter;
        }

        @Override
        protected void onPostExecute(InstalledAppsAdapter installedAppsAdapter) {
            super.onPostExecute(installedAppsAdapter);
            mProgressBar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mRecyclerView.setAdapter(installedAppsAdapter);
            showSnackBar("Applications have been loaded.");
        }

        private List<AppInfo> getAppInfos(boolean onlyUserApps) {
            packageManager = getPackageManager();
            List<ApplicationInfo> applicationInfos = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
            List<AppInfo> appInfos = formatToAppInfo(applicationInfos, onlyUserApps);
            return appInfos;
        }

        private List<AppInfo> formatToAppInfo(List<ApplicationInfo> applicationInfos, boolean onlyUserApps) {
            List<AppInfo> formattedAppInfos = new ArrayList<>();
            for (ApplicationInfo applicationInfo : applicationInfos) {
                if (onlyUserApps && !isUserApplication(applicationInfo)) {
                    continue;
                }
                formattedAppInfos.add(new AppInfo(applicationInfo.uid
                        , applicationInfo.loadLabel(packageManager).toString()
                        , applicationInfo.loadIcon(packageManager))
                );
            }
            return formattedAppInfos;
        }

        private boolean isUserApplication(ApplicationInfo applicationInfo) {
            return (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
        }
    }
}
