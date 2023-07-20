package sg.edu.rp.c346.id22013272.myndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert,btnShow;
    TextView tvTitle,tvSinger,tvYear,tvStar;
    EditText etTitle,etSinger,etYear;
    RadioGroup Star;
    RadioButton s1,s2,s3,s4,s5;
    ArrayList<Song> al;
    ArrayAdapter<Song>aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = findViewById(R.id.buttonInsert);
        btnShow = findViewById(R.id.buttonSL);
        tvTitle = findViewById(R.id.textViewTitle);
        tvSinger = findViewById(R.id.textViewSing);
        tvYear = findViewById(R.id.textViewYear);
        tvStar = findViewById(R.id.textViewStar);
        etTitle = findViewById(R.id.editTextTitle);
        etSinger = findViewById(R.id.editTextSinger);
        etYear = findViewById(R.id.editTextYear);
        Star=findViewById(R.id.radioGroup);

        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this,
                android.R.layout.simple_list_item_1, al);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            DBHelper db= new DBHelper(MainActivity.this);

            String newtitle = etTitle.getText().toString();
            String newSinger = etSinger.getText().toString();
            int newyear = Integer.parseInt(etYear.getText().toString());
            int newstar = 0;
            int checkedRadioId = Star.getCheckedRadioButtonId();
            if (checkedRadioId == R.id.s1)
            {
                newstar += 1;
            }
            else if (checkedRadioId == R.id.s2)
            {
                    newstar += 2;
            }
            else if (checkedRadioId == R.id.s3)
            {
                newstar += 3;
            }
            else if (checkedRadioId == R.id.s4)
            {
                newstar += 4;
            }
            else if (checkedRadioId == R.id.s5)
            {
                newstar += 5;
            }


            db.insertSong(newtitle,newSinger,newyear,newstar);

            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListView.class);
                startActivity(i);
            }
        });
    }
}