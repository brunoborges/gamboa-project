package code.services.email

case class EmailSettings(var from: String, var to: String, var subject: String, var person: String, var template: String)
