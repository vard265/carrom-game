/**
  * Author: Manjunath Davanam <manjunathdavanam@gmail.com>
  */
package com.sahaj.models

class Models extends Serializable {}

/**
  *
  * @param ruleType      - Which defines the type of rule. It can be strike, multiStrike, redStrike ..etc.,
  * @param score         - Score for the particular rule
  * @param onBlockCoins  - Number of block coins are pockted
  * @param onRedCoins    - Number of red coins are pockted
  * @param isValidStrike - Which defines, Is particualr rule is invalidStrike (ie., Any negative rule)
  * @param playingStatus - Which defines should allow player to continue to play or not
  * @param maxAttempts   - Number attempts are allowed
  */
case class StrikeRules(var ruleType: String,
                       var score: Int,
                       var onBlockCoins: Int,
                       var onRedCoins: Int,
                       var isValidStrike: Boolean,
                       var playingStatus: Boolean,
                       var maxAttempts: Option[Int]
                      )

/**
  *
  * @param strikeRules    - List of StrikeRules
  * @param onFouls        - On fouls, Deduct the number of points
  * @param maxFouls       - Allowed max fouls
  * @param minPointsToWon - Minimum score required to win the game
  * @param opponMinDiff   - Minimum score required compare to opponenet
  */
case class Rules(strikeRules: List[StrikeRules],
                 onFouls: Int,
                 maxFouls: Int,
                 minPointsToWon: Int,
                 opponMinDiff: Int
                )

/**
  * Which gives the stats of the player/Game - Metrics
  *
  * @param identifier - Player identifier
  * @param score      - Player score
  * @param isPlaying  - Playing metrics - true/false
  * @param redCoins   - Number of red-coins are pockted
  * @param blockCoins - Number of block coins are pockted
  * @param isWon      - isPlayer won
  * @param status     - Game metrics - It can be WON,DRAW, NOT_WON
  */
case class GameMetrics(identifier: String,
                       score: Int,
                       isPlaying: Boolean,
                       redCoins: Int,
                       blockCoins: Int,
                       isWon: Boolean,
                       status: Option[String]
                       )




