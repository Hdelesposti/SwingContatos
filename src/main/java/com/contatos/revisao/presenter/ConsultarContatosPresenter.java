package com.contatos.revisao.presenter;

import com.contatos.revisao.collection.ContatoCollection;
import com.contatos.revisao.model.Contato;
import com.contatos.revisao.observer.Observer;
import com.contatos.revisao.view.ConsultarContatosView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author clayton
 */
public class ConsultarContatosPresenter implements Observer {

    private ConsultarContatosView view;
    private ContatoCollection contatos;
    private DefaultTableModel tmContatos;

    public ConsultarContatosPresenter() {
        view = new ConsultarContatosView();
        this.contatos = ContatoCollection.instance();
        this.contatos.addSubject(this);

        view.getTblContatos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Object colunas[] = {"id", "Nome", "Telefone"};
        tmContatos = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };

        update();

        view.getTblContatos().setModel(tmContatos);

        view.getTblContatos().getColumnModel().getColumn(0).setMinWidth(0);
        view.getTblContatos().getColumnModel().getColumn(0).setMaxWidth(0);

        view.getBtnFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });

        this.view.getBtnVisualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    visualizarContato();
                } catch (Exception ex) {
                    Logger.getLogger(ConsultarContatosPresenter.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(view,
                            "Erro ao tentar selecionar contato",
                            "Erro",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        this.view.getBtnEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    editarContato();
                } catch (Exception ex) {
                    Logger.getLogger(ConsultarContatosPresenter.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(view,
                            "Erro ao tentar selecionar contato",
                            "Erro",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        this.view.getBtnDeletar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deletarContato();
                } catch (Exception ex) {
                    Logger.getLogger(ConsultarContatosPresenter.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(view,
                            "Erro ao tentar selecionar contato",
                            "Erro",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        view.setVisible(true);

    }

    private void visualizarContato() throws Exception {
        JTable tabela = this.view.getTblContatos();

        if (tabela.getSelectedRow() != -1) {
            int linhaSelecionada = tabela.getSelectedRow();
            String id = (String) tabela.getValueAt(linhaSelecionada, 0);

            Contato contato = this.contatos.bucarPorId(id);
            contato.setId(id);
            this.contatos.setContatoSelecionado(contato);

            ManterContatoPresenter presenter = new ManterContatoPresenter();
            presenter.visualizacao();
            
            fechar();
            
        } else {
            JOptionPane.showMessageDialog(view,
                    "Selecione um contato para visualizar",
                    "Contato não selecionado",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void editarContato() throws Exception {
        JTable tabela = this.view.getTblContatos();

        if (tabela.getSelectedRow() != -1) {
            int linhaSelecionada = tabela.getSelectedRow();
            String id = (String) tabela.getValueAt(linhaSelecionada, 0);

            Contato contato = this.contatos.bucarPorId(id);
            contato.setId(id);
            this.contatos.setContatoSelecionado(contato);

            ManterContatoPresenter presenter = new ManterContatoPresenter();
            presenter.edicao();
            
            fechar();
            
        } else {
            JOptionPane.showMessageDialog(view,
                    "Selecione um contato para editar",
                    "Contato não selecionado",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void deletarContato() throws Exception {
        JTable tabela = this.view.getTblContatos();

        if (tabela.getSelectedRow() != -1) {
            int linhaSelecionada = tabela.getSelectedRow();
            String id = (String) tabela.getValueAt(linhaSelecionada, 0);

            if (this.contatos.deletar(id) == 200) {
                JOptionPane.showMessageDialog(view,
                    "Contato removindo com sucesso",
                    "Confirmação",
                    JOptionPane.INFORMATION_MESSAGE);
                
                update();
            } else {
                JOptionPane.showMessageDialog(view,
                    "Não foi possível remover o contato",
                    "Erro",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view,
                    "Selecione um contato para remover",
                    "Contato não selecionado",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void fechar() {
        view.dispose();
    }

    @Override
    public void update() {
        tmContatos.setNumRows(0);
        ListIterator<Contato> it = null;
        try {
            it = contatos.getContatos().listIterator();
        } catch (Exception ex) {
            Logger.getLogger(ConsultarContatosPresenter.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(view,
                    "Erro ao tentar buscar lista de contato",
                    "Erro",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        if (tmContatos.getRowCount() > 0) {
            tmContatos.setRowCount(0);
        }
        while (it.hasNext()) {
            Contato contato = it.next();
            tmContatos.addRow(new Object[]{contato.getId(), contato.getNome(), contato.getTelefone()});
        }
    }

}
