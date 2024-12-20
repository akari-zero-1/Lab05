package hust.soict.hedspi.aims.media;

public class Disc extends Media {
    
    private int length;
    private String director;

    // Constructor 1
    public Disc(int id, String title) {
        super(id, title);
    }

    // Constructor 2
    public Disc(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    // Constructor 3
    public Disc(int id, String title, String category, String director, float cost) {
        super(id, title, category, cost);
        this.director = director;
    }

    // Constructor 4
    public Disc(int id, String title, String category, String director, int length, float cost) {
        super(id, title, category, cost);
        this.length = length;
        this.director = director;
    }

    // Getter for length
    public int getLength() {
        return length;
    }

    // Getter for director
    public String getDirector() {
        return director;
    }

    // Setter for length
    protected void setLength(int length) {
        this.length = length;
    }

    // Setter for director
    protected void setDirector(String director) {
        this.director = director;
    }

    // Override toString method for Disc details
    @Override
    public String toString() {
        return String.format("Disc [ID: %d, Title: %s, Category: %s, Director: %s, Length: %d, Cost: %.2f]",
                getId(), getTitle(), getCategory(), director, length, getCost());
    }
}
