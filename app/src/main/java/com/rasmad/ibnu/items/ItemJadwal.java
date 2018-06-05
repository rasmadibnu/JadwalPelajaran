package com.rasmad.ibnu.items;

public class ItemJadwal {
	public int type;
	private String guru;
	private String jam;
	private String mapel;
	private String ruangan;
	private String seragam;
	private String ket;
	public static final int MAPEL_MODEL=0;
	public static final int ISTIRAHAT_MODEL=1;

	public ItemJadwal(int type, String ket, String mapel, String jam, String guru, String ruangan, String seragam) {
		this.type = type;
		this.ket = ket;
		this.mapel = mapel;
		this.jam = jam;
		this.guru = guru;
		this.ruangan = ruangan;
		this.seragam = seragam;
	}

	public String getGuru() {
		return guru;
	}

	public String getJam() {
		return jam;
	}

	public String getMapel() {
		return mapel;
	}

	public String getRuangan() {
		return ruangan;
	}

	public String getSeragam() {
		return seragam;
	}
	
	public String getKet() {
		return ket;
	}

	public void setGuru(String guru) {
		this.guru = guru;
	}

	public void setJam(String jam) {
		this.jam = jam;
	}

	public void setMapel(String mapel) {
		this.mapel = mapel;
	}

	public void setRuangan(String ruangan) {
		this.ruangan = ruangan;
	}

	public void setSeragam(String seragam) {
		this.seragam = seragam;
	}
	
	public void setKet(String ket) {
		this.ket = ket;
	}
}
