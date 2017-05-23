package com.touclick.test.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import com.touclick.android.test.R;
import com.touclick.android.api.TCListener;
import com.touclick.android.ui.ImgTxtButton;

public class IconActivity extends AppCompatActivity {

	private TextView mTvResult;
	// private ImgTxtRelativeLayout mImgButton;
	private ImgTxtButton mImgButton;
	static ImageView bg_u22;

	private static class MyHandler extends Handler {

		// 对Activity的弱引用
		private final WeakReference<IconActivity> mActivity;

		public MyHandler(IconActivity activity) {
			mActivity = new WeakReference<IconActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			IconActivity activity = mActivity.get();
			if (activity == null) {
				super.handleMessage(msg);
				return;
			}
			switch (msg.what) {
			case 1000:
				bg_u22.setVisibility(View.VISIBLE);
				bg_u22.startAnimation(myAnimation);
				mHandler.postDelayed(new Runnable() {
					@Override
					public void run() {
						mHandler.sendEmptyMessage(1001);
					}
				}, 2000);
				break;
			case 1001:
				bg_u22.setVisibility(View.GONE);
				break;
			default:
				super.handleMessage(msg);
				break;
			}
		}
	}

	private static MyHandler mHandler;
	static Animation myAnimation;

	@SuppressLint("ShowToast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_icon2);
		mHandler = new MyHandler(this);
		mTvResult = (TextView) findViewById(R.id.tvresult);
		// mImgButton = (ImgTxtRelativeLayout)
		// findViewById(R.id.imgtxtrelativelayout);
		mImgButton = (ImgTxtButton) findViewById(R.id.imgtxtbutton);
		String publicKey = "";
		mImgButton.setTCListener(publicKey, new TCListener() {

			@Override
			public void onSuccess(int status, String token, String checkAddress, String sid) {
				System.out
						.println("状态：" + status + ";token:" + token + ";checkAddress:" + checkAddress + ";sid:" + sid);
				mTvResult.setText("token" + token + "checkAddress=" + checkAddress + "sid=" + sid);
			}

			@Override
			public void onFailture(int status, String msg, String sid) {
				Toast.makeText(getBaseContext(), "状态：" + status + ";msg:" + msg + ";sid:" + sid, 0).show();
				mTvResult.setText("失败" + sid);
			}

			@Override
			public void onClose(int status, String msg) {
				Toast.makeText(getBaseContext(), "状态：" + status + ";msg:" + msg, 0).show();
				System.out.println("状态：" + status + ";msg:" + msg);
			}

			@Override
			public void onError(int status, Object msg) {
				Toast.makeText(getBaseContext(), "状态：" + status + ";msg:" + msg, 0).show();
				System.out.println("状态：" + status + ";msg:" + msg);
			}
		});
		mImgButton.setFocusable(false);
		mImgButton.setTouchable(false);

		bg_u22 = (ImageView) findViewById(R.id.bg_u22);
		bg_u22.setVisibility(View.GONE);
		myAnimation = AnimationUtils.loadAnimation(this, R.anim.my_anim);
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 3; i++) {
					mHandler.sendEmptyMessage(1000);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
