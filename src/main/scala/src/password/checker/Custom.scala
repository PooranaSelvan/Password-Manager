package src.password.checker
import utils.{CheckPassword, UserInput}

class Custom {
  private val redColor = "\n\u001B[91m"
  private val resetColor = "\u001B[0m"
  private val cyanColor = "\n\u001B[96m"
  private val greenColor = "\n\u001B[92m"
  private val yellowColor = "\n\u001B[93m"
  val textBold = "\u001B[1m"
  val checkPassword = new CheckPassword()

  def checkPassword(input: UserInput): Unit = {
    val userPass = input.getUserInput("Enter the Password to Check Strength : ")

    if (userPass.isEmpty) {
      println(redColor + "\nEnter the Password Correctly!\n" + resetColor)
      return
    }

    checkPassword.checkStrength(userPass)
  }
}
