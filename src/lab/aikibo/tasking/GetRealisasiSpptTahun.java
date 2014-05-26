package lab.aikibo.tasking;

import java.util.Calendar;

import lab.aikibo.request.RealisasiSPPTRequest;
import lab.aikibo.util.Formator;

import android.os.AsyncTask;

public class GetRealisasiSpptTahun extends AsyncTask<String, Void, String> {
	private Calendar tglAkhir;
	private String thnPajak;
	private String result;
	
	public GetRealisasiSpptTahun() {
		tglAkhir = Calendar.getInstance();
		thnPajak = "" + tglAkhir.get(Calendar.YEAR);
	}
	
	public GetRealisasiSpptTahun(String thnPajak, String tglAkhir) throws Exception {
		this.thnPajak = thnPajak;
		this.tglAkhir = Formator.getTanggal(tglAkhir);
	}

	@Override
	protected String doInBackground(String... arg0) {
		try {
			result = new RealisasiSPPTRequest().getRealisasiPeriodic(thnPajak, Formator.getTanggal(tglAkhir));
		} catch (Exception e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}
	
	public String getResult() {
		return result;
	}

}
