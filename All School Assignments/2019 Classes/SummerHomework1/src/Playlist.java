public class Playlist {

    final int MAX_SONGS = 50;

    private SongRecord[] songs = new SongRecord[MAX_SONGS];
    private int songCounter = 0;


    public int getSongCounter() {
        return songCounter;
    }

    public void setSongCounter(int songCounter) {
        this.songCounter = songCounter;
    }


    public Playlist() {

    }


    public SongRecord[] getSongs() {
        return songs;
    }

    public SongRecord[] clone() {

        SongRecord[] songsCopy = new SongRecord[MAX_SONGS];

        for (int i = 0; i < songsCopy.length; i++) {
            songsCopy[i] = songs[i].clone();
        }

        return songsCopy;
    }


    public boolean equals(Object obj) {

        if (obj instanceof Playlist) {
            SongRecord[] songArray = ((Playlist) obj).getSongs();

            if (songArray.length == songs.length) {

                for (int i = 0; i < songArray.length; i++) {

                    if (!songArray[i].equals(songs[i])) {
                        return false;
                    }

                }

            }


        } else {
            return false;
        }

        return true;
    }


    public void addSong(SongRecord song, int position) throws FullPlaylistException, IllegalArgumentException {

        SongRecord[] newArray = new SongRecord[MAX_SONGS];


        if (songCounter == MAX_SONGS) {
            throw new FullPlaylistException("There is no more room on this playlist.");
        }

        if (position < 1 || position > songCounter + 1) {
            throw new IllegalArgumentException("Invalid position.");
        }

        if (position == songCounter + 1) {
            songs[position + 1] = song;
        }






        songCounter++;


    }

    public void removeSong(int position) throws IllegalArgumentException {

        if (position < 1 || position > songCounter) {
            throw new IllegalArgumentException("Invalid position.");
        }


        for (int i = position - 1; i < songCounter - position; i++) {
            songs[i] = songs[i + 1];
        }

        songCounter--;

    }

    public SongRecord getSong(int position) throws IllegalArgumentException {
        if (position < 1 || position > songCounter) {
            throw new IllegalArgumentException("Invalid position.");
        }


        return songs[position - 1];


    }

    public void printAllSongs() {
        System.out.print(toString());
    }


    public Playlist getSongsByArtist(Playlist originalList, String artist) {
        if (originalList == null || artist == null) {
            return null;
        }

        Playlist newPlaylist = new Playlist();
        int counter = 0;


        for (int i = 0; i < originalList.getSongCounter(); i++) {

            try {
                if (originalList.getSong(i).getArtist().equals(artist)) {
                    newPlaylist.songs[counter] = originalList.getSong(i);
                    counter++;
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }


        }


        return newPlaylist;

    }


    public String toString() {

        String output = new String("Song#     ");

        output += c
        output += "\n";


        for (int i = 0; i <= songCounter; i++) {
            output += ((i + 1) + "         " + songs[i].toString() + "\n");
        }

        return output;
    }


}
