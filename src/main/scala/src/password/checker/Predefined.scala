package src.password.checker
import controllers.Database
import model.User
import src.password.ViewPassword
import utils.{CheckPassword, Colors, UserInput}

class Predefined {
  private val view = new ViewPassword()
  val checkPassword = new CheckPassword()

  def checkPassword(user: User, db: Database): Unit = {
    view.viewPasswords(user, db)

    val passId = UserInput.getUserInputInt("Enter the Password ID to Strength Check : ")

    val pass = db.getPasswordById(passId)

    if (pass == null) {
      println(Colors.redColor + "\nThere is no Password in this ID!\n" + Colors.resetColor)
      return
    }

    checkPassword.checkStrength(pass.password)
  }
}
