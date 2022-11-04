package space.harbour.RealEstateSellingSystem.domain;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "realtors")
public class Realtor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column
    private String phoneNumber;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "realtor")
    private List<Property> properties;
    @Override
    public String toString() {
        return phoneNumber;
    }
}
