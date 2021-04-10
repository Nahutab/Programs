public class SongRecord {

    private String title;
    private String artist;
    private int minuteLength;
    private int secondLength;


    public SongRecord() {

    }

    public SongRecord(String artist, String title, int minutes, int seconds) {
        this.artist = artist;
        this.title = title;
        this.minuteLength = minutes;
        this.secondLength = seconds;
    }


    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setMinuteLength(int minuteLength) throws InvalidTimeDurationException {

        if (minuteLength < 0) {
            throw new InvalidTimeDurationException("Invalid minute duration.");
        }

        this.minuteLength = minuteLength;
    }

    public void setSecondLength(int secondLength) throws InvalidTimeDurationException {

        if (secondLength < 0 || secondLength > 59) {
            throw new InvalidTimeDurationException("Invalid second duration.");
        }


        this.secondLength = secondLength;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMinuteLength() {
        return minuteLength;
    }

    public int getSecondLength() {
        return secondLength;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }


    public boolean equals(SongRecord obj) {

        if (obj.getArtist().equals(getArtist()) && obj.getTitle().equals(getTitle()) && obj.getMinuteLength() ==
                getMinuteLength() && obj.getSecondLength() == getSecondLength()) {
            return true;
        } else return false;


    }


    public SongRecord clone() {
        SongRecord song = new SongRecord(getTitle(), getArtist(), getMinuteLength(), getSecondLength());

        return song;

    }

    public String getTimeDuration() {

        String output = new String("");
        output += getMinuteLength() + ":";

        if (getSecondLength() < 10) {
            output += "0" + getSecondLength();
        } else if (getSecondLength() >= 10) {
            output += getSecondLength();
        }

        return output;
    }


    public String toString() {

        String output = String.format("%-21s%-26s%19s", getTitle(), getArtist(), getTimeDuration());


        return output;

    }


}
