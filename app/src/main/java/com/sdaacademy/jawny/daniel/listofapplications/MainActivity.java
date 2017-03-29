package com.sdaacademy.jawny.daniel.listofapplications;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.main_activity_layout)
    CoordinatorLayout mMainLayout;

    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;
    private PackageManager packageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showSnackBar();
        setRecycleView();

//        AppDetailsDialogFragment appDetailsDialogFragment = AppDetailsDialogFragment.newInstance();
//        appDetailsDialogFragment.setCancelable(false);
//        appDetailsDialogFragment.show(getSupportFragmentManager(), "");
    }

    private void setRecycleView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new InstalledAppsAdapter(getAppInfos()));
    }

    private void showSnackBar() {
        final Snackbar snackbar = Snackbar.make(mMainLayout, "Snackbar", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        snackbar.show();
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

    private List<AppInfo> getAppInfos() {
        packageManager = this.getPackageManager();
        List<ApplicationInfo> applicationInfos = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        List<AppInfo> appInfos = formatToAppInfo(applicationInfos);
        return appInfos;
    }

    private List<AppInfo> formatToAppInfo(List<ApplicationInfo> applicationInfos) {
        List<AppInfo> formattedAppInfos = new ArrayList<>();
        for (ApplicationInfo applicationInfo : applicationInfos) {
            formattedAppInfos.add(new AppInfo(applicationInfo.uid
                    , applicationInfo.loadLabel(packageManager).toString()
                    , applicationInfo.loadIcon(packageManager))
            );
        }
        return formattedAppInfos;
    }
}
