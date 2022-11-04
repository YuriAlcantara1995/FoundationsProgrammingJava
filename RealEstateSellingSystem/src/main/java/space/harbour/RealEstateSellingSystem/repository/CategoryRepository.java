package space.harbour.RealEstateSellingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.harbour.RealEstateSellingSystem.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
