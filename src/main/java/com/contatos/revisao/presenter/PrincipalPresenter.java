package com.contatos.revisao.presenter;

import com.contatos.revisao.view.PrincipalView;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author clayton
 */
public class PrincipalPresenter {

    private PrincipalView view;

    public PrincipalPresenter() {
        configTela();
    }

    private void configTela() {
        this.view = new PrincipalView();

        this.view.getMiIncluirContato().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManterContatoPresenter presenter = new ManterContatoPresenter();
                presenter.inclusao();
            }
        });

        this.view.getMiConsultarContato().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultarContatosPresenter();
            }
        });

        this.view.setVisible(true);
        fullScreen(this.view);
    }

    private void fullScreen(JFrame aFrame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        aFrame.setSize(screenSize.width, screenSize.height);
    }

}
