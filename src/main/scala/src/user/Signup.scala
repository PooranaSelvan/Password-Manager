package src.user

import controllers.Database
import utils.{GenerateObjects, UserInput, Validations}
import model.User
import scala.util.control.Breaks.*

class Signup {
  private val redColor = "\n\u001B[91m"
  private val resetColor = "\u001B[0m"
  private val cyanColor = "\n\u001B[96m"
  private val greenColor = "\n\u001B[92m"
  private val yellowColor = "\n\u001B[93m"
  val textBold = "\u001B[1m"

  private val input = new UserInput()
  private val generate = new GenerateObjects()
  private val validate = new Validations()
  
  def signUpUser(db: Database): User = {
    println(cyanColor + "\n----- SignUp -----" + resetColor)

    val userName = input.getUserInput("Enter your Name : ")
    val userEmail = validate.validateEmail("Enter your Email : ")
    val userPass = input.getUserInput("Enter your Master Password : ")
    val userPhone = validate.validatePhone("Enter your Phone : ")

    var userRecEmail = ""
    breakable {
      while (true) {
        userRecEmail = input.getUserInput("Enter your Recovery Email : ")

        if (userEmail.equals(userRecEmail)) {
          userRecEmail = ""
          println(redColor + "\nRecovery Email should not be same as Your Email!\n" + resetColor)
        } else {
          break
        }
      }
    }

    val generatedUser = generate.generateUserObject(0, userName, userEmail, userPass, userPhone, userRecEmail)

    val isSignedUp = db.signUpUser(generatedUser)

    if (isSignedUp == null) {
      println(redColor + "\nSignUp Failed!\n" + resetColor)
      return null
    }

    println(greenColor + "\n--- SignUp Successful ---\n" + resetColor)
    return isSignedUp
  }
}
