package space.harbour.RealEstateSellingSystem.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.harbour.RealEstateSellingSystem.domain.Property;
import space.harbour.RealEstateSellingSystem.exception.PropertyNotFoundException;
import space.harbour.RealEstateSellingSystem.repository.PropertyRepository;

import java.util.List;

@Service
public class PropertyService implements PropertyServiceInterface{
    @Autowired
    private PropertyRepository propertyRepo;
    @Override
    public List<Property> getAll() {
        return (List<Property>) propertyRepo.findAll();
    }

    @Override
    public Property save(Property property) {
        return propertyRepo.save(property);
    }

    @Override
    public Property getById(long id) {

        return propertyRepo.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        propertyRepo.deleteById(id);
    }
}
