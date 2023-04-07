import com.sahaj.services.CarromBoardService
import org.scalatest.FreeSpec

class BaseSpec extends FreeSpec {
  def prepareTest(): Unit = {
    // Do something Before test execution
    CarromBoardService.init()
  }
}
