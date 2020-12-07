package com.contatos.revisao.collection;

import com.contatos.revisao.model.Contato;
import com.contatos.revisao.observer.Subject;
import com.contatos.revisao.service.HttpService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.contatos.revisao.observer.Observer;

/**
 *
 * @author clayton
 */
public class ContatoCollection implements Subject {

    private ArrayList<Contato> contatos;
    private List<Observer> observers;
    private static ContatoCollection INSTANCE = null;
    private Contato contatoSelecionado;

    private ContatoCollection() {
        contatos = new ArrayList<>();
        this.observers = new ArrayList();
    }

    public static ContatoCollection instance() {
        if (INSTANCE == null) {
            INSTANCE = new ContatoCollection();
        }
        return INSTANCE;
    }

    public Contato bucarPorId(String id) throws Exception {
        return HttpService.instance().sendGetRequest(id);
    }

    public Contato editar(Contato contato) throws Exception {
        return HttpService.instance().sendPutRequest(contato);
    }

    public int deletar(String id) throws Exception {
        return HttpService.instance().sendDeleteRequest(id);
    }

    public void add(Contato contato) throws Exception {
        if (contato != null) {
            Contato post = HttpService.instance().sendPostRequest(contato);
            if (post != null) {
                contatos.add(post);
                notifyObservers();
                
                throw new RuntimeException("Contato " + post.getNome() + " salvo com sucesso");
            } else {
                throw new RuntimeException("Não foi possível salvar o contato");
            }
        } else {
            throw new RuntimeException("Forneça uma instância válida de um contato");
        }
    }

    public Contato getContatoSelecionado() {
        return contatoSelecionado;
    }

    public void setContatoSelecionado(Contato contatoSelecionado) {
        this.contatoSelecionado = contatoSelecionado;
    }

    public List<Contato> getContatos() throws Exception {
        List<Contato> result = HttpService.instance().sendGetListRequest();
        return Collections.unmodifiableList(result);
    }

    @Override
    public void addSubject(Observer o) {
        if (!observers.contains(o)) {
            this.observers.add(o);
        }
    }

    @Override
    public void removeSubject(Observer o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        this.observers.forEach(o -> {
            o.update();
        });
    }

}
