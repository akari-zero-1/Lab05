package hust.soict.hedspi.aims.media;

import java.util.HashSet;
import java.util.Set;

import hust.soict.hedspi.aims.exception.PlayerException;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private Set<Track> tracks = new HashSet<>();

    // Constructor 1
    public CompactDisc(int id, String title, String artist) {
        super(id, title);
        this.artist = artist;
    }

    // Constructor 2
    public CompactDisc(int id, String title, String artist, String category, float cost) {
        super(id, title, category, cost);
        this.artist = artist;
    }

    // Constructor 3
    public CompactDisc(int id, String title, String artist, String category, String director, float cost) {
        super(id, title, category, director, cost);
        this.artist = artist;
    }

    // Getter for artist
    public String getArtist() {
        return artist;
    }

    // Add a track to the list
    public void addTrack(Track track) {
        if (tracks.add(track)) {
            System.out.println("Track added successfully.");
        } else {
            System.out.println("Track is already in the list.");
        }
    }

    // Remove a track from the list
    public void removeTrack(Track track) {
        if (tracks.remove(track)) {
            System.out.println("Track removed successfully.");
        } else {
            System.out.println("Track is not in the list.");
        }
    }

    // Override toString method
    @Override
    public String toString() {
        StringBuilder trackTitles = new StringBuilder();
        for (Track track : tracks) {
            trackTitles.append(track.getTitle()).append(", ");
        }
        // Remove the last ", " if tracks are available
        if (trackTitles.length() > 0) {
            trackTitles.setLength(trackTitles.length() - 2);
        }

        return String.format("%d. CD - %s - %s - %s - %s - %d: %.2f$", 
                getId(), getTitle(), getCategory(), artist, trackTitles.toString(), getLength(), getCost());
    }

    // Get total length of all tracks
    public int getLength() {
        return tracks.stream().mapToInt(Track::getLength).sum();
    }

    // Play all tracks in the CD
    @Override
    public void play() throws PlayerException {
        if (getLength() > 0) {
            System.out.println("Playing CD: " + getTitle());
            for (Track track : tracks) {
                try {
                    track.play();
                } catch (PlayerException e) {
                    throw e;
                }
            }
        } else {
            throw new PlayerException("ERROR: CD length is non-positive.");
        }
    }

    // Return a string output for playing media
    @Override
    public String playMedia() throws PlayerException {
        if (getLength() > 0) {
            StringBuilder out = new StringBuilder("Playing CD: " + getTitle() + "\n");
            for (Track track : tracks) {
                out.append(track.playMedia()).append("\n");
            }
            return out.toString();
        } else {
            throw new PlayerException("ERROR: CD length is non-positive.");
        }
    }
}
