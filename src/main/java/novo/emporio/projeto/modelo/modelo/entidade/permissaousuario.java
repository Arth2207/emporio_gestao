
package novo.emporio.projeto.modelo.modelo.entidade;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class permissaousuario {
   Long permissaid;
   Long usuarioid;
}
