package model;

import app.Usuario;
import app.Administrador;
import app.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "AdministradorDAO", urlPatterns = {"/AdministradorDAO"})
public class AdministradorDAO extends HttpServlet {

    private Connection conexao;
    
    public AdministradorDAO() {
        try {
            conexao = conexaoBD.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }
    /*----------------------------------------------------------FUNÇÕES SOBRE USUÁRIOS----------------------------------------------------------*/
    //cadastra usuários no sistema
    public boolean cadastrarUsuario( Usuario usuario ) {
        try {
            String sql;

            sql = "INSERT INTO usuarios (nome,cpf,senha,suspenso) VALUES (?,?,?,?)";
 
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCpf());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getSuspenso());
            
            ps.execute();
            
            return true;
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    //edita os usuários do sistema
    public boolean editarUsuario( Usuario usuario ) {
        try {
            String sql;

            sql = "UPDATE usuarios SET nome=?, senha=?, suspenso=? WHERE cpf = ?";
 
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getSuspenso());
            ps.setString(4, usuario.getCpf());
            
            ps.execute();
            
            return true;
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    //exclui usuários do sistema
    public boolean excluirUsuario(String cpf ) {
        try {
            String sql = "DELETE FROM usuarios WHERE cpf = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.execute();
            
            return true;
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    //lista os usuários
    public ArrayList<Usuario> getListaUsuarios() {

        ArrayList<Usuario> resultado = new ArrayList<>();
        try {            
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios ORDER BY id");
 
            while( rs.next() ) {
                Usuario usuario = new Usuario();
                
                usuario.setId(rs.getInt("id"));
                usuario.setNome( rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setSenha( rs.getString("senha"));
                usuario.setSuspenso( rs.getString("suspenso"));
   
                resultado.add(usuario);
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return resultado;
    }
    
    //lista os usuários liberados
    public ArrayList<Usuario> getListaUsuariosLiberados() {
        ArrayList<Usuario> resultado = new ArrayList<>();
        
        try {            
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE suspenso = 'N' ORDER BY id");

            while( rs.next() ) {
                Usuario usuario = new Usuario();
                
                usuario.setId(rs.getInt("id"));
                usuario.setNome( rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setSenha( rs.getString("senha"));
                usuario.setSuspenso( rs.getString("suspenso"));
                resultado.add(usuario);
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return resultado;
    }
    
    //lista os usuários suspensos
    public ArrayList<Usuario> getListaUsuariosSuspensos() {
        ArrayList<Usuario> resultado = new ArrayList<>();
        
        try {            
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE suspenso='S' ORDER BY id");

            while( rs.next() ) {
                Usuario usuario = new Usuario();
                
                usuario.setId(rs.getInt("id") );
                usuario.setNome( rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setSenha( rs.getString("senha") );
                usuario.setSuspenso( rs.getString("suspenso"));
                resultado.add(usuario);
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return resultado;
    }
    
    //libera usuários que estão suspensos
    public boolean liberarUsuario(String cpf) {
        try {
            String sql;

            sql = "UPDATE usuarios SET suspenso='N' WHERE cpf = ?";
 
            PreparedStatement ps = conexao.prepareStatement(sql);
  
            ps.setString(1, cpf);
            
            ps.execute();
            
            return true;           
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    //suspende usuários que estão liberados
    public boolean suspenderUsuario(String cpf) {
        try {
            String sql;

            sql = "UPDATE usuarios SET suspenso='S' WHERE cpf = ?";
 
            PreparedStatement ps = conexao.prepareStatement(sql);
  
            ps.setString(1, cpf);
            
            ps.execute();
  
            return true;    
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    /*----------------------------------------------------------FUNÇÕES SOBRE ADMINISTRADORES----------------------------------------------------------*/
    //cadastra administradores no sistema
    public boolean cadastrarAdministrador( Administrador administrador ) {
        try {
            String sql;
           
            sql = "INSERT INTO administradores (nome,cpf,senha) VALUES (?,?,?)";
       
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, administrador.getNome());
            ps.setString(2, administrador.getCpf());
            ps.setString(3, administrador.getSenha());
            
            ps.execute();
            
            return true;
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    //edita os administradores do sistema
    public boolean editarAdministrador(Administrador admin) {
        try {
            String sql;

            sql = "UPDATE administradores SET nome=?, senha=? WHERE cpf = ?";
 
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, admin.getNome());
            ps.setString(2, admin.getSenha());
            ps.setString(3, admin.getCpf());
            
            ps.execute();
            
            return true;
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    //exclui administradores do sistema
    public boolean excluirAdministrador(String cpf) {
        try {
            String sql = "DELETE FROM administradores WHERE cpf = ?";
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, cpf);
            
            ps.execute();
            
            return true;   
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    //lista os administradores
    public ArrayList<Administrador> getListaAdministradores() {
        ArrayList<Administrador> resultado = new ArrayList<>();
        try {            
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM administradores ORDER BY id");
            
            while( rs.next() ) {
                Administrador administrador = new Administrador();
                
                administrador.setId(rs.getInt("id") );
                administrador.setNome( rs.getString("nome") );
                administrador.setCpf(rs.getString("cpf") );
                administrador.setSenha( rs.getString("senha") );
                resultado.add(administrador);
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return resultado;
    }
    
    /*----------------------------------------------------------FUNÇÕES SOBRE CATEGORIAS----------------------------------------------------------*/
    //cadastra categorias no sistema
    public boolean cadastrarCategoria( Categoria categoria ) {
        try {
            String sql;
            
            sql = "INSERT INTO categorias (descricao) VALUES (?)";

            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, categoria.getDescricao());
            
            if ( categoria.getId()> 0 )
                ps.setInt(5, categoria.getId());
            
            ps.execute();
            
            return true;
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    //edita as categorias no sistema
    public boolean editarCategoria(Categoria cat) {
        try {
            String sql;

            sql = "UPDATE categorias SET descricao=? WHERE id = ?";
 
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, cat.getDescricao());
            ps.setInt(2, cat.getId());
            
            ps.execute();
            
            return true;
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    //exclui categorias do sistema
    public boolean excluirCategoria(String descricao) {
        try {
            String sql = "DELETE FROM categorias WHERE descricao = ?";
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, descricao);
            
            ps.execute();
            
            return true;      
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    //lista as categorias
    public ArrayList<Categoria> getListaCategorias() {
        ArrayList<Categoria> resultado = new ArrayList<>();
        
        try {            
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM categorias ORDER BY id");

            while( rs.next() ) {
                Categoria categoria = new Categoria();
                
                categoria.setId(rs.getInt("id"));
                categoria.setDescricao( rs.getString("descricao"));
                resultado.add(categoria);
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } 
        return resultado;
    }
}