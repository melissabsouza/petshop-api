package fiap.tds.petshop.dto;

import fiap.tds.petshop.entity.Tutor;
import fiap.tds.petshop.entity.enums.SexoType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDTO {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @NotNull(message = "Nome não pode ser nulo")
    @Size(min=2, message = "Nome deve ter pelo menos 2 caracteres")
    private String nome;

    @NotBlank(message = "Tipo é obrigatório")
    @NotNull(message = "Tipo não pode ser nulo")
    @Size(min=4, message = "Tipo deve ter pelo menos 4 caracteres")
    private String tipo;

    @NotBlank(message = "Raça é obrigatória")
    @NotNull(message = "Raça não pode ser nula")
    @Size(min=4, message = "Raça deve ter pelo menos 4 caracteres")
    private String raca;

    @NotNull(message = "Data não pode ser nula")
    private LocalDate dataNascimento;

    @NotNull(message = "Peso não pode ser nulo")
    @Min(value = 0, message = "peso não pode ser negativo")
    private double peso;

    private SexoType sexo;

    @NotNull(message = "não pode ser nulo")
    private boolean castrado;

    @NotBlank(message = "Cpf é obrigatório")
    @NotNull(message = "Cpf não pode ser nulo")
    @Pattern(regexp= "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$",
            message= "Formato de CPF inválido, use 12345678900")
    private String tutorCpf;
}
