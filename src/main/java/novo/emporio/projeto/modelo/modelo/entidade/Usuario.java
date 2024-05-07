package novo.emporio.projeto.modelo.modelo.entidade;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String perfil;
    private Boolean estado;
    private LocalDateTime dataCriacao;
    private LocalDateTime ultimoLogin;
}
