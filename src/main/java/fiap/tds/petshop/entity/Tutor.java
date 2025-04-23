package fiap.tds.petshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Cpf é obrigatório")
    @NotNull(message = "Cpf não pode ser nulo")
    @Pattern(regexp= "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$",
    message= "Formato de CPF inválido, use 12345678900")
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
    @Pattern(regexp = "\\d{2}\\s?(9\\d{4}-?\\d{4}|\\d{4}-?\\d{4})",
            message = "Use XX 9XXXX-XXXX ou XX XXXX-XXXX")
    private String telefone;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Animal> animais;
}
