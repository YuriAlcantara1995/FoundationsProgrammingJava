package space.harbour.RealEstateSellingSystem.dto;

import lombok.*;
import jakarta.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealtorDto {
    private Long id;
    @NotEmpty(message = "User Id should not be empty")
    private Long userId;
    @NotEmpty(message = "Phone Number should not be empty")
    private String phoneNumber;
}
