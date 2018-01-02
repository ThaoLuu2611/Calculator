package thao.com.calculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.EmptyStackException;
import java.util.Locale;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    GridView grid;
    int i;
    final int BACKGROUND_IMAGE = 1;
    final int BACKGROUND_MUSIC = 2;
    final int EXIT = 3;
    final int ABOUT = 4;
    final int BIGCITY = 1;
    final int JURASSIC = 2;
    final int WALTDISNEY = 3;
    EditText editText;
    TextToSpeech tts;
    Typeface t;
    Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bAdd,bSub,bMul,bDiv,bRst,bClear;
    AnalogClock clock;
    LinearLayout mLayout;
    String editView="";
    String editValue;
    String number="";
    String[] expression;
    ArrayAdapter adapter;
    double kq;
    String result;
    int flag = 0;
    int flag1 = 0;
    int flag2=0;
    Stack<String> exp = new Stack<String>();
    Stack<String> value = new Stack<String>();
    Stack<String> oper = new Stack<String>();
    String[] str;
    String v1,v2;
    double n1,n2;
    Button[] arrayButton = new Button[10];
    TextView about;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,(ViewGroup)findViewById(R.id.toast_layout_id));
        Toast toast = Toast.makeText(getApplicationContext(), "test",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0,0);
        toast.setView(layout);
        toast.show();
        tts = new TextToSpeech(this,MyTTSListener);
        editText = (EditText)findViewById(R.id.text);
        mLayout = (LinearLayout)findViewById(R.id.layoutid);
        MediaController mc = new MediaController(this);
        VideoView vv = (VideoView) findViewById(R.id.video01);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/cartoon");
        vv.setVideoURI(uri);
        vv.setMediaController(mc);
        vv.requestFocus();
        vv.start();
        about = (TextView)findViewById(R.id.aboutText);
        int []idB = {R.id.button0,R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7
                ,R.id.button8,R.id.button9};
        // arrayButton[0] =(Button)findViewById(R.id.button0);

        clock = (AnalogClock)findViewById(R.id.clock);
        registerForContextMenu(clock);
        b0 = (Button)findViewById(R.id.button0);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);
        b5 = (Button)findViewById(R.id.button5);
        b6 = (Button)findViewById(R.id.button6);
        b7 = (Button)findViewById(R.id.button7);
        b8 = (Button)findViewById(R.id.button8);
        b9 = (Button)findViewById(R.id.button9);

        bAdd = (Button)findViewById(R.id.buttonAdd);
        bSub = (Button)findViewById(R.id.buttonSub);
        bMul = (Button)findViewById(R.id.buttonMul);
        bDiv = (Button)findViewById(R.id.buttonDiv);
        bRst = (Button)findViewById(R.id.buttonResult);
        bClear = (Button)findViewById(R.id.buttonClear);
        b0.setOnClickListener(click);
        b1.setOnClickListener(click);
        b2.setOnClickListener(click);
        b3.setOnClickListener(click);
        b4.setOnClickListener(click);
        b5.setOnClickListener(click);
        b6.setOnClickListener(click);
        b7.setOnClickListener(click);
        b8.setOnClickListener(click);
        b9.setOnClickListener(click);
        bAdd.setOnClickListener(operator);
        bSub.setOnClickListener(operator);
        bMul.setOnClickListener(operator);
        bDiv.setOnClickListener(operator);
        bRst.setOnClickListener(operator);
        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                editView="";
                editValue="";


                //editText.setText(Integer.toString(str.length));
                //str = new String[0];
                while(!exp.isEmpty())
                    exp.pop();
                //editText.setText(Integer.toString(exp.size()));

                editText.setText("");
            }
        });
        Drawable a1 = b1.getBackground();
        a1.setAlpha(200);
        Drawable a2 = b2.getBackground();
        a2.setAlpha(200);
        Drawable a3 = b3.getBackground();
        a3.setAlpha(200);
        Drawable a4 = b4.getBackground();
        a4.setAlpha(200);
        Drawable a5 = b5.getBackground();
        a5.setAlpha(200);
        Drawable a6 = b6.getBackground();
        a6.setAlpha(200);
        Drawable a7 = b7.getBackground();
        a7.setAlpha(200);
        Drawable a8 = b8.getBackground();
        a8.setAlpha(200);
        Drawable a9 = b9.getBackground();
        a9.setAlpha(200);
        Drawable a0 = b0.getBackground();
        a0.setAlpha(200);
        Drawable aAdd = bAdd.getBackground();
        aAdd.setAlpha(200);
        Drawable aSub = bSub.getBackground();
        aSub.setAlpha(200);
        Drawable aMul = bMul.getBackground();
        aMul.setAlpha(200);
        Drawable aDiv = bDiv.getBackground();
        aDiv.setAlpha(200);
        Drawable aRst = bRst.getBackground();
        aRst.setAlpha(200);
        Drawable aClear = bClear.getBackground();
        aClear.setAlpha(200);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        menu.add(0,BACKGROUND_IMAGE,0,"Background Image");
        menu.add(0,BACKGROUND_MUSIC,0,"Background Music");
        menu.add(0,EXIT,0,"Exit");
        menu.add(0,ABOUT,0,"About");
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case BACKGROUND_IMAGE:
                flag1^=1;
                if(flag1==1)
                    mLayout.setBackgroundResource(R.drawable.penguin);
                else
                    mLayout.setBackgroundResource(R.drawable.jellyfish);
                break;

            case BACKGROUND_MUSIC:
                flag ^= 1;
                Intent intent = new Intent("com.samsung.service.test");
                if (flag == 1)
                    startService(intent);
                else
                    stopService(intent);
                break;
            case ABOUT:
                Intent intent1 = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent1);
                break;
            case EXIT:
                AlertDialog.Builder d=new AlertDialog.Builder(MainActivity.this);
                d.setIcon(R.drawable.android);
                d.setTitle("Default Dialog");
                d.setMessage("Are you sure?");
                d.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int Item) {
                        // TODO Auto-generated method stub
                        MainActivity.this.finish();
                    }
                });
                d.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int Item) {
                        dialog.cancel();
                    }
                });
                d.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Choose font below");
        menu.add(0,BIGCITY,0,"BigCity");
        menu.add(0,JURASSIC,0,"Jurassic");
        menu.add(0,WALTDISNEY,0,"Waltdisney");
    }
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case BIGCITY:
                t = Typeface.createFromAsset(getAssets(), "font/BigCity.ttf");
                break;
            case JURASSIC:
                t = Typeface.createFromAsset(getAssets(), "font/Jurassic.ttf");
                break;

        }
        setType(t);
        return super.onContextItemSelected(item);
    }
    public void setType(Typeface t){
        b0.setTypeface(t);
        b1.setTypeface(t);
        b2.setTypeface(t);
        b3.setTypeface(t);
        b4.setTypeface(t);
        b5.setTypeface(t);
        b6.setTypeface(t);
        b7.setTypeface(t);
        b8.setTypeface(t);
        b9.setTypeface(t);
        bAdd.setTypeface(t);
        bSub.setTypeface(t);
        bMul.setTypeface(t);
        bDiv.setTypeface(t);
        bClear.setTypeface(t);
        bRst.setTypeface(t);
        editText.setTypeface(t);

    }
    View.OnClickListener click = new View.OnClickListener() {
        public void onClick(View v) {
            if(editText.getText().toString().equals("0"))
                editView="";
            Button btn = (Button)v;
            editValue = btn.getText().toString();
            editView=editView.concat(editValue);
            editText.setText(editView);
            number = number.concat(editValue);
            tts.speak(editValue, TextToSpeech.QUEUE_FLUSH,null);
        }
    };
    View.OnClickListener operator = new View.OnClickListener() {
        public void onClick(View v) {
            Button btn = (Button)v;
            String o = btn.getText().toString();
            String s = editText.getText().toString();
            tts.speak(o, TextToSpeech.QUEUE_FLUSH,null);
            if(number.length()>0){
                exp.push(number);
                number="";
            }
            if(!o.equals("=")){
                if(s.equals("") && priority(o)==1)
                    exp.push("0");
                editView=s.concat(o);
                exp.push(o);
                editText.setText(editView);
            }
            int n = exp.size();
            str = new String[n];
            int i = n-1;
            String e="";
            if(o.equals("=")){
                while(!exp.isEmpty())
                    str[i--] = exp.pop();
                calculate(str);
                editView="";
            }
        }
    };

    public int priority(String operator){
        if(operator.equals("+") || operator.equals("-"))
            return 1;
        if(operator.equals("*") || operator.equals("/"))
            return 2;
        else
            return 0;
    }
    public void calculate(String[] str){
        //convert to postfix
        int i,n=str.length,j=0;
        for(i=0;i<n;i++){
            if(priority(str[i])==0)
                exp.push(str[i]);
            else{
                //	if(priority(str[0])==1)
                //	exp.push("0");
                while(!oper.isEmpty() && priority(oper.peek())>=priority(str[i]))
                    exp.push(oper.pop());
                oper.push(str[i]);
            }
        }
        while(!oper.isEmpty() )
            exp.push(oper.pop());
        expression = new String[exp.size()];
        int t = expression.length-1;
        i = 0;
        while(!exp.isEmpty())
            expression[t--]=exp.pop();
        for(i=0;i<expression.length;i++)
            System.out.println(expression[i]);
        //calculate expression;
        calculateExpression(expression);
    }
    public void calculateExpression(String str[])
    {
        int i;
        for(i=0;i<str.length;i++){
            if(priority(str[i])==0)
                exp.push(str[i]);
            else{
                if(!exp.isEmpty()){
                    try{
                        String v1 = exp.pop();
                        String v2 = exp.pop();
                        n1 = Double.parseDouble(v1);
                        n2 = Double.parseDouble(v2);
                    }
                    catch(EmptyStackException e){
                        //Log.i("Demo", "Demo - Demo Exception");
					/*	AlertDialog.Builder d1=new AlertDialog.Builder(CalculatorActivity.this);
						d1.setIcon(R.drawable.ic_launcher);
						d1.setTitle("Invalid input");
						d1.setMessage("Enter valid input");
						d1.setPositiveButton("Close", null);*/
                        exp.push("");
                        Log.e("My tag","Invalid input");
                    }

                    try{
                        if(str[i].equals("+"))
                            kq = n2+n1;
                        else if(str[i].equals("-"))
                            kq = n2-n1;
                        else if(str[i].equals("*"))
                            kq = n2*n1;
                        else
                            kq = n2/n1;
                        result = Double.toString(kq);
                        exp.push(result);
                    }
                    catch(NumberFormatException e){
                        Log.i("Demo", "Demo - Demo Exception");
					/*	AlertDialog.Builder d1=new AlertDialog.Builder(CalculatorActivity.this);
								d1.setIcon(R.drawable.ic_launcher);
								d1.setTitle("Invalid input");
								d1.setMessage("Enter valid input");
								d1.setPositiveButton("Close", null);*/
                        exp.push("");
                    }

                }
            }
        }
        editText.setText(result);
        tts.speak(result, TextToSpeech.QUEUE_FLUSH,null);
        //System.out.println("result : "+result);
    }

    TextToSpeech.OnInitListener MyTTSListener = new TextToSpeech.OnInitListener() {

        @Override
        public void onInit(int status) {
            if(status==TextToSpeech.SUCCESS){
                int result = tts.setLanguage(Locale.US);
                if(result==TextToSpeech.LANG_MISSING_DATA ||
                        result == TextToSpeech.LANG_NOT_SUPPORTED)
                    Log.e("My tag","Not avalable");
            }
        }
    };
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if(tts!=null){
            tts.stop();
            tts.shutdown();
        }
    }
}