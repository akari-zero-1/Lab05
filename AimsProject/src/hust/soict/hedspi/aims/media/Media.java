package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public abstract class Media {
    
    private int id;
    private String title;
    private String category;
    private float cost;

    // Các Comparator tĩnh cho việc so sánh
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();
    
    // Constructor 1
    public Media(int id, String title) {
        this(id, title, null, 0); // Default giá trị cho category và cost
    }

    // Constructor 2
    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    // Getter
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getCost() {
        return cost;
    }

    // Setter
    protected void setId(int id) {
        this.id = id;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    protected void setCategory(String category) {
        this.category = category;
    }

    protected void setCost(float cost) {
        this.cost = cost;
    }

    // Override phương thức equals để so sánh đối tượng Media
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return Float.compare(media.cost, cost) == 0 && title.equals(media.title);
    }

    // Kiểm tra xem đối tượng Media có trùng với tiêu đề không
    public boolean isMatch(String title) {
        return this.title.equalsIgnoreCase(title);
    }

    // Kiểm tra xem đối tượng Media có trùng với ID không
    public boolean isMatch(int id) {
        return this.id == id;
    }

    // Override toString để hiển thị thông tin về Media
    @Override
    public String toString() {
        return String.format("Media [ID: %d, Title: %s, Category: %s, Cost: %.2f]", id, title, category, cost);
    }
}
