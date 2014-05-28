package lab.aikibo.pbbinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ParameterSppt extends Activity {
	
	private EditText edNop;
	private EditText edThnPajak;
	private Button btnProses;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parameter_sppt);
		
		init();
	}
	
	private void init() {
		edNop = (EditText) findViewById(R.id.ed_nop);
		edThnPajak = (EditText) findViewById(R.id.ed_thn_pajak);
		btnProses = (Button) findViewById(R.id.btnProses);
		
		btnProses.setOnClickListener(new OnClickListener() {
			@Override 
			public void onClick(View view) {
				btnProsesOnClick();
			}
		});
	}
	
	public void btnProsesOnClick() {
		Intent intent = new Intent();
		String packageName = "lab.aikibo.pbbinfo";
		String className = "lab.aikibo.pbbinfo.SpptInfoUI";
		intent.setClassName(packageName, className);
		intent.putExtra("nop", edNop.getText());
		intent.putExtra("thnPajak", edThnPajak.getText());
		startActivity(intent);
	}

}
