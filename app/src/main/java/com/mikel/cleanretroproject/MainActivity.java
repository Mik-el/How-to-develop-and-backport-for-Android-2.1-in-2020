package com.mikel.cleanretroproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


/* Clean Retro Project by Mik-el
https://github.com/Mik-el/How-to-develop-and-backport-for-Android-2.1-in-2020/
*/

public class MainActivity extends Activity {

	//Buttons
	private Button button1;
	private Button button2;

	public void onCreate(Bundle savedInstanceState) {
		final Context context = this;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		button1 = (Button) findViewById(R.id.button1_id);
		button2 = (Button) findViewById(R.id.button2_id);



		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				String site1 = "https://mikelweb.altervista.org/redir/crp_app";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(site1));
				startActivity(i);

			}

		});

		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				String site2 = "https://paypal.me/donationMikel";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(site2));
				startActivity(i);

			}

		});

	}

}