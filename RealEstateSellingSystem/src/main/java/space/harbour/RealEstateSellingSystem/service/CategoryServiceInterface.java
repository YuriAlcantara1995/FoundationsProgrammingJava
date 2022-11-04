package space.harbour.RealEstateSellingSystem.service;

import space.harbour.RealEstateSellingSystem.domain.Category;

import java.util.List;

public interface CategoryServiceInterface {
    List<Category> getAll();
    void save(Category category);
    Category getById(long id);
    void deleteById(long id);

}
