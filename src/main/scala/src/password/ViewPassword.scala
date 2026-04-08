package src.password

import controllers.Database
import model.User

class ViewPassword {
  //  Colors
  private val redColor = "\n\u001B[91m"
  private val resetColor = "\u001B[0m"
  private val cyanColor = "\n\u001B[96m"
  private val greenColor = "\n\u001B[92m"
  private val yellowColor = "\n\u001B[93m"
  val textBold = "\u001B[1m"

  def viewPasswords(user: User, db: Database): Unit = {
    if (user.id < 1) {
      println(redColor + "\nLogin to View Passwords!\n" + resetColor)
      return
    }

    val passwords = db.getPasswords(user.id)

    if (passwords == null) {
      println(redColor + "\nThere is no Passwords Stored!\n Start Storing Passwords!\n" + resetColor)
      return
    }

    if (passwords.isEmpty) {
      println(redColor + "\nThere is no Passwords Stored!\n Start Storing Passwords!\n" + resetColor)
      return
    }

    println(cyanColor + "\n----- All Passwords -----" + resetColor)
    for (password <- passwords) {
      println(password)
    }
  }
}
