
package novo.emporio.projeto.modelo.modelo.entidade;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class venda {
    private Long id;
    private BigDecimal totalvenda;
    private BigDecimal valorpago;   
    private BigDecimal descontototal;
    private BigDecimal troco;
    private Long clienteid;
    private Long usuarioid;
    private LocalDateTime datacriacao;
    private LocalDateTime dataalteracao;

}