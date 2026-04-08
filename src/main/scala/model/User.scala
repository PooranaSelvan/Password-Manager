package model

case class User(id: Int = 0, name: String, email: String, password: String, recovery_email: String, phone: String) {
  override def toString: String = {
    return s"| ID --> $id , Name --> $name , Email --> $email , Password --> $password , Phone --> $phone , RecoveryEmail --> $recovery_email |"
  }
}
