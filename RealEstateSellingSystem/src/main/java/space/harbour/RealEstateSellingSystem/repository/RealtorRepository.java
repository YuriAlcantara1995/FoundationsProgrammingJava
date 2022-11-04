package space.harbour.RealEstateSellingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.harbour.RealEstateSellingSystem.domain.Realtor;

@Repository
public interface RealtorRepository extends JpaRepository<Realtor, Long> {
}
