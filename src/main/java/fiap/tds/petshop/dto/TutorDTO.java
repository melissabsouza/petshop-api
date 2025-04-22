package fiap.tds.petshop.dto;

import fiap.tds.petshop.entity.Animal;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorDTO {
    private Long id;

    private String cpf;

    @NotBlank(message = "Nome é obrigatório")
    @NotNull(message = "Nome não pode ser nulo")
    @Size(min=3, message = "Nome deve ter pelo menos 3 caracteres")
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Column(length = 100, nullable = false)
    @Pattern(regexp="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?)*$",
            message = "Formato de E-mail inválido")
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\d{2}\\s?(9\\d{4}-\\d{4}|\\d{4}-\\d{4})",
            message = "Use XX 9XXXX-XXXX ou XX XXXX-XXXX")
    private String telefone;

    private List<String> nomesDosAnimais;
}
