package lab.aikibo.pbbinfo;

import java.io.IOException;
import java.util.Date;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import lab.aikibo.util.ConnectionInfo;

import android.app.Activity;
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
	private TextView tvNilaiTotal;
	private TextView tvNilaiSPPT;
	private TextView tvNilaiSKP;
	private TextView tvNilaiSKPKB;
	private TextView tvNilaiSKPSPOP;
	private TextView tvNilaiSTP;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_realisasi_tahunan);
		
		requestCode = getIntent().getExtras().getInt("requestcode");
		if(requestCode == RealisasiUI.RC_NOW) {
			Toast.makeText(this, "masuk ke update data", Toast.LENGTH_LONG);
			init_now();
		} else {
			Toast.makeText(this, "masuk ke periode data", Toast.LENGTH_LONG);
			init_periode();
		}
	}
	
	private void init_component() {
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		tvNilaiTotal = (TextView) findViewById(R.id.tvNilaiTotal);
		tvNilaiSPPT = (TextView) findViewById(R.id.tvNilaiSPPT);
		tvNilaiSKP = (TextView) findViewById(R.id.tvNilaiSKP);
		tvNilaiSKPKB = (TextView) findViewById(R.id.tvNilaiSKPKB);
		tvNilaiSKPSPOP = (TextView) findViewById(R.id.tvNilaiSKPSPOP);
		tvNilaiSTP = (TextView) findViewById(R.id.tvNilaiSTP);
	}
	
	private void init_now() {
		init_component();
		
		tvTitle.setText("Data Realisasi periode " + new Date());
		Task task = new Task();
		task.execute("");
	}
	
	private void init_periode() {
		init_component();
		tanggal = getIntent().getExtras().getInt("tanggal");
		bulan = getIntent().getExtras().getInt("bulan");
		tahun = getIntent().getExtras().getInt("tahun");
		tvTitle.setText("Data Realisasi periode " + tanggal + "/" + bulan + "/" + tahun);
		
		Task task = new Task();
		task.execute("");
	}
	
	public void setRequestCode(int rc) {
		requestCode = rc;
	}
	
	public int getRequestCode() {
		return requestCode;
	}
	
	private void getToast() {
		Toast.makeText(this, "ada kesalahan pengambilan data", Toast.LENGTH_LONG);
	}
	
	private class Task extends AsyncTask<String, Void, String> {
		SoapPrimitive response;
		
		@Override
		protected String doInBackground(String... arg0) {
			SoapObject request = null;
			if(requestCode == RealisasiUI.RC_NOW) {
				request = new SoapObject(ConnectionInfo.NAMESPACE, 
						ConnectionInfo.METHOD_NAME_REALISASI_SPPT_NOW);
			} else if(requestCode == RealisasiUI.RC_PERIODE) {
				request = new SoapObject(ConnectionInfo.NAMESPACE,
						ConnectionInfo.METHOD_NAME_REALISASI_SPPT_DATE);
				PropertyInfo prop1 = new PropertyInfo();
				prop1.setName("tanggal");
				prop1.setValue(tanggal);
				prop1.setType(int.class);
				PropertyInfo prop2 = new PropertyInfo();
				prop2.setName("bulan");
				prop2.setValue(bulan);
				prop2.setType(int.class);
				PropertyInfo prop3 = new PropertyInfo();
				prop3.setName("tahun");
				prop3.setValue(tahun);
				prop3.setType(int.class);
				request.addProperty(prop1);
				request.addProperty(prop2);
				request.addProperty(prop3);
			}
			//PropertyInfo prop = new PropertyInfo();
			//prop.setName("return");
			//prop.setType(String.class);
			//request.addProperty(prop);
			if(request == null) {
				getToast();
				return "";
			}
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = false;
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHttpTransport = new HttpTransportSE(ConnectionInfo.URL);
			
			try {
				androidHttpTransport.call(ConnectionInfo.SOAP_ACTION, envelope);
				response = (SoapPrimitive) envelope.getResponse();
			} catch(IOException ioe) {
				ioe.printStackTrace();
			} catch(XmlPullParserException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			tvNilaiTotal.setText("Sedang mengunduh...");
			tvNilaiSPPT.setText("Sedang mengunduh...");
			tvNilaiSKP.setText("Sedang mengunduh...");
			tvNilaiSKPKB.setText("Sedang mengunduh...");
			tvNilaiSKPSPOP.setText("Sedang mengunduh...");
			tvNilaiSTP.setText("Sedang mengunduh...");
		}
		
		@Override
		protected void onPostExecute(String result) {
			tvNilaiTotal.setText(response.toString());
			tvNilaiSPPT.setText(response.toString());
			tvNilaiSKP.setText("Rp. 0,-");
			tvNilaiSKPKB.setText("Rp. 0,-");
			tvNilaiSKPSPOP.setText("Rp. 0,-");
			tvNilaiSTP.setText("Rp. 0,-");
		}
		
	}

}
