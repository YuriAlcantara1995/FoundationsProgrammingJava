package space.harbour.RealEstateSellingSystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import space.harbour.RealEstateSellingSystem.domain.CustomUserDetails;
import space.harbour.RealEstateSellingSystem.domain.Realtor;
import space.harbour.RealEstateSellingSystem.domain.User;
import space.harbour.RealEstateSellingSystem.dto.RealtorDto;
import space.harbour.RealEstateSellingSystem.service.RealtorService;
import space.harbour.RealEstateSellingSystem.service.UserService;

@Controller
public class RealtorController {

    @Autowired
    private RealtorService realtorService;
    @Autowired
    private UserService userService;

    @GetMapping("/realtors")
    public String showRealtors(Model model) {
        model.addAttribute("realtorList", realtorService.getAll());
        return "realtors";
    }

    @GetMapping("/realtor/add")
    public String addRealtor(Model model) {
        Realtor realtor = new Realtor();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails  userDetails = (CustomUserDetails) authentication.getPrincipal();
        User currentUser = userService.findUserByEmail(userDetails.getEmail());
        RealtorDto realtorDto = new RealtorDto(realtor.getId(), currentUser.getId(), null);
        model.addAttribute("realtor", realtorDto);
        model.addAttribute("user", currentUser);
        return "addRealtor";
    }

    @PostMapping("/realtor/save")
    public String saveRealtor(@ModelAttribute("realtor") RealtorDto realtorDto) {
        Realtor realtor = getRealtor(realtorDto);
        realtorService.save(realtor);
        return "redirect:/realtors";
    }

    @GetMapping("/realtor/update/{id}")
    public String updateRealtor(@PathVariable(value = "id") long id, Model model) {
        Realtor realtor = realtorService.getById(id);
        RealtorDto realtorDto = new RealtorDto(realtor.getId(), realtor.getUser().getId(), realtor.getPhoneNumber());
        model.addAttribute("realtor", realtorDto);
        model.addAttribute("user", realtor.getUser());
        return "updateRealtor";
    }

    @GetMapping("/realtor/show/{id}")
    public String showRealtor(@PathVariable(value = "id") long id, Model model) {
        Realtor realtor = realtorService.getById(id);
        model.addAttribute("realtor", realtor);
        return "showRealtor";
    }

    @GetMapping("/realtor/delete/{id}")
    public String deleteRealtor(@PathVariable(value = "id") long id) {
        realtorService.deleteById(id);
        return "redirect:/realtors";

    }

    public Realtor getRealtor(RealtorDto realtorDto) {
        Realtor realtor = new Realtor();
        if(realtorDto.getId() != null)
            realtor.setId(realtorDto.getId());
        realtor.setPhoneNumber(realtorDto.getPhoneNumber());
        realtor.setUser(userService.findById(realtorDto.getUserId()));
        return realtor;
    }
}