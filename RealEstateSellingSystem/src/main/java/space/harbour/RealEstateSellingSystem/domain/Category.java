package space.harbour.RealEstateSellingSystem.domain;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Property> properties;
    @Override
    public String toString() {
        return name;
    }
}
