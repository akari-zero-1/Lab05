package hust.soict.hedspi.aims.store;

import java.util.ArrayList;
import hust.soict.hedspi.aims.media.Media;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<>();

    // Method to add media to the store
    public boolean addMedia(Media media) {
        if (media == null) {
            System.out.println("Cannot add null media.");
            return false;
        }
        if (itemsInStore.contains(media)) {
            System.out.println("Media is already in the store.");
            return false;
        }
        itemsInStore.add(media);
        System.out.println("Media added successfully.");
        return true;
    }

    // Method to remove media from the store
    public boolean removeMedia(Media media) {
        if (media == null) {
            System.out.println("Cannot remove null media.");
            return false;
        }
        if (!itemsInStore.contains(media)) {
            System.out.println("Media is not in the store.");
            return false;
        }
        itemsInStore.remove(media);
        System.out.println("Media removed successfully.");
        return true;
    }

    // Method to get the list of media in store
    public ArrayList<Media> getItemsInStore() {
        return new ArrayList<>(itemsInStore);  // Return a copy to avoid external modification
    }

    // Method to find media by title
    public Media findMediaByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Title cannot be null or empty.");
            return null;
        }
        for (Media m : itemsInStore) {
            if (m.isMatch(title)) {
                return m;
            }
        }
        System.out.println("No media found with the title: " + title);
        return null;
    }

    // Method to display all items in the store
    public void showStore() {
        if (itemsInStore.isEmpty()) {
            System.out.println("The store is empty.");
            return;
        }
        System.out.println("Items in Store:");
        for (int i = 0; i < itemsInStore.size(); i++) {
            System.out.println(i + 1 + ". " + itemsInStore.get(i).toString());
        }
    }
}
