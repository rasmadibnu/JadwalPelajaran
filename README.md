# JadwalPelajaran
Aplikasi jadwal pelajaran yang bersifat insert data (not editable) ini di buat hanya untuk belajar dan mengisi waktu tidak untuk di perjual belikan atau di jadikan komersil, Project ini di buat sejak tahun 2017 dari versi BETA hingga Stable di tahun 2018 dan di versi Stable ini saya menjadikan project ini Open Source untuk berbagi dan mungkin bisa di terapkan untuk sekolah atau kelas kalian. Untuk menerapkan aplikasi ini di sekolah atau kelas kalian harus mengubah data terlebih dahulu agar sesuai dengan jadwal sekolah atau kelas kalian. Untuk cara mengubahnya bisa di liat di bawah ini

**Gunakan Mode Desktop Untuk Melihat Lebih Jelas**

# Tampilan Aplikasi
| Light | another Light | Dark | another Dark
|:-:|:-:|:-:|:-:|
| ![Light1] | ![Light2] | ![Dark1] | ![Dark2] |

# Cara Mengubah Data Jadwal Pelajaran dan Jadwal Istirahat
Di dalam aplikasi terdapat 6 Tab sesusai hari sekolah Senin s/d Sabtu perTab tersebut terdiri dari 6 Fragment yang terbentuk dengan program Java, Jika ingin mengubah Jadwal kita harus mengubah script di dalam file java tersebut letaknya ada di [app/src/java/com/rasmad/ibnu/tabs][Tabs]

| Nama File | Nama Tab |
|:-:|:-:|
| Tab1.java | Senin |
| Tab2.java | Selasa |
| Tab3.java | Rabu |
| Tab4.java | Kamis |
| Tab5.java | Jum'at |
| Tab6.java | Sabtu |

