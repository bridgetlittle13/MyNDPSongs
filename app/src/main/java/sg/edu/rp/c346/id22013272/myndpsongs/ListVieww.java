package sg.edu.rp.c346.id22013272.myndpsongs;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListVieww extends AppCompatActivity {
    ArrayList<Song> songsList;
    ListView lv;
    Button btnShow5star, back;
    CustomAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
        songsList = new ArrayList<Song>();
        adapter = new CustomAdapter(this, R.layout.row, songsList);
        lv.setAdapter(adapter);
        Intent i = getIntent();
        DBHelper db = new DBHelper(ListVieww.this);
        songsList.clear();
        songsList.addAll(db.getSong());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        btnShow5star = findViewById(R.id.btn5star);
        lv = findViewById(R.id.lvDisplay);
        back = findViewById(R.id.btnback);


        lv.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song selectedSong = songsList.get(position);
                Intent i = new Intent(ListVieww.this, EditActivity.class);
                i.putExtra("ID", selectedSong.getId());
                i.putExtra("Title", selectedSong.getTitle());
                i.putExtra("Singers", selectedSong.getSinger());
                i.putExtra("Year", selectedSong.getYear());
                i.putExtra("Stars", selectedSong.getStar());
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnShow5star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ListVieww.this);
                songsList.clear();
                songsList.addAll(dbh.getAll5StarSongs());
                adapter.notifyDataSetChanged();
            }
        });
    }
}

