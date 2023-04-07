/**
  * Author: Manjunath Davanam <manjunathdavanam@gamil.com>
  */

package com.sahaj.executors

/**
  * Player class
  *
  * @param id      - Player identifier
  * @param wonToss - Player won toss metrics
  */
class Player(id: String, wonToss: Boolean) {

  /**
    * Identifier of the player
    */
  private var identifier: String = id

  /**
    * Count of red coins the player is having
    */
  private var redCoins: Int = 0

  /**
    * Count of block coins the player is having
    */
  private var blockCoins: Int = 0

  /**
    * Score of the player
    */
  private var score: Int = 0

  /**
    * Player won metrics
    */
  private var isWon: Option[Boolean] = Some(false)

  /**
    * Status of the player - weather is playing or not.
    */
  private var isPlaying: Boolean = wonToss

  /**
    * To track the attempts
    */
  private var attempts: Int = 0

  /**
    * To track the fouls count
    */
  private var foulsCount: Int = 0

  /**
    * Method to get the identifier
    *
    * @return - Identifier
    */
  def getIdentifier: String = {
    this.identifier
  }

  /**
    * Method to get the score
    *
    * @return - Score
    */
  def getScore: Int = {
    this.score
  }

  /**
    * Method to update the score of the player
    *
    * @param score - Score
    */
  def setScore(score: Int): Unit = {
    this.score = score
  }

  /**
    * Method to get the attempts count of the player
    *
    * @return _ attempts
    */
  def getAttempts: Int = {
    this.attempts
  }

  /**
    * Method to update the attempts of the player
    *
    * @param attempt
    */
  def setAttempts(attempt: Int): Unit = {
    this.attempts = attempt
  }

  /**
    * Method to get the player won metrics
    *
    * @return - Won metrics - true/false
    */
  def getWonStatus: Boolean = {
    this.isWon.getOrElse(false)
  }

  /**
    * Method to update the won metrics of the player
    *
    * @param status - True/False
    */
  def setWonStatus(status: Option[Boolean]): Unit = {
    this.isWon = status
  }

  /**
    * Method to get red coins from the player
    *
    * @return - Red coins count
    */
  def getRedCoins: Int = {
    this.redCoins
  }

  /**
    * Method to set the red coins for the player
    *
    * @param redCoins - Red coins
    */
  def setRedCoins(redCoins: Int): Unit = {
    this.redCoins = redCoins
  }

  /**
    * Method to get the player block coins
    *
    * @return - Block coins count
    */
  def getBlackCoins: Int = {
    this.blockCoins
  }

  /**
    * Method to set the player block coins
    */
  def setBlackCoins(blackCoins: Int): Unit = {
    this.blockCoins = blackCoins
  }

  /**
    * Method to set the player playing metrics - playing or not.
    *
    * @param isPlaying - Playing metrics of the player(true/false)
    */
  def setPlayingStatus(isPlaying: Boolean): Unit = {
    this.isPlaying = isPlaying
  }

  /**
    * Method to get the player playing metrics - playing or not.
    *
    * @return - Playing metrics
    */
  def getPlayingStatus: Boolean = {
    this.isPlaying
  }

  /**
    * Method to set the fouls count to the player
    *
    * @param count - fouls count
    */
  def setFoulsCount(count: Int): Unit = {
    this.foulsCount = count
  }

  /**
    * Method to get the fouls count of the player
    *
    * @return - Fouls count
    */
  def getFoulsCount: Int = {
    this.foulsCount
  }

  /**
    * Method to reset the player instance
    */
  def reset(): Unit = {
    this.redCoins = 0
    this.blockCoins = 0
    this.score = 0
    this.isWon = Some(false)
    this.attempts = 0
    this.isPlaying = false
    this.foulsCount = 0
  }


}
