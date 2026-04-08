package src.user
import controllers.Database
import utils.{GenerateObjects, UserInput, Validations}
import model.User

class Login {
  private val redColor = "\n\u001B[91m"
  private val resetColor = "\u001B[0m"
  private val cyanColor = "\n\u001B[96m"
  private val greenColor = "\n\u001B[92m"
  private val yellowColor = "\n\u001B[93m"
  val textBold = "\u001B[1m"


  private val input = new UserInput()
  private val generate = new GenerateObjects()
  private val validate = new Validations()


  def loginUser(db: Database): User = {
    println(cyanColor + "\n----- Login -----" + resetColor)
    val userEmail = validate.validateEmail("Enter your Email : ")
    val userPass = input.getUserInput("Enter your Password : ")

    if (userEmail.isEmpty || userPass.isEmpty) {
      println(redColor + "\nInvalid Email or Password!\n" + resetColor)
      return null
    }

    val isLoggedIn = db.selectUserByEmail(userEmail)

    if (isLoggedIn == null) {
      println(redColor + "\nInvalid Email or Password!\n" + resetColor)
      return null
    }

    if (!userPass.equals(isLoggedIn.password)) {
      println(redColor + "\nInvalid Email or Password!\n" + resetColor)
      return null
    }

    println(greenColor + "Login Successful" + resetColor)
    return isLoggedIn
  }
}
