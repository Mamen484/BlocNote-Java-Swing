/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.max.swingDB.dao;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import fr.max.swingDB.entity.ListDTO;

/**
 * @author formation
 */
public class ListDAO implements DAOInterface<ListDTO, ListDAO> {

    private Connection dbConnection;
    private PreparedStatement pStatement;
    private ResultSet resultSet;

    public ListDAO(Connection dbConnection, PreparedStatement pStatement, ResultSet resultSet) {
        this.dbConnection = dbConnection;
        this.pStatement = pStatement;
        this.resultSet = resultSet;
    }

    public ResultSet getResul() {
        return this.resultSet;
    }

    /**
     * Insertion dans la base de données
     *
     * @param entity
     * @throws java.sql.SQLException
     */
    @Override
    public void insert(ListDTO entity) throws SQLException {
        String sql = "INSERT INTO taches (nomDeTache, id_categorie, tacheTerminee) VALUES (?,?,?)";
        pStatement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        pStatement.setString(1, entity.getTache());
        pStatement.setInt(2, entity.getId_cat());
        pStatement.setBoolean(3, entity.getTerminee());

        //Récupération de la clef auto incrémentée
//        ResultSet keyRs = pStatement.getGeneratedKeys();
//        if (keyRs.next()) {
//            entity.setId(keyRs.getInt("id"));
//        }
        pStatement.executeUpdate();
    }

    @Override
    public void deleteOneById(ListDTO entity) throws SQLException {
        String sql = "DELETE FROM taches WHERE id_tache=?";
        pStatement = dbConnection.prepareStatement(sql);
        pStatement.setInt(1, (int) entity.getId());

        pStatement.executeUpdate();
    }

    /**
     * Insertion dans la base de données
     * @param entity
     * @throws java.sql.SQLException
     */
    @Override
    public void update(ListDTO entity) throws SQLException {
        String sql = "UPDATE taches SET nomDeTache=?, tacheTerminee=?, id_categorie=?  WHERE id_tache=?";
        pStatement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        pStatement.setString(1, entity.getTache());
        pStatement.setBoolean(2, entity.getTerminee());
        pStatement.setInt(3, entity.getId_cat());
        pStatement.setInt(4, entity.getId());

        ResultSet key = pStatement.getGeneratedKeys();
        if (key.next()) {
            entity.setTdId(key.getInt("id"));
        }
        pStatement.executeUpdate();
    }

    public void deleteAllTdEvent() throws SQLException {
        String sql = "DELETE FROM taches ";

        pStatement = dbConnection.prepareStatement(sql);

        pStatement.executeUpdate();
    }

    /**
     * Récupération d'une entitité en fonction de sa clef primaire
     *
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public ListDAO findOneById(int id) throws SQLException {
        String sql = "SELECT * FROM taches, categories WHERE id_tache=?";
        pStatement = dbConnection.prepareStatement(sql);
        pStatement.setInt(1, id);

        resultSet = pStatement.executeQuery();
        return this;
    }

    /**
     * Exécute une requête sélectionnant l'ensemble des lignes de la table
     *
     * @return
     * @throws SQLException
     */
    @Override
    public ListDAO findAll() throws SQLException {
        String sql = "SELECT id_tache, nomDeTache, tacheTerminee, nomDeCategorie FROM categories INNER JOIN taches ON taches.id_categorie = categories.id_cat";
        pStatement = dbConnection.prepareStatement(sql);
        resultSet = pStatement.executeQuery();

        return this;
    }

    /**
     * Récupération des résultats d'une requête sous la forme d'une entité
     *
     * @return
     * @throws SQLException
     */
    @Override
    public ListDTO getOne() throws SQLException {
        ListDTO entity = new ListDTO();
        if (resultSet.next()) {
            entity.setTache(resultSet.getString("nomDeTache"));
            entity.setCategorie(resultSet.getString("nomDeCategorie"));
            entity.setTerminee(resultSet.getBoolean("tacheTerminee"));
        }
        return entity;
    }

    /**
     * Récupération des résultats d'une requête sous la forme d'un tableau
     * associatif
     *
     * @return
     * @throws SQLException
     */
    @Override
    public Map<String, String> getOneAsMap() throws SQLException {
        Map<String, String> listData = new HashMap<>();

        if (resultSet.next()) {
            listData.put("tache", resultSet.getString("nomDeTache"));
            listData.put("id_cat", String.valueOf(resultSet.getInt("id_categorie")));
            listData.put("categorie", resultSet.getString("nomDeCategorie"));
            listData.put("terminee", String.valueOf(resultSet.getBoolean("tacheTerminee")));
        }
        return listData;
    }

    /**
     * Récupération des résultats d'une requête sous la forme d'une liste
     * d'entités
     *
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public List<Map<String, String>> getAll() throws SQLException {
        List<Map<String, String>> tacheListMap = new ArrayList<>();

        if (resultSet.isBeforeFirst()) {
            while (!resultSet.isLast()) {
                tacheListMap.add(this.getOneAsMap());
            }
        }
        return tacheListMap;
    }

    /**
     * Récupération des résultats d'une requête sous la forme d'une liste de
     * tableaux associatifs
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<ListDTO> getAllAsArray() throws SQLException {
        List<ListDTO> tacheListDTO = new ArrayList<>();

        if (resultSet.isBeforeFirst()) {
            while (!resultSet.isLast()) {
                tacheListDTO.add(this.getOne());
            }
        }
        return tacheListDTO;
    }

    public Map<String, Integer> getFkMap() throws SQLException {
        String sql = "SELECT * FROM categories";
        pStatement = dbConnection.prepareStatement(sql);
        resultSet = pStatement.executeQuery();

        Map<String, Integer> map = new HashMap<>();

        while (resultSet.next()) {
            map.put(resultSet.getString(2), resultSet.getInt(1));

        }
        return map;
    }
}
