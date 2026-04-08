package src.user

import controllers.Database
import utils.{Colors, UserInput, Validations}
import model.User

import scala.util.control.Breaks.*

class Signup {
  private val validate = new Validations()
  
  def signUpUser(db: Database): User = {
    println(Colors.cyanColor + "\n----- SignUp -----" + Colors.resetColor)

    val userName = UserInput.getUserInput("Enter your Name : ")
    val userEmail = validate.validateEmail("Enter your Email : ")
    val userPass = UserInput.getUserInput("Enter your Master Password : ")
    val userPhone = validate.validatePhone("Enter your Phone : ")

    var userRecEmail = ""
    breakable {
      while (true) {
        userRecEmail = UserInput.getUserInput("Enter your Recovery Email : ")

        if (userEmail.equals(userRecEmail)) {
          userRecEmail = ""
          println(Colors.redColor + "\nRecovery Email should not be same as Your Email!\n" + Colors.resetColor)
        } else {
          break
        }
      }
    }

    val generatedUser = User(0, userName, userEmail, userPass, userPhone, userRecEmail)

    val isSignedUp = db.signUpUser(generatedUser)

    if (isSignedUp == null) {
      println(Colors.redColor + "\nSignUp Failed!\n" + Colors.resetColor)
      return null
    }

    println(Colors.greenColor + "\n--- SignUp Successful ---\n" + Colors.resetColor)
    return isSignedUp
  }
}
