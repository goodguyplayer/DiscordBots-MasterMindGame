package DAO;

import models.Code;
import models.GameSession;
import models.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao class made to contain methods related to score db
 *
 * @author Nathan (goodguyplayer)
 * @Version 0
 * @since 2021-04-19
 */

/*
Changelog.:

Version 0.:
    - Class created
    - Implemented methods

 */
public class ScoreDAO implements DAO<Player>, DAOFields {
    private Connection connection;

    public ScoreDAO() {
        try {
            String myDBConnectionString = "jdbc:sqlite:sqlite.db";
            connection = DriverManager.getConnection(myDBConnectionString);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Player> get(String condition) {
        List<Player> list = new ArrayList<Player>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getSelectConditionalString(getTableName()) + condition);
            while(result.next()){
                Player player = new Player(
                        result.getString("name"),
                        result.getString("guild")
                );
                player.setScore(result.getInt("score"));
                list.add(player);
            }
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error - Either wrong syntax or not in database");
        }
        return list;
    }

    @Override
    public List<Player> getAll() {
        List<Player> list = new ArrayList<Player>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getSelectAllString(getTableName()));
            while(result.next()){
                Player player = new Player(
                        result.getString("name"),
                        result.getString("guild")
                );
                player.setScore(result.getInt("score"));
                list.add(player);
            }
            result.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int update(Player player) {
        int retorno;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getUpdateString(getTableName()));
            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getGuild());
            preparedStatement.setInt(3, player.getScore());
            preparedStatement.setString(4, player.getName());
            retorno = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            retorno = 0;
        }
        return retorno;
    }

    @Override
    public int delete(Player player) {
        int retorno;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getDeleteString(getTableName()));
            preparedStatement.setString(1, player.getName());
            retorno = preparedStatement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
            retorno = 0;
        }
        return retorno;
    }

    @Override
    public int create(Player player) {
        int retorno;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getInsertString(getTableName()));
            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getGuild());
            preparedStatement.setInt(3, 1000000000);
            retorno = preparedStatement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
            retorno = 0;
        }
        return retorno;
    }

    @Override
    public String getTableName() {
        return "scoredb";
    }

    @Override
    public String getDeleteString(String table) {
        return "DELETE FROM "+ table +" WHERE name = ?;";
    }

    @Override
    public String getUpdateString(String table) {
        return "UPDATE "+ table +" SET name = ?, guild = ?, score = ? WHERE name = ?;";
    }

    @Override
    public String getInsertString(String table) {
        return "INSERT INTO "+ table + " (name, guild, score) VALUES (?, ?, ?);";
    }

    @Override
    public String getSelectAllString(String table) {
        return "SELECT * FROM " + table;
    }

    @Override
    public String getSelectConditionalString(String table) {
        return "SELECT * FROM " + table + " WHERE ";
    }
}
