/**
  * Author: Manjunath Davanam <manjunathdavanam@gamil.com>
  */


package com.sahaj.command

import com.sahaj.executors.{CarromBoard, Player}
import com.sahaj.models.GameMetrics

class Strike(game: CarromBoard) extends Command {
  /**
    * CarromBoard Class instance
    */
  private val carrom: CarromBoard = game

  /**
    *
    * @param player : Player
    * @return GameMetrics - {identifier: String, score: Int, isPlaying: Boolean, redCoins: Int, blockCoins: Int, isWon: Boolean, metrics:Option[String]}
    */
  override def execute(player: Player): GameMetrics = {
    this.carrom.strike(player)
  }
}
