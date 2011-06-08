package code.services.email;

import java.util.Map;

trait EmailSenderService {

  def send(settings: EmailSettings, to: String, values: Map[String, Object])

  def send(settings: EmailSettings, values: Map[String, Object])

}

