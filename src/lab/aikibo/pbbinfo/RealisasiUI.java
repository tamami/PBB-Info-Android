package lab.aikibo.pbbinfo;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.xmlpull.v1.XmlPullParserException;

import lab.aikibo.request.RealisasiSPPTRequest;
import lab.aikibo.tasking.GetRealisasiSpptTahun;
import lab.aikibo.util.ConnectionInfo;
import lab.aikibo.util.Formator;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class RealisasiUI extends Activity {
	
	public static final int RC_NOW = 0;
	public static final int RC_PERIODE = 1;
	
	private int requestCode;
	private int tanggal;
	private int bulan;
	private int tahun;
	private TextView tvTitle;
	private TextView tvThnN;
	private TextView tvThnN_1;
	private TextView tvThnN_2;
	private TextView tvThnN_3;
	private TextView tvThnN_4;
	private TextView tvThnN_5;
	private TextView tvNilaiTotal;
	private TextView tvNilaiThnN;
	private TextView tvNilaiThnN_1;
	private TextView tvNilaiThnN_2;
	private TextView tvNilaiThnN_3;
	private TextView tvNilaiThnN_4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_realisasi_tahunan);
		
		requestCode = getIntent().getExtras().getInt("requestcode");
		if(requestCode == RealisasiUI.RC_NOW) {
			init_now();
		} else {
			init_periode();
		}
	}
	
	private void init_component() {
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		tvNilaiTotal = (TextView) findViewById(R.id.tvNilaiTotal);
		tvNilaiThnN = (TextView) findViewById(R.id.tvNilaiThnN);
		tvNilaiThnN_1 = (TextView) findViewById(R.id.tvNilaiThnN_1);
		tvNilaiThnN_2 = (TextView) findViewById(R.id.tvNilaiThnN_2);
		tvNilaiThnN_3 = (TextView) findViewById(R.id.tvNilaiThnN_3);
		tvNilaiThnN_4 = (TextView) findViewById(R.id.tvNilaiThnN_4);
		tvThnN = (TextView) findViewById(R.id.tvThnN);
		tvThnN_1 = (TextView) findViewById(R.id.tvThnN_1);
		tvThnN_2 = (TextView) findViewById(R.id.tvThnN_2);
		tvThnN_3 = (TextView) findViewById(R.id.tvThnN_3);
		tvThnN_4 = (TextView) findViewById(R.id.tvThnN_4);
	}
	
	private void init_now() {
		init_component();
		Calendar cal = Calendar.getInstance();
		//cal.set(Calendar.YEAR, 1995);
		
		tvTitle.setText("Data Realisasi periode " + new Date());
		tvThnN.setText("  Tahun " + cal.get(Calendar.YEAR));
		tvThnN_1.setText("  Tahun " + (cal.get(Calendar.YEAR)-1));
		tvThnN_2.setText("  Tahun " + (cal.get(Calendar.YEAR)-2));
		tvThnN_3.setText("  Tahun " + (cal.get(Calendar.YEAR)-3));
		tvThnN_4.setText("  Tahun " + (cal.get(Calendar.YEAR)-4));
		
		Task task = new Task(Formator.getTanggal(cal));
		task.execute("");
		
	}
	
	private void init_periode() {
		init_component();
		tanggal = getIntent().getExtras().getInt("tanggal");
		bulan = getIntent().getExtras().getInt("bulan");
		tahun = getIntent().getExtras().getInt("tahun");
		tvTitle.setText("Data Realisasi periode " + tanggal + "/" + bulan + "/" + tahun);
		tvNilaiThnN.setText("  Tahun " + tahun);
		tvNilaiThnN_1.setText("  Tahun " + (tahun-1));
		tvNilaiThnN_2.setText("  Tahun " + (tahun-2));
		tvNilaiThnN_3.setText("  Tahun " + (tahun-3));
		tvNilaiThnN_4.setText("  Tahun " + (tahun-4));
		
		Task task = new Task(Formator.getTanggal(tanggal, bulan, tahun));
		task.execute("");
	}
	
	public void setRequestCode(int rc) {
		requestCode = rc;
	}
	
	public int getRequestCode() {
		return requestCode;
	}
	
	private void getToast() {
		//Toast.makeText(this, "ada kesalahan pengambilan data", Toast.LENGTH_LONG);
	}
	
	private class Task extends AsyncTask<String, Void, String> {
		String valueFromServer;
		String resultTotal;
		String resultThnN;
		String resultThnN_1;
		String resultThnN_2;
		String resultThnN_3;
		String resultThnN_4;
		String tgl;
		
		public Task(String tgl) {
			this.tgl = tgl;
		}
		
		@Override
		protected String doInBackground(String... arg0) {
			if(requestCode == RealisasiUI.RC_NOW) {
				try {
					resultTotal = new RealisasiSPPTRequest().getRealisasiToday();
					resultThnN = new RealisasiSPPTRequest().getRealisasiPeriodic(Formator.getTahun(tgl), tgl);
					int thn = Integer.parseInt(Formator.getTahun(tgl));
					resultThnN_1 = new RealisasiSPPTRequest().getRealisasiPeriodic("" + (thn - 1), tgl);
					resultThnN_2 = new RealisasiSPPTRequest().getRealisasiPeriodic("" + (thn - 2), tgl);
					resultThnN_3 = new RealisasiSPPTRequest().getRealisasiPeriodic("" + (thn - 3), tgl);
					resultThnN_4 = new RealisasiSPPTRequest().getRealisasiPeriodic("" + (thn - 4), tgl);
				} catch (ResourceException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch(Exception e) {}
				
			} else if(requestCode == RealisasiUI.RC_PERIODE) {
				try {
					String tglAkhir = Formator.getTanggal(tanggal, bulan, tahun);
					resultTotal = new RealisasiSPPTRequest().getRealisasiTotalPerTanggal(tglAkhir);
					resultThnN = new RealisasiSPPTRequest().getRealisasiPeriodic(Formator.getTahun(tglAkhir), tglAkhir);
					int thnN = Integer.parseInt(Formator.getTahun(tglAkhir));
					resultThnN_1 = new RealisasiSPPTRequest().getRealisasiPeriodic("" + (thnN - 1), tglAkhir);
				} catch (ResourceException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch(Exception e) {}
			}
						
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			tvNilaiTotal.setText("Sedang mengunduh...");
			tvNilaiThnN.setText("Sedang mengunduh...");
			tvNilaiThnN_1.setText("Sedang mengunduh...");
			tvNilaiThnN_2.setText("Sedang mengunduh...");
			tvNilaiThnN_3.setText("Sedang mengunduh...");
			tvNilaiThnN_4.setText("Sedang mengunduh...");
		}
		
		@Override
		protected void onPostExecute(String result) {
			try {
				tvNilaiTotal.setText(this.resultTotal);
				tvNilaiThnN.setText(this.resultThnN);
				tvNilaiThnN_1.setText(this.resultThnN_1);
				tvNilaiThnN_2.setText(this.resultThnN_2);
				tvNilaiThnN_3.setText(this.resultThnN_3);
				tvNilaiThnN_4.setText(this.resultThnN_4);
			} catch(NullPointerException npe) {
				getToast();
			}
		}
		
	}

}
