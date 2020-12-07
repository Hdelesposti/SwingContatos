package com.contatos.revisao.observer;

public interface Subject {
    public void addSubject(Observer o);
    public void removeSubject(Observer o);
    public void notifyObservers();
}
