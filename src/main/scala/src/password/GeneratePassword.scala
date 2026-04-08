package src.password
import org.passay.{CharacterRule, EnglishCharacterData, PasswordGenerator}
import utils.UserInput

class GeneratePassword {

  private val redColor = "\n\u001B[91m"
  private val resetColor = "\u001B[0m"
  private val cyanColor = "\n\u001B[96m"
  private val greenColor = "\n\u001B[92m"
  private val yellowColor = "\n\u001B[93m"
  val textBold = "\u001B[1m"
  
  
  def generate(input: UserInput): Unit = {
    println(cyanColor + "\n----- Password Generator -----" + resetColor)

    val lowerRule = new CharacterRule(EnglishCharacterData.LowerCase)
    lowerRule.setNumberOfCharacters(2)

    val upperRule = new CharacterRule(EnglishCharacterData.UpperCase)
    upperRule.setNumberOfCharacters(2)

    val digitRule = new CharacterRule(EnglishCharacterData.Digit)
    digitRule.setNumberOfCharacters(2)

    val specialRule = new CharacterRule(EnglishCharacterData.Special)
    specialRule.setNumberOfCharacters(2)

    val pass = new PasswordGenerator()

    val passLength = input.getUserInputInt("Enter the Length of the Password : ")

    val generatedPass = pass.generatePassword(passLength, lowerRule, upperRule, digitRule, specialRule)

    println(greenColor + "\nYour Password has been Generated!")
    println("---------------------------------" + resetColor)
    println(generatedPass)
    println(greenColor + "---------------------------------")
    println("- COPY THE PASSWORD IMMEDIATELY -\n" + resetColor)
  }
}
