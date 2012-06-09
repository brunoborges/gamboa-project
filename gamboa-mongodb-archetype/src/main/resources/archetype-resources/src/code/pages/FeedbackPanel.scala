package code.pages

import org.apache.wicket.feedback.FeedbackMessage

class FeedbackPanel(id: String) extends org.apache.wicket.markup.html.panel.FeedbackPanel(id) {

  override def getCSSClass(message: FeedbackMessage): String = {
    return "form-msg-" + message.getLevelAsString().toLowerCase();
  }

}
