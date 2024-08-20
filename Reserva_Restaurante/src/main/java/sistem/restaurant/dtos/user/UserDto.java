package sistem.restaurant.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sistem.restaurant.entities.UserRole;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class UserDto
{
    private String name;
    private String email;
    private String password;
    private UserRole role;
}
