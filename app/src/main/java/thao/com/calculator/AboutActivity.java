package thao.com.calculator;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends Activity {
	TextView text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		text = (TextView)findViewById(R.id.aboutText);
	//	text.setTextColor(Color.BLUE);
		setContentView(R.layout.about);
	}
}
