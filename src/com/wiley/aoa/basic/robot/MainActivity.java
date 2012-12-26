package com.wiley.aoa.basic.robot;

import java.io.IOException;

import android.app.Activity;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.view.View;

import com.wiley.wrox.basic.robot.R;
import com.wiley.wroxaccessories.UsbConnection12;
import com.wiley.wroxaccessories.WroxAccessory;

/**
 * This example is written for the normal version of the ADK, if you want to
 * change this to the backported version (Android OS 2.3.4) of the ADK or
 * Bluetooth then simple change the connection type (and if you're using USB,
 * make sure to use to correct UsbManager)
 */
public class MainActivity extends Activity {

	/** The Wrox Accessory class, handles communication for us */
	private WroxAccessory mAccessory;

	/**
	 * The USB Manager, change this to com.android.hardware.UsbManager if you
	 * want the SDK 12 version of the Accessory
	 */
	private UsbManager mUsbManager;

	/**
	 * The Connection object, need to change this too if you want to use another
	 * type of accessory.
	 */
	private UsbConnection12 connection;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 1. Get a reference to the UsbManager (there's only one, so you don't
		// instantiate it)
		mUsbManager = (UsbManager) getSystemService(USB_SERVICE);

		// 2. Create the Connection object
		connection = new UsbConnection12(this, mUsbManager);

		// 3. Instantiate the WroxAccessory
		mAccessory = new WroxAccessory(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 4. Initiate a connection to the accessory, using the connection
		// object
		try {
			mAccessory.connect(WroxAccessory.USB_ACCESSORY_10, connection);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// 5. Disconnect the accessory
		try {
			mAccessory.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop(View v) {
		byte[] buffer = new byte[1];
		buffer[0] = 0;

		try {
			mAccessory.publish("mv", buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void forward(View v) {

		byte[] buffer = new byte[1];
		buffer[0] = 1;

		try {
			mAccessory.publish("mv", buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void right(View v) {
		byte[] buffer = new byte[1];
		buffer[0] = 2;

		try {
			mAccessory.publish("mv", buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void backward(View v) {
		byte[] buffer = new byte[1];
		buffer[0] = 3;

		try {
			mAccessory.publish("mv", buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void left(View v) {
		byte[] buffer = new byte[1];
		buffer[0] = 4;

		try {
			mAccessory.publish("mv", buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
