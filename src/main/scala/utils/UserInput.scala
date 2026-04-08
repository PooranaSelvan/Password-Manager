package utils

import scala.io.StdIn

object UserInput {
  def getUserInput(message: String): String = {
    print(message)
    return StdIn.readLine()
  }

  def getUserInputInt(message: String): Int = {
    print(message)
    return StdIn.readLine().toInt
  }
}
