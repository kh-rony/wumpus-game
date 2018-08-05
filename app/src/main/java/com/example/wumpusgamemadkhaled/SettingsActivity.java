package com.example.wumpusgamemadkhaled;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.wumpusgamemadkhaled.NonActivityClasses.StaticComponents;

public class SettingsActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		Switch switchCheatMode = (Switch) findViewById(R.id.switchCheatMode);

		switchCheatMode.setChecked(StaticComponents.cheatMode);

		switchCheatMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				// do something, the isChecked will be
				// true if the switch is in the On position

				StaticComponents.cheatMode = isChecked;
			}
		});
	}
}
