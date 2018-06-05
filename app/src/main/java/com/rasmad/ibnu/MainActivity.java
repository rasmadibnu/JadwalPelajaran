package com.rasmad.ibnu;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.MenuItem;
import android.view.Menu;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CheckBox;
import android.util.Base64;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rasmad.ibnu.database.DataBaseHelper;
import com.rasmad.ibnu.tabs.Tab1;
import com.rasmad.ibnu.tabs.Tab2;
import com.rasmad.ibnu.tabs.Tab3;
import com.rasmad.ibnu.tabs.Tab4;
import com.rasmad.ibnu.tabs.Tab5;
import com.rasmad.ibnu.tabs.Tab6;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity 
{
	private CoordinatorLayout coorLayout;
	private Toolbar toolbar;
	private DrawerLayout drawerLayout;
	private NavigationView navigationView;
	private TabLayout tabLayout;
	private ViewPager viewPager;
	private CircleImageView profilepict;
	private TextView username, userschool;
	private SwitchCompat switchCompat;
	private DataBaseHelper myDb;
	private SharedPreferences sharedPreferences;
	private String encodedImage;
	static final int DOCUMENT_REQUEST = 1;
	static final int CAMERA_REQUEST = 2;
	static final int STORAGE_PERMISSION_CODE = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		cekNightMode();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		coorLayout = (CoordinatorLayout) findViewById(R.id.coorLayout);
		
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle(R.string.app_name);
		
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
	
		navigationView = (NavigationView) findViewById(R.id.navigation_view);
		navigationHeader();
		navigationItemAction();
		
		viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
		
		welcomeDialog();
		cekDP();
		cekUsername();
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.version, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId()){
			case R.id.version:
				Snackbar.make(coorLayout, "Version 7.0", Snackbar.LENGTH_LONG).show();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == RESULT_OK) {
			try {
				Bitmap decode = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(data.getData()));
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				decode.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
				encodedImage = Base64.encodeToString(outputStream.toByteArray(), 0);
				sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putString("my_image", encodedImage);
				editor.commit();
				byte[] dataimage = Base64.decode(this.encodedImage, 0);
				Bitmap image = BitmapFactory.decodeByteArray(dataimage, 0, dataimage.length);
				profilepict.setImageBitmap(image);
			} catch (FileNotFoundException error) {
				error.printStackTrace();
			} catch (IOException error) {
				error.printStackTrace();
			}
		}
		
		else if(requestCode == 2 && resultCode == RESULT_OK) {
			Bitmap bitmap = (Bitmap) data.getExtras().get("data");
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
			encodedImage = Base64.encodeToString(outputStream.toByteArray(), 0);
			sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putString("my_image", encodedImage);
			editor.commit();
			byte[] dataImage = Base64.decode(encodedImage, Base64.DEFAULT);
			Bitmap image = BitmapFactory.decodeByteArray(dataImage, 0, dataImage.length);
			profilepict.setImageBitmap(image);
		}
	}
	
	private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1(), "Senin");
		adapter.addFragment(new Tab2(), "Selasa");
		adapter.addFragment(new Tab3(), "Rabu");
		adapter.addFragment(new Tab4(), "Kamis");
		adapter.addFragment(new Tab5(), "Jum'at");
		adapter.addFragment(new Tab6(), "Sabtu");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
	
	private void cekNightMode()
	{
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
	    String nightmode = String.valueOf(sharedPreferences.getBoolean("night_mode", false));
		if(nightmode == "true") {
			AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
		} else if(nightmode == "false") {
			AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
		} else if(nightmode == null) {
			AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
		}
	}

	private void cekDP()
	{
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		encodedImage = sharedPreferences.getString("my_image", "");
		if(!encodedImage.equalsIgnoreCase("")) {
			byte[] decode = Base64.decode(encodedImage, Base64.DEFAULT);
			Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
			profilepict.setImageBitmap(bitmap);
		} else {
			profilepict.setImageResource(R.drawable.default_dp);
		}
	}

	private void cekUsername()
	{
		myDb = new DataBaseHelper(this);
		Cursor res = myDb.getUsername();
		if(res != null && res.getCount() > 0) {
			while(res.moveToNext()) {
				username.setText(res.getString(1));
				userschool.setText("SMK Yuppentek 2");
			}
		} else {
			username.setText("Masukan nama anda...");
			userschool.setText("SMK Yuppentek 2");
			myDb.insertUsername("Masukan nama anda...");
		}
	}
	
	private void welcomeDialog() {
		AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_checkbox, null);
        CheckBox mCheckBox = mView.findViewById(R.id.checkBox);
        mBuilder.setTitle("Congratulations");
        mBuilder.setMessage("Selamat kepada teman-temanku yang berhasil naik ke kelas XI(11), Selamat juga untuk yang mendapatkan rangking 10 besar untuk yang belum dapat jangan menyerah karena perjalanan kita masih panjang.\n\nEducation is a progressive discovery of our own ignorance. ~Will Durant");
        mBuilder.setView(mView);
		mBuilder.setCancelable(false);
        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
					dialogInterface.dismiss();
				}
			});

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
					if(compoundButton.isChecked()){
						storeDialogStatus(true);
					}else{
						storeDialogStatus(false);
					}
				}
			});

        if(getDialogStatus()){
            mDialog.hide();
        }else{
            mDialog.show();
        }
	}
	
	private void storeDialogStatus(boolean isChecked){
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor mEditor = sharedPreferences.edit();
        mEditor.putBoolean("dialogStatus", isChecked);
        mEditor.apply();
    }

    private boolean getDialogStatus(){
        return sharedPreferences.getBoolean("dialogStatus", false);
    }
	
	private void profilpictOnClick()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("Pilihan");
		String[] items = {"Ambil foto profil dari galeri", "Ambil foto profil dari kamera", "Hapus foto profil"};
		builder.setItems(items, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch (which) {
						case 0:
							Intent gallery = new Intent();
							gallery.setType("image/*");
							gallery.setAction("android.intent.action.GET_CONTENT");
							gallery.addCategory("android.intent.category.OPENABLE");
							MainActivity.this.startActivityForResult(gallery, 1);
							break;
						case 1:
							Intent camera = new Intent("android.media.action.IMAGE_CAPTURE");
							MainActivity.this.startActivityForResult(camera, 2);
							break;
						case 2:
							sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
							SharedPreferences.Editor editor = sharedPreferences.edit();
							editor.remove("my_image");
							editor.commit();
							profilepict.setImageResource(R.drawable.default_dp);
							Toast.makeText(MainActivity.this, "Foto profil berhasil di hapus", Toast.LENGTH_SHORT).show();
							break;
					}
				}
			});
		builder.show();
	}	
	
	private void navigationHeader()
	{
		View headerView = navigationView.getHeaderView(0);

		profilepict = headerView.findViewById(R.id.displaypict);

		profilepict.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View p1)
				{
					if (Build.VERSION.SDK_INT >= 23) {
						if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
							profilpictOnClick();
						} else {
							requestStoragePermission();
						}
					} else {
						AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
						builder.setTitle("Pilihan");
						String[] items = {"Ambil foto profil dari galeri", "Ambil foto profil dari kamera", "Hapus foto profil"};
						builder.setItems(items, new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									switch (which) {
										case 0:
											Intent gallery = new Intent();
											gallery.setType("image/*");
											gallery.setAction("android.intent.action.GET_CONTENT");
											gallery.addCategory("android.intent.category.OPENABLE");
											MainActivity.this.startActivityForResult(gallery, 1);
											break;
										case 1:
											Intent camera = new Intent("android.media.action.IMAGE_CAPTURE");
											MainActivity.this.startActivityForResult(camera, 2);
											break;
										case 2:
											sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
											SharedPreferences.Editor editor = sharedPreferences.edit();
											editor.clear();
											editor.commit();
											profilepict.setImageResource(R.drawable.default_dp);
											Toast.makeText(MainActivity.this, "Foto profil berhasil di hapus", Toast.LENGTH_SHORT).show();
											break;
									}
								}
							});
						builder.show();
					}

				}

			});

		username = headerView.findViewById(R.id.username);
		userschool = headerView.findViewById(R.id.userschool);

		username.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View p1)
				{
					AlertDialog.Builder usernameDialog = new AlertDialog.Builder(MainActivity.this, R.style.AppTheme_AlertDialogTheme);
					LayoutInflater inflater = getLayoutInflater();
					View dialogView = inflater.inflate(R.layout.dialog_input_username, null);
					usernameDialog.setView(dialogView);
					final EditText etUsername = dialogView.findViewById(R.id.etUsername);
					usernameDialog.setTitle("Nama Pengguna");
					usernameDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface p1, int p2)
							{
								myDb.updateUsername("1", etUsername.getText().toString());
								username.setText(etUsername.getText().toString());
							}
						});
					usernameDialog.setNegativeButton("batal", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface p1, int p2)
							{
							}
						});
					AlertDialog alertDialog = usernameDialog.create();
					alertDialog.show();
				}
			});
	}
	
	private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, 
																Manifest.permission.READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this, R.style.AppTheme_AlertDialogTheme)
				.setTitle("Izin ditolak")
				.setMessage("Izin ditolak, Aplikasi ini butuh izin akses penyimpanan karena aplikasi ini akan mengambil foto dari penyimpanan anda")
				.setPositiveButton("ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						ActivityCompat.requestPermissions(MainActivity.this, 
														  new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
					}
				})
				.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				})
				.create().show();

        } else {
            ActivityCompat.requestPermissions(this, 
											  new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Snackbar sb = Snackbar.make(coorLayout, "Berhasil diizinkan", Snackbar.LENGTH_LONG);
				sb.setCallback(new Snackbar.Callback() {
					@Override
					public void onDismissed(Snackbar snackbar, int event) {
						profilpictOnClick();
					}

					@Override
					public void onShown(Snackbar snackbar) {
							
					}
				});
				sb.show();
            } else {
                Snackbar.make(coorLayout, "Izin ditolak", Snackbar.LENGTH_LONG).show();
            }
        }
    }
	
	private void navigationItemAction()
	{
		navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
				@Override
				public boolean onNavigationItemSelected(MenuItem menuItem) {
					if(menuItem.isChecked()) menuItem.setChecked(false);
					else menuItem.setChecked(true);
					
					switch (menuItem.getItemId()){
						case R.id.nav_walikelas:
							drawerLayout.closeDrawers();
							Intent walas = new Intent("android.intent.action.DIAL");
							walas.setData(Uri.parse("tel:+6281385871440"));
							startActivity(walas);
							break;
						case R.id.nav_ketuakelas:
							drawerLayout.closeDrawers();
							Intent km = new Intent("android.intent.action.DIAL");
							km.setData(Uri.parse("tel:+6283813802564"));
							startActivity(km);
							break;
						case R.id.nav_sekolah:
							drawerLayout.closeDrawers();
							Intent sekolah = new Intent("android.intent.action.DIAL");
							sekolah.setData(Uri.parse("tel:+62215980876"));
							startActivity(sekolah);
							break;
						case R.id.tentang:
							drawerLayout.closeDrawers();
							Intent tentang = new Intent(MainActivity.this, TentangActivity.class);
							startActivity(tentang);
							break;
						case R.id.modemalam:
							drawerLayout.closeDrawers();
							break;
						case R.id.keluar:
							drawerLayout.closeDrawers();
							AlertDialog.Builder keluar = new AlertDialog.Builder(MainActivity.this, R.style.AppTheme_AlertDialogTheme);
							keluar.setTitle("Keluar");
							keluar.setMessage("Anda yakin ingin keluar dari aplikasi ini?");
							keluar.setPositiveButton("ok", new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface p1, int p2)
									{
										finish();
									}					
								});
							keluar.setNegativeButton("batal", new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface p1, int p2)
									{

									}
								});
							keluar.show();
					}
					return true;
				}
			});

		switchCompat = (SwitchCompat) navigationView.getMenu().findItem(R.id.modemalam).getActionView();
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
	    String nightmode = String.valueOf(sharedPreferences.getBoolean("night_mode", false));
		if(nightmode == "true") {
			switchCompat.setChecked(true);
		} else if(nightmode == "false"){
			switchCompat.setChecked(false);
		}

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
						sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
						SharedPreferences.Editor editor = sharedPreferences.edit();
						editor.putBoolean("night_mode", isChecked);
						editor.apply();
						recreate();
					} else {
						AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
						sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
						SharedPreferences.Editor editor = sharedPreferences.edit();
						editor.putBoolean("night_mode", false);
						editor.apply();
						recreate();
					}
				}
			});
	}
}
