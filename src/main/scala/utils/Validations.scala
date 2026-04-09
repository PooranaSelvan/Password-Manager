package utils
import scala.util.control.Breaks.*

object Validations {
  def validateEmail(message: String): String = {
    val pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$".r
    var userEmail = ""

    breakable {
      while (true) {
        userEmail = UserInput.getUserInput(message)

        if (userEmail.isEmpty || !userEmail.matches(pattern.regex)) {
          println(Colors.redColor + "Invalid Email Format" + Colors.resetColor)
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
        userPhone = UserInput.getUserInput(message)
        
        if(userPhone.isEmpty || !userPhone.matches(pattern.regex)) {
          println(Colors.redColor + "Invalid Phone Number Format" + Colors.resetColor)
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
        userDate = UserInput.getUserInput(message)
        
        if(userDate.isEmpty || !userDate.matches(pattern.regex)) {
          println(Colors.redColor + "Invalid Date Format" + Colors.resetColor)
        } else {
          break
        }
      }
    }

    return userDate
  }
}
