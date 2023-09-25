package ifpr.pgua.eic.tarefas.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Categoria;
import ifpr.pgua.eic.tarefas.model.entities.Tarefa;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioCategorias;
import ifpr.pgua.eic.tarefas.utils.DBUtils;

public class JDBCTarefasDAO implements TarefasDAO {

    private static final String INSERTSQL = "INSERT INTO tarefas (titulo,descricao,prazo,categoria_id) VALUES (?,?,?,?)";
    private static final String SELECTSQL = "SELECT * FROM tarefas";
    private static final String SELECTSQLID = "SELECT * FROM categorias WHERE id = (?)";

    private FabricaConexoes fabrica;
    private RepositorioCategorias repositorioCategorias;
    private Tarefa tarefa;

    public JDBCTarefasDAO(FabricaConexoes fabrica, RepositorioCategorias repositorioCategorias) {
        this.fabrica = fabrica;
        this.repositorioCategorias = repositorioCategorias;
    }

    @Override
    public Resultado criarTarefa(Tarefa tarefa) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(INSERTSQL, Statement.RETURN_GENERATED_KEYS);

            java.sql.Date sqlDate = java.sql.Date.valueOf(tarefa.getPrazo());
            
            pstm.setString(1, tarefa.getTitulo());
            pstm.setString(2, tarefa.getDescricao());
            pstm.setDate(3, sqlDate);
            pstm.setInt(4, tarefa.getCategoria().getId());
            

            int ret = pstm.executeUpdate();

            if(ret == 1){
                int id = DBUtils.getLastId(pstm);

                tarefa.setId(id);

                return Resultado.sucesso("Tarefa cadastrada", tarefa);
            }
            return Resultado.erro("Erro desconhecido!");


        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(SELECTSQL);

            ResultSet rs = pstm.executeQuery();
            
            ArrayList<Tarefa> lista = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                LocalDate prazo = rs.getDate("prazo").toLocalDate();

                int idCategoria = rs.getInt("categoria_id");

                Resultado resultado = repositorioCategorias.buscarPorId(idCategoria);

                if(resultado.foiErro()){
                    return resultado;
                }
                Categoria categoria = (Categoria)resultado.comoSucesso().getObj();

                Tarefa tarefa = new Tarefa(id,titulo, descricao, prazo,categoria);

                lista.add(tarefa);
            }

            return Resultado.sucesso("Categorias listadas", lista);

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado buscarPorId(int id) {
        
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(SELECTSQLID);

            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                LocalDate prazo = rs.getDate("prazo").toLocalDate();

                int idCategoria = rs.getInt("categoria_id");

                Resultado resultado = repositorioCategorias.buscarPorId(idCategoria);

                if(resultado.foiErro()){
                    return resultado;
                }
                Categoria categoria = (Categoria)resultado.comoSucesso().getObj();

                tarefa = new Tarefa(id,titulo, descricao, prazo,categoria);
            }

            return Resultado.sucesso("Categorias listadas", tarefa);
        }catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
    
}
