package src.user
import controllers.Database
import utils.{Colors, GenerateObjects, UserInput, Validations}
import model.User

class Login {
  private val validate = new Validations()

  def loginUser(db: Database): User = {
    println(Colors.cyanColor + "\n----- Login -----" + Colors.resetColor)
    val userEmail = validate.validateEmail("Enter your Email : ")
    val userPass = UserInput.getUserInput("Enter your Password : ")

    if (userEmail.isEmpty || userPass.isEmpty) {
      println(Colors.redColor + "\nInvalid Email or Password!\n" + Colors.resetColor)
      return null
    }

    val isLoggedIn = db.selectUserByEmail(userEmail)

    if (isLoggedIn == null) {
      println(Colors.redColor + "\nInvalid Email or Password!\n" + Colors.resetColor)
      return null
    }

    if (!userPass.equals(isLoggedIn.password)) {
      println(Colors.redColor + "\nInvalid Email or Password!\n" + Colors.resetColor)
      return null
    }

    println(Colors.greenColor + "Login Successful" + Colors.resetColor)
    return isLoggedIn
  }
}
