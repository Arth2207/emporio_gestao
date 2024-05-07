
package novo.emporio.projeto.modelo.modelo.util;

import java.lang.reflect.Field;
import java.util.Set;
import org.reflections.ReflectionUtils;

public class SQLformatoAtualiza<T> implements SQLformate {
        private Class<T> t;

    public SQLformatoAtualiza(Class<T> t) {
        this.t = t;
    }

    @Override

    public String formato() {

        Set<Field> campos = ReflectionUtils.getFields(this.t, e -> true);
        StringBuilder atributos = new StringBuilder();
        try {
            for (Field campo : campos) {
             if(campo.getName().equalsIgnoreCase("id")) continue;
             
             atributos.append(campo.getName()).append("=?,");
            }
            
            String atributo = removerUltimoCaracter(atributos.toString());
            String SQL = String.format("UPDATE %s SET %s WHERE id = ?", t.getSimpleName(), atributo);
            
            return SQL;
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    private String removerUltimoCaracter(String texto){
       if(!texto.isBlank()){
        return texto.substring(0, texto.length() -1);
       }
       throw new RuntimeException("Texto está vazio");
    }
}
