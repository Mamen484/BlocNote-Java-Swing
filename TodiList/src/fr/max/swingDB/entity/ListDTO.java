/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.max.swingDB.entity;

/**
 *
 * @author formation
 */
public class ListDTO {
    
    private Integer id;
    
    private Integer id_cat;
    
    private String tache;
    
    private String categorie;
    
    private boolean terminee;

    public ListDTO() {
    }

    public ListDTO(Integer id, Integer id_cat, String tache, String categorie, boolean terminee) {
        this.id = id;
        this.id_cat = id_cat;
        this.tache = tache;
        this.categorie = categorie;
        this.terminee = terminee;
    }

    public Integer getId() {
        return id;
    }

    public Integer getId_cat() {
        return id_cat;
    }

    public String getTache() {
        return tache;
    }

    public String getCategorie() {
        return categorie;
    }

    public boolean isTerminee() {
        return terminee;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId_cat(Integer id_cat) {
        this.id_cat = id_cat;
    }

    public void setTache(String tache) {
        this.tache = tache;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setTerminee(boolean terminee) {
        this.terminee = terminee;
    }

    @Override
    public String toString() {
        return "ListDTO{" + "id=" + id + ", tache=" + tache + ", categorie=" + categorie + ", terminee=" + terminee + '}';
    }

    public boolean getTerminee() {
        return false;
        
    }

    public void setTdId(int aInt) {

    }

    
}
