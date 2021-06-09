package sg.edu.rp.c346.id20002288.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnadd,btnclear,btndel;
    EditText etname;
    ListView lv;
    ArrayList<String> aList;
    ArrayAdapter<String> aa;
    Spinner spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnadd=findViewById(R.id.btnadd);
        btnclear=findViewById(R.id.btnclear);
        btndel=findViewById(R.id.btndel);
        etname=findViewById(R.id.etname);
        lv = findViewById(R.id.lvlist);
        spin=findViewById(R.id.spin);


        aList = new ArrayList<>();
        aa= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,aList);
        lv.setAdapter(aa);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input=etname.getText().toString();
                aList.add(input);
                aa.notifyDataSetChanged();
                etname.setText(null);
            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aa.notifyDataSetChanged();
                aList.clear();
            }
        });


        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aList.size()==0){
                    Toast.makeText(MainActivity.this, getString(R.string.empty),Toast.LENGTH_LONG).show();
                    return;
                }
                int input=Integer.parseInt(etname.getText().toString());
                if(input>aList.size()-1){
                    Toast.makeText(MainActivity.this, getString(R.string.wrongindex),Toast.LENGTH_LONG).show();
                    return;
                }
                aList.remove(input);
                aa.notifyDataSetChanged();
                etname.setText(null);
            }
        });
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btndel.setEnabled(false);
                        btnadd.setEnabled(true);
                        etname.setHint(R.string.addtask);
                        break;

                    case 1:
                        btnadd.setEnabled(false);
                        btndel.setEnabled(true);
                        etname.setHint(R.string.del);
                        break;
                }
            }




            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}