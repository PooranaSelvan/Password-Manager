package src.password.checker
import utils.{CheckPassword, Colors, UserInput}

class Custom {
  def checkPassword(): Unit = {
    val userPass = UserInput.getUserInput("Enter the Password to Check Strength : ")

    if (userPass.isEmpty) {
      println(Colors.redColor + "\nEnter the Password Correctly!\n" + Colors.resetColor)
      return
    }

    CheckPassword.checkStrength(userPass)
  }
}
