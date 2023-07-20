package sg.edu.rp.c346.id22013272.myndpsongs;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String title;
    private String singer;

    private int year;
    private int star;

    public Song(int id,String title, String singer, int year, int star) {
        this.id = id;
        this.title = title;
        this.singer = singer;
        this.year = year;
        this.star = star;
    }
    public static void add(Song obj){

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public String getSinger() {
        return singer;
    }



    public int getYear() {
        return year;
    }

    public int getStar() {
        return star;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setStar(int star) {
        this.star = star;
    }

    @Override
    public String toString() {
        String stars = "";
        if(star == 1)
        {
            stars = "*";
        }
        else if(star == 2)
        {
            stars = "**";
        }
        else if(star == 3)
        {
            stars = "***";
        }
        else if(star == 4)
        {
            stars = "****";
        }
        else if(star == 5)
        {
            stars = "*****";
        }

        return stars;  }
}

