package model

class Password(var id: Int = 0, var userId: Int, var serviceName: String, var password: String, var category: String, var note: String, var importance: PassImportance, var expiryDate: String) {
  override def toString: String = {
    return s"| ID --> $id , ServiceName --> $serviceName , Password --> $password , Category --> $category , Note --> $note , Importance --> $importance , ExpiryDate --> $expiryDate"
  }
}


enum PassImportance:
  case Low, Medium, High