package space.harbour.RealEstateSellingSystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import space.harbour.RealEstateSellingSystem.domain.Property;
import space.harbour.RealEstateSellingSystem.service.CategoryService;
import space.harbour.RealEstateSellingSystem.service.PropertyService;
import space.harbour.RealEstateSellingSystem.service.RealtorService;
import space.harbour.RealEstateSellingSystem.util.FileUploadUtil;

import java.io.IOException;

@Controller
public class PropertyController {

    @Autowired
    private PropertyService propertyService;
    @Autowired
    private RealtorService realtorService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/properties")
    public String showProperties(Model model) {
        model.addAttribute("propertyList", propertyService.getAll());
        return "properties";
    }

    @GetMapping("/property/add")
    public String addProperty(Model model) {
        Property property = new Property();
        model.addAttribute("property", property);
        model.addAttribute("realtorList", realtorService.getAll());
        model.addAttribute("categoryList", categoryService.getAll());
        return "addProperty";
    }

    @PostMapping("/property/save")
    public String saveProperty(@ModelAttribute("property") Property property, @RequestParam("image") MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        String extension = fileName.lastIndexOf('.') != -1 ? fileName.substring(fileName.lastIndexOf('.')) : "";
        property.setPicture(extension);
        Property savedProperty = propertyService.save(property);
        String uploadDir = System.getProperty("user.dir") + "/src/main/property-pictures";
        FileUploadUtil.saveFile(uploadDir, savedProperty.getId().toString() + extension, image);
        return "redirect:/properties";
    }

    @GetMapping("/property/update/{id}")
    public String updateProperty(@PathVariable(value = "id") long id, Model model) {
        Property property = propertyService.getById(id);
        model.addAttribute("property", property);
        model.addAttribute("realtorList", realtorService.getAll());
        model.addAttribute("categoryList", categoryService.getAll());
        return "updateProperty";
    }

    @GetMapping("/property/show/{id}")
    public String showProperty(@PathVariable(value = "id") long id, Model model) {
        Property property = propertyService.getById(id);
        model.addAttribute("property", property);
        return "showProperty";
    }

    @GetMapping("/property/delete/{id}")
    public String deleteProperty(@PathVariable(value = "id") long id) {
        propertyService.deleteById(id);
        return "redirect:/properties";

    }
}