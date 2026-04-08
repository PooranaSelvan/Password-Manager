package src.password
import model.User
import controllers.Database
import utils.{Colors, UserInput}

class DeletePassword {
  private val view = new ViewPassword()
  val textBold = "\u001B[1m"

  
  def removePassword(user: User, db: Database): Unit = {
    view.viewPasswords(user, db)

    println(Colors.cyanColor + "\n----- Delete Password ------" + Colors.resetColor)
    val passId = UserInput.getUserInputInt("Enter the Password ID to Delete : ")

    val pass = db.getPasswordById(passId)

    if (pass == null) {
      println("There is no Password in this ID!")
      return
    }

    val isDeleted = db.deletePassword(passId, user.id)

    if (isDeleted) {
      println(Colors.greenColor + "\nPassword Deleted Successfully!\n" + Colors.resetColor)
    } else {
      println(Colors.redColor + "\nPassword Deletion Failed!\n" + Colors.resetColor)
    }
  }
}
