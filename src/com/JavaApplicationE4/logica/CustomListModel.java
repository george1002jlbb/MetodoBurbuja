/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JavaApplicationE4.logica;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author jbermudezb
 */
public class CustomListModel extends AbstractListModel {

    public ArrayList lista = new ArrayList<>();

    public ArrayList getLista() {
        return lista;
    }

    public void setLista(ArrayList lista) {
        this.lista = lista;
    }
    
    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public Object getElementAt(int index) {
        Numero a = (Numero) lista.get(index);
        return a.getNumero();
    }

    public void addNumero(Numero n) {
        lista.add(n);
        this.fireIntervalAdded(this, getSize(), getSize() + 1);
    }

    public void eliminarNumero(int index0) {
        lista.remove(index0);
        this.fireIntervalRemoved(index0, getSize(), getSize() + 1);
    }

    public Numero getPersona(int index) {
        return (Numero) lista.get(index);
    }
    
    public void vaciar(){
        lista.clear();
//        lista.removeAll(lista);
    }

}
