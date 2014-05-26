package lab.aikibo.pbbinfo;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button btnRealisasiUpdate;
	private Button btnRealisasiPerTanggal;
	private Button btnStatistik;
	
	
	private String result;
	//private SoapPrimitive response;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
	}
	
	private void init() {
		btnRealisasiPerTanggal = (Button) findViewById(R.id.btn_get_realisasi_per_tanggal);
		btnRealisasiUpdate = (Button) findViewById(R.id.btn_get_realisasi_updated);
		btnStatistik = (Button) findViewById(R.id.btn_get_statistik);
		
		btnRealisasiPerTanggal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				btnRealisasiPerTanggalOnClick();
			}
		});
		btnRealisasiUpdate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				btnRealisasiUpdateOnClick();
			}
		});
		btnStatistik.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				btnStatistikOnClick();
			}
		});
	}
	
	private void btnStatistikOnClick() {
		Intent intent = new Intent();
		String packageName = "lab.aikibo.pbbinfo";
		String className = "lab.aikibo.pbbinfo.StatistikUI";
		intent.setClassName(packageName, className);
		startActivity(intent);
	}
	
	private void btnRealisasiPerTanggalOnClick() {
		Intent intent = new Intent();
		String packageName = "lab.aikibo.pbbinfo";
		String className = "lab.aikibo.pbbinfo.RequestTanggalUI";
		intent.setClassName(packageName, className);
		intent.putExtra("requestcode", RealisasiUI.RC_PERIODE);
		startActivity(intent);
	}
	
	private void btnRealisasiUpdateOnClick() {
		Intent intent = new Intent();
		String packageName = "lab.aikibo.pbbinfo";
		String className = "lab.aikibo.pbbinfo.RealisasiUI";
		intent.setClassName(packageName, className);
		intent.putExtra("requestcode", RealisasiUI.RC_NOW);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
