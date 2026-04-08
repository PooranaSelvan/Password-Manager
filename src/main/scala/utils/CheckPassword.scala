package utils

import scala.collection.mutable

class CheckPassword {
  private val redColor = "\n\u001B[91m"
  private val resetColor = "\u001B[0m"
  private val cyanColor = "\n\u001B[96m"
  private val greenColor = "\n\u001B[92m"
  private val yellowColor = "\n\u001B[93m"
  val textBold = "\u001B[1m"
  
  def checkStrength(pass: String): Unit = {
    val map = new mutable.HashMap[String, Integer]()
    map("upper") = 0
    map("lower") = 0
    map("digit") = 0
    map("char") = 0

    for (i <- pass) {
      if (i.isUpper) map("upper") += 1
      else if (i.isLower) map("lower") += 1
      else if (i.isDigit) map("digit") += 1
      else map("char") += 1
    }


    var passwordStrength = 0

    if (map("upper") > 0) passwordStrength += 1
    if (map("lower") > 0) passwordStrength += 1
    if (map("digit") > 0) passwordStrength += 1
    if (map("char") > 0) passwordStrength += 1
    if (pass.length >= 8) passwordStrength += 1

    if (passwordStrength == 5) println(greenColor + "\n--- Strong password ---\n" + resetColor)
    else if (passwordStrength >= 3) println(yellowColor + "\n--- Medium password ---\n" + resetColor)
    else println(redColor + "\n--- Weak password ---\n" + resetColor)
  }
}
