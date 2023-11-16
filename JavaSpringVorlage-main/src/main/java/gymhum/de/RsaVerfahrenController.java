package gymhum.de;

import java.math.BigInteger;
import java.util.Scanner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RsaVerfahrenController {
    BigInteger p;
    BigInteger q;
    BigInteger n;
    BigInteger m;
    BigInteger d;
    BigInteger e;
    boolean keysequal;

    public RsaVerfahrenController() {
        setP(BigInteger.valueOf(13));
        setQ(BigInteger.valueOf(7));
        setE(BigInteger.valueOf(11));
    }

    @GetMapping("/rsaverfahren")
    public String rsaverfahren(@RequestParam(name = "activePage", required = false, defaultValue = "rsaverfahren") String activePage, Model model) {
        model.addAttribute("activePage", "rsaverfahren");
        return "index.html";
    }

    @GetMapping("/rsaverfahren_choose_p_and_q")
    public String diffiehellman_choose_p_and_q(
            @RequestParam(name = "activePage", required = false, defaultValue = "rsavefahren") String activePage, Model model) {
        model.addAttribute("activePage", "rsaverfahren_choose_p_and_q");
        return "index.html";
    }

    @GetMapping("/rsavefahren_choose_p_and_q_error")
    public String rsaverfahren_choose_p_and_q_error(
            @RequestParam(name = "activePage", required = false, defaultValue = "rsaverfahren") String activePage,
            Model model) {
        model.addAttribute("activePage", "rsaverfahren_p_and_q_error");
        return "index.html";
    }

    public void generateRSAKeys() {
        // Berechne n
        n = p.multiply(q);

        // Berechne φ(n) (totient-Funktion)
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Wähle d so, dass d*e ≡ 1 (mod φ(n))
        d = e.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger plaintext) {
        // Verschlüsselung: c = plaintext^e % n
        return plaintext.modPow(e, n);
    }

    public BigInteger decrypt(BigInteger ciphertext) {
        // Entschlüsselung: plaintext = ciphertext^d % n
        return ciphertext.modPow(d, n);
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getP() {
        return p;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }

    public BigInteger getQ() {
        return q;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }

    public BigInteger getD() {
        return d;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getN() {
        return n;
    }

    public void setM(BigInteger m) {
        this.m = m;
    }

    public BigInteger getM() {
        return m;
    }

    public void setE(BigInteger e) {
        this.e = e;
    }

    public BigInteger getE() {
        return e;
    }

    public static void main(String[] args) {
        RsaVerfahrenController rsaController = new RsaVerfahrenController();
        rsaController.generateRSAKeys(); // Stelle sicher, dass die Schlüssel generiert wurden

        // Benutzereingabe für den Text
        Scanner scanner = new Scanner(System.in);
        System.out.print("Gib den zu verschlüsselnden Text ein: ");
        String plaintext = scanner.nextLine();

        // Text verschlüsseln
        BigInteger encryptedText = rsaController.encryptText(plaintext);

        // Ausgabe des verschlüsselten Textes
        System.out.println("Verschlüsselter Text: " + encryptedText);

        // Schließe den Scanner
        scanner.close();
    }

    // Methode, um einen Text in eine BigInteger umzuwandeln
    public static BigInteger convertTextToBigInteger(String text) {
        byte[] bytes = text.getBytes();
        return new BigInteger(bytes);
    }

    // Methode, um Text mit dem öffentlichen Schlüssel zu verschlüsseln
    public BigInteger encryptText(String text) {
        return encrypt(convertTextToBigInteger(text));
    }
}