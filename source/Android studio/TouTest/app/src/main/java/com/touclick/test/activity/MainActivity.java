package com.touclick.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.touclick.test.R;

public class MainActivity extends AppCompatActivity {

	private Button mBtnImageText;
	private Button mBtnIcon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mBtnImageText = (Button) findViewById(R.id.btnimgtext);
		mBtnIcon = (Button) findViewById(R.id.btnicon);
		mBtnImageText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ImageTextActivity.class);
				startActivity(intent);
			}
		});

		mBtnIcon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, IconActivity.class);
				startActivity(intent);
			}
		});
	}
}
