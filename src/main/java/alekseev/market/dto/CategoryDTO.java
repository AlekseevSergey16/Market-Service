package alekseev.market.dto;


public class CategoryDTO {

    private int categoryId;
    private String nameCategory;

    public CategoryDTO() {
    }

    public CategoryDTO(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public CategoryDTO(int categoryId, String nameCategory) {
        this.categoryId = categoryId;
        this.nameCategory = nameCategory;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }


    @Override
    public String toString() {
        return "CategoryDTO{" +
                "categoryId=" + categoryId +
                ", nameCategory='" + nameCategory + '\'' +
                '}';
    }
}
