/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package points;

//Imports
import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import view.JDialogConexao;
import view.JFrameSobre;
import view.JFrameDados;

/**
 *
 * @author Maycon L. Silva
 */
public class Points {

    public static void main(String[] args) {

        //Setando o LookAndFeels
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameSobre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameSobre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameSobre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameSobre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //Fim Setando o LookAndFeels

        //Declarando uma constante do tipo TrayIcon
        final TrayIcon trayIcon;

        //Aqui vamos testar se o recurso é suportado
        if (SystemTray.isSupported()) {

            //Declarando uma variavel  do tipo SystemTray
            SystemTray tray = SystemTray.getSystemTray();

            //Declarando uma variavel  do tipo Image que contera a imagem tray.gif
            Image image = Toolkit.getDefaultToolkit().getImage("PointsLogoIcone.gif");

            //Criamos um ActionListener para a ação de encerramento do programa.
            ActionListener exitListener = new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    //Imprime uma mensagem de despedida na tela
                    System.out.println("Saindo...");                    
                    
                    //JOptionPane.showMessageDialog(null, "Saindo...");
                    
                    JOptionPane.showMessageDialog(null,"Saindo...",
               "Sair",JOptionPane.WARNING_MESSAGE);

                    System.exit(0);

                }

            };

            //Criamos um ActionListener para a exibir a tela sobre ao clicarmos  
            ActionListener mostrarJanelaSobre = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    //JOptionPane.showMessageDialog(null,"Siste de Pontuação e Qualidade - Registrador ");
                    new JFrameSobre().setVisible(true);
                }

            };
            
            //Criamos um ActionListener para a exibir a tela ao clicarmos  
            ActionListener mostrarJanelaDados = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    //JOptionPane.showMessageDialog(null,"Siste de Pontuação e Qualidade - Registrador ");
                    new JFrameDados().setVisible(true);
                }

            };
            
            //Criamos um ActionListener para a exibir a tela ao clicarmos  
            ActionListener mostrarJanelaConexao = new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    
                    JDialogConexao dialogConex = new JDialogConexao(new javax.swing.JFrame(), true);
                    dialogConex.setVisible(true);
                }

            };
            
            

            //Criando um objeto PopupMenu com ele aparece todos os menus
            PopupMenu popup = new PopupMenu("Menu de Opções");
            //Fim do objeto PopupMenu com ele aparece todos os menus

            //Criando itens do menu
            MenuItem menuSobre = new MenuItem("Sobre");
            MenuItem menuConexao = new MenuItem("Testar Conexão");
            MenuItem menuDados = new MenuItem("Meus Dados");

            //Criando objetos do tipo Checkbox
            CheckboxMenuItem SincAutomatica = new CheckboxMenuItem("Sinc. Automática");

            //Criando um SubMenu
            PopupMenu SubMenuSincronizacao = new PopupMenu("Sincronização");
            MenuItem SincCliente = new MenuItem("Sincronizar Clientes");
            MenuItem SincAbaste = new MenuItem("Sincronizar Abastecidas");

            SubMenuSincronizacao.add(SincCliente);
            SubMenuSincronizacao.add(SincAbaste);

            MenuItem menuSair = new MenuItem("Sair");

            //Na linha a seguir associamos os objetos aos eventos
            menuSobre.addActionListener(mostrarJanelaSobre);
            menuDados.addActionListener(mostrarJanelaDados);
            menuSair.addActionListener(exitListener);
            menuConexao.addActionListener(mostrarJanelaConexao);
            
            

            //Adicionando itens ao PopupMenu
            popup.add(menuSobre);
            popup.add(menuConexao);

            //Adiconando um separador
            popup.addSeparator();
            //Fim do Separador
            
            popup.add(menuDados);
            popup.add(SincAutomatica);
            popup.add(SubMenuSincronizacao);
            
            //Adiconando um separador
            popup.addSeparator();
            //Fim do Separador
            
            popup.add(menuSair);

            //Criando um objeto do tipo TrayIcon
            trayIcon = new TrayIcon(image, "Points - Sistema de Pontuação e Qualidade", popup);

            //Evento ActionListener
            ActionListener actionListener = new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    trayIcon.displayMessage("Action Event",
                            "Um Evento foi disparado",
                            TrayIcon.MessageType.INFO);

                }

            };
    //Fim do evento ActionListener    

            //Na linha a seguir a imagem a ser utilizada como icone sera redimensionada
            trayIcon.setImageAutoSize(true);

            //Seguida adicionamos os actions listeners
            trayIcon.addActionListener(actionListener);

            //Tratamento de erros
            try {

                tray.add(trayIcon);

            } catch (AWTException e) {

                System.err.println("Erro, TrayIcon não sera adicionado.");

            }
            //Fim do tratamento de erros 

        } else {

            //Caso o item  System Tray não for suportado
            JOptionPane.showMessageDialog(null, "recurso ainda não esta disponível pra o seu sistema");

        }

    }

}
