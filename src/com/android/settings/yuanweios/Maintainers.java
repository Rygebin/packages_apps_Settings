package com.android.settings.yuanweios;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.R;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCatecory;

import com.android.settings.InstrumentedFragment;

public class Maintainers extends SettingsPreferenceFragment
{
    private static final String KEY_MAINTAINS = "yuanweios_maintainers";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.yuanweios_maintainers);
        
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
    protected int getMetricsCategory() {
        return InstrumentedFragment.YUANWEIOSABOUT;
    }
}
