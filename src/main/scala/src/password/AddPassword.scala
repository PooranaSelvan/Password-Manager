package src.password
import controllers.Database
import model.{PassImportance, User}
import utils.{GenerateObjects, UserInput, Validations}

class AddPassword {
  //  Colors
  private val redColor = "\n\u001B[91m"
  private val resetColor = "\u001B[0m"
  private val cyanColor = "\n\u001B[96m"
  private val greenColor = "\n\u001B[92m"
  private val yellowColor = "\n\u001B[93m"
  val textBold = "\u001B[1m"

  def addPassword(user: User, db: Database, input: UserInput, generate: GenerateObjects, validate: Validations): Unit = {
    println(cyanColor + "\n----- Store New Password -----" + resetColor)

    if (user.id < 1) {
      println(redColor + "\nLogin to Store Passwords!\n" + resetColor)
      return
    }
    val userId = user.id
    val serviceName = input.getUserInput("Enter the Service Name : ")
    val password = input.getUserInput("Enter the Password : ")
    val category = input.getUserInput("Enter the Category : ")
    val note = input.getUserInput("Enter the Note : ")
    val importance = input.getUserInput("Enter the Importance of Password (Low --> Medium --> High) : ")
    val expiryDate = validate.validateDate("Enter the Expiry Date of the Password (dd:mm:yyyy) : ")

    val parts = expiryDate.split(":")
    val isoDate = s"${parts(2)}-${parts(1)}-${parts(0)}"

    val generatedPassword = generate.generatePasswordObject(0, userId, serviceName, password, category, note, PassImportance.valueOf(importance.capitalize), isoDate)

    val isStored = db.savePassword(generatedPassword)

    if (isStored == null) {
      println(redColor + "\nPassword Storing Failed!\n" + resetColor)
      return
    }

    println(greenColor + "\n--- Password Stored Successfully! ---\n" + resetColor)
  }
}
