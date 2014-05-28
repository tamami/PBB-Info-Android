package lab.aikibo.pbbinfo;

import java.io.IOException;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.restlet.resource.ResourceException;

import lab.aikibo.request.SpptInfoRequest;
import lab.aikibo.util.Formator;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.SpannableString;
import android.widget.TextView;
import android.widget.Toast;

public class SpptInfoUI extends Activity {
	
	private TextView tvNop;
	private TextView tvNamaWp;
	private TextView tvStatusLunas;
	private SpannableString nop;
	private SpannableString thnPajak;
	private String namaWp;
	private String statusLunas;
	private String errMessage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sppt_info);
		
		init();
	}
	
	private void init() {
		tvNop = (TextView) findViewById(R.id.tv_nop_info);
		tvNamaWp = (TextView) findViewById(R.id.tv_nama_wp_info);
		tvStatusLunas = (TextView) findViewById(R.id.tv_status_lunas);
		
		nop = (SpannableString) getIntent().getExtras().get("nop");
		thnPajak = (SpannableString) getIntent().getExtras().get("thnPajak");
		
		SpptInfoTask task = new SpptInfoTask();
		task.execute("");
	}
	
	private class SpptInfoTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... arg0) {
			try {
				namaWp = new SpptInfoRequest().getNamaWp(nop.toString(), thnPajak.toString());
				statusLunas = new SpptInfoRequest().getStatusLunas(nop.toString(), thnPajak.toString());
			} catch (ResourceException e) {
				errMessage = "Kesalahan di modul Resource, hubungi developer.";
				namaWp = null;
				e.printStackTrace();
			} catch (IOException e) {
				errMessage = "Kesalahan di modul IO, hubungi developer.";
				namaWp = null;
				e.printStackTrace();
			} catch (JSONException e) {
				errMessage = "Kesalahan di modul JSON, hubungi developer.";
				namaWp = null;
				e.printStackTrace();
			} catch (ParseException e) {
				errMessage = "Kesalahan di modul Parsing JSON, hubungi developer.";
				namaWp = null;
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			tvNop.setText("Sedang parsing...");
			tvNamaWp.setText("Sedang mengunduh...");
			tvStatusLunas.setText("Sedang mengunduh...");
		}
		
		@Override
		protected void onPostExecute(String result) {
			try {
				tvNop.setText(Formator.getNopFormatted(nop.toString()));
			} catch (Exception e) {
				errMessage = "Panjang nop tidak sesuai, silahkan cek kembali.";
				namaWp = null;
				e.printStackTrace();
			}
			tvNamaWp.setText(namaWp);
			tvStatusLunas.setText(statusLunas);
		}
		
	}

}
