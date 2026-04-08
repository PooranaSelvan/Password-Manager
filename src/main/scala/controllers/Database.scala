package controllers

import model.PassImportance.{High, Low, Medium}
import model.{PassImportance, Password, User}
import utils.{DBConnection, GenerateObjects, Queries}

import java.sql.{Connection, DriverManager, ResultSet, Statement}
import scala.collection.mutable.ArrayBuffer

class Database {
  val conn: Connection = DBConnection.conn;

  def selectUserByEmail(email: String): User = {
      val ps = conn.prepareStatement(Queries.selectUserByEmailQuery)
      ps.setString(1, email)

      val rs = ps.executeQuery()

      if(rs.next()) {
        val id = rs.getInt("id")
        val name = rs.getString("name")
        val email = rs.getString("email")
        val password = rs.getString("password")
        val phone = rs.getString("phone")
        val recoveryEmail = rs.getString("recovery_email")

        return GenerateObjects.generateUserObject(id, name, email, password, recoveryEmail, phone)
      }

      return null
  }

  def signUpUser(user: User): User = {
    val ps = conn.prepareStatement(Queries.insertUserQuery, Statement.RETURN_GENERATED_KEYS)
    ps.setString(1, user.name)
    ps.setString(2, user.email)
    ps.setString(3, user.password)
    ps.setString(4, user.phone)
    ps.setString(5, user.recovery_email)

    val res = ps.executeUpdate()

    if(res == 0) {
      return null
    }

    val rs = ps.getGeneratedKeys

    if(rs.next()) {
      return GenerateObjects.generateUserObject(rs.getInt(1), user.name, user.email, user.password, user.recovery_email, user.phone)
    }

    return null
  }

  def getPasswords(userId: Int): ArrayBuffer[Password] = {
    val passwords = ArrayBuffer[Password]()

    val ps = conn.prepareStatement(Queries.selectPasswordsQuery)
    ps.setInt(1, userId)

    val rs = ps.executeQuery()

    while(rs.next()) {
      val id = rs.getInt("id")
      val serviceName = rs.getString("service_name")
      val password = rs.getString("password")
      val category = rs.getString("category")
      val note = rs.getString("note")
      val importance = PassImportance.valueOf(rs.getString("importance").capitalize)
      val expiryDate = rs.getString("expiry_date")

      passwords += GenerateObjects.generatePasswordObject(id, userId, serviceName, password, category, note, importance, expiryDate)
    }

    if(passwords.isEmpty) {
      return null
    }

    return passwords
  }

  def getPasswordById(passId: Int): Password = {
    val ps = conn.prepareStatement(Queries.selectPasswordByIdQuery)
    ps.setInt(1, passId)

    val rs = ps.executeQuery()

    if(rs.next()) {
      val userId = rs.getInt("user_id")
      val serviceName = rs.getString("service_name")
      val password = rs.getString("password")
      val category = rs.getString("category")
      val note = rs.getString("note")
      val importance = PassImportance.valueOf(rs.getString("importance").capitalize)
      val expiryDate = rs.getString("expiry_date")

      return GenerateObjects.generatePasswordObject(passId, userId, serviceName, password, category, note, importance, expiryDate)
    }

    return null
  }

  def savePassword(pass: Password): Password = {
    val ps = conn.prepareStatement(Queries.insertPasswordQuery, Statement.RETURN_GENERATED_KEYS)
    ps.setInt(1, pass.userId)
    ps.setString(2, pass.serviceName)
    ps.setString(3, pass.password)
    ps.setString(4, pass.category)
    ps.setString(5, pass.note)
    ps.setString(6, pass.importance.toString.toLowerCase)
    ps.setString(7, pass.expiryDate)

    val res = ps.executeUpdate()

    if(res == 0) {
      return null
    }

    val rs = ps.getGeneratedKeys

    if(rs.next()) {
      return GenerateObjects.generatePasswordObject(rs.getInt(1), pass.userId, pass.serviceName, pass.password, pass.category, pass.note, pass.importance, pass.expiryDate)
    }

    return null
  }

  def updatePassword(password: Password, userId: Int): Boolean = {
    val ps = conn.prepareStatement(Queries.editPasswordQuery)
    ps.setString(1, password.serviceName)
    ps.setString(2, password.password)
    ps.setString(3, password.category)
    ps.setString(4, password.note)
    ps.setString(5, password.importance.toString.toLowerCase)
    ps.setString(6, password.expiryDate)
    ps.setInt(7, password.id)
    ps.setInt(8, userId)

    val res = ps.executeUpdate()

    if (res == 0) {
      return false
    }

    return true
  }

  def deletePassword(passId: Int, userId: Int): Boolean = {
    val ps = conn.prepareStatement(Queries.deletePasswordQuery)
    ps.setInt(1, passId)
    ps.setInt(2, userId)

    val res = ps.executeUpdate()

    if(res == 0) {
      return false
    }

    return true
  }
}
