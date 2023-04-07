/**
  * Author: Nanjunda Vardhan <nanjundavardhan5@gamil.com>
  */

import com.sahaj.services.{AppConfig, CarromBoardService}

class TestCarromService extends BaseSpec {
  prepareTest()

  "CarromBoard Service" - {


    "When The player strike the coin" - {
      "Player1 should win a points" in {
        /**
          * GIVEN:
          *  1. Register two new player
          */

        var player1 = CarromBoardService.registerPlayer("Vardhan", true)
        var player2 = CarromBoardService.registerPlayer("Arjun", false)
        /**
          * When player1 strike
          */
        val status = CarromBoardService.play(player1, AppConfig.getConfig("com.sahaj.command.strike"))

        assert(status.score === 1)
        assert(status.identifier === "Vardhan")
      }
    }

    "When a player pockets more than one coin (Multi-strike)" - {
      "Player1 should win a more than 2 points" in {
        /**
          * GIVEN:
          *  1. Register two new player
          */

        var player1 = CarromBoardService.registerPlayer("Vardhan", false)
        var player2 = CarromBoardService.registerPlayer("Arjun", true)

        /**
          * When player did multi-strike
          */
        val status = CarromBoardService.play(player2, AppConfig.getConfig("com.sahaj.command.multiStrike"))

        assert(status.score === 2)
        assert(status.identifier === "Arjun")
      }
    }

    "When a player pockets red coin (Red strike)" - {
      "Player should wins 3 points" in {
        /**
          * GIVEN:
          *  1. Register two new player
          */

        var player1 = CarromBoardService.registerPlayer("Vardhan", false)
        var player2 = CarromBoardService.registerPlayer("Arjun", true)

        /**
          * When player did redStrike
          */
        val status = CarromBoardService.play(player2, AppConfig.getConfig("com.sahaj.command.redStrike"))
        assert(status.score === 3)
        assert(status.identifier === "Arjun")
      }
    }


    "When a player pockets the striker (Striker strike)" - {
      "Player should lose a point" in {
        /**
          * GIVEN:
          *  1. Register two new player
          */
		var player1 = CarromBoardService.registerPlayer("Vardhan", false)
		var player2 = CarromBoardService.registerPlayer("Arjun", true)

        /**
          * When player did striker-strike
          */
        val status = CarromBoardService.play(player2, AppConfig.getConfig("com.sahaj.command.strikerStrike"))
        assert(status.score === -1)
		assert(status.identifier === "Arjun")
      }
    }


    "When a coin is thrown out of the carrom-board, due to a strike (Defunct coin -)" - {
      "Player should lose a point" in {
        /**
          * GIVEN:
          *  1. Register two new player
          */

        var player1 = CarromBoardService.registerPlayer("Manjunath", false)
        var player2 = CarromBoardService.registerPlayer("Irudayam", true)

        /**
          * When player did defunct coin
          */
        val status = CarromBoardService.play(player2, AppConfig.getConfig("com.sahaj.command.defunctCoin"))
        assert(status.score === -2)
        assert(status.identifier === "Irudayam")
      }
    }


    "When a player does not pocket a coin for 3 successive turns (None)" - {
      "Player should lose a point" in {
        /**
          * GIVEN:
          *  1. Register two new player
          */

        var player1 = CarromBoardService.registerPlayer("Manjunath", false)
        var player2 = CarromBoardService.registerPlayer("Irudayam", true)

        val hit = 1
        val max_invalid_strike = 3

        /**
          * When player does not pocket any coin for 3 successive truns - failed hit
          */
        val res = for (hit <- 1 to max_invalid_strike) yield {
          CarromBoardService.play(player2, AppConfig.getConfig("com.sahaj.command.failedHit"))
        }
        assert(res.lift(max_invalid_strike - 1).get.score === -1)
        assert(res.lift(max_invalid_strike - 1).get.identifier === "Irudayam")
      }
    }

    "When a player fouls 3 times (FOULS)" - {
      "Player should lose 1 point additional" in {
        /**
          * GIVEN:
          *  1. Register two new player
          */
        val player1 = CarromBoardService.registerPlayer("Raju", false)
        val player2 = CarromBoardService.registerPlayer("Irudayam", true)

        // Player2 score : 2
        CarromBoardService.play(player2, AppConfig.getConfig("com.sahaj.command.multiStrike"))

        /**
          * Player fouls for 3 times: Should loose extra 1 point
          */

        // Player2 Score here: 0
        var fouls1 = CarromBoardService.play(player2, AppConfig.getConfig("com.sahaj.command.defunctCoin"))

        // Player2 Score here: -1
        var fouls2 = CarromBoardService.play(player2, AppConfig.getConfig("com.sahaj.command.strikerStrike"))
        // Player score here -2
        val fouls3 = CarromBoardService.play(player2, AppConfig.getConfig("com.sahaj.command.strikerStrike"))
        // Actual is -2, Since it is fouls for 3 times then aditional 1 point will deduct
        assert(fouls3.score === -3)
        assert(fouls3.identifier === "Irudayam")
      }
    }


    "A game is won by the player" - {
      "Should have altease 5 Points in total and 3 points more than opponent " in {

        val player1 = CarromBoardService.registerPlayer("Manju", true)
        val player2 = CarromBoardService.registerPlayer("Manoj", false)

        //player1-Score: 2
        CarromBoardService.play(player1, AppConfig.getConfig("com.sahaj.command.multiStrike"))

        //player2-Score: 2
        CarromBoardService.play(player2, AppConfig.getConfig("com.sahaj.command.multiStrike"))


        //player2-Score: 2-2 = 0
        CarromBoardService.play(player2, AppConfig.getConfig("com.sahaj.command.defunctCoin"))


        //player1-Score: 2+2
        CarromBoardService.play(player1, AppConfig.getConfig("com.sahaj.command.multiStrike"))

        //player1-Score: 3+1
        CarromBoardService.play(player1, AppConfig.getConfig("com.sahaj.command.strike"))

        val gameResult = CarromBoardService.getMatchStatus(player1, player2)

        assert(gameResult.identifier === "Manju")
        assert(gameResult.score === 5) // Manju player got 5 score and 3 points more than opponent.

      }
    }
  }
}
