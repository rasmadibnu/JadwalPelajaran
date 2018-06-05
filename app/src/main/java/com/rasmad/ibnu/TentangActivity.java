package com.rasmad.ibnu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.content.ContextCompat;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.widget.TextView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.text.method.LinkMovementMethod;
import java.util.List;
import java.util.ArrayList;

import com.rasmad.ibnu.adapters.AdapterCredit;
import com.rasmad.ibnu.adapters.AdapterMe;
import com.rasmad.ibnu.adapters.AdapterLibrary;
import com.rasmad.ibnu.items.ItemCredit;
import com.rasmad.ibnu.items.ItemLibrary;
import com.rasmad.ibnu.items.ItemMe;

import de.hdodenhof.circleimageview.CircleImageView;
import android.content.*;
import android.net.*;

public class TentangActivity extends AppCompatActivity
{
	private CoordinatorLayout coorLayout;
	private CollapsingToolbarLayout ctl;
	private Toolbar toolbar;
	private CircleImageView ic_peofile;
	private LinearLayoutManager llm_credit, llm_library, llm_me;
	private RecyclerView rv_credit, rv_library, rv_me;
	private AdapterCredit rvAdapterCredit;
	private List<ItemCredit> dataCredit;
	private AdapterLibrary rvAdapterLibrary;
	private List<ItemLibrary> dataLibrary;
	private AdapterMe rvAdapterMe;
	private List<ItemMe> dataMe;
	
	private List<ItemCredit> getItemCredit() {
		List<ItemCredit> itemCredit = new ArrayList<ItemCredit>();
		itemCredit.add(new ItemCredit("• AIDE", "https://www.android-ide.com/"));
		itemCredit.add(new ItemCredit("• Google", "https://developer.android.com/topic/libraries/support-library/"));
		itemCredit.add(new ItemCredit("• Material Design Guidelines", "https://material.io/guidelines/"));
		itemCredit.add(new ItemCredit("• Willi Ye aka Grarak", "https://github.com/Grarak/"));
		itemCredit.add(new ItemCredit("• Henning Dodenhof", "https://github.com/hdodenhof/"));
		itemCredit.add(new ItemCredit("• Wynoons", "https://www.instagram.com/wynonaptr/"));
		itemCredit.add(new ItemCredit("• Muhammad Azrial", "https://www.facebook.com/azrialmons/"));
		itemCredit.add(new ItemCredit("• Putriiiiiioiiiiiiiioooiioiioii", "https://www.instagram.com/dwiptr27"));
		return itemCredit;
	}
	
	private List<ItemLibrary> getItemLibrary() {
		List<ItemLibrary> itemLibrary = new ArrayList<ItemLibrary>();
		itemLibrary.add(new ItemLibrary("Support Library Compat", "Google", "https://developer.android.com/topic/libraries/support-library/"));
		itemLibrary.add(new ItemLibrary("appcompat-V7", "Google", "https://developer.android.com/topic/libraries/support-library/"));
		itemLibrary.add(new ItemLibrary("recyclerview-v7", "Google", "https://developer.android.com/topic/libraries/support-library/"));
		itemLibrary.add(new ItemLibrary("design", "Google", "https://developer.android.com/topic/libraries/support-library/"));
		itemLibrary.add(new ItemLibrary("cardview-v7", "Google", "https://developer.android.com/topic/libraries/support-library/"));
		itemLibrary.add(new ItemLibrary("Circle Image View", "Henning Dodenhof", "https://github.com/hdodenhof/CircleImageView"));
		return itemLibrary;
	}
	
