package lab.aikibo.pbbinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;

public class RequestTanggalUI extends Activity {
	
	private DatePicker dpPeriode;
	private Button btnProses;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_tanggal);
		
		init();
	}
	
	private void init() {
		dpPeriode = (DatePicker) findViewById(R.id.datePicker1);
		btnProses = (Button) findViewById(R.id.btn_get_realisasi);
		
		btnProses.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				btnProsesOnClick();
			}
		});
	}
	
	private void btnProsesOnClick() {
		Intent intent = new Intent();
		String packageName = "lab.aikibo.pbbinfo";
		String className = "lab.aikibo.pbbinfo.RealisasiUI";
		intent.setClassName(packageName, className);
		intent.putExtra("requestcode", RealisasiUI.RC_PERIODE);
		intent.putExtra("tanggal", dpPeriode.getDayOfMonth());
		intent.putExtra("bulan", dpPeriode.getMonth() + 1);
		intent.putExtra("tahun", dpPeriode.getYear());
		startActivity(intent);
	}

}