Jika ingin mengubah isi jadwal harus sesuai dengan nama file dan tab di atas. Contoh jika anda ingin mengubah jadwal di hari senin anda harus mengubah file [Tab1.Java][Tab1], jika anda ingin mengubah jadwal di hari selasa maka anda harus mengubah file [Tab2.java][Tab2] dan begitu seterusnya. <br />
## Sebagai contoh mengubah jadwal di hari Senin
Buka [Tab1.java][Tab1] <br />
Anda akan menemukan function [getJadwal()][getJadwal] <br />
Anda bisa mengubah jadwal hari senin di dalam function tersebut <br />
```java
private ArrayList<ItemJadwal> getJadwal() {
		ArrayList<ItemJadwal> itemJadwal = new ArrayList<ItemJadwal>();
		// Ubah jadwal hari senin disini
		return itemJadwal;
	}
```
**Cara Menambah Jadwal**
```java
itemJadwal.add(new ItemJadwal.add(ItemJadwal.MAPEL_MODEL, "", "mapel", "jamMulai - jamSelesai", "namaGuru", "ruangan", "seragam"));
```
**Cara Menambah Jadwal Istirahat**
```java
itemJadwal.add(new ItemJadwal(ItemJadwal.ISTIRAHAT_MODEL, "Jam Istirahat dimulai - Jam Istirahat Selesai (Total Waktu Istirahat)", "", "", "", "", ""));	
```
Contoh jika di tambahkan di dalam function [getJadwal()][getJadwal] akan menjadi seperti ini
```java
private ArrayList<ItemJadwal> getJadwal() {
		ArrayList<ItemJadwal> itemJadwal = new ArrayList<ItemJadwal>();
		itemJadwal.add(new ItemJadwal(ItemJadwal.MAPEL_MODEL, "","Pemrograman Desktop", "07:00 - 11:50", "Fery Updi, S.Kom, M.Kom", "WS. RPL", "Wearpack"));
		itemJadwal.add(new ItemJadwal(ItemJadwal.ISTIRAHAT_MODEL, "11:50 - 12:30 (40 Menit)", "", "", "", "", ""));
		return itemJadwal;
	}
```
Lalu compile dan hasilnya akan menjadi seperti ini<br />
![EXM](https://github.com/rasmadibnu/JadwalPelajaran/blob/master/assets/example1.jpg)<br />
Gunakan cara berikut untuk mengubah jadwal di hari selasa, rabu, dst

**Keterangan**
> ItemJadwal.MAPEL_MODEL untuk cardview jadwal <br />
> ItemJadwal.ISTIRAHAT_MODEL untuk text istirahat

# Mengubah nama dan nomor telepon wali kelas, ketua kelas, dan sekolah di menu drawer
Ini berada di bagian menu drawer aplikasi jika men-slide dari kiri ke kanan akan muncul menu drawer kontak dari wali kelas, ketua kelas, dan sekolah ketika menu itu di klik akan memanggil android.intent.action.DIAL alias akan memanggil nomor telepon yang kita siapkan di dalam file [MainActivity.java][MainActivity] <br />
## Cara mengubah nama 
Buka [nav_items.xml][navItems] di [app/src/main/res/menu][Menu] <br />
Anda akan menemukan 6 item menu yang anda harus ubah cukup item 1 - 3 saja lebih tepatnya di baris ke 12, 17, 22. <br />
>Baris 12 untuk nama wali kelas <br />
>Baris 17 untuk nama ketua kelas <br />
>Baris 22 untuk nama sekolah
```xml
<item 
      android:id="@+id/nav_walikelas" 
      android:icon="@drawable/ic_phone"
      android:title="Ahsin S.Pdi (Wali Kelas)" />
<item 
      android:id="@+id/nav_ketuakelas" 
      android:icon="@drawable/ic_phone"
      android:title="Bagas Afrizal (Ketua Kelas)" />
<item 
      android:id="@+id/nav_sekolah"
      android:icon="@drawable/ic_phone"
      android:title="SMK Yuppentek 2" />
```
Ubah di bagian android:title="nama" <br />
## Cara mengubah nomor telepon dan nama sekolah
Buka [MainActivity.java][MainActivity] di [app/src/main/java/com/rasmad/ibnu/](https://github.com/rasmadibnu/JadwalPelajaran/tree/master/app/src/main/java/com/rasmad/ibnu/) <br />
```java
private String no_walas = "+6281385871440"; //Nomor telepon wali kelas
private String no_km = "+6283813802564"; //Nomor telepon ketua kelas
private String no_sekolah = "+62215980876"; //Nomor telepon sekolah
private String nama_sekolah = "SMK Yuppentek 2"; //Nama sekolah
```
Anda akan menemukan script di atas pada baris 79 dan silahkan ubah sesuai nomor telepon walas, km, dan sekolah anda, Dan jangan lupa untuk mengubah nama sekolah sesuai nama sekolah anda.

**Jika kurang jelas bisa kontak saya di bawah**

# Contact Me
- [Facebook: @rasmadbnu](https://www.facebook.com/rasmasibnu/)
- [Instagram: @rasmadibnu](https://www.instagram.com/rasmadibnu/)
- [Twitter: @rasmadibnu](https://twitter.com/rasmadibnu)
- [Gmail: rasmadibnua@gmail.com](https://www.google.com/gmail/)

# Happy Coding :)))

[MainActivity]: <https://github.com/rasmadibnu/JadwalPelajaran/tree/master/app/src/main/java/com/rasmad/ibnu/MainActivity.java>
[Tabs]: <https://github.com/rasmadibnu/JadwalPelajaran/tree/master/app/src/main/java/com/rasmad/ibnu/tabs>
[Tab1]: <https://github.com/rasmadibnu/JadwalPelajaran/tree/master/app/src/main/java/com/rasmad/ibnu/tabs/Tab1.java>
[Tab2]: <https://github.com/rasmadibnu/JadwalPelajaran/tree/master/app/src/main/java/com/rasmad/ibnu/tabs/Tab2.java>
[getJadwal]: <https://github.com/rasmadibnu/JadwalPelajaran/tree/master/app/src/main/java/com/rasmad/ibnu/tabs/Tab1.java#L23>
[Menu]: <https://github.com/rasmadibnu/JadwalPelajaran/tree/master/app/src/main/res/menu/>
[navItems]: <https://github.com/rasmadibnu/JadwalPelajaran/tree/master/app/src/main/res/menu/nav_items.xml>
[Light1]: <https://github.com/rasmadibnu/JadwalPelajaran/blob/master/assets/screener_redmi4x_light(1).png>
[Light2]: <https://github.com/rasmadibnu/JadwalPelajaran/blob/master/assets/screener_redmi4x_light(2).png>
[Dark1]: <https://github.com/rasmadibnu/JadwalPelajaran/blob/master/assets/screener_redmi4x_dark(1).png>
[Dark2]: <https://github.com/rasmadibnu/JadwalPelajaran/blob/master/assets/screener_redmi4x_dark(2).png>
