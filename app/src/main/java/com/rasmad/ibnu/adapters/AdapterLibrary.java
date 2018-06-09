package com.rasmad.ibnu.adapters;

import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AlertDialog;
import android.content.Context;
import java.util.List;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import com.rasmad.ibnu.R;
import com.rasmad.ibnu.items.ItemLibrary;

public class AdapterLibrary extends RecyclerView.Adapter<AdapterLibrary.LibraryViewHolder> {
	private Context context;
	private List<ItemLibrary> itemList;

	public AdapterLibrary(Context context, List<ItemLibrary> itemList) {
		this.itemList = itemList;
		this.context = context;
	}

	@Override
	public int getItemCount() {
		return this.itemList.size();
	}

	@Override
	public LibraryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_library, parent, false);
		LibraryViewHolder vh = new LibraryViewHolder(view);
		return vh;
	}

	@Override
	public void onBindViewHolder(LibraryViewHolder holder, final int position) {
		holder.libName.setText(itemList.get(position).getNameLib());
		holder.libOwn.setText(itemList.get(position).getOwnLib());
		holder.rootLayoout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View p1)
			{
				AlertDialog.Builder ad = new AlertDialog.Builder(context);
				ad.setTitle("Link");
				ad.setMessage("Buka url " + itemList.get(position).getLink());
				ad.setNegativeButton("batal", null);
				ad.setPositiveButton("ya", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface p1, int p2)
					{
						context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(itemList.get(position).getLink())));
					}
				});
				ad.show();
			}
		});
	}

	public class LibraryViewHolder extends RecyclerView.ViewHolder
	{
		private TextView libName, libOwn;
		private RelativeLayout rootLayoout;

		public LibraryViewHolder(View view) {
			super(view);
			context = view.getContext();
			libName = view.findViewById(R.id.libName);
			libOwn = view.findViewById(R.id.libOwn);
			rootLayoout = view.findViewById(R.id.rootLayoutLibrary);
		}
	}
}
