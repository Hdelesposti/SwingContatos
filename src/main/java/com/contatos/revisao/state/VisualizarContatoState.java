package com.contatos.revisao.state;

import com.contatos.revisao.collection.ContatoCollection;
import com.contatos.revisao.model.Contato;
import com.contatos.revisao.presenter.ManterContatoPresenter;
import com.contatos.revisao.view.ManterContatoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualizarContatoState extends ManterContatoState{
    
    private ManterContatoView view;
    private ContatoCollection contatos;

    public VisualizarContatoState(ManterContatoPresenter presenter) {
        super(presenter);
        this.view = presenter.getView();
        this.contatos = ContatoCollection.instance();
    }

    @Override
    public void visualizacao() {
        Contato contato = this.contatos.getContatoSelecionado();
        
        this.view.setTitle("Visualizar Contato");
        this.view.getTxtNome().setText(contato.getNome());
        this.view.getTxtTelefone().setText(contato.getTelefone());
        
        this.view.getTxtNome().setEnabled(false);
        this.view.getTxtTelefone().setEnabled(false);
        this.view.getBtnSalvar().setText("Editar");
        
        this.view.getBtnSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManterContatoPresenter presenter = new ManterContatoPresenter();
                presenter.edicao();
                
                view.dispose();
            }
        });
        
    }
}
