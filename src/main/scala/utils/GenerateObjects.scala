package utils
import model.{PassImportance, Password, User}

class GenerateObjects {
  def generateUserObject(id: Int, name: String, email: String, password: String, recoveryEmail: String, phone: String): User = {
    return new User(id, name, email, password, recoveryEmail, phone)
  }
  
  def generatePasswordObject(id: Int, userId: Int, serviceName: String, password: String, category: String, note: String, importance: PassImportance, expiryDate: String): Password = {
    return new Password(id, userId, serviceName, password, category, note, importance, expiryDate)
  }
}
