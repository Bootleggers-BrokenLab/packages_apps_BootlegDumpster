package com.bootleggers.dumpster.fragments;

import com.android.internal.logging.nano.MetricsProto;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.UserHandle;
import android.content.ContentResolver;
import android.content.res.Resources;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceCategory;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.PreferenceFragment;
import androidx.preference.SwitchPreference;
import android.provider.Settings;
import com.android.settings.R;

import java.util.Locale;
import android.text.TextUtils;
import android.view.View;

import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;
import android.util.Log;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class StatusBarSettings extends SettingsPreferenceFragment implements
        OnPreferenceChangeListener {

        private static final String KEY_SHOW_FOURG = "show_fourg_icon";

        private SwitchPreference mShowFourg;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        addPreferencesFromResource(R.xml.bootleg_dumpster_frag_status_bar);

        PreferenceScreen prefSet = getPreferenceScreen();

        mShowFourg = (SwitchPreference) findPreference(KEY_SHOW_FOURG);

        if (!TelephonyUtils.isVoiceCapable(getActivity())) {
            prefScreen.removePreference(mShowFourg);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        ContentResolver resolver = getActivity().getContentResolver();
        switch (preference.getKey()) {
            default:
                return false;
        }

    public static void reset(Context mContext) {
        ContentResolver resolver = mContext.getContentResolver();
        Settings.System.putIntForUser(resolver,
                Settings.System.SHOW_FOURG_ICON, 0, UserHandle.USER_CURRENT);
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.BOOTLEG;
    }

    /**
     * For search
     */
    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider(R.xml.bootleg_dumpster_frag_status_bar) {
                @Override
                public List<String> getNonIndexableKeys(Context context) {
                    List<String> keys = super.getNonIndexableKeys(context);
                    if (!TelephonyUtils.isVoiceCapable(context)) {
                        keys.add(KEY_SHOW_FOURG);
                    }

                    return keys;
                }
            };
}
