package src.password
import controllers.Database
import model.{PassImportance, User}
import src.password.ViewPassword
import utils.{Colors, UserInput, Validations}

import scala.io.StdIn


class UpdatePassword {
  private val view = new ViewPassword()
  val textBold = "\u001B[1m"

  def editPassword(user: User, db: Database, validate: Validations): Unit = {
    view.viewPasswords(user, db)

    println(Colors.cyanColor + "\n----- Edit Password Details -----" + Colors.resetColor)
    val passId = UserInput.getUserInputInt("Enter the Password ID to Edit : ")

    val pass = db.getPasswordById(passId)

    if (pass == null) {
      println(Colors.redColor + "There is no Password in this ID!" + Colors.resetColor)
      return
    }

    print("What do you want to Edit?\n1. Service Name\n2. Password\n3. Category\n4. Note\n5. Importance\n6. Expiry Date\n7. Exit\nEnter your Option Number : ")
    val userChoice = StdIn.readLine().toInt


    userChoice match {
      case 1 =>
        pass.serviceName = UserInput.getUserInput("Enter the New Service Name : ")
      case 2 =>
        pass.password = UserInput.getUserInput("Enter the New Password : ")
      case 3 =>
        pass.category = UserInput.getUserInput("Enter the New Category : ")
      case 4 =>
        pass.note = UserInput.getUserInput("Enter the New Note : ")
      case 5 =>
        print("Enter the New Importance (Low, Medium, High) : ")
        pass.importance = PassImportance.valueOf(StdIn.readLine())
      case 6 =>
        pass.expiryDate = validate.validateDate("Enter the Expiry Date of the Password (dd:mm:yyyy) : ")
      case 7 => return

      case _ => println(Colors.redColor + "\nInvalid Option Number!\n" + Colors.resetColor)
    }

    val isUpdated = db.updatePassword(pass, user.id)

    if (isUpdated) {
      println(Colors.greenColor + "\nPassword Has been Updated Successfully!\n" + Colors.resetColor)
    } else {
      println(Colors.redColor + "\nPassword Updating Failed\n" + Colors.resetColor)
    }
  }
}
