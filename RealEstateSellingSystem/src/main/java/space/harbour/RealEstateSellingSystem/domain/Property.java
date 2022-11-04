package space.harbour.RealEstateSellingSystem.domain;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @ManyToOne
    @JoinColumn(name = "realtor_id", nullable = false)
    private Realtor realtor;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column
    private String picture;
    @Override
    public String toString() {
        return name;
    }
    public void setPicture(String picture) {
        System.out.println(picture + "is this");
        System.out.println(!picture.equals(""));
        System.out.println(this.picture == null);
        if(!picture.equals("") || this.picture == null)
            this.picture = picture;
    }
}
