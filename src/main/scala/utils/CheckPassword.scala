package utils

import scala.collection.mutable

class CheckPassword {
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

    if (passwordStrength == 5) println(Colors.greenColor + "\n--- Strong password ---\n" + Colors.resetColor)
    else if (passwordStrength >= 3) println(Colors.yellowColor + "\n--- Medium password ---\n" + Colors.resetColor)
    else println(Colors.redColor + "\n--- Weak password ---\n" + Colors.resetColor)
  }
}
