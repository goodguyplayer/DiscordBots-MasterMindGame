package DAO;

import models.Code;
import models.GameSession;
import models.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class to store game instances.
 * Whenever a game is called or started, store here. Once a game ends, remove it from table.
 *
 * @author Nathan (goodguyplayer)
 * @Version 0.1
 * @since 2021-04-18
 */

/*
Changelog.:

Version 0.1.:
    - methods written. Hopefully they work.

Version 0.:
    - class created
    - implemented methods
 */

public class GameDAO implements DAO<GameSession>, DAOFields{
    private Connection connection;

    public GameDAO() {
        try {
            String myDBConnectionString = "jdbc:sqlite:sqlite.db";
            connection = DriverManager.getConnection(myDBConnectionString);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<GameSession> get(String condition) {
        List<GameSession> list = new ArrayList<GameSession>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getSelectConditionalString(getTableName()) + condition);
            while(result.next()){
                GameSession game = new GameSession();
                Player player = new Player(result.getString("name"),
                        result.getString("guild"));
                player.setScore(result.getInt("score"));
                Code code = new Code(result.getString("code"));
                list.add(game.loadSession(player, code));
            }
            result.close();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("Error - Either wrong syntax or not in database");
        }
        return list;
    }

    @Override
    public List<GameSession> getAll() {
        List<GameSession> list = new ArrayList<GameSession>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getSelectAllString(getTableName()));
            while(result.next()){
                GameSession game = new GameSession();
                Player player = new Player(result.getString("name"),
                        result.getString("guild"));
                player.setScore(result.getInt("score"));
                Code code = new Code(result.getString("code"));
                list.add(game.loadSession(player, code));
            }
            result.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int update(GameSession gameSession) {
        int retorno;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getUpdateString(getTableName()));
            preparedStatement.setString(1, gameSession.getPlayer().getName());
            preparedStatement.setString(2, gameSession.getPlayer().getGuild());
            preparedStatement.setInt(3, gameSession.getPlayer().getScore());
            preparedStatement.setString(4, gameSession.getCode().getCode());
            preparedStatement.setString(5, gameSession.getPlayer().getName());
            retorno = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            retorno = 0;
        }
        return retorno;
    }

    @Override
    public int delete(GameSession gameSession) {
        int retorno;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getDeleteString(getTableName()));
            preparedStatement.setString(1, gameSession.getPlayer().getName());

            retorno = preparedStatement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
            retorno = 0;
        }
        return retorno;
    }

    @Override
    public int create(GameSession gameSession) {
        int retorno;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getInsertString(getTableName()));
            preparedStatement.setString(1, gameSession.getPlayer().getName());
            preparedStatement.setString(2, gameSession.getPlayer().getGuild());
            preparedStatement.setInt(3, gameSession.getPlayer().getScore());
            preparedStatement.setString(4, gameSession.getCode().getCode());
            retorno = preparedStatement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
            retorno = 0;
        }
        return retorno;
    }

    @Override
    public String getTableName() {
        return "gamedb";
    }

    @Override
    public String getDeleteString(String table) {
        return "DELETE FROM "+ table +" WHERE name = ?;";
    }

    @Override
    public String getUpdateString(String table) {
        return "UPDATE "+ table +" SET name = ?, guild = ?, score = ?, code = ? WHERE name = ?;";
    }

    @Override
    public String getInsertString(String table) {
        return "INSERT INTO "+ table + " (name, guild, score, code) VALUES (?, ?, ?, ?);";
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
