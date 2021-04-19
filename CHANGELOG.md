# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [0.0.7] - 2021-04-19
### Added 
- Class ScoreDAO to track player's score

### Changed
- Class listenerCommands to include commands related to the score database

## [0.0.6] - 2021-04-19
### Changed
- GameDAO updated to contain methods related to sql
- class Player to help with the code
- class GameSession to help with the code
- class ListenerCommands to include commands related to the game
- pom.xml to include jdbc

## [0.0.5] - 2021-04-18
### Added
- Created DAO
- Created DAOFields
- Created GameDAO

### Changed
- GameSession in the progress of refactoration to represent a game instance.
- Removed variable attempt and related methods in Player class
- Session depreciated and to be removed

## [0.0.4] - 2021-04-18
### Added
- Created class DiscordBotSettings, meant to deal with the start up settings.
- Created class ListenerCommands, meant to deal with the commands for the listener.

### Changed
- pom.xml to include log4j

## [0.0.3] - 2021-04-18
### Added
- Created class FileReader, meant to load the keys required for the bot.

### Changed
- Formatting to class Code
- Formatting to class Player
- Formatting to class Session
- pom.xml to include jUtil

## [0.0.2] - 2021-04-17
### Added
- Imported folders and files from the [previous attempt](https://github.com/goodguyplayer/MastermindGame-JavaAttempt)

## [0.0.1] - 2021-04-17
### Added
- This Changelog
- readme file
- initial files to start the project
- pom.xml to include Javacord