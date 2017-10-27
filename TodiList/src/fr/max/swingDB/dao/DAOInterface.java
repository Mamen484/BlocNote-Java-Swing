/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.max.swingDB.dao;

import fr.max.swingDB.entity.ListDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author formation
 * @param <T>
 * @param <DAO>
 */
public interface DAOInterface <T, DAO> {

     /**
     * Suppression d'une entité en fonction de son identifiant
     * @param entity
     * @throws java.sql.SQLException
     */
   public void deleteOneById(ListDTO entity) throws SQLException;
   public void insert(ListDTO entity) throws SQLException;
   public void update(ListDTO entity) throws SQLException;
   
    /**
     * Persistance de l'entité Student
     * @return 
     * @throws java.sql.SQLException
     */
    public Map<String,Integer>getFkMap() throws SQLException;
    
    public ListDAO findOneById(int id) throws SQLException;
    
    public ListDAO findAll() throws SQLException;
    
    public ListDTO getOne() throws SQLException;
    
    public Map<String, String> getOneAsMap() throws SQLException;
    
    public List<Map<String, String>> getAll() throws SQLException;
    
    public List<ListDTO> getAllAsArray() throws SQLException;
    
}
