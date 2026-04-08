package utils

object Queries {
//  Encyption Key
  private val key = "thisissecretkey"

//  model.User
  val selectUserByEmailQuery = s"SELECT id, name, email, pgp_sym_decrypt(password::bytea, '$key') AS password, phone, recovery_email FROM users WHERE email = ?"
  val insertUserQuery = s"INSERT INTO users (name, email, password, phone, recovery_email) values(?, ?, pgp_sym_encrypt(?, '$key'), ?, ?)"

//  Password
  val selectPasswordsQuery = s"SELECT id, user_id, service_name, pgp_sym_decrypt(password::bytea, '$key') AS password, category, note, importance, expiry_date FROM passwords WHERE user_id = ?"
  val selectPasswordByIdQuery = s"SELECT id, user_id, service_name, pgp_sym_decrypt(password::bytea, '$key') AS password, category, note, importance, expiry_date FROM passwords WHERE id = ?"
  val insertPasswordQuery = s"INSERT INTO passwords (user_id, service_name, password, category, note, importance, expiry_date) values(?, ?, pgp_sym_encrypt(?, '$key'), ?, ?, ?::pass_importance ,?::date)"
  val editPasswordQuery = s"UPDATE passwords SET service_name = ? , password =  pgp_sym_encrypt(?, '$key'), category = ? , note = ? , importance = ?::pass_importance , expiry_date = ?::date WHERE id = ? AND user_id = ?"
  val deletePasswordQuery = "DELETE FROM passwords WHERE id = ? AND user_id = ?"
}
