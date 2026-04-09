package src.password
import controllers.Database
import model.{PassImportance, User}
import utils.{Colors, GenerateObjects, UserInput, Validations}

class AddPassword {
  val textBold = "\u001B[1m"

  def addPassword(user: User, db: Database): Unit = {
    println(Colors.cyanColor + "\n----- Store New Password -----" + Colors.resetColor)

    if (user.id < 1) {
      println(Colors.redColor + "\nLogin to Store Passwords!\n" + Colors.resetColor)
      return
    }
    val userId = user.id
    val serviceName = UserInput.getUserInput("Enter the Service Name : ")
    val password = UserInput.getUserInput("Enter the Password : ")
    val category = UserInput.getUserInput("Enter the Category : ")
    val note = UserInput.getUserInput("Enter the Note : ")
    val importance = UserInput.getUserInput("Enter the Importance of Password (Low --> Medium --> High) : ")
    val expiryDate = Validations.validateDate("Enter the Expiry Date of the Password (dd:mm:yyyy) : ")

    val parts = expiryDate.split(":")
    val isoDate = s"${parts(2)}-${parts(1)}-${parts(0)}"

    val generatedPassword = GenerateObjects.generatePasswordObject(0, userId, serviceName, password, category, note, PassImportance.valueOf(importance.capitalize), isoDate)

    val isStored = db.savePassword(generatedPassword)

    if (isStored == null) {
      println(Colors.redColor + "\nPassword Storing Failed!\n" + Colors.resetColor)
      return
    }

    println(Colors.greenColor + "\n--- Password Stored Successfully! ---\n" + Colors.resetColor)
  }
}
