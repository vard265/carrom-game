
/**
  * Author: Manjunath Davanam <manjunathdavanam@gmail.com>
  */

package com.sahaj.mediator

import com.sahaj.executors.Player
import com.sahaj.models.{GameMetrics, Rules, StrikeRules}


/**
  * Mediator trait class - Any meditor should implement this spec
  */
trait Mediator {

  /**
    * Which get the rules
    *
    * @return - Rules
    */
  def getRules: Rules

  /**
    * Which get the strike Rule
    *
    * @param name - Rule name
    * @return - StrikeRules - Particular strike rule
    */
  def getStrikeRules(name: String): StrikeRules

  /**
    * Which validate the game for the particular player with spcific strike rule
    *
    * @param player     - Player
    * @param ruleType   - name fo the strike rule
    * @param blockCoins - Number of block coins are present in the carrom instance
    * @param redCoins   - Number of red coins are present in the carrom instance
    * @return - Metrics of the game (Game metrics)
    */
  def validate(player: Player, ruleType: String, blockCoins: Option[Int], redCoins: Option[Int]): GameMetrics

}

