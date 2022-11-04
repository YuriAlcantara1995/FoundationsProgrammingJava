package space.harbour.RealEstateSellingSystem.service;

import space.harbour.RealEstateSellingSystem.domain.Realtor;

import java.util.List;

public interface RealtorServiceInterface {
    List<Realtor> getAll();
    void save(Realtor realtor);
    Realtor getById(long id);
    void deleteById(long id);
}
