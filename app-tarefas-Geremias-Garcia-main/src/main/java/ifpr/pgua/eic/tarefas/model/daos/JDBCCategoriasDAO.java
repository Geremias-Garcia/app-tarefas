package ifpr.pgua.eic.tarefas.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Categoria;

public class JDBCCategoriasDAO implements CategoriasDAO{
    private static final String INSERTSQL = "INSERT INTO categorias (nome,descricao) VALUES (?,?)";
    private static final String SELECTSQL = "SELECT * FROM categorias";
    private static final String SELECTSQLID = "SELECT * FROM categorias WHERE id = (?)";

    private FabricaConexoes fabrica;
    private Categoria categoria;


    public JDBCCategoriasDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Categoria categorias) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement(INSERTSQL, Statement.RETURN_GENERATED_KEYS);
            
            // Ajustar os parâmetros
            pstm.setString(1, categorias.getNome());
            pstm.setString(2, categorias.getDescricao());
            // Executar o comando
            int ret = pstm.executeUpdate();

            if (ret == 1) {
                return Resultado.sucesso("Categoria cadastrada", categoria);
            }
            return Resultado.erro("Erro não identificado!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {

        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(SELECTSQL);

            ResultSet rs = pstm.executeQuery();

            ArrayList<Categoria> lista = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");

                //iremos buscar artista e genero através do repositório
                Categoria categoria = new Categoria(id,nome, descricao);

                lista.add(categoria);
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
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                categoria = new Categoria(id, nome, descricao);
            }
            
            return Resultado.sucesso("Categoria", categoria);
        }catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
    
}
