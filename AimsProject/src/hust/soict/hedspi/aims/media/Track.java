package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class Track implements Playable {
    private String title;
    private int length;

    // Constructor
    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Getter for length
    public int getLength() {
        return length;
    }

    // Override equals to compare Track objects
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if both are the same object
        if (obj == null || getClass() != obj.getClass()) return false; // Check for null or different class

        Track track = (Track) obj;
        return length == track.length && title.equals(track.title); // Correct comparison for Strings
    }

    // Play the track (method 1)
    @Override
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing track: " + this.getTitle());
            System.out.println("Track length: " + this.getLength());
        } else {
            throw new PlayerException("ERROR: Track length is non-positive!");
        }
    }

    // Return play information about the track (method 2)
    @Override
    public String playMedia() throws PlayerException {
        if (this.getLength() > 0) {
            return "Playing track: " + this.getTitle() + "\nTrack length: " + this.getLength();
        } else {
            throw new PlayerException("ERROR: Track length is non-positive!");
        }
    }
}
