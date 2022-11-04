package space.harbour.RealEstateSellingSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.harbour.RealEstateSellingSystem.domain.Realtor;
import space.harbour.RealEstateSellingSystem.repository.RealtorRepository;

import java.util.List;
@Service
public class RealtorService implements  RealtorServiceInterface{
    @Autowired
    private RealtorRepository realtorRepo;
    @Override
    public List<Realtor> getAll() {
        return (List<Realtor>) realtorRepo.findAll();
    }

    @Override
    public void save(Realtor realtor) {
        realtorRepo.save(realtor);
    }

    @Override
    public Realtor getById(long id) {
        return realtorRepo.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        realtorRepo.deleteById(id);
    }
}
