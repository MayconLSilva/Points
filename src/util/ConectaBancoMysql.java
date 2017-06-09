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
public class ConectaBancoMysql {
    
    public Statement stm;
    public ResultSet rs;
    private String driver ="org.mysql.driver";    
    public Connection conn;
    private String bancodadosmysql;
    private String usuariomysql;
    private String senhamysql;
    private String servidormysql;
    
    private String nomeCaminhoFinal;
    
    public void LerXml() {
        //Ler Caminho do executavel
        String nomeArquivoConfig = "\\configPoints.xml";
        try {
            File file = new File(".");
            String nomeCaminhoSistema = file.getCanonicalPath();
            nomeCaminhoFinal = nomeCaminhoSistema + nomeArquivoConfig;            
        } catch (Exception erro) {
        }
        //Fim do ler Caminho do executavel
        
        String mostra = "";        
        String nomeArq = "dist\\configPoints.xml"; //Nome do arquivo, pode ser absoluto, ou pastas /dir/teste.txt
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
                    servidormysql = leitor.readLine();
                    if (servidormysql == null) {
                        break;
                    }
                    
                    
                    bancodadosmysql = leitor.readLine(); //</pessoa
                    usuariomysql = leitor.readLine();
                    senhamysql = leitor.readLine();
                    //Para pegar entre as Tags, foi preciso criar uma lógica para quebrar a String
                    servidormysql = servidormysql.substring(servidormysql.indexOf(">") + 1, servidormysql.indexOf("</") - 1); //pegando entre as tags
                    bancodadosmysql = bancodadosmysql.substring(bancodadosmysql.indexOf(">") + 1, bancodadosmysql.indexOf("</") - 0); //pegando entre as tags
                    usuariomysql = usuariomysql.substring(usuariomysql.indexOf(">") + 1, usuariomysql.indexOf("/") - 1);
                    senhamysql = senhamysql.substring(senhamysql.indexOf(">") + 1, senhamysql.indexOf("/") - 1);

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
        
            try {
                System.setProperty("jdbc.Drivers",driver);
                conn=DriverManager.getConnection(bancodadosmysql, usuariomysql,senhamysql);
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
