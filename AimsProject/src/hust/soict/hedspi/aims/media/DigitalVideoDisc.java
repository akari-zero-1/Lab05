package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {
    
    // Constructor 1
    public DigitalVideoDisc(int id, String title) {
        super(id, title);
    }

    // Constructor 2
    public DigitalVideoDisc(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    // Constructor 3
    public DigitalVideoDisc(int id, String title, String category, String director, float cost) {
        super(id, title, category, director, cost);
    }

    // Constructor 4
    public DigitalVideoDisc(int id, String title, String category, String director, int length, float cost) {
        super(id, title, category, director, length, cost);
    }

    // Override toString method
    @Override
    public String toString() {
        return String.format("%d. DVD - %s - %s - %s - %d: %.2f$", 
                getId(), getTitle(), getCategory(), getDirector(), getLength(), getCost());
    }

    // Play the DVD if its length is positive
    @Override
    public void play() throws PlayerException {
        if (getLength() > 0) {
            System.out.println("Playing DVD: " + getTitle());
            System.out.println("DVD length: " + getLength());
        } else {
            throw new PlayerException("ERROR: DVD length is non-positive!");
        }
    }

    // Return a string output for playing media
    @Override
    public String playMedia() throws PlayerException {
        if (getLength() > 0) {
            return String.format("Playing DVD: %s\nDVD length: %d", getTitle(), getLength());
        } else {
            throw new PlayerException("ERROR: DVD length is non-positive!");
        }
    }
}
