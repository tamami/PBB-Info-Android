package lab.aikibo.pbbinfo;

import java.io.IOException;
import java.util.Date;

import org.ksoap2.SoapEnvelope;
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

public class StatistikUI extends Activity {
	
	private TextView tvTitle;
	
	private TextView tvCacahKetetapanSppt;
	private TextView tvNilaiKetetapanSppt;
	private TextView tvCacahRealisasiSppt;
	private TextView tvNilaiRealisasiSppt;
	private TextView tvProsentaseSppt;
	
	private TextView tvCacahKetetapanSkp;
	private TextView tvNilaiKetetapanSkp;
	private TextView tvCacahRealisasiSkp;
	private TextView tvNilaiRealisasiSkp;
	private TextView tvProsentaseSkp;
	
	private TextView tvCacahKetetapanSkpKb;
	private TextView tvNilaiKetetapanSkpKb;
	private TextView tvCacahRealisasiSkpKb;
	private TextView tvNilaiRealisasiSkpKb;
	private TextView tvProsentaseSkpKb;
	
	private TextView tvCacahKetetapanSkpSpop;
	private TextView tvNilaiKetetapanSkpSpop;
	private TextView tvCacahRealisasiSkpSpop;
	private TextView tvNilaiRealisasiSkpSpop;
	private TextView tvProsentaseSkpSpop;
	
	private TextView tvCacahKetetapanStp;
	private TextView tvNilaiKetetapanStp;
	private TextView tvCacahRealisasiStp;
	private TextView tvNilaiRealisasiStp;
	private TextView tvProsentaseStp;
	
