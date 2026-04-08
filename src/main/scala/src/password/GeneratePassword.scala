package src.password
import org.passay.{CharacterRule, EnglishCharacterData, PasswordGenerator}
import utils.{Colors, UserInput}

class GeneratePassword {
  val textBold = "\u001B[1m"
  
  
  def generate(): Unit = {
    println(Colors.cyanColor + "\n----- Password Generator -----" + Colors.resetColor)

    val lowerRule = new CharacterRule(EnglishCharacterData.LowerCase)
    lowerRule.setNumberOfCharacters(2)

    val upperRule = new CharacterRule(EnglishCharacterData.UpperCase)
    upperRule.setNumberOfCharacters(2)

    val digitRule = new CharacterRule(EnglishCharacterData.Digit)
    digitRule.setNumberOfCharacters(2)

    val specialRule = new CharacterRule(EnglishCharacterData.Special)
    specialRule.setNumberOfCharacters(2)

    val pass = new PasswordGenerator()

    val passLength = UserInput.getUserInputInt("Enter the Length of the Password : ")

    val generatedPass = pass.generatePassword(passLength, lowerRule, upperRule, digitRule, specialRule)

    println(Colors.greenColor + "\nYour Password has been Generated!")
    println("---------------------------------" + Colors.resetColor)
    println(generatedPass)
    println(Colors.greenColor + "---------------------------------")
    println("- COPY THE PASSWORD IMMEDIATELY -\n" + Colors.resetColor)
  }
}
