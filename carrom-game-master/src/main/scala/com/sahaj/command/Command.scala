/**
  * Author: Manjunath Davanam <manjunathdavanam@gamil.com>
  */

package com.sahaj.command

import com.sahaj.executors.Player
import com.sahaj.models.GameMetrics

/**
  * Command Interface
  */
trait Command {
  /**
    * Interface method to execute the commands
    *
    * @param player : Player
    * @return GameMetrics - {identifier: String, score: Int, isPlaying: Boolean, redCoins: Int, blockCoins: Int, isWon: Boolean, metrics:Option[String]}
    */
  def execute(player: Player): GameMetrics
}
