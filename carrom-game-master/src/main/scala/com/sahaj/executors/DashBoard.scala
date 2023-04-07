/**
  * Author: Manjunath Davanam <manjunathdavanam@gamil.com>
  */


package com.sahaj.executors

import com.sahaj.models.GameMetrics
import com.sahaj.services.AppConfig

/**
  * DashBoard Trait
  */
trait MainDashBoard {
  /**
    * Method to show the player/carrom/game metrics
    *
    * @param player - Player properties(id, score, isWon, ..etc.,
    * @param carram - CarromBoard, To show remaning red/block coins left on the carrom board
    * @param metrics - Status of the Player/Game
    */
  def show(player: Option[Player], carrom: Option[CarromBoard], metrics: Option[GameMetrics]): Unit

  /**
    * Method to clear the dashboard
    *
    * @return
    */
  def clear: Boolean

  /**
    * Method to read the input
    *
    * @return - Integer keys
    */
  def read: Int

}

/**
  * Dashboard manager to create the Dashboard to show score/player/match metrics
  */
object DashBoard extends MainDashBoard {
  override def clear: Boolean = ???

  /**
    * Method to show the player/carrom/game metrics
    *
    * @param player - Player properties(id, score, isWon, ..etc.,
    * @param carram - CarromBoard, To show remaning red/block coins left on the carrom board
    * @param metrics - Status of the Player/Game
    */
  override def show(player: Option[Player], carram: Option[CarromBoard], metrics: Option[GameMetrics]): Unit = {
    if (player.isDefined) {
      println("==============PLAYER====================")

      println("IDENTIFIER: " + player.get.getIdentifier)
      println("SCORE: " + player.get.getScore)
      println("WON: " + player.get.getWonStatus)
      println("ATTEMPT: " + player.get.getAttempts)
      println("PLAYING STATUS: " + player.get.getPlayingStatus)
      println("FOULS COUNT:" + player.get.getFoulsCount)
      println("========================================")
    }
    else if (metrics.isDefined) {
      println("==================GAME STATUS  ===============")
      println("IDENTIFIER -> " + metrics.get.identifier)
      println("WON -> " + metrics.get.isWon)
      println("SCORE -> " + metrics.get.score)
      println("========================================")
    }
    else {
      println("==============CARROM====================")

      println("RED COINS" + carram.get.getRedCoins)
      println("BLOCK COINS" + carram.get.getBloackCoins)

      println("========================================")
    }
  }

  /**
    * Method to read the input
    *
    * @return - Integer keys
    */
  override def read: Int = {
    scala.io.StdIn.readInt()
  }

  /**
    * Method to show the options and read the commands from  the player
    *
    * @param player - Player
    * @return - Command
    */
  def promptOptions(player: Player): String = {
    println("..........................................................")
    println(player.getIdentifier.toUpperCase() + ": IT'S YOUR TURN, PLEASE SELECT THE CHOICE NUMBER")
    println("1." + AppConfig.getConfig("com.sahaj.command.strike"))
    println("2." + AppConfig.getConfig("com.sahaj.command.multiStrike"))
    println("3." + AppConfig.getConfig("com.sahaj.command.redStrike"))
    println("4." + AppConfig.getConfig("com.sahaj.command.strikerStrike"))
    println("5." + AppConfig.getConfig("com.sahaj.command.defunctCoin"))
    println("6." + AppConfig.getConfig("com.sahaj.command.failedHit"))
    println("7. EXIT")
    println("..........................................................")
    val number = this.read
    number match {
      case 1 => AppConfig.getConfig("com.sahaj.command.strike")
      case 2 => AppConfig.getConfig("com.sahaj.command.multiStrike")
      case 3 => AppConfig.getConfig("com.sahaj.command.redStrike")
      case 4 => AppConfig.getConfig("com.sahaj.command.strikerStrike")
      case 5 => AppConfig.getConfig("com.sahaj.command.defunctCoin")
      case 6 => AppConfig.getConfig("com.sahaj.command.failedHit")
      case 7 =>
        println("System exit..")
        System.exit(0)
        "EXIT"
      case _ =>
        println("INVALID OPTION:" + number)
        "INVALID"
    }

  }
}



