/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author PEGAZUS
 */
public class ConectaBancoPostgres {
    
    public Statement stm;
    public ResultSet rs;
    private String driver ="org.postgresql.Driver";    
    public Connection conn;
    private String bancodadospostgres;
    private String usuarioposgres;
    private String senhaposgres;
    private String servidorpostgres;        
    private String nomeCaminhoFinal;
    
    public void LerXml() {
        //Ler Caminho do executavel
        String nomeArquivoConfig = "\\configSecundariaPoints.xml";
        try {
            File file = new File(".");
            String nomeCaminhoSistema = file.getCanonicalPath();
            nomeCaminhoFinal = nomeCaminhoSistema + nomeArquivoConfig;            
        } catch (Exception erro) {
        }
        //Fim do ler Caminho do executavel
        
        String mostra = "";        
        String nomeArq = "dist\\configSecundariaPoints.xml"; //Nome do arquivo, pode ser absoluto, ou pastas /dir/teste.txt
        String linha = "";
        //String servidormysql, bancodadosmysql, usuariomysql, senhamysql;
        File arq = new File(nomeCaminhoFinal);

        //Arquivo existe
        if (arq.exists()) {
            //tentando ler arquivo
            try {
                
                //abrindo arquivo para leitura
                FileReader reader = new FileReader(nomeCaminhoFinal);
                //leitor do arquivo texto (ponteiro)
                BufferedReader leitor = new BufferedReader(reader);
                leitor.readLine(); //<xml
                leitor.readLine(); //<cadastro
                while (true) {
                    leitor.readLine(); //<pessoa
                    servidorpostgres = leitor.readLine();
                    if (servidorpostgres == null) {
                        break;
                    }
                    
                    
                    bancodadospostgres = leitor.readLine(); //</pessoa
                    usuarioposgres = leitor.readLine();
                    senhaposgres = leitor.readLine();
                    //Para pegar entre as Tags, foi preciso criar uma lógica para quebrar a String
                    servidorpostgres = servidorpostgres.substring(servidorpostgres.indexOf(">") + 1, servidorpostgres.indexOf("</") - 1); //pegando entre as tags
                    bancodadospostgres = bancodadospostgres.substring(bancodadospostgres.indexOf(">") + 1, bancodadospostgres.indexOf("</") - 0); //pegando entre as tags
                    usuarioposgres = usuarioposgres.substring(usuarioposgres.indexOf(">") + 1, usuarioposgres.indexOf("/") - 1);
                    senhaposgres = senhaposgres.substring(senhaposgres.indexOf(">") + 1, senhaposgres.indexOf("/") - 1);

                    leitor.readLine();
                }

            } catch (Exception erro) {
            }
            
        } //Se nao existir
        else {
            JOptionPane.showMessageDialog(null, "Arquivo nao existe!", "Erro", 0);
        }

    }
    
    public void conexao(){ 
        LerXml();
        
            try { //jdbc.Drivers
                System.setProperty("jdbc.drivers",driver);                
                conn=DriverManager.getConnection(bancodadospostgres, usuarioposgres,senhaposgres);
                
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro de conexão!\n Erro:" + ex.getMessage());
            }
            
        }
    
    public void executaSQL(String sql){
        LerXml();
        try {
            stm = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de ExecutaSQL!\n Erro:" + ex.getMessage());
        }
        
    }
    
    public void desconecta(){
        try{
            conn.close();
            //JOptionPane.showMessageDialog(null,"Desconectado com sucesso!");
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Erro ao fechar a conexão!\n Erro:" + ex.getMessage());
        }
    }
        
    public static void main(String[] args) {
        
        
        
    }

      

}
