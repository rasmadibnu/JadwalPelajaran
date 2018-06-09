package com.rasmad.ibnu.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import java.util.List;

import com.rasmad.ibnu.R;
import com.rasmad.ibnu.items.ItemCredit;

public class AdapterCredit extends RecyclerView.Adapter<AdapterCredit.CreditViewHolder> {
	private Context context;
	private List<ItemCredit> itemList;

	public AdapterCredit(Context context, List<ItemCredit> itemList) {
		this.itemList = itemList;
		this.context = context;
	}

	@Override
	public int getItemCount() {
		return this.itemList.size();
	}

	@Override
	public CreditViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_credit, parent, false);
		CreditViewHolder vh = new CreditViewHolder(view);
		return vh;
	}

	@Override
	public void onBindViewHolder(CreditViewHolder holder, final int position) {
		holder.name.setText(itemList.get(position).getName());
		holder.name.setPaintFlags(holder.name.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
		holder.name.setOnClickListener(new View.OnClickListener() {

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

	public class CreditViewHolder extends RecyclerView.ViewHolder
	{
		public TextView name;

		public CreditViewHolder(View view) {
			super(view);
			context = view.getContext();
			name = view.findViewById(R.id.credit_text);
		}
	}
}
