package code.services.email.settings
import code.services.email.EmailSettings

object SignUpSettings
  extends EmailSettings("foo@foo.com", null, "Sign up completed", "${artifactId}",
    "/templates/signup-success.vm")