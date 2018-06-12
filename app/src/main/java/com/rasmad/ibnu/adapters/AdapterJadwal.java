package com.rasmad.ibnu.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import java.util.ArrayList;

import com.rasmad.ibnu.R;
import com.rasmad.ibnu.items.ItemJadwal;

public class AdapterJadwal extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	int total_types;
	private Context context;
	private ArrayList<ItemJadwal> itemList;

	public class JadwalTypeViewHolder extends RecyclerView.ViewHolder {
		public TextView tvGuru;
		public TextView tvJam;
		public TextView tvMapel;
		public TextView tvRuangan;
		public TextView tvSeragam;

		public JadwalTypeViewHolder(View view) {
			super(view);
			tvMapel = view.findViewById(R.id.tvMapel);
			tvJam = view.findViewById(R.id.tvJam);
			tvGuru = view.findViewById(R.id.tvGuru);
			tvRuangan = view.findViewById(R.id.tvRuangan);
			tvSeragam = view.findViewById(R.id.tvSeragam);
		}
	}
	
	public class IstirahatTypeViewHolder extends RecyclerView.ViewHolder {
		public TextView ket;
		
		public IstirahatTypeViewHolder(View view) {
			super(view);
			ket = view.findViewById(R.id.txtIstirahat);
		}
	}
	public AdapterJadwal(Context context, ArrayList<ItemJadwal> itemList) {
		this.itemList = itemList;
		this.context = context;
		total_types = itemList.size();
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view;
		switch(viewType) {
			case ItemJadwal.MAPEL_MODEL:
				view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_jadwal, parent, false);
				return new JadwalTypeViewHolder(view);
			case ItemJadwal.ISTIRAHAT_MODEL:
				view = LayoutInflater.from(parent.getContext()).inflate(R.layout.txt_istirahat, parent, false);
				return new IstirahatTypeViewHolder(view);
				
		}
		return null;
	}

	@Override
	public int getItemViewType(int position)
	{
		switch(itemList.get(position).type) {
			case 0:
				return ItemJadwal.MAPEL_MODEL;
			case 1:
				return ItemJadwal.ISTIRAHAT_MODEL;
			default:
				return -1;
		}
	}
	
	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		ItemJadwal object = itemList.get(position);
		if(object != null) {
			switch(object.type){
				case ItemJadwal.MAPEL_MODEL:
					((JadwalTypeViewHolder) holder).tvMapel.setText(itemList.get(position).getMapel());
					((JadwalTypeViewHolder) holder).tvJam.setText(itemList.get(position).getJam());
					((JadwalTypeViewHolder) holder).tvGuru.setText(itemList.get(position).getGuru());
					((JadwalTypeViewHolder) holder).tvRuangan.setText(itemList.get(position).getRuangan());
					((JadwalTypeViewHolder) holder).tvSeragam.setText(itemList.get(position).getSeragam());
					break;
				case ItemJadwal.ISTIRAHAT_MODEL:
					((IstirahatTypeViewHolder) holder).ket.setText("Istirahat " + itemList.get(position).getKet());
			}
		}
	}
	
	@Override
	public int getItemCount() {
		return this.itemList.size();
	}
	
}
