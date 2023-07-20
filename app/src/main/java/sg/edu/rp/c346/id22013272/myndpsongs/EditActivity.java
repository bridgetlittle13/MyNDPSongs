package sg.edu.rp.c346.id22013272.myndpsongs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    TextView tvID;
    EditText etTitle,etSinger,etYear;
    RadioGroup Star;
    Song ID,TITLE,SINGER,YEAR,STARS;
    Button btnUpdate, btnDelete,btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //initialize the variables with UI here
        tvID = findViewById(R.id.textViewID);
        etTitle = findViewById(R.id.editTextTitle);
        etSinger = findViewById(R.id.editTextSinger);
        etYear = findViewById(R.id.editTextYear);
        btnCancel = findViewById(R.id.btnCancel);
        Star = findViewById(R.id.radioGroupStar);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        Intent i = getIntent();

        ID=(Song)i.getSerializableExtra("ID");
        tvID.setText(ID.getId());
        TITLE=(Song)i.getSerializableExtra("Title");
        etTitle.setText(TITLE.getTitle());
        SINGER=(Song)i.getSerializableExtra("Singer");
        etSinger.setText(SINGER.getSinger());
        YEAR=(Song)i.getSerializableExtra("Year");
        etYear.setText(YEAR.getYear());
        STARS=(Song)i.getSerializableExtra("Stars");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(EditActivity.this);
                String nTitle= String.valueOf(etTitle.getText());
                String nSinger= String.valueOf(etSinger.getText());
                int nYear=Integer.valueOf(String.valueOf(etYear.getText()));

                int star=0;
                int checkRadioID=Star.getCheckedRadioButtonId();

                if (checkRadioID==R.id.s1){
                    star+=1;
                } else if (checkRadioID==R.id.s2) {
                    star+=2;
                } else if (checkRadioID==R.id.s3) {
                    star+=3;
                } else if (checkRadioID==R.id.s4) {
                    star+=4;
                } else if (checkRadioID==R.id.s5) {
                    star+=5;
                }

                TITLE.setTitle(nTitle);
                SINGER.setSinger(nSinger);
                YEAR.setYear(nYear);
                STARS.setStar(star);

                db.updateSong(TITLE,SINGER,YEAR,STARS);
                db.close();
                finish();

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(EditActivity.this);
                db.deleteSong(ID.getId());
                finish();

            }

        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
