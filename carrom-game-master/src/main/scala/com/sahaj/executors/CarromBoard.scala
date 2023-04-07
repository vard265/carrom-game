/**
  * Author: Manjunath Davanam <manjunathdavanam@gamil.com>
  */

package com.sahaj.executors

import com.sahaj.mediator.RuleManager
import com.sahaj.models.GameMetrics
import com.sahaj.services.AppConfig

/**
  * CarromBoard class
  */
class CarromBoard {
  /**
    * Red coins left in the carrom board
    */
  private var redCoins: Int = 1
  /**
    * Block coins left in the carrom board
    */
  private var bloackCoins: Int = 9

  /**
    * When player stiked coins
    *
    * @param player - Player
    * @return - GameMetrics
    */
  def strike(player: Player): GameMetrics = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.strike"), Option(this.redCoins), Option(this.bloackCoins))
    this.updateCarromStatus(response.redCoins, response.blockCoins)
    response
  }

  /**
    * When player striked on multiple coins
    *
    * @param player - Player
    * @return - GameMetrics
    */
  def multiStrike(player: Player): GameMetrics = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.multiStrike"), Option(this.redCoins), Option(this.bloackCoins))
    this.updateCarromStatus(response.redCoins, response.blockCoins)
    response
  }

  /**
    * When player strike the red coin
    *
    * @param player - Player
    * @return - GameMetrics
    */
  def redStrike(player: Player): GameMetrics = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.redStrike"), Option(this.redCoins), Option(this.bloackCoins))
    this.updateCarromStatus(response.redCoins, response.blockCoins)
    response
  }

  /**
    * When player strike the striker
    *
    * @param player - Player
    * @return - GameMetrics
    */
  def strikerStrike(player: Player): GameMetrics = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.strikerStrike"), Option(this.redCoins), Option(this.bloackCoins))
    this.updateCarromStatus(response.redCoins, response.blockCoins)
    response
  }

  /**
    * When player defunct coin
    *
    * @param player - Player
    * @return - GameMetrics
    */
  def defunctCoin(player: Player): GameMetrics = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.defunctCoin"), Option(this.redCoins), Option(this.bloackCoins))
    this.updateCarromStatus(response.redCoins, response.blockCoins)
    response
  }

  /**
    * When player failed hit
    *
    * @param player - Player
    * @return - GameMetrics
    */
  def failedHit(player: Player): GameMetrics = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.failedHit"), Option(this.redCoins), Option(this.bloackCoins))
    this.updateCarromStatus(response.redCoins, response.blockCoins)
    response
  }


  /**
    * Method to update the block coins
    *
    * @param coins - Block coins count
    */
  def setBloackCoins(coins: Int): Unit = {
    this.bloackCoins = bloackCoins
  }

  /**
    * Method to update the count of the red coins
    *
    * @param coins - red coins count
    */
  def setRedCoins(coins: Int): Unit = {
    this.redCoins = coins
  }

  /**
    * Method to get the vlock coins left on the carrom board
    *
    * @return - Block coins count
    */
  def getBloackCoins: Int = {
    this.bloackCoins
  }

  /**
    * Method to get the red coins left on the carrom board
    *
    * @return - red coins count
    */
  def getRedCoins: Int = {
    this.redCoins
  }

  /**
    * Method to update the metrics of the carrom board
    *
    * @param redCoin   - Remaining red coins
    * @param blockCoin - Remaining block coin
    */
  private def updateCarromStatus(redCoin: Int, blockCoin: Int): Unit = {
    this.setRedCoins(this.getRedCoins - redCoin)
    this.setBloackCoins(this.getBloackCoins - blockCoin)
  }

}
