package lab.aikibo.util;

import java.text.NumberFormat;
import java.util.Calendar;

public class Formator {
	
	// hasilnya tanggal awal tahun dengan format DD-MM-YYYY
	public static String getTglAwal(String tanggal) throws Exception {
		if(tanggal.length() != 10) {
			throw new Exception("Panjang karakter tidak sesuai format DD-MM-YYYY");
		}
		String pembatas1 = tanggal.substring(2,3);
		String pembatas2 = tanggal.substring(5,6);
		if(!pembatas1.equals("-") || !pembatas2.equals("-")) {
			throw new Exception("Panjang karakter tidak sesuai format DD-MM-YYYY");
		} else {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_YEAR, 1);
			cal.set(Calendar.YEAR, Integer.parseInt(tanggal.substring(6,10)));
			
			String tglAwal = getTanggal(cal);
			return tglAwal;
		}
	}
	
	// hasilnya tanggal berformat DD-MM-YYYY
	public static String getTanggal(Calendar cal) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMinimumIntegerDigits(2);
		String tgl = nf.format(cal.get(Calendar.DAY_OF_MONTH)) + "-" +
			nf.format(cal.get(Calendar.MONTH) + 1 ) + "-" +
			cal.get(Calendar.YEAR);
		return tgl;
	}
	
	// hasil nya tanggal berformat DD-MM-YYYY
	public static String getTanggal(int tgl, int bulan, int tahun) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMinimumIntegerDigits(2);
		String tanggal = nf.format(tgl) + "-" + nf.format(bulan) + "-" + tahun;
		return tanggal;
	}
	
	// hasilnya tanggal dengan tipe Calendar
	public static Calendar getTanggal(String tanggal) throws Exception {
		if(tanggal.length() != 10) {
			throw new Exception("Panjang karakter tidak sesuai format DD-MM-YYYY");
		}
		String pembatas1 = tanggal.substring(2,3);
		String pembatas2 = tanggal.substring(5,6);
		if(!pembatas1.equals("-") || !pembatas2.equals("-")) {
			throw new Exception("Panjang karakter tidak sesuai format DD-MM-YYYY");
		} else {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tanggal.substring(0,2)));
			cal.set(Calendar.MONTH, Integer.parseInt(tanggal.substring(3,5)));
			cal.set(Calendar.YEAR, Integer.parseInt(tanggal.substring(6,10)));
			
			return cal;
		}
	}
	
	public static String getTahun(String tanggal) throws Exception {
		if(tanggal.length() != 10) {
			throw new Exception("Panjang karakter tidak sesuai format DD-MM-YYYY");
		}
		String pembatas1 = tanggal.substring(2,3);
		String pembatas2 = tanggal.substring(5,6);
		if(!pembatas1.equals("-") || !pembatas2.equals("-")) {
			throw new Exception("Panjang karakter tidak sesuai format DD-MM-YYYY");
		} else {
			return tanggal.substring(6,10);
		}
	}
	
	// mengubah barisan nop tanpa tanda baca, jadi nop berformat SPPT
	public static String getNopFormatted(String nop) throws Exception {
		if(nop.length() != 18) {
			throw new Exception("Panjang karakter tidak sesuai dengan panjang NOP di database");
		}
		
		return nop.substring(0,2) + "." + nop.substring(2,4) + "." +
				nop.substring(4,7) + "." + nop.substring(7,10) + "." + 
				nop.substring(10,13) + "-" + nop.substring(13,17) + "." + 
				nop.substring(17,18);
	}

}
