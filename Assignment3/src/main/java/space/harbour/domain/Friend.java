package space.harbour.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Friend {
    public String id;
    public String name;
    public String email;
    public String phoneNumber;
    public String address;
    public Date birthday;
}
