/**
  * Author: Manjunath Davanam <manjunathdavanam@gamil.com>
  */

package com.sahaj.services

import com.sahaj.command._
import com.sahaj.executors.{CarromBoard, DashBoard, Player}
import com.sahaj.mediator.RuleManager
import com.sahaj.models.GameMetrics

/**
  * CarromBoardService which is exposed to user to play the carrom game.
  */
object CarromBoardService {
  private val carrom = this.getCarromBoard

  /**
    * Method to register to the players
    *
    * @param identifier - Player identifier
    * @param isWonToss  - Player won toss metrics
    * @return - Active player
    */
  def registerPlayer(identifier: String, isWonToss: Boolean): Player = {
    new Player(identifier, isWonToss)
  }

  /**
    * Method to get the carrom board instance
    *
    * @return - CarromBoard instance
    */
  def getCarromBoard: CarromBoard = {
    new CarromBoard()
  }

  /**
    * Method to initialize the carromBoard - It will to register all supported commands
    */
  def init(): Unit = {
    CommandManager.register(AppConfig.getConfig("com.sahaj.command.strike"), new Strike(carrom))
    CommandManager.register(AppConfig.getConfig("com.sahaj.command.multiStrike"), new MultiStrike(carrom))
    CommandManager.register(AppConfig.getConfig("com.sahaj.command.strikerStrike"), new StrikerStrike(carrom))
    CommandManager.register(AppConfig.getConfig("com.sahaj.command.redStrike"), new RedStrike(carrom))
    CommandManager.register(AppConfig.getConfig("com.sahaj.command.defunctCoin"), new DefunctCoin(carrom))
    CommandManager.register(AppConfig.getConfig("com.sahaj.command.failedHit"), new FailedStrike(carrom))
  }

  /**
    * Method to auto play the carrom board game - Until game won, It will keep showing the
    * Dashboard to player to to play the game.
    *
    * @param player1 - Player1 instance of Player
    * @param player2 - Player2 instance of Player
    */
  def autoPlay(player1: Player, player2: Player): Unit = {
    while (!player1.getWonStatus || !player2.getWonStatus) {
      val activePlayer = RuleManager.getPlayer(player1, player2)
      val command = DashBoard.promptOptions(activePlayer)
      val playerStatus = CommandManager.execute(command, activePlayer)
      DashBoard.show(None, None, Option(playerStatus))
      val gameStatus = this.getMatchStatus(player1, player2)
      if (gameStatus != null) {
        DashBoard.show(None, None, Option(gameStatus))
      }
    }
  }

  /**
    * Which validate the game - (Who won the game by comparing between players in the carrom board
    *
    * @param player1 - Player instance
    * @param player2 - Player Instance
    * @return - Metrics of the game (Game metrics)
    */
  def getMatchStatus(player1: Player, player2: Player): GameMetrics = {
    RuleManager.getMatchStatus(player1, player2, carrom)
  }

  /**
    * Which allows to play the carrom board game
    *
    * @param player  - Player Instnace
    * @param command - Which command to invoke
    * @return - Metrics of the game (Game metrics)
    */
  def play(player: Player, command: String): GameMetrics = {
    CommandManager.execute(command, player)
  }
}
