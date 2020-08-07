import java.util.Date;

public class SongInfo {
    String songName, writter, singer;
    Date reDate;
    double revenue;

    public SongInfo(String songName, String writter, String singer, Date reDate, double revenue) {
        this.songName = songName;
        this.writter = writter;
        this.singer = singer;
        this.reDate = reDate;
        this.revenue = revenue;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getWritter() {
        return writter;
    }

    public void setWritter(String writter) {
        this.writter = writter;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Date getReDate() {
        return reDate;
    }

    public void setReDate(Date reDate) {
        this.reDate = reDate;
    }

    public double getRevenue() {
        String newRevenue = String.format("%.2f",revenue);
        revenue = Double.parseDouble(newRevenue);
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "SongInfo{" +
                "songName='" + songName + '\'' +
                ", writter='" + writter + '\'' +
                ", singer='" + singer + '\'' +
                ", reDate=" + reDate +
                ", revenue=" + revenue +
                '}';
    }
}
