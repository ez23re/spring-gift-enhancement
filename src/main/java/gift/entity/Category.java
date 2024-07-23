package gift.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "color", nullable = false, columnDefinition = "VARCHAR(50)")
    private String color;

    @Column(name = "img_url", nullable = false, columnDefinition = "VARCHAR(255)")
    private String imgUrl;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;

    protected Category() {}

    public Category(String name, String color, String imgUrl, String description) {
        this.name = name;
        this.color = color;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getColor() { return color; }
    public String getImgUrl() { return imgUrl; }
    public String getDescription() { return description; }
    public List<Product> getProducts() { return products; }
}