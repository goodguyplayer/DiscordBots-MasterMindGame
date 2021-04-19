package DAO;

import models.GameSession;

import java.util.List;

/**
 * DAO class to store game instances.
 * Whenever a game is called or started, store here. Once a game ends, remove it from table.
 *
 * @author Nathan (goodguyplayer)
 * @Version 0
 * @since 2021-04-18
 */

/*
Changelog.:

Version 0.:
    - class created
    - implemented methods
 */

public class GameDAO implements DAO<GameSession>, DAOFields{

    @Override
    public List<GameSession> get(String condition) {
        return null;
    }

    @Override
    public List<GameSession> getAll() {
        return null;
    }

    @Override
    public int update(GameSession gameSession) {
        return 0;
    }

    @Override
    public int delete(GameSession gameSession) {
        return 0;
    }

    @Override
    public int create(GameSession gameSession) {
        return 0;
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public String getDeleteString(String table) {
        return null;
    }

    @Override
    public String getUpdateString(String table) {
        return null;
    }

    @Override
    public String getInsertString(String table) {
        return null;
    }

    @Override
    public String getSelectAllString(String table) {
        return null;
    }

    @Override
    public String getSelectConditionalString(String table) {
        return null;
    }
}
