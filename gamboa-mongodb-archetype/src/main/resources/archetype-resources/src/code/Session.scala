package code

import java.util.Locale
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession
import org.apache.wicket.authroles.authorization.strategies.role.Roles
import org.apache.wicket.injection.Injector
import org.apache.wicket.request.Request
import org.apache.wicket.spring.injection.annot.SpringBean
import org.slf4j.{ LoggerFactory, Logger }
import org.springframework.security.authentication.{ AuthenticationManager, UsernamePasswordAuthenticationToken }
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder

class Session(request: Request) extends AuthenticatedWebSession(request) {

  @SpringBean
  var authenticationManager: AuthenticationManager = _
  var user: User = _

  val defaultLocale = new Locale("pt", "BR") // change this to your prefered locale
  val roles = new Roles()

  override def getRoles(): Roles = roles
  override def getLocale = defaultLocale 
  def injectDependencies() = Injector.get().inject(this)

  injectDependencies()

  def ensureDependenciesNotNull =
    if (authenticationManager == null)
      throw new IllegalStateException(
        "AdminSession requires an authenticationManager.")

  override def authenticate(username: String, password: String) = {
    var authenticated = false
    var authentication = authenticationManager
      .authenticate(new UsernamePasswordAuthenticationToken(username,
        password))
    SecurityContextHolder.getContext().setAuthentication(authentication)
    authenticated = authentication.isAuthenticated()
    if (authenticated) {
      user = authentication.getPrincipal().asInstanceOf[User]
      addRolesFromAuthentication()
    }

    authenticated
  }

  def addRolesFromAuthentication() = {
    val authentication = SecurityContextHolder.getContext()
      .getAuthentication()

    var collectionAuthorities = List.fromArray[GrantedAuthority](authentication.getAuthorities().toArray(new Array[GrantedAuthority](0)))
    collectionAuthorities.foreach(a => roles.add(a.getAuthority()))
  }

  override def signOut = {
    super.signOut()
    user = null
  }

}
