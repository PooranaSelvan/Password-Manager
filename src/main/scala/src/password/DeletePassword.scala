package src.password
import model.User
import controllers.Database
import utils.UserInput

class DeletePassword {
  private val view = new ViewPassword()
  private val redColor = "\n\u001B[91m"
  private val resetColor = "\u001B[0m"
  private val cyanColor = "\n\u001B[96m"
  private val greenColor = "\n\u001B[92m"
  private val yellowColor = "\n\u001B[93m"
  val textBold = "\u001B[1m"

  
  def removePassword(user: User, db: Database, input: UserInput): Unit = {
    view.viewPasswords(user, db)

    println(cyanColor + "\n----- Delete Password ------" + resetColor)
    val passId = input.getUserInputInt("Enter the Password ID to Delete : ")

    val pass = db.getPasswordById(passId)

    if (pass == null) {
      println("There is no Password in this ID!")
      return
    }

    val isDeleted = db.deletePassword(passId, user.id)

    if (isDeleted) {
      println(greenColor + "\nPassword Deleted Successfully!\n" + resetColor)
    } else {
      println(redColor + "\nPassword Deletion Failed!\n" + resetColor)
    }
  }
}
