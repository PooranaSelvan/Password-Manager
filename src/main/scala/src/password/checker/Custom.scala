package src.password.checker
import utils.{CheckPassword, Colors, UserInput}

class Custom {
  private val checkPass = new CheckPassword()

  def checkPassword(): Unit = {
    val userPass = UserInput.getUserInput("Enter the Password to Check Strength : ")

    if (userPass.isEmpty) {
      println(Colors.redColor + "\nEnter the Password Correctly!\n" + Colors.resetColor)
      return
    }

    checkPass.checkStrength(userPass)
  }
}
