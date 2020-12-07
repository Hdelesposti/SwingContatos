
package com.contatos.revisao.state;

import com.contatos.revisao.collection.ContatoCollection;
import com.contatos.revisao.model.Contato;
import com.contatos.revisao.presenter.ManterContatoPresenter;
import com.contatos.revisao.view.ManterContatoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class EditarContatoState extends ManterContatoState{
    
    private ManterContatoView view;
    private ContatoCollection contatos;

    public EditarContatoState(ManterContatoPresenter presenter) {
        super(presenter);
        this.view = presenter.getView();
        this.contatos = ContatoCollection.instance();
    }

    @Override
    public void edicao() {
        Contato contato = this.contatos.getContatoSelecionado();
        
        this.view.setTitle("Editar Contato");
        this.view.getTxtNome().setText(contato.getNome());
        this.view.getTxtTelefone().setText(contato.getTelefone());
        
        this.view.getBtnSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    editarContato(contato);
                } catch (Exception ex) {
                    //Logger.getLogger(EditarContatoState.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(view,
                    ex.getMessage(),
                    "Informação",
                    JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
    }
    
    private void editarContato(Contato contato) throws Exception {
        
        String nome = view.getTxtNome().getText();
        String telefone = view.getTxtTelefone().getText();
        
        if (nome.isEmpty() || nome.isBlank()) {
            throw new RuntimeException("Informe o nome do contato");
        }

        if (telefone.isEmpty() || telefone.isBlank()) {
            throw new RuntimeException("Informe o telefone do contato");
        }

        contato.setNome(nome);
        contato.setTelefone(telefone);
        
        this.contatos.editar(contato);
        
        this.view.dispose();
    }
    
}
