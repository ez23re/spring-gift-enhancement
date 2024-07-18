package gift.category.model;

import jakarta.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @Column(name = "imgUrl")
    private String imgUrl;

    // JPA에서 필요로 하는 기본 생성자
    protected Category() {
    }

    public Category(String name, String color, String imgUrl) {
        this.name = name;
        this.color = color;
        this.imgUrl = imgUrl;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}