	private String responseCacahKetetapanSppt; 
	private String responseNilaiKetetapanSppt;
	private String responseCacahRealisasiSppt;
	private String responseNilaiRealisasiSppt;
	private String responseProsentaseSppt;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistik);
		
		init();
		//tvTitle.setText("STATISTIK " + new Date());
	}
	
	private void init() {
		//tvTitle = (TextView) findViewById(R.id.tvTitle);
		
		tvCacahKetetapanSppt = (TextView) findViewById(R.id.tvCacahKetetapanSPPT);
		tvNilaiKetetapanSppt = (TextView) findViewById(R.id.tvNilaiKetetapanSPPT);
		tvCacahRealisasiSppt = (TextView) findViewById(R.id.tvCacahRealisasiSPPT);
		tvNilaiRealisasiSppt = (TextView) findViewById(R.id.tvNilaiRealisasiSPPT);
		tvProsentaseSppt = (TextView) findViewById(R.id.tvProsentaseSPPT);
		
		tvCacahKetetapanSkp = (TextView) findViewById(R.id.tvCacahKetetapanSKP);
		tvNilaiKetetapanSkp = (TextView) findViewById(R.id.tvNilaiKetetapanSKP);
		tvCacahRealisasiSkp = (TextView) findViewById(R.id.tvCacahRealisasiSKP);
		tvNilaiRealisasiSkp = (TextView) findViewById(R.id.tvNilaiRealisasiSKP);
		tvProsentaseSkp = (TextView) findViewById(R.id.tvProsentaseSKP);
		
		tvCacahKetetapanSkpKb = (TextView) findViewById(R.id.tvCacahKetetapanSKPKB);
		tvNilaiKetetapanSkpKb = (TextView) findViewById(R.id.tvNilaiKetetapanSKPKB);
		tvCacahRealisasiSkpKb = (TextView) findViewById(R.id.tvCacahRealisasiSKPKB);
		tvNilaiRealisasiSkpKb = (TextView) findViewById(R.id.tvNilaiRealisasiSKPKB);
		tvProsentaseSkpKb = (TextView) findViewById(R.id.tvProsentaseSKPKB);
		
		tvCacahKetetapanSkpSpop = (TextView) findViewById(R.id.tvCacahKetetapanSKPSPOP);
		tvNilaiKetetapanSkpSpop = (TextView) findViewById(R.id.tvNilaiKetetapanSKPSPOP);
		tvCacahRealisasiSkpSpop = (TextView) findViewById(R.id.tvCacahRealisasiSKPSPOP);
		tvNilaiRealisasiSkpSpop = (TextView) findViewById(R.id.tvNilaiRealisasiSKPSPOP);
		tvProsentaseSkpSpop = (TextView) findViewById(R.id.tvProsentaseSKPSPOP);
		
		tvCacahKetetapanStp = (TextView) findViewById(R.id.tvCacahKetetapanSTP);
		tvNilaiKetetapanStp = (TextView) findViewById(R.id.tvNilaiKetetapanSTP);
		tvCacahRealisasiStp = (TextView) findViewById(R.id.tvCacahRealisasiSTP);
		tvNilaiRealisasiStp = (TextView) findViewById(R.id.tvNilaiRealisasiSTP);
		tvProsentaseStp = (TextView) findViewById(R.id.tvProsentaseSTP);
		
		CacahKetetapanSpptTask cksTask = new CacahKetetapanSpptTask();
		cksTask.execute("");
		
		NilaiKetetapanSpptTask nksTask = new NilaiKetetapanSpptTask();
		nksTask.execute("");
		
		CacahRealisasiSpptTask crsTask = new CacahRealisasiSpptTask();
		crsTask.execute("");
		
		NilaiRealisasiSpptTask nrsTask = new NilaiRealisasiSpptTask();
		nrsTask.execute("");
		
		ProsentaseSpptTask psTask = new ProsentaseSpptTask();
		psTask.execute("");
		
		DefaultTask defaultTask = new DefaultTask();
		defaultTask.execute("");
	}
	
	private class DefaultTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... str) {
			
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			tvCacahKetetapanSkp.setText("Mengunduh");
			tvNilaiKetetapanSkp.setText("Mengunduh");
			tvCacahRealisasiSkp.setText("Mengunduh");
			tvNilaiRealisasiSkp.setText("Mengunduh");
			tvProsentaseSkp.setText("Mengunduh");
			
			tvCacahKetetapanSkpKb.setText("Mengunduh");
			tvNilaiKetetapanSkpKb.setText("Mengunduh");
			tvCacahRealisasiSkpKb.setText("Mengunduh");
			tvNilaiRealisasiSkpKb.setText("Mengunduh");
			tvProsentaseSkpKb.setText("Mengunduh");
			
			tvCacahKetetapanSkpSpop.setText("Mengunduh");
			tvNilaiKetetapanSkpSpop.setText("Mengunduh");
			tvCacahRealisasiSkpSpop.setText("Mengunduh");
			tvNilaiRealisasiSkpSpop.setText("Mengunduh");
			tvProsentaseSkpSpop.setText("Mengunduh");
			
			tvCacahKetetapanStp.setText("Mengunduh");
			tvNilaiKetetapanStp.setText("Mengunduh");
			tvCacahRealisasiStp.setText("Mengunduh");
			tvNilaiRealisasiStp.setText("Mengunduh");
			tvProsentaseStp.setText("Mengunduh");
		}
		
		@Override
		protected void onPostExecute(String str) {
			tvCacahKetetapanSkp.setText("NIHIL");
			tvNilaiKetetapanSkp.setText("NIHIL");
			tvCacahRealisasiSkp.setText("NIHIL");
			tvNilaiRealisasiSkp.setText("NIHIL");
			tvProsentaseSkp.setText("NIHIL");
			
			tvCacahKetetapanSkpKb.setText("NIHIL");
			tvNilaiKetetapanSkpKb.setText("NIHIL");
			tvCacahRealisasiSkpKb.setText("NIHIL");
			tvNilaiRealisasiSkpKb.setText("NIHIL");
			tvProsentaseSkpKb.setText("NIHIL");
			
			tvCacahKetetapanSkpSpop.setText("NIHIL");
			tvNilaiKetetapanSkpSpop.setText("NIHIL");
			tvCacahRealisasiSkpSpop.setText("NIHIL");
			tvNilaiRealisasiSkpSpop.setText("NIHIL");
			tvProsentaseSkpSpop.setText("NIHIL");
			
			tvCacahKetetapanStp.setText("NIHIL");
			tvNilaiKetetapanStp.setText("NIHIL");
			tvCacahRealisasiStp.setText("NIHIL");
			tvNilaiRealisasiStp.setText("NIHIL");
			tvProsentaseStp.setText("NIHIL");
		}
	}
	
	private class ProsentaseSpptTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... str) {
			SoapObject request = new SoapObject(ConnectionInfo.NAMESPACE,
					ConnectionInfo.METHOD_NAME_PROSENTASE_REALISASI_SPPT_NOW);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = false;
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHtse = new HttpTransportSE(ConnectionInfo.URL);
			try {
				androidHtse.call(ConnectionInfo.SOAP_ACTION, envelope);
				SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
				responseProsentaseSppt = response.toString();
			} catch(IOException ioe) {
			} catch(XmlPullParserException xppe) {}
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			tvProsentaseSppt.setText("Mengunduh");
		}
		
		@Override
		protected void onPostExecute(String str) {
			tvProsentaseSppt.setText(responseProsentaseSppt + " %");
		}
	}
	
	private class NilaiRealisasiSpptTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... str) {
			SoapObject request = new SoapObject(ConnectionInfo.NAMESPACE,
					ConnectionInfo.METHOD_NAME_REALISASI_SPPT_NOW);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = false;
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHtse = new HttpTransportSE(ConnectionInfo.URL);
			try {
				androidHtse.call(ConnectionInfo.SOAP_ACTION, envelope);
				SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
				responseNilaiRealisasiSppt = response.toString();
			} catch(IOException ioe) {
			} catch(XmlPullParserException xppe) {}
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			tvNilaiRealisasiSppt.setText("Mengunduh");
		}
		
		@Override
		protected void onPostExecute(String str) {
			tvNilaiRealisasiSppt.setText(responseNilaiRealisasiSppt);
		}
	}
	
	private class CacahRealisasiSpptTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... str) {
			SoapPrimitive response = new SoapPrimitive(ConnectionInfo.NAMESPACE, 
					"cacahRealisasiSppt", "-");
			responseCacahRealisasiSppt = response.toString();
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			tvCacahRealisasiSppt.setText("Mengunduh");
		}
		
		@Override
		protected void onPostExecute(String str) {
			tvCacahRealisasiSppt.setText(responseCacahRealisasiSppt);
		}
	}
	
	private class NilaiKetetapanSpptTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... str) {
			SoapObject request = new SoapObject(ConnectionInfo.NAMESPACE, 
					ConnectionInfo.METHOD_NAME_NILAI_SPPT_NOW);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = false;
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHttpTransport = new HttpTransportSE(ConnectionInfo.URL);
			
			try {
				androidHttpTransport.call(ConnectionInfo.SOAP_ACTION, envelope);
				SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
				responseNilaiKetetapanSppt = response.toString();
			} catch(IOException ioe) {	
			} catch(XmlPullParserException xppe) {}
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			tvNilaiKetetapanSppt.setText("Mengunduh");
		}
		
		@Override
		protected void onPostExecute(String str) {
			tvNilaiKetetapanSppt.setText(responseNilaiKetetapanSppt);
		}
	}
	
	private class CacahKetetapanSpptTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... arg0) {
			SoapObject request = new SoapObject(ConnectionInfo.NAMESPACE,
					ConnectionInfo.METHOD_NAME_KETETAPAN_SPPT_NOW);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = false;
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHttpTransport = new HttpTransportSE(ConnectionInfo.URL);
			
			try {
				androidHttpTransport.call(ConnectionInfo.SOAP_ACTION, envelope);
				SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
				responseCacahKetetapanSppt = response.toString();
			} catch(IOException ioe) {
				ioe.printStackTrace();
			} catch(XmlPullParserException xppe) {
				xppe.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			tvCacahKetetapanSppt.setText("Mengunduh");
		}
		
		@Override
		protected void onPostExecute(String result) {
			tvCacahKetetapanSppt.setText(responseCacahKetetapanSppt.toString());
			//getDefaultValueForAllExceptSppt();
		}
		
	}
	
	private void preGetData() {
		tvCacahKetetapanSppt.setText("Mengunduh");
		tvNilaiKetetapanSppt.setText("Mengunduh");
		tvCacahRealisasiSppt.setText("Mengunduh");
		tvNilaiRealisasiSppt.setText("Mengunduh");
		tvProsentaseSppt.setText("Mengunduh");
		
		tvCacahKetetapanSkp.setText("Mengunduh");
		tvNilaiKetetapanSkp.setText("Mengunduh");
		tvCacahRealisasiSkp.setText("Mengunduh");
		tvNilaiRealisasiSkp.setText("Mengunduh");
		tvProsentaseSkp.setText("Mengunduh");
		
		tvCacahKetetapanSkpKb.setText("Mengunduh");
		tvNilaiKetetapanSkpKb.setText("Mengunduh");
		tvCacahRealisasiSkpKb.setText("Mengunduh");
		tvNilaiRealisasiSkpKb.setText("Mengunduh");
		tvProsentaseSkpKb.setText("Mengunduh");
		
		tvCacahKetetapanSkpSpop.setText("Mengunduh");
		tvNilaiKetetapanSkpSpop.setText("Mengunduh");
		tvCacahRealisasiSkpSpop.setText("Mengunduh");
		tvNilaiRealisasiSkpSpop.setText("Mengunduh");
		tvProsentaseSkpSpop.setText("Mengunduh");
		
		tvCacahKetetapanStp.setText("Mengunduh");
		tvNilaiKetetapanStp.setText("Mengunduh");
		tvCacahRealisasiStp.setText("Mengunduh");
		tvNilaiRealisasiStp.setText("Mengunduh");
		tvProsentaseStp.setText("Mengunduh");
	}
	
	private void getValueForCacahKetetapanSppt() {
		
	}
	
	private void getValueForNilaiKetetapanSppt() {
		SoapObject request = new SoapObject(ConnectionInfo.NAMESPACE,
				ConnectionInfo.METHOD_NAME_NILAI_SPPT_NOW);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = false;
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(ConnectionInfo.URL);
		
		try {
			androidHttpTransport.call(ConnectionInfo.SOAP_ACTION, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			responseNilaiKetetapanSppt = response.toString();
		} catch(IOException ioe) {
			//Toast.makeText(this, "kesalahan IO", Toast.LENGTH_LONG);
		} catch(XmlPullParserException xppe) {
			//Toast.makeText(this,  "Kesalahan parsing xml", Toast.LENGTH_LONG);
		}
	}
	
	private void getValueForCacahRealisasiSppt() {
		SoapPrimitive response = new SoapPrimitive(ConnectionInfo.METHOD_NAME_KETETAPAN_SPPT_NOW, 
				"cacahRealisasiSppt", "-");
		responseCacahRealisasiSppt = response.toString();
	}
	
	private void getValueForNilaiRealisasiSppt() {
		SoapObject request = new SoapObject(ConnectionInfo.NAMESPACE,
				ConnectionInfo.METHOD_NAME_REALISASI_SPPT_NOW);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = false;
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(ConnectionInfo.URL);
		
		try {
			androidHttpTransport.call(ConnectionInfo.SOAP_ACTION, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			responseNilaiRealisasiSppt = response.toString();
		} catch(IOException ioe) {
			//Toast.makeText(this, "kesalahan IO", Toast.LENGTH_LONG);
		} catch(XmlPullParserException xppe) {
			//Toast.makeText(this,  "Kesalahan parsing xml", Toast.LENGTH_LONG);
		}
	}
	
	private void getValueForProsentaseSppt() {
		SoapObject request = new SoapObject(ConnectionInfo.NAMESPACE,
				ConnectionInfo.METHOD_NAME_PROSENTASE_REALISASI_SPPT_NOW);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = false;
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(ConnectionInfo.URL);
		
		try {
			androidHttpTransport.call(ConnectionInfo.SOAP_ACTION, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			responseProsentaseSppt = response.toString();
		} catch(IOException ioe) {
			//Toast.makeText(this, "kesalahan IO", Toast.LENGTH_LONG);
		} catch(XmlPullParserException xppe) {
			//Toast.makeText(this,  "Kesalahan parsing xml", Toast.LENGTH_LONG);
		}
	}
	
	private void getDefaultValueForAllExceptSppt() {
		tvCacahKetetapanSkp.setText("NIHIL");
		tvNilaiKetetapanSkp.setText("NIHIL");
		tvCacahRealisasiSkp.setText("NIHIL");
		tvNilaiRealisasiSkp.setText("NIHIL");
		tvProsentaseSkp.setText("NIHIL");
		
		tvCacahKetetapanSkpKb.setText("NIHIL");
		tvNilaiKetetapanSkpKb.setText("NIHIL");
		tvCacahRealisasiSkpKb.setText("NIHIL");
		tvNilaiRealisasiSkpKb.setText("NIHIL");
		tvProsentaseSkpKb.setText("NIHIL");
		
		tvCacahKetetapanSkpSpop.setText("NIHIL");
		tvNilaiKetetapanSkpSpop.setText("NIHIL");
		tvCacahRealisasiSkpSpop.setText("NIHIL");
		tvNilaiRealisasiSkpSpop.setText("NIHIL");
		tvProsentaseSkpSpop.setText("NIHIL");
		
		tvCacahKetetapanStp.setText("NIHIL");
		tvNilaiKetetapanStp.setText("NIHIL");
		tvCacahRealisasiStp.setText("NIHIL");
		tvNilaiRealisasiStp.setText("NIHIL");
		tvProsentaseStp.setText("NIHIL");
	}

}
