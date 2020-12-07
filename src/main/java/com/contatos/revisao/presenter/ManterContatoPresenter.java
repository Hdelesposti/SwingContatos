package com.contatos.revisao.presenter;

import com.contatos.revisao.state.EditarContatoState;
import com.contatos.revisao.state.IncluirContatoState;
import com.contatos.revisao.state.ManterContatoState;
import com.contatos.revisao.state.VisualizarContatoState;
import com.contatos.revisao.view.ManterContatoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author clayton
 */
public class ManterContatoPresenter {

    private ManterContatoView view;
    private ManterContatoState state;

    public ManterContatoPresenter() {
        view = new ManterContatoView();

        view.getBtnFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }

        });

        view.setVisible(true);
    }
    
    public void inclusao() {
        this.setState(new IncluirContatoState(this));
        this.state.inclusao();
    }
    
    public void edicao() {
        this.setState(new EditarContatoState(this));
        this.state.edicao();
    }
    
    public void visualizacao() {
        this.setState(new VisualizarContatoState(this));
        this.state.visualizacao();
    }

    public void setState(ManterContatoState state) {
        this.state = state;
    }

    private void fechar() {
        view.dispose();
    }

    public ManterContatoView getView() {
        return view;
    }

}
