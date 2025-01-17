
package novo.emporio.projeto.modelo.modelo.repositorio.impl;

import novo.emporio.projeto.modelo.modelo.repositorio.repositorio;
import java.util.Set;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import novo.emporio.projeto.modelo.modelo.conexao.ConexaoMySQL;
import novo.emporio.projeto.modelo.modelo.util.SQLformate;
import novo.emporio.projeto.modelo.modelo.util.SQLformatoAtualiza;
import novo.emporio.projeto.modelo.modelo.util.SQLformatoInserir;
import org.reflections.ReflectionUtils;

public abstract class crudrepositorioimplento<T> implements repositorio<T> {

    private Class<T> t;

    public crudrepositorioimplento(Class<T> t) {
        this.t = t;
    }


    @Override
    public boolean salvar(T t) {
        Object id = null;
        Set<Field> campos = ReflectionUtils.getFields(this.t, e -> true);

        try {
            for (Field campo : campos) {
                campo.setAccessible(true);
                if (campo.getName().equalsIgnoreCase("id")) {
                    id = campo.get(t);
                }
            }
            if (id == null) {
                SQLformate sql = new SQLformatoInserir(this.t);
                return persistencia(sql.formato(), t, false);
            }

            SQLformate sql = new SQLformatoAtualiza(this.t);
            return persistencia(sql.formato(), t, true);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private boolean persistencia(String sql, Object t, boolean atualiza) {
        try {
            PreparedStatement ps = ConexaoMySQL.obterconexao().prepareStatement(sql);

            preencherPs(t, ps, atualiza);

            int resultado = ps.executeUpdate();

            return resultado == 1;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void preencherPs(Object t, PreparedStatement ps, boolean atualiza) {
        int contador = 1;
        Set<Field> campos = ReflectionUtils.getFields(this.t, e -> true);

        try {
            for (Field campo : campos) {
                campo.setAccessible(true);
                if (atualiza && campo.getName().equalsIgnoreCase("id")) {
                    ps.setObject(campos.size(), campo.get(t));
                    continue;
                }

                ps.setObject(contador, campo.get(t));
                contador++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public List<T> buscarTodos(){
        List<T> lista = new ArrayList<>();
        try {
            String SQL = String.format("SELECT * FROM %s", t.getSimpleName());
            ResultSet resultset = ConexaoMySQL.obterconexao().prepareStatement(SQL)
                    .executeQuery();
            
            while(resultset.next()){
              lista.add(getT(resultset));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    
     private T getT(ResultSet resultSet){
         try {
            T tNovo = t.newInstance();
            Method metodo = null;
            Set<Field> campos = ReflectionUtils.getAllFields(t, e -> true);
            
            for(Field campo : campos){
               Object valor = null;
               String nome = campo.getName();
               
               switch(campo.getType().getSimpleName().toUpperCase()){
                   case "STRING" -> { 
                   valor = resultSet.getString(nome);
                   metodo = t.getMethod("set" + primeiraLetraMaiuscula(nome), String.class);
                   }
                   
                   case "LONG" -> { 
                   valor = resultSet.getLong(nome);
                   metodo = t.getMethod("set" + primeiraLetraMaiuscula(nome), Long.class);
                   }
                   case "INTENGER" -> { 
                   valor = resultSet.getInt(nome);
                   metodo = t.getMethod("set" + primeiraLetraMaiuscula(nome), Integer.class);
                   }
                    case "BOOLEAN" -> { 
                   valor = resultSet.getBoolean(nome);
                   metodo = t.getMethod("set" + primeiraLetraMaiuscula(nome), Boolean.class);
                   }
                    case "OBJECT" -> { 
                   valor = resultSet.getObject(nome);
                   metodo = t.getMethod("set" + primeiraLetraMaiuscula(nome), Object.class);
                   }
                    case "LOCALDATETIME" -> { 
                   valor = resultSet.getObject(nome, LocalDateTime.class);
                   metodo = t.getMethod("set" + primeiraLetraMaiuscula(nome), LocalDateTime.class);
                   }
                   case "BIGDECIMAL" -> { 
                   valor = resultSet.getBigDecimal(nome);
                   metodo = t.getMethod("set" + primeiraLetraMaiuscula(nome), BigDecimal.class);
                   }
               }
               metodo.invoke(tNovo, valor);
            }
            
            
            return tNovo;
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
     }
     
    private String primeiraLetraMaiuscula(String texto){
         if(!texto.isBlank()){
          return texto.substring(0, 1).toUpperCase().concat(texto.substring(1));
         }
         
         return "";
    
    }

    @Override
    public Optional<T> buscarPeloId(Long id) {
        try {
            String SQL = String.format("SELECT * FROM %s WHERE id = ?", t.getSimpleName());
            
            PreparedStatement ps = ConexaoMySQL.obterconexao().prepareStatement(SQL);
            ps.setLong(1, id);
            
            ResultSet resultSet = ps.executeQuery();
            
            if(resultSet.next()){
              return Optional.ofNullable(getT(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public boolean removerPeloId(Long id) {
        try {
            String SQL = String.format("DELETE FROM %s WHERE id = ?", t.getSimpleName());
            
            PreparedStatement ps = ConexaoMySQL.obterconexao().prepareStatement(SQL);
            ps.setLong(1, id);
            
            int resultado = ps.executeUpdate();
            
            return resultado == 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    
}
