package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {

    private List<String> authors = new ArrayList<>();

    // Constructor
    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    // Add author to the list
    public void addAuthor(String authorName) {
        if (authors.contains(authorName)) {
            System.out.println("Author is already in the list.");
            return;
        }
        authors.add(authorName);
        System.out.println("Add author successfully.");
    }

    // Remove author from the list
    public void removeAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            System.out.println("Author is not in the list.");
            return;
        }
        authors.remove(authorName);
        System.out.println("Remove author successfully.");
    }

    // Override toString method
    @Override
    public String toString() {
        StringBuilder authorsList = new StringBuilder();
        for (String author : authors) {
            authorsList.append(author).append(", ");
        }
        // Remove the last ", " if the list is not empty
        if (authorsList.length() > 0) {
            authorsList.setLength(authorsList.length() - 2);
        }
        return getId() + ". Book - " + getTitle() + " - " + getCategory() + " - " + authorsList + ": " + getCost() + "$";
    }
}
