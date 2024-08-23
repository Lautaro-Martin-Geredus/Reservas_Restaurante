package sistem.restaurant.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credential
{
    @NotNull(message = "identity can't by null")
    @JsonProperty("identity")
    private String identity;

    @NotNull(message = "password can't by null")
    private String password;

}