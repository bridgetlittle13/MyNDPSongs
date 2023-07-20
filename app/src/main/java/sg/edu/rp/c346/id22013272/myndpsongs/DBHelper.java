package sg.edu.rp.c346.id22013272.myndpsongs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "song.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SONG = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SONG_CONTENT = "song_content";
    private static final String COLUMN_TITLE = "song_title";
    private static final String COLUMN_NAME = "singers_name";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STAR = "star";
    public DBHelper( Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            String createSongTableSql = "CREATE TABLE " + TABLE_SONG + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITLE + " TEXT, "
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_YEAR + " INTEGER, "
                    + COLUMN_STAR + " INTEGER) ";

            // Execute the SQL statement to create the table
        db.execSQL(createSongTableSql);
        Log.i("info", "created tables");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE " + TABLE_SONG + " ADD COLUMN  module_name TEXT ");
        onCreate(db);

    }
    public long insertSong(String title,String singer,int year, int star) {

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE,title);
        values.put(COLUMN_NAME,singer);
        values.put(COLUMN_YEAR,year);
        values.put(COLUMN_STAR,star);

        // Insert the row into the TABLE_TASK
        long result = db.insert(TABLE_SONG, null, values);
        db.close();
        Log.d("SQL Insert", "ID:" + result); //id returned, shouldn’t be -1
        return result;
    }
    public int updateSong(Song title, Song singer, Song year, Song star){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title.getTitle());
        values.put(COLUMN_NAME, singer.getSinger());
        values.put(COLUMN_YEAR, year.getYear());
        values.put(COLUMN_STAR, star.getStar());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(title.getId())};
        int result = db.update(TABLE_SONG, values, condition, args);
        db.close();
        return result;
    }
    public int deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }
    public ArrayList<Song> getAllSong(String keyword) {
        ArrayList<Song> Song = new ArrayList<Song>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_NAME, COLUMN_YEAR, COLUMN_STAR};

        String selection = COLUMN_TITLE + " LIKE ? OR " + COLUMN_NAME + " LIKE ?";
        String[] args = {"%" + keyword + "%", "%" + keyword + "%"};

        Cursor cursor = db.query(TABLE_SONG, columns, selection, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String content = cursor.getString(1);
                String title = cursor.getString(2);
                String name = cursor.getString(3);
                int year = cursor.getInt(4);
                int star = cursor.getInt(5);
                Song s1 = new Song(id,title, content, year, star);
                Song.add(s1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Song;
    }

    public ArrayList<Song> getAll5StarSongs() {
        ArrayList<Song> Song = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_NAME, COLUMN_YEAR, COLUMN_STAR};

        String selection = COLUMN_STAR + " = ?";
        String[] selectionArgs = {"5"};

        Cursor cursor = db.query(TABLE_SONG, columns, selection, selectionArgs,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String name= cursor.getString(2);
                int year= cursor.getInt(3);
                int star= cursor.getInt(4);
                Song song2 = new Song(id,title,name, year,star);
                Song.add(song2);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Song;
    }

    public ArrayList<Song> getSong() {
        ArrayList<Song> Song = new ArrayList<Song>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_NAME, COLUMN_YEAR, COLUMN_STAR};
        Cursor cursor = db.query(TABLE_SONG, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                int year = cursor.getInt(3);
                int star = cursor.getInt(4);
                Song obj = new Song(id,title, singer, year, star);
                Song.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Song;
    }
}