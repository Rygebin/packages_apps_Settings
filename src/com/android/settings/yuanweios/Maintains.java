package com.android.settings.yuanweios;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceGroup;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.PreferenceGroupAdapter;
import android.support.v7.preference.PreferenceViewHolder;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.provider.Settings;
import android.preference.SwitchPreference;
import android.os.UserHandle;
import java.util.regex.Matcher;
import com.android.settings.InstrumentedFragment;

import com.android.internal.logging.MetricsLogger;
import java.util.Date;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;
import java.util.regex.Pattern;
import android.util.Log;

public class YuanweiosAbout extends SettingsPreferenceFragment
{
    private static final String KEY_MAINTAINS = "yuanweios_maintains";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.yuanweios_maintains);
        
     PreferenceCategory maintainers = (PreferenceCategory)findPreference(KEY_MAINTAINS);

        String[] maintainers_title = getResources().getStringArray(R.array.maintainers_title);
        String[] maintainers_devices = getResources().getStringArray(R.array.maintainers_devices);
        String[] maintainers_url = getResources().getStringArray(R.array.maintainers_url);

        for (int i = 0; i < maintainers_title.length; i++) {
            Preference maintainer = new Preference(getPrefContext());
            final String maintainer_url = maintainers_url[i];
            maintainer.setIcon(R.drawable.ic_devs_phone);
            maintainer.setTitle(maintainers_title[i]);
            maintainer.setSummary(String.format(getString(R.string.maintainer_description), maintainers_devices[i]));
            maintainer.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(maintainer_url)));
                    return true;
                }
            });
            maintainers.addPreference(maintainer);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    
  
    @Override
    protected int getMetricsCategory() {
        return InstrumentedFragment.YUANWEIOSABOUT;
    }
}