	private List<ItemMe> getItemMe() {
		List<ItemMe> itemMe = new ArrayList<ItemMe>();
		itemMe.add(new ItemMe(R.drawable.ic_instagram, "Instagram", "@rasmadibnu", "http://www.instagram.com/rasmadibnu/"));
		itemMe.add(new ItemMe(R.drawable.ic_facebook_box, "Facebook", "@rasmadibnu", "http://www.facebook.com/rasmadibnu/"));
		itemMe.add(new ItemMe(R.drawable.ic_twitter_box, "Twitter", "@rasmadibnu", "http://www.twitter.com/rasmadibnu/"));
		itemMe.add(new ItemMe(R.drawable.ic_gmail, "Gmail", "rasmadibnua@gmail.com", "null"));
		itemMe.add(new ItemMe(R.drawable.ic_whatsapp, "WhatsApp", "0895330376636", "null"));
		return itemMe;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tentang);
		
		coorLayout = (CoordinatorLayout) findViewById(R.id.coorLayout);
		
		ctl = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
		ctl.setTitleEnabled(false);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle("Tentang");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.primary_dark));
		ic_peofile = (CircleImageView) findViewById(R.id.ic_profile);
		ic_peofile.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View p1)
			{
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/rasmadibnu")));
			}
		});
		TextView aboutme = (TextView) findViewById(R.id.about_me);
		aboutme.setText("i'm just random shit guy and still have a lot to learn android programming, the purpose of making this application only to help your learning activities. akmj");
		
		dataCredit = getItemCredit();
		rv_credit = (RecyclerView) findViewById(R.id.recyclerview_credit);
		llm_credit = new LinearLayoutManager(this);
		rv_credit.setNestedScrollingEnabled(false);
		rv_credit.setLayoutManager(llm_credit);
		rvAdapterCredit = new AdapterCredit(this, dataCredit);
		rv_credit.setAdapter(rvAdapterCredit);
		dataLibrary = getItemLibrary();
		rv_library = (RecyclerView) findViewById(R.id.recyclerview_library);
		llm_library = new LinearLayoutManager(this);
		rv_library.setNestedScrollingEnabled(false);
		rv_library.setLayoutManager(llm_library);
		rvAdapterLibrary = new AdapterLibrary(this, dataLibrary);
		rv_library.setAdapter(rvAdapterLibrary);
		dataMe = getItemMe();
		rv_me = (RecyclerView) findViewById(R.id.recyclerview_me);
		llm_me = new LinearLayoutManager(this);
		rv_me.setNestedScrollingEnabled(false);
		rv_me.setLayoutManager(llm_me);
		rvAdapterMe = new AdapterMe(this, dataMe);
		rv_me.setAdapter(rvAdapterMe);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.options_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId()){
			case android.R.id.home:
				finish();
				break;
			case R.id.donation:
				View donationV = getLayoutInflater().inflate(R.layout.dialog_donation, null);
				TextView donationTV = donationV.findViewById(R.id.donationTextView);
				donationTV.setText(R.string.paypal_message);
				donationTV.setMovementMethod(LinkMovementMethod.getInstance());
				AlertDialog.Builder donation = new AlertDialog.Builder(TentangActivity.this);
				donation.setTitle("Donasi");
				donation.setCancelable(false);
				donation.setView(donationV);
				donation.setPositiveButton("ok", null);
				donation.show();
				break;
			case R.id.changelog:
				AlertDialog.Builder cg = new AlertDialog.Builder(TentangActivity.this);
				cg.setTitle("Changelog V7.0");
				cg.setMessage("• Improvements UI\n• Bugs Fixes\n• Support for Android 8.1 (oreo)\n• Support Multi-Window\n• Get Stable Version\n• Night Mode\n• Adding some bugs to fix soon lol\n• Latest android support library version");
				cg.setPositiveButton("ok", null);
				cg.show();
				break;
			case R.id.licenses:
				WebView view = (WebView) LayoutInflater.from(TentangActivity.this).inflate(R.layout.webview_licenses, null);
				view.loadUrl("file:///android_asset/licenses.html");
				AlertDialog.Builder lc = new AlertDialog.Builder(TentangActivity.this);
				lc.setView(view);
				lc.setPositiveButton("ok", null);
				lc.show();
				break;
			case R.id.open_source:
				Snackbar.make(coorLayout, "Coming Soon", Snackbar.LENGTH_LONG).show();
		}
		return super.onOptionsItemSelected(item);
	}
}
