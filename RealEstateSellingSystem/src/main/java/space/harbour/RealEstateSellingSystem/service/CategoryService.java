package space.harbour.RealEstateSellingSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.harbour.RealEstateSellingSystem.domain.Category;
import space.harbour.RealEstateSellingSystem.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService implements CategoryServiceInterface{
    @Autowired
    private CategoryRepository categoryRepo;
    @Override
    public List<Category> getAll() {
        return (List<Category>) categoryRepo.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public Category getById(long id) {

        return categoryRepo.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        categoryRepo.deleteById(id);
    }

}
