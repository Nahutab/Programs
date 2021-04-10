import java.util.Scanner;

public class PlaylistOperations {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        boolean flag = true;

        String input = stdin.next();

        while (flag) {
            System.out.print("A) Add Song \n" +
                    "B) Print Songs by Artist \n" +
                    "G) Get Song \n" +
                    "R) Remove Song \n" +
                    "P) Print All Songs \n" +
                    "S) Size \n" +
                    "Q) Quit \n" +
                    "\n" +
                    "Select a menu option:");

            Playlist playlist = new Playlist();


            if (input.equalsIgnoreCase("A")) {
                System.out.print("Enter the song title:");
                String title = stdin.next();
                System.out.print("Enter the song artist:");
                String artist = stdin.next();
                System.out.print("Enter the song length (Minutes):");
                int minutes = stdin.nextInt();
                System.out.print("Enter the song length (Seconds):");
                int seconds = stdin.nextInt();
                System.out.print(" Enter the position: ");
                int position = stdin.nextInt();


                SongRecord song = new SongRecord(artist, title, minutes, seconds);

                try {
                    playlist.addSong(song, position);
                } catch (FullPlaylistException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }


            } else if (input.equalsIgnoreCase("P")) {
                playlist.printAllSongs();
            } else if (input.equalsIgnoreCase("G")) {
                System.out.print("Enter the position: ");
                int position = stdin.nextInt();

                System.out.print("Song#     Title           Artist          Length\n" +
                        "------------------------------------------------");

                try {
                    System.out.print(playlist.getSong(position).toString());
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }


            } else if (input.equalsIgnoreCase("R")) {
                System.out.print("Enter the position: 1");
                int position = stdin.nextInt();

                try {
                    playlist.removeSong(position);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }

            } else if (input.equalsIgnoreCase("B")) {
                System.out.print("Enter the artist");

                String artist = stdin.next();

                Playlist artistPlaylist = playlist.getSongsByArtist(playlist, artist);
                artistPlaylist.printAllSongs();

            } else if (input.equalsIgnoreCase("Q")) {
                flag = false;
            } else if (input.equalsIgnoreCase("S")) {
                System.out.print("There are " + playlist.getSongCounter() + " song(s) in the current playlist.");
            }


        }


    }


    public static void mainMenu() {
        System.out.print("A) Add Song \n" +
                "B) Print Songs by Artist \n" +
                "G) Get Song \n" +
                "R) Remove Song \n" +
                "P) Print All Songs \n" +
                "S) Size \n" +
                "Q) Quit \n" +
                "\n" +
                "Select a menu option:");

    }


}
