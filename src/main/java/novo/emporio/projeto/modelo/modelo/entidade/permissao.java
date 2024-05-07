
package novo.emporio.projeto.modelo.modelo.entidade;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data


public class permissao {
    private Long id;
    private String nome;
    private String descricao;
}
