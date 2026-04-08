package src.password
import controllers.Database
import model.{PassImportance, User}
import src.password.ViewPassword
import utils.{UserInput, Validations}

import scala.io.StdIn


class UpdatePassword {
  private val view = new ViewPassword()
  private val redColor = "\n\u001B[91m"
  private val resetColor = "\u001B[0m"
  private val cyanColor = "\n\u001B[96m"
  private val greenColor = "\n\u001B[92m"
  private val yellowColor = "\n\u001B[93m"
  val textBold = "\u001B[1m"

  def editPassword(user: User, db: Database, input: UserInput, validate: Validations): Unit = {
    view.viewPasswords(user, db)

    println(cyanColor + "\n----- Edit Password Details -----" + resetColor)
    val passId = input.getUserInputInt("Enter the Password ID to Edit : ")

    val pass = db.getPasswordById(passId)

    if (pass == null) {
      println(redColor + "There is no Password in this ID!" + resetColor)
      return
    }

    print("What do you want to Edit?\n1. Service Name\n2. Password\n3. Category\n4. Note\n5. Importance\n6. Expiry Date\n7. Exit\nEnter your Option Number : ")
    val userChoice = StdIn.readLine().toInt


    userChoice match {
      case 1 =>
        pass.serviceName = input.getUserInput("Enter the New Service Name : ")
      case 2 =>
        pass.password = input.getUserInput("Enter the New Password : ")
      case 3 =>
        pass.category = input.getUserInput("Enter the New Category : ")
      case 4 =>
        pass.note = input.getUserInput("Enter the New Note : ")
      case 5 =>
        print("Enter the New Importance (Low, Medium, High) : ")
        pass.importance = PassImportance.valueOf(StdIn.readLine())
      case 6 =>
        pass.expiryDate = validate.validateDate("Enter the Expiry Date of the Password (dd:mm:yyyy) : ")
      case 7 => return

      case _ => println(redColor + "\nInvalid Option Number!\n" + resetColor)
    }

    val isUpdated = db.updatePassword(pass, user.id)

    if (isUpdated) {
      println(greenColor + "\nPassword Has been Updated Successfully!\n" + resetColor)
    } else {
      println(redColor + "\nPassword Updating Failed\n" + resetColor)
    }
  }
}
