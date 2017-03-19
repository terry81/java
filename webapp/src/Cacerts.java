
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ssl. We need to know keyfile along with issuer /
 * serial number of the CAs.
 */
@WebServlet("/Cacerts")
public class Cacerts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String caCerts = "<br><br><b>Tusted CAs:</b><br><br>";
	// Load the JDK's cacerts keystore file
	private String filename = System.getProperty("java.home")
			+ "/lib/security/cacerts".replace('/', File.separatorChar);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cacerts() {

		try {

			FileInputStream is = new FileInputStream(filename);
			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			String password = "changeit";
			keystore.load(is, password.toCharArray());

			PKIXParameters params = new PKIXParameters(keystore);

			Iterator it = params.getTrustAnchors().iterator();
			while (it.hasNext()) {
				TrustAnchor ta = (TrustAnchor) it.next();
				// Get certificate
				String caIssuer = ta.getTrustedCert().getIssuerX500Principal().toString();
				String caSerial = ", Serial: " + ta.getTrustedCert().getSerialNumber().toString();
				caCerts += caIssuer + " " + caSerial + "<br><br>";

			}
		} catch (CertificateException e) {
		} catch (KeyStoreException e) {
		} catch (NoSuchAlgorithmException e) {
		} catch (InvalidAlgorithmParameterException e) {
		} catch (IOException e) {
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("<b>Keystore file: </b>" + filename + caCerts);
	}

}
