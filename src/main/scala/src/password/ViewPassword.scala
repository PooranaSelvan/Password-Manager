package src.password

import controllers.Database
import model.User
import utils.Colors

class ViewPassword {
  def viewPasswords(user: User, db: Database): Unit = {
    if (user.id < 1) {
      println(Colors.redColor + "\nLogin to View Passwords!\n" + Colors.resetColor)
      return
    }

    val passwords = db.getPasswords(user.id)

    if (passwords == null) {
      println(Colors.redColor + "\nThere is no Passwords Stored!\n Start Storing Passwords!\n" + Colors.resetColor)
      return
    }

    if (passwords.isEmpty) {
      println(Colors.redColor + "\nThere is no Passwords Stored!\n Start Storing Passwords!\n" + Colors.resetColor)
      return
    }

    println(Colors.cyanColor + "\n----- All Passwords -----" + Colors.resetColor)
    for (password <- passwords) {
      println(password)
    }
  }
}
