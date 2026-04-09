package src

import controllers.Database
import model.User
import src.password.checker.{Custom, Predefined}
import src.password.{AddPassword, DeletePassword, GeneratePassword, UpdatePassword, ViewPassword}
import src.user.{Login, Signup}
import utils.{Colors, DBConnection}

import java.sql.Connection
import scala.io.StdIn

object PasswordManager {
  val conn: Connection = DBConnection.conn;
  var user: User = null
  
//  Util Classes
  private val db = new Database()
  
//  User Classes
  private val login = new Login()
  private val signup = new Signup()

//  Password Classes
  private val viewPassword = new ViewPassword()
  private val addPassword = new AddPassword()
  private val updatePassword = new UpdatePassword()
  private val removePassword = new DeletePassword()
  private val generatePass = new GeneratePassword()
  private val checkPredefinedPassword = new Predefined()
  private val checkCustomPassword = new Custom()

  def main(args: Array[String]): Unit = {
    println("----- Welcome to Password Manager -----\n")
    showMenuOptions()
  }


  //  Main Options
  private def showMenuOptions(): Unit = {
    while (true) {
      print("Password Manager Options : \n1. Login\n2. SignUp\n3. Reset Password\n4. Exit\nEnter your Choice : ")
      val userChoice = StdIn.readLine().toInt

      userChoice match {
        case 1 => userLogin()
        case 2 => userSignUp()
        case 3 => resetPassword()
        case 4 => return

        case _ => println(Colors.redColor + "Invalid Option Number!" + Colors.resetColor)
      }
    }
  }

  //  Main Options
  private def userLogin(): Unit = {
    val isLoggedIn = login.loginUser(db)
    user = isLoggedIn
    showUserOptions()
  }

  private def userSignUp(): Unit = {
    val isSignedUp = signup.signUpUser(db)
    user = isSignedUp
    showUserOptions()
  }


  private def showUserOptions(): Unit = {
    while (true) {
      print("\n----------\n1. View All Passwords\n2. Store New Password\n3. Edit Password\n4. Delete Password\n5. Generate Password\n6. Check Password Strength\n7. Exit\nEnter your Choice : ")
      val userChoice = StdIn.readLine().toInt

      userChoice match {
        case 1 => viewAllPasswords()
        case 2 => storePassword()
        case 3 => editPassword()
        case 4 => deletePassword()
        case 5 => generatePassword()
        case 6 => checkPasswordStrength()
        case 7 => return

        case _ => println(Colors.redColor + "\nInvalid Option Number!\n" + Colors.resetColor)
      }
    }

    def viewAllPasswords(): Unit = {
      viewPassword.viewPasswords(user, db)
    }

    def storePassword(): Unit = {
      addPassword.addPassword(user, db)
    }

    def editPassword(): Unit = {
      updatePassword.editPassword(user, db)
    }

    def deletePassword(): Unit = {
      removePassword.removePassword(user, db)
    }

    def generatePassword(): Unit = {
      generatePass.generate()
    }

    def checkPasswordStrength(): Unit = {
      println(Colors.cyanColor + "\n----- Password Checker -----" + Colors.resetColor)
      print("1. View Stored Passwords\n2. Enter a Password\nEnter your Option Number : ")
      val userChoice = StdIn.readLine().toInt

      userChoice match {
        case 1 => checkStoredPasswords()
        case 2 => typePassword()
      }

      def checkStoredPasswords(): Unit = {
        checkPredefinedPassword.checkPassword(user, db)
      }

      def typePassword(): Unit = {
        checkCustomPassword.checkPassword()
      }
    }
  }


  private def resetPassword(): Unit = {
    println(Colors.redColor + "\nNot Available!\n" + Colors.resetColor)
  }
}
