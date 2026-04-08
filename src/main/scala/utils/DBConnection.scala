package utils

import java.sql.{Connection, DriverManager}

object DBConnection {
  private val url = "jdbc:postgresql://localhost:5432/password_manager"
  private val user = "postgres"
  private val password = "postgres"

  lazy val conn: Connection = DriverManager.getConnection(url, user, password)
}
