
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

public class estoque {
    private Long id;
    private Long produtoid;
    private Integer quantidade;
    private Boolean estado;
    private Long usuarioid;
    private LocalDateTime datacriacao;
    
}
