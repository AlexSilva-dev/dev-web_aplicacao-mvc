package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Usuario;

/*
-- Estrutura da tabela `usuarios`

CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `senha` varchar(8) NOT NULL,
  `endereco` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

 */
public class UsuarioDAO {

    public void inserir(Usuario usuario) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO usuarios (nome, cpf, endereco, senha)"
                    + " VALUES (?,?,?,?)");
            sql.setString(1, usuario.getNome());
            sql.setString(2, usuario.getCpf());
            sql.setString(3, usuario.getEndereco());
            sql.setString(4, usuario.getSenha());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("no dao");
        } finally {
            conexao.closeConexao();
        }
    }

    public Usuario getUsuario(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Usuario usuario = new Usuario();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM usuarios WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    usuario.setId(Integer.parseInt(resultado.getString("ID")));
                    usuario.setNome(resultado.getString("NOME"));
                    usuario.setCpf(resultado.getString("CPF"));
                    usuario.setEndereco(resultado.getString("ENDERECO"));
                    usuario.setSenha(resultado.getString("SENHA"));
                    usuario.setAprovado(resultado.getString("aprovado"));
                }
            }
            return usuario;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void alterar(Usuario usuario) throws Exception {
        Conexao conexao = new Conexao();

        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE usuarios SET nome = ?, cpf = ?, endereco = ?, senha = ?, aprovado = ?  WHERE id = ? ");
            sql.setString(1, usuario.getNome());
            sql.setString(2, usuario.getCpf());
            sql.setString(3, usuario.getEndereco());
            sql.setString(4, usuario.getSenha());
            sql.setString(5, usuario.getAprovado());
            sql.setInt(6, usuario.getId());

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void excluir(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM usuarios WHERE id = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Usuario> ListaDeUsuarios() {
        ArrayList<Usuario> meusUsuarios = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM usuarios order by nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Usuario usuario = new Usuario(resultado.getString("NOME"),
                            resultado.getString("CPF"),
                            resultado.getString("ENDERECO"),
                            resultado.getString("SENHA"));
                    usuario.setId(Integer.parseInt(resultado.getString("id")));
                    meusUsuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeUsuarios) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusUsuarios;
    }

    public Usuario Logar(Usuario usuario) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM usuarios WHERE cpf=? and senha =? LIMIT 1");
            sql.setString(1, usuario.getCpf());
            sql.setString(2, usuario.getSenha());
            ResultSet resultado = sql.executeQuery();
            Usuario usuarioObtido = new Usuario();
            if (resultado != null) {
                while (resultado.next()) {
                    usuarioObtido.setId(Integer.parseInt(resultado.getString("ID")));
                    usuarioObtido.setNome(resultado.getString("NOME"));
                    usuarioObtido.setCpf(resultado.getString("CPF"));
                    usuarioObtido.setEndereco(resultado.getString("ENDERECO"));
                    usuarioObtido.setSenha(resultado.getString("SENHA"));
                    usuarioObtido.setAprovado(resultado.getString("aprovado"));
                }
            }
            return usuarioObtido;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Usuario> getAll() {
        ArrayList<Usuario> meusUsuarios = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM usuarios";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Usuario usuario = new Usuario(
                            resultado.getInt("id"),
                            resultado.getString("nome"),
                            resultado.getString("cpf"),
                            resultado.getString("endereco"),
                            resultado.getString("senha")
                    );
                    usuario.setAprovado(resultado.getString("aprovado"));
                    meusUsuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (GetAll) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusUsuarios;
    }

}
