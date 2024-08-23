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
public class UserResponseDto
{
    private String name;
    private String email;
    private UserRole role;
}
