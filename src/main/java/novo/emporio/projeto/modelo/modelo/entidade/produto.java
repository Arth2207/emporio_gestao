
package novo.emporio.projeto.modelo.modelo.entidade;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.swing.JTextField;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class produto {
   private Long Id;
   private String Nome;
   private String Descricao;
   private Integer Quantidade;
   private BigDecimal Preco;
   private Long CategoriaId;
   private LocalDateTime datacriacao; 
}