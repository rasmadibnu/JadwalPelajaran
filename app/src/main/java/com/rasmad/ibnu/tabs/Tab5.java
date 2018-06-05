package com.rasmad.ibnu.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import com.rasmad.ibnu.R;
import com.rasmad.ibnu.adapters.AdapterJadwal;
import com.rasmad.ibnu.items.ItemJadwal;

public class Tab5 extends Fragment {
	private ArrayList<ItemJadwal> datajadwal;
	private LinearLayoutManager llm;
	private RecyclerView recyclerview;
	private AdapterJadwal rvAdapter;

	private ArrayList<ItemJadwal> getJadwal() {
		/*
		 Ubah jadwal anda disini sesuai jadwal yamg anda ingingkan
		 ItemJadwal.MAPEL_MODEL untuk tipe cardview jadwal
		 ItemJadwal.ISTIRAHAT_MODEL untuk tipe istirahat

		 Isi tipe model dengan benar jika kirang jelas lihat contoh di bawah ini
		 */
		ArrayList<ItemJadwal> itemJadwal = new ArrayList<ItemJadwal>();
		itemJadwal.add(new ItemJadwal(ItemJadwal.MAPEL_MODEL, "","Pemrograman Desktop", "07:00 - 11:50", "Fery Updi, S.Kom, M.Kom", "WS. RPL", "Wearpack"));
		itemJadwal.add(new ItemJadwal(ItemJadwal.ISTIRAHAT_MODEL, "Istirahat 11:50 - 12:30 (40 Menit)", "", "", "", "", ""));
		itemJadwal.add(new ItemJadwal(ItemJadwal.MAPEL_MODEL, "", "Bahasa Indonesia", "12:30 - 14:00", "M.S Endang Roestini, S.Pd", "R3.09", "Putih Abu - abu"));
		itemJadwal.add(new ItemJadwal(ItemJadwal.MAPEL_MODEL, "", "Bahasa Inggris", "14:00 - 15:30", "Risty Paradilla, S.Pd", "R3.09", "Putih Abu - abu"));
		itemJadwal.add(new ItemJadwal(ItemJadwal.ISTIRAHAT_MODEL,"Istirahat 15:30 - 15:45 (15 Menit)", "", "", "", "", ""));
		itemJadwal.add(new ItemJadwal(ItemJadwal.MAPEL_MODEL, "", "Sejarah Indonesia", "15:45 - 17:15", "M. Pairan, S.Pd", "R3.09", "Putih Abu - abu"));
		return itemJadwal;
	}

	@Override
	public void onCreate(Bundle var1) {
		super.onCreate(var1);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_tab1, container, false);

		datajadwal = getJadwal();
	    llm = new LinearLayoutManager(this.getActivity());

		recyclerview = view.findViewById(R.id.recyclerview);
		recyclerview.setLayoutManager(llm);
		rvAdapter = new AdapterJadwal(getActivity(), datajadwal);
		recyclerview.setAdapter(rvAdapter);
		return view;
	}
}
