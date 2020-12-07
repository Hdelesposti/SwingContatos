package com.contatos.revisao.state;

import com.contatos.revisao.collection.ContatoCollection;
import com.contatos.revisao.model.Contato;
import com.contatos.revisao.presenter.ManterContatoPresenter;
import com.contatos.revisao.view.ManterContatoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class IncluirContatoState extends ManterContatoState {

    private ManterContatoView view;
    private ContatoCollection contatos;

    public IncluirContatoState(ManterContatoPresenter presenter) {
        super(presenter);
        this.view = presenter.getView();
    }

    @Override
    public void inclusao() {
        this.contatos = ContatoCollection.instance();

        view.getBtnSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    salvar();
                } catch (Exception ex) {
                    //Logger.getLogger(ManterContatoPresenter.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(view,
                            ex.getMessage(),
                            "Informação",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void salvar() throws Exception {
        String nome = view.getTxtNome().getText();
        String telefone = view.getTxtTelefone().getText();

        if (nome.isEmpty() || nome.isBlank()) {
            throw new RuntimeException("O campo nome não pode ser nulo");
        }

        if (telefone.isEmpty() || telefone.isBlank()) {
            throw new RuntimeException("O campo telefone não pode ser nulo");
        }

        Contato contato = new Contato("", nome, telefone);

        contatos.add(contato);
    }

}
