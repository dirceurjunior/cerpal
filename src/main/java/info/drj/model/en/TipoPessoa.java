/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.drj.model.en;

/**
 *
 * @author Dirceu Junior
 */
public enum TipoPessoa {

    FISICA("FÍSICA"), JURIDICA("JURÍDICA");

    private String descricao;

    TipoPessoa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
