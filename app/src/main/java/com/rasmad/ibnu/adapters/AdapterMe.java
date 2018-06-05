package com.rasmad.ibnu.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.content.Intent;
import android.content.ActivityNotFoundException;
import java.util.List;
import android.net.Uri;

import com.rasmad.ibnu.R;
import com.rasmad.ibnu.items.ItemMe;

public class AdapterMe extends RecyclerView.Adapter<AdapterMe.MeViewHolder> {
	private Context context;
	private List<ItemMe> itemList;

	public AdapterMe(Context context, List<ItemMe> itemList) {
		this.itemList = itemList;
		this.context = context;
	}

	@Override
	public int getItemCount() {
		return this.itemList.size();
	}

	@Override
	public MeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_me, parent, false);
		MeViewHolder vh = new MeViewHolder(view);
		return vh;
	}

	@Override
	public void onBindViewHolder(MeViewHolder holder, final int position) {
		holder.image.setImageResource(itemList.get(position).getImage());
		holder.title.setText(itemList.get(position).getTitle());
		holder.subtitle.setText(itemList.get(position).getSubtitle());
		holder.rootLayoout.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View p1)
			    {
					if(itemList.get(position).getTitle() == "Instagram"){
						try {
							Intent intent = new Intent(Intent.ACTION_VIEW);
							intent.setData(Uri.parse("http://instagram.com/_u/rasmadibnu"));
							intent.setPackage("com.instagram.android");
							context.startActivity(intent);
						} 
						catch (android.content.ActivityNotFoundException anfe) 
						{
							context.startActivity(new Intent(Intent.ACTION_VIEW,
													 Uri.parse("https://www.instagram.com/rasmadibnu")));
						}
					}
					else if(itemList.get(position).getTitle() == "Facebook"){
						String fbid = "100008617336634";
						try {
							context.getPackageManager()
								.getPackageInfo("com.facebook.katana", 0);
							Intent fb = new Intent(Intent.ACTION_VIEW,
											  Uri.parse("fb://profile/" + fbid));
							context.startActivity(fb);
						} catch (Exception e){

							Intent fb = new Intent(Intent.ACTION_VIEW,
											  Uri.parse("https://www.facebook.com/rasmadibnu"));
							context.startActivity(fb);
						}
					}
					else if(itemList.get(position).getTitle() == "Twitter"){
						try {
							context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=rasmadibnu")));
						}catch (Exception e) {
							context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/rasmadibnu")));
						}
					}
					else if(itemList.get(position).getTitle() == "Gmail"){
						Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
						emailIntent.setData(Uri.parse("mailto:rasmadibnua@gmail.com"));

						try {
							context.startActivity(emailIntent);
						} catch (ActivityNotFoundException e) {
							Toast.makeText(context, "Aplikasi tidak ditemukan", Toast.LENGTH_LONG).show();
						}
					}
					else if(itemList.get(position).getTitle() == "WhatsApp"){
						Intent wa = new Intent("android.intent.action.MAIN");
						try {
							wa.setAction(Intent.ACTION_VIEW);
							wa.setPackage("com.whatsapp");
							String url = "https://api.whatsapp.com/send?phone=" + "+62895330376636" + "&text=" + "Assalamualaikum :))))))))";
							wa.setData(Uri.parse(url));
							context.startActivity(wa);
						} catch (Exception e) {
							Toast.makeText(context, "WhastApp tidak terpasang", Toast.LENGTH_SHORT)
								.show();
					    }  
					}
				}
			});
	}

	public class MeViewHolder extends RecyclerView.ViewHolder
	{
		private ImageView image;
		private TextView title, subtitle;
		private RelativeLayout rootLayoout;

		public MeViewHolder(View view) {
			super(view);
			context = view.getContext();
			image = view.findViewById(R.id.imageme);
			title = view.findViewById(R.id.titleme);
			subtitle = view.findViewById(R.id.subtitleme);
			rootLayoout = view.findViewById(R.id.rootLayoutMe);
		}
	}
}
