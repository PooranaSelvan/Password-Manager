package src.password.checker
import controllers.Database
import model.User
import src.password.ViewPassword
import utils.{CheckPassword, UserInput}

class Predefined {
  private val view = new ViewPassword()
  private val redColor = "\n\u001B[91m"
  private val resetColor = "\u001B[0m"
  private val cyanColor = "\n\u001B[96m"
  private val greenColor = "\n\u001B[92m"
  private val yellowColor = "\n\u001B[93m"
  val textBold = "\u001B[1m"
  val checkPassword = new CheckPassword()

  def checkPassword(user: User, db: Database, input: UserInput): Unit = {
    view.viewPasswords(user, db)

    val passId = input.getUserInputInt("Enter the Password ID to Strength Check : ")

    val pass = db.getPasswordById(passId)

    if (pass == null) {
      println(redColor + "\nThere is no Password in this ID!\n" + resetColor)
      return
    }

    checkPassword.checkStrength(pass.password)
  }
}
