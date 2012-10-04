package code.services.email.settings
import code.services.email.EmailSettings

object ContactSettings
  extends EmailSettings("foo@foo.com", "contact@foo.com", "Web site contact",
    "${artifactId}", "/templates/contact.vm")