
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

public class Estoquehistorico {
    private Long id;
    private String produto;
    private Integer quantidade;
    private String estado;
    private String observacao;
    private String usuarioid;
    private LocalDateTime datacriacao;
}
