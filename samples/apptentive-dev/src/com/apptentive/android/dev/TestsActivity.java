/*
 * Copyright (c) 2014, Apptentive, Inc. All Rights Reserved.
 * Please refer to the LICENSE file for the terms and conditions
 * under which redistribution and use of this file is permitted.
 */

package com.apptentive.android.dev;

import android.content.*;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import com.apptentive.android.sdk.ApptentiveActivity;
import com.apptentive.android.sdk.Log;

import java.util.List;

/**
 * @author Sky Kelsey
 */
public class TestsActivity extends ApptentiveActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tests);
	}

	public void testTweet(@SuppressWarnings("unused") View view) {
		try {
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.putExtra(Intent.EXTRA_TEXT, "Testing…");
			intent.setType("text/plain");
			final PackageManager pm = getPackageManager();
			final List<ResolveInfo> activityList = pm.queryIntentActivities(intent, 0);
			for (ResolveInfo app : activityList) {
				if (app.activityInfo.name.contains("twitter")) {
					Log.e("TWITTER: %s", app.activityInfo.name);
				}
				if ("com.twitter.android.PostActivity".equals(app.activityInfo.name)) {
					final ActivityInfo activityInfo = app.activityInfo;
					final ComponentName name = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
					intent.addCategory(Intent.CATEGORY_LAUNCHER);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
					intent.setComponent(name);
					startActivity(intent);
					break;
				}
				if ("com.twitter.applib.composer.TextFirstComposerActivity".equals(app.activityInfo.name)) {
					final ActivityInfo activityInfo = app.activityInfo;
					final ComponentName name = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
					intent.addCategory(Intent.CATEGORY_LAUNCHER);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
					intent.setComponent(name);
					startActivity(intent);
					break;
				}
				if ("com.twitter.android.composer.TextFirstComposerActivity".equals(app.activityInfo.name)) {
					final ActivityInfo activityInfo = app.activityInfo;
					final ComponentName name = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
					intent.addCategory(Intent.CATEGORY_LAUNCHER);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
					intent.setComponent(name);
					startActivity(intent);
					break;
				}
			}
		} catch (final ActivityNotFoundException e) {
			android.util.Log.i("APPTENTIVE", "No native twitter app.", e);
		}
	}

	public void throwNpe(@SuppressWarnings("unused") View view) {
		throw new NullPointerException("This is just an exception to test out how the SDK handles it.");
	}
}
