package utils
import scala.util.control.Breaks.*

class Validations {
  val input = UserInput()
  private val redColor = "\n\u001B[91m"
  private val resetColor = "\u001B[0m"

  def validateEmail(message: String): String = {
    val pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$".r
    var userEmail = ""

    breakable {
      while (true) {
        userEmail = input.getUserInput(message)

        if (userEmail.isEmpty || !userEmail.matches(pattern.regex)) {
          println(redColor + "Invalid Email Format" + resetColor)
        } else {
          break
        }
      }
    }
    
    return userEmail
  }

  def validatePhone(message: String): String = {
    val pattern = "^[6-9][0-9]{9}$".r
    var userPhone = ""
    
    breakable {
      while(true) {
        userPhone = input.getUserInput(message)
        
        if(userPhone.isEmpty || !userPhone.matches(pattern.regex)) {
          println(redColor + "Invalid Phone Number Format" + resetColor)
        } else {
          break
        }
      }
    }
    
    return userPhone
  }

  def validateDate(message: String): String = {
    val pattern = "^(0[1-9]|[12][0-9]|3[01]):(0[1-9]|1[0-2]):\\d{4}$".r
    var userDate = ""
    
    breakable {
      while(true) {
        userDate = input.getUserInput(message)
        
        if(userDate.isEmpty || !userDate.matches(pattern.regex)) {
          println(redColor + "Invalid Date Format" + resetColor)
        } else {
          break
        }
      }
    }

    return userDate
  }
}
