package space.harbour.RealEstateSellingSystem.service;

import org.springframework.stereotype.Service;
import space.harbour.RealEstateSellingSystem.domain.Property;
import space.harbour.RealEstateSellingSystem.exception.PropertyNotFoundException;
import java.util.List;

public interface PropertyServiceInterface {
    List<Property> getAll();
    Property save(Property property);
    Property getById(long id);
    void deleteById(long id);

}
