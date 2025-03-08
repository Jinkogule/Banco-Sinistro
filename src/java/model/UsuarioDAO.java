package model;

import app.Categoria;
import app.Conta;
import app.Lancamento;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

@WebServlet(name = "UsuarioDAO", urlPatterns = {"/UsuarioDAO"})
public class UsuarioDAO extends HttpServlet {
    private Connection conexao;
    
    public UsuarioDAO() {
        try {
            // Cria a conexão com o banco de dados
            conexao = conexaoBD.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }
    
    /*----------------------------------------------------------FUNÇÕES SOBRE CONTAS----------------------------------------------------------*/
    //usada para o usuário logado na sessão cadastrar uma conta para si
    public boolean cadastrarConta(Conta conta) {
        try {
            String sql;

            sql = "INSERT INTO contas (id_usuario, nome_conta, banco, agencia, conta_corrente) VALUES (?,?,?,?,?)";
 
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, conta.getId_usuario());
            ps.setString(2, conta.getNome_conta());
            ps.setString(3, conta.getBanco());
            ps.setString(4, conta.getAgencia());
            ps.setString(5, conta.getConta_corrente());
            
            if ( conta.getId()> 0 )
                ps.setInt(6, conta.getId());
            
            ps.execute();
            
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    //usada para o usuário logado na sessão editar suas contas   
    public boolean editarConta(Conta conta) {
        try {
            String sql;

            sql = "UPDATE contas SET nome_conta=?, agencia=?, conta_corrente=? WHERE id_usuario = ? AND banco=?";
 
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, conta.getNome_conta());
            ps.setString(2, conta.getAgencia());
            ps.setString(3, conta.getConta_corrente());
            ps.setInt(4, conta.getId_usuario());
            ps.setString(5, conta.getBanco());
            
            ps.execute();
            
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    //usada para o usuário logado na sessão excluir uma de suas contas
    public boolean excluirConta(int id) {
        try {
            String sql = "DELETE FROM contas WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            
            return true;
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    //usada para listar as contas do usuário logado na sessão
    public ArrayList<Conta> getListaContas(Integer id_usuario) {
        ArrayList<Conta> resultado = new ArrayList<>();
        
        try {            
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM contas WHERE id_usuario="+id_usuario);
            
            while( rs.next() ) {
                Conta conta = new Conta();
                
                conta.setId(rs.getInt("id") );
                conta.setNome_conta(rs.getString("nome_conta") );
                conta.setBanco( rs.getString("banco"));
                conta.setAgencia( rs.getString("agencia"));
                conta.setConta_corrente( rs.getString("conta_corrente"));
                
                resultado.add(conta);
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } 
        return resultado;
    }
    
        public ArrayList<Conta> getListaContasPorId(Integer id_usuario, int id_conta) {
        ArrayList<Conta> resultado = new ArrayList<>();
        
        try {            
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM contas WHERE id_usuario="+id_usuario+" AND id_conta="+id_conta);
            
            while( rs.next() ) {
                Conta conta = new Conta();
                
                conta.setId(rs.getInt("id") );
                conta.setNome_conta(rs.getString("nome_conta") );
                conta.setBanco( rs.getString("banco"));
                conta.setAgencia( rs.getString("agencia"));
                conta.setConta_corrente( rs.getString("conta_corrente"));
                
                resultado.add(conta);
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } 
        return resultado;
    }
    /*----------------------------------------------------------FUNÇÕES SOBRE LANÇAMENTOS----------------------------------------------------------*/
    //usada para cadastrar um lançamento sobre uma conta do usuário logado na sessão    
    public boolean cadastrarLancamento(Lancamento lancamento) {
        try {
            String sql;

            sql = "INSERT INTO lancamentos (id_conta, id_categoria, valor, operacao, data, descricao) VALUES (?,?,?,?,?,?)";
 
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, lancamento.getId_conta());
            ps.setInt(2, lancamento.getId_categoria());
            ps.setFloat(3, lancamento.getValor());
            ps.setString(4, lancamento.getOperacao());
            ps.setString(5, lancamento.getData());
            ps.setString(6, lancamento.getDescricao());
            
            ps.execute();
            
            return true;
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    //usada para listar os lançamentos feitos pelo usuário logado na sessão
    public ArrayList<Lancamento> getListaLancamentos(Integer id_usuario) {
        ArrayList<Lancamento> resultado = new ArrayList<>();
        
        try {            
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM lancamentos JOIN contas ON (lancamentos.id_conta = contas.id)"
                    + " WHERE contas.id_usuario="+id_usuario+" ORDER BY contas.id, data");
            
            while( rs.next() ) {
                Lancamento lancamento = new Lancamento();
                
                lancamento.setId(rs.getInt("id") );
                lancamento.setId_conta(rs.getInt("id_conta") );
                lancamento.setId_categoria(rs.getInt("id_categoria"));
                lancamento.setValor(rs.getFloat("valor"));
                lancamento.setOperacao(rs.getString("operacao"));
                lancamento.setData(rs.getString("data"));
                lancamento.setDescricao(rs.getString("descricao"));

                resultado.add(lancamento);
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return resultado;
    }
    
    /*----------------------------------------------------------OUTRAS FUNÇÕES----------------------------------------------------------*/
    //usada para mostrar nome da categoria na lista de lançamentos do usuário logado na sessão
    public String getNomeContaPorId(int id_conta){
        try {
            conexao = conexaoBD.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
        try {
            
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM contas WHERE id="+id_conta);
            while( rs.next() ) {
                Conta conta = new Conta();
                conta.setNome_conta(rs.getString("nome_conta"));
                
                return rs.getString("nome_conta");
                
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return null;
    }
    
    //usada para mostrar nome da categoria na lista de lançamentos do usuário logado na sessão
    public String getNomeCategoriaPorId(int id_categoria){
        try {
            conexao = conexaoBD.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
        try {
            
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categorias WHERE id="+id_categoria);
            while( rs.next() ) {
                Categoria categoria = new Categoria();
                categoria.setDescricao(rs.getString("descricao"));
                
                return rs.getString("descricao");
                
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return null;
    }
  
    //usada para mostrar o saldo de cada categoria sobre cada conta do usuário logado na sessão
    public float getSaldoPorCategoria(Integer id_usuario, int id_conta, int id_categoria) {
        try {            
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT categorias.id, id_conta, nome_conta, id_usuario,"
                    + " sum(CASE WHEN lancamentos.operacao = 'C' AND categorias.id ="+id_categoria+" THEN lancamentos.valor ELSE 0 END)"
                    + "-sum(CASE WHEN lancamentos.operacao = 'D' AND categorias.id ="+id_categoria+" THEN lancamentos.valor ELSE 0 END)"
                    + " AS saldo_conta FROM lancamentos JOIN contas ON (lancamentos.id_conta = contas.id) JOIN categorias ON"
                    + " (categorias.id = lancamentos.id_categoria) WHERE id_usuario ="+id_usuario+" AND contas.id ="+id_conta+" AND"
                    + " categorias.id ="+id_categoria+" GROUP  BY categorias.id ORDER BY contas.id;");

            while( rs.next() ) {

                return rs.getFloat("saldo_conta");       
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }  
        return 0;
    }
    
    public float getDebitoPorCategoria(Integer id_usuario, int id_conta, int id_categoria) {
        try {            
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT categorias.id, id_conta, nome_conta, id_usuario,"
                    + " sum(CASE WHEN lancamentos.operacao = 'D' AND categorias.id ="+id_categoria+" THEN lancamentos.valor ELSE 0 END)"
                    + " AS saldo_conta FROM lancamentos JOIN contas ON (lancamentos.id_conta = contas.id) JOIN categorias ON"
                    + " (categorias.id = lancamentos.id_categoria) WHERE id_usuario ="+id_usuario+" AND contas.id ="+id_conta+" AND"
                    + " categorias.id ="+id_categoria+" GROUP  BY categorias.id ORDER BY contas.id;");

            while( rs.next() ) {

                return rs.getFloat("saldo_conta");       
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }  
        return 0;
    }
    
    public float getCreditoPorCategoria(Integer id_usuario, int id_conta, int id_categoria) {
        try {            
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT categorias.id, id_conta, nome_conta, id_usuario,"
                    + " sum(CASE WHEN lancamentos.operacao = 'C' AND categorias.id ="+id_categoria+" THEN lancamentos.valor ELSE 0 END)"                
                    + " AS saldo_conta FROM lancamentos JOIN contas ON (lancamentos.id_conta = contas.id) JOIN categorias ON"
                    + " (categorias.id = lancamentos.id_categoria) WHERE id_usuario ="+id_usuario+" AND contas.id ="+id_conta+" AND"
                    + " categorias.id ="+id_categoria+" GROUP  BY categorias.id ORDER BY contas.id;");

            while( rs.next() ) {

                return rs.getFloat("saldo_conta");       
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }  
        return 0;
    }
    
    
    //usada para mostrar o saldo total de cada conta do usuário logado na sessão
    public float getSaldoContas(Integer id_usuario, int id_conta) {
        try {            
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT id_conta, nome_conta, id_usuario, sum(CASE WHEN lancamentos.operacao = 'C' THEN lancamentos.valor ELSE 0 END)"
                    + "-sum(CASE WHEN lancamentos.operacao = 'D' THEN lancamentos.valor ELSE 0 END) AS saldo_conta FROM lancamentos JOIN contas ON"
                    + "(lancamentos.id_conta = contas.id) WHERE id_usuario = "+id_usuario+" AND contas.id ="+id_conta+" GROUP  BY contas.nome_conta");
            
            while( rs.next() ) {
                return rs.getFloat("saldo_conta");        
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } 
        return 0;
    }
    //usada para mostrar o saldo total das contas do usuário logado na sessão
    public float getSaldoContasTotal(Integer id_usuario) {
        try {            
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT sum(CASE WHEN lancamentos.operacao = 'C' THEN lancamentos.valor ELSE 0 END)"
                    + "-sum(CASE WHEN lancamentos.operacao = 'D' THEN lancamentos.valor ELSE 0 END) AS saldo_conta FROM lancamentos"
                    + " JOIN contas ON (lancamentos.id_conta = contas.id) JOIN usuarios ON (contas.id_usuario = usuarios.id) WHERE"
                    + " usuarios.id ="+id_usuario);
            
            while( rs.next() ) {
                return rs.getFloat("saldo_conta");        
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } 
        return 0;
    }
    
        public float getCreditadoContasTotal(Integer id_usuario) {
        try {            
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT sum(CASE WHEN lancamentos.operacao = 'C' THEN lancamentos.valor ELSE 0 END)"
                    + " AS saldo_conta FROM lancamentos"
                    + " JOIN contas ON (lancamentos.id_conta = contas.id) JOIN usuarios ON (contas.id_usuario = usuarios.id) WHERE"
                    + " usuarios.id ="+id_usuario);
            
            while( rs.next() ) {
                return rs.getFloat("saldo_conta");        
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } 
        return 0;
    }
        
    public float getDebitadoContasTotal(Integer id_usuario) {
        try {            
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT "
                    + "sum(CASE WHEN lancamentos.operacao = 'D' THEN lancamentos.valor ELSE 0 END) AS saldo_conta FROM lancamentos"
                    + " JOIN contas ON (lancamentos.id_conta = contas.id) JOIN usuarios ON (contas.id_usuario = usuarios.id) WHERE"
                    + " usuarios.id ="+id_usuario);
            
            while( rs.next() ) {
                return rs.getFloat("saldo_conta");        
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } 
        return 0;
    }
    
    public boolean verifSaldoTotalNegativo(Integer id_usu){
        if (getSaldoContasTotal(id_usu) < 0){
            return true;
        }
        else{
            return false;
        }
    }
    //usada para verificar se alguma conta do usuário logado na sessão está no negativo e, caso alguma esteja, o usuário ser alertado na view
    public boolean verifExistenciaSaldoNegativo(Integer id_usuario) {
        try {            
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT id_conta, nome_conta, id_usuario, sum(CASE WHEN lancamentos.operacao = 'C' THEN lancamentos.valor ELSE 0 END)"
                    + "-sum(CASE WHEN lancamentos.operacao = 'D' THEN lancamentos.valor ELSE 0 END) AS saldo_conta FROM lancamentos JOIN contas ON"
                    + "(lancamentos.id_conta = contas.id)"
                    + " WHERE id_usuario = "+id_usuario+" GROUP  BY contas.nome_conta");
            
            while( rs.next() ) {
                if (rs.getFloat("saldo_conta") < 0){
                    return true;
                }  
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }  
        return false;
    }
    

    
    public String getPercentualCreditoPorCategoria(Integer id_usuario, int id_conta, int id_categoria) {
        float soma = 0;
        UsuarioDAO usuariodao = new UsuarioDAO();
        try {            
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT categorias.id, id_conta, nome_conta, id_usuario,"
                    + " sum(CASE WHEN lancamentos.operacao = 'C' THEN lancamentos.valor ELSE 0 END)"                
                    + " AS saldo_conta FROM lancamentos JOIN contas ON (lancamentos.id_conta = contas.id) JOIN categorias ON"
                    + " (categorias.id = lancamentos.id_categoria) WHERE id_usuario ="+id_usuario+" AND contas.id ="+id_conta+""
                    + " GROUP  BY categorias.id ORDER BY contas.id;");

            while( rs.next() ) {

                soma += rs.getFloat("saldo_conta");       
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        if (soma == 0){
            return "0,0";
        }
        else{
            float resultado = usuariodao.getCreditoPorCategoria(id_usuario, id_conta, id_categoria)/soma*100;
        
            DecimalFormat df = new DecimalFormat("0.0");
            String resultado_formatado = df.format(resultado);

            return resultado_formatado;
            
        }
    }
    
    
    public String getPercentualDebitoPorCategoria(Integer id_usuario, int id_conta, int id_categoria) {
        float soma = 0;
        UsuarioDAO usuariodao = new UsuarioDAO();
        try {            
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT categorias.id, id_conta, nome_conta, id_usuario,"
                    + " sum(CASE WHEN lancamentos.operacao = 'D' THEN lancamentos.valor ELSE 0 END)"
                    + " AS saldo_conta FROM lancamentos JOIN contas ON (lancamentos.id_conta = contas.id) JOIN categorias ON"
                    + " (categorias.id = lancamentos.id_categoria) WHERE id_usuario ="+id_usuario+" AND contas.id ="+id_conta+""
                    + " GROUP  BY categorias.id ORDER BY contas.id;");

            while( rs.next() ) {

                soma += rs.getFloat("saldo_conta");       
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        if (soma == 0){
            return "0,0";
        }
        else{
            float resultado = usuariodao.getDebitoPorCategoria(id_usuario, id_conta, id_categoria)/soma*100;
        
            DecimalFormat df = new DecimalFormat("0.0");
            String resultado_formatado = df.format(resultado);

            return resultado_formatado;
            
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        public float getDebitoPorCategoriaTotal(Integer id_usuario, int id_categoria) {
        try {            
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT categorias.id, id_conta, nome_conta, id_usuario,"
                    + " sum(CASE WHEN lancamentos.operacao = 'D' AND categorias.id ="+id_categoria+" THEN lancamentos.valor ELSE 0 END)"
                    + " AS saldo_conta FROM lancamentos JOIN contas ON (lancamentos.id_conta = contas.id) JOIN categorias ON"
                    + " (categorias.id = lancamentos.id_categoria) WHERE id_usuario ="+id_usuario+" AND"
                    + " categorias.id ="+id_categoria+" GROUP  BY categorias.id ORDER BY contas.id;");

            while( rs.next() ) {

                return rs.getFloat("saldo_conta");       
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }  
        return 0;
    }
    
    public float getCreditoPorCategoriaTotal(Integer id_usuario, int id_categoria) {
        try {            
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT categorias.id, id_conta, nome_conta, id_usuario,"
                    + " sum(CASE WHEN lancamentos.operacao = 'C' AND categorias.id ="+id_categoria+" THEN lancamentos.valor ELSE 0 END)"                
                    + " AS saldo_conta FROM lancamentos JOIN contas ON (lancamentos.id_conta = contas.id) JOIN categorias ON"
                    + " (categorias.id = lancamentos.id_categoria) WHERE id_usuario ="+id_usuario+" AND"
                    + " categorias.id ="+id_categoria+" GROUP  BY categorias.id ORDER BY contas.id;");

            while( rs.next() ) {

                return rs.getFloat("saldo_conta");       
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }  
        return 0;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        public String getPercentualCreditoPorCategoriaTotal(Integer id_usuario, int id_categoria) {
        float soma = 0;
        UsuarioDAO usuariodao = new UsuarioDAO();
        try {            
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT categorias.id, id_conta, nome_conta, id_usuario,"
                    + " sum(CASE WHEN lancamentos.operacao = 'C' THEN lancamentos.valor ELSE 0 END)"                
                    + " AS saldo_conta FROM lancamentos JOIN contas ON (lancamentos.id_conta = contas.id) JOIN categorias ON"
                    + " (categorias.id = lancamentos.id_categoria) WHERE id_usuario ="+id_usuario+""
                    + " GROUP  BY categorias.id ORDER BY contas.id;");

            while( rs.next() ) {

                soma += rs.getFloat("saldo_conta");       
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        if (soma == 0){
            return "0,0";
        }
        else{
            float resultado = usuariodao.getCreditoPorCategoriaTotal(id_usuario, id_categoria)/soma*100;
        
            DecimalFormat df = new DecimalFormat("0.0");
            String resultado_formatado = df.format(resultado);

            return resultado_formatado;
            
        }
    }
    
    
    public String getPercentualDebitoPorCategoriaTotal(Integer id_usuario, int id_categoria) {
        float soma = 0;
        UsuarioDAO usuariodao = new UsuarioDAO();
        try {            
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT categorias.id, id_conta, nome_conta, id_usuario,"
                    + " sum(CASE WHEN lancamentos.operacao = 'D' THEN lancamentos.valor ELSE 0 END)"
                    + " AS saldo_conta FROM lancamentos JOIN contas ON (lancamentos.id_conta = contas.id) JOIN categorias ON"
                    + " (categorias.id = lancamentos.id_categoria) WHERE id_usuario ="+id_usuario+" "
                    + " GROUP  BY categorias.id ORDER BY contas.id;");

            while( rs.next() ) {

                soma += rs.getFloat("saldo_conta");       
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        if (soma == 0){
            return "0,0";
        }
        else{
            float resultado = usuariodao.getDebitoPorCategoriaTotal(id_usuario, id_categoria)/soma*100;
        
            DecimalFormat df = new DecimalFormat("0.0");
            String resultado_formatado = df.format(resultado);

            return resultado_formatado;
            
        }
    }
    
    
        public float getSaldoPorCategoriaTotal(Integer id_usuario, int id_categoria) {
        try {            
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT categorias.id, id_conta, nome_conta, id_usuario,"
                    + " sum(CASE WHEN lancamentos.operacao = 'C' AND categorias.id ="+id_categoria+" THEN lancamentos.valor ELSE 0 END)"
                    + "-sum(CASE WHEN lancamentos.operacao = 'D' AND categorias.id ="+id_categoria+" THEN lancamentos.valor ELSE 0 END)"
                    + " AS saldo_conta FROM lancamentos JOIN contas ON (lancamentos.id_conta = contas.id) JOIN categorias ON"
                    + " (categorias.id = lancamentos.id_categoria) WHERE id_usuario ="+id_usuario+" AND"
                    + " categorias.id ="+id_categoria+" GROUP  BY categorias.id ORDER BY contas.id;");

            while( rs.next() ) {

                return rs.getFloat("saldo_conta");       
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }  
        return 0;
    }
}