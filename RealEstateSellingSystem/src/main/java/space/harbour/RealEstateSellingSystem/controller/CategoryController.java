package space.harbour.RealEstateSellingSystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import space.harbour.RealEstateSellingSystem.domain.CustomUserDetails;
import space.harbour.RealEstateSellingSystem.domain.Category;
import space.harbour.RealEstateSellingSystem.domain.User;
import space.harbour.RealEstateSellingSystem.service.CategoryService;
import space.harbour.RealEstateSellingSystem.service.UserService;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String showCategorys(Model model) {
        model.addAttribute("categoryList", categoryService.getAll());
        return "categories";
    }

    @GetMapping("/category/add")
    public String addCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "addCategory";
    }

    @PostMapping("/category/save")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/category/update/{id}")
    public String updateCategory(@PathVariable(value = "id") long id, Model model) {
        Category category = categoryService.getById(id);
        model.addAttribute("category", category);
        return "updateCategory";
    }

    @GetMapping("/category/show/{id}")
    public String showCategory(@PathVariable(value = "id") long id, Model model) {
        Category category = categoryService.getById(id);
        model.addAttribute("category", category);
        return "showCategory";
    }

    @GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable(value = "id") long id) {
        categoryService.deleteById(id);
        return "redirect:/categories";

    }
}