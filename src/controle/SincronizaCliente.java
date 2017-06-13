/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import util.ConectaBancoMysql;
import util.ConectaBancoPostgres;

/**
 *
 * @author PEGAZUS
 */
public class SincronizaCliente {
    
    ConectaBancoMysql conectaMysql = new ConectaBancoMysql();
    ConectaBancoPostgres conectaPosgres = new ConectaBancoPostgres();
    String nome;
    
    public void SincCliente(){
        try{
            //Trecho do código onde faz o select no banco primários
            conectaPosgres.conexao();
            conectaPosgres.executaSQL("select *from cliente");
            
            //Trecho do código onde vai pegando todos os dados REPETIÇÃO
            while(conectaPosgres.rs.next()){
               nome = conectaPosgres.rs.getString("nome");
               System.out.println(nome); 
            //fim do trecho do código onde faz o select no banco primários
               
            //Trecho do código onde faz a inserção de dados   
               conectaMysql.conexao();
                PreparedStatement pst = conectaMysql.conn.prepareStatement("INSERT INTO tblcliente(nometblCliente) SELECT ? FROM DUAL WHERE NOT EXISTS(SELECT nometblCliente FROM tblcliente WHERE nometblCliente = ?)");
                pst.setString(1, nome);
                pst.setString(2, nome);
                pst.execute();
            }
            //Fim trecho do código onde faz a inserção de dados   
            
            //Desconecta do banco de dados
            conectaMysql.desconecta();
            conectaPosgres.desconecta();
            
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao buscar os dados do pedido de venda!\nERRO:"+ex);
        }
        
    }
    
    
}
