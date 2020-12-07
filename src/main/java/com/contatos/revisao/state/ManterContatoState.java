package com.contatos.revisao.state;

import com.contatos.revisao.presenter.ManterContatoPresenter;

public abstract class ManterContatoState {
    private final ManterContatoPresenter presenter;
    
    public ManterContatoState(ManterContatoPresenter presenter) {
        this.presenter = presenter;
    }

    public void inclusao() {
        throw new RuntimeException("Estado não implementado");
    }
    
    public void edicao() {
        throw new RuntimeException("Estado não implementado");
    }

    public void visualizacao() {
        throw new RuntimeException("Estado não implementado");
    }
}
