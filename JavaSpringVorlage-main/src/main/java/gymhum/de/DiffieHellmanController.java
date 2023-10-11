package gymhum.de;

import java.math.BigInteger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiffieHellmanController {

    BigInteger p;
    BigInteger g;
    BigInteger a;
    BigInteger b;
    BigInteger BigA;
    BigInteger BigB;
    BigInteger K1;
    BigInteger K2;
    boolean keysequal;

    public DiffieHellmanController(){
        setP(BigInteger.valueOf(11));
        setG(BigInteger.valueOf(7));
        setA(BigInteger.valueOf(3));
        setB(BigInteger.valueOf(6));
        generatekey();
    }

    @GetMapping("/diffiehellman")
    public String diffiehellman(@RequestParam(name="activePage", required = false, defaultValue = "diffiehellman") String activePage, Model model){
        model.addAttribute("activePage", "diffiehellman");
        return "index.html";
    }
    
    @GetMapping("/diffiehellman_choose_p_and_g")
    public String diffiehellman_choose_p_and_g(@RequestParam(name="activePage", required = false, defaultValue = "diffiehellman") String activePage, Model model){
        model.addAttribute("activePage", "diffiehellman_choose_p_and_g");
        return "index.html";
    }

    @GetMapping("/diffiehellman_choose_p_and_g_error")
    public String diffiehellman_choose_p_and_g_error(@RequestParam(name="activePage", required = false, defaultValue = "diffiehellman") String activePage, Model model){
        model.addAttribute("activePage", "diffiehellman_choose_p_and_g_error");
        return "index.html";
    }

    @GetMapping("/diffiehellman_choose_p_and_g_submit")
    public String diffiehellman_choose_p_and_g_submit(@RequestParam(name="activePage", required = false, defaultValue = "diffiehellman") String activePage, @RequestParam(name="p", required = false, defaultValue = "0") int p, @RequestParam(name="g", required = false, defaultValue = "0") int g, Model model){
        model.addAttribute("activePage", "diffiehellman_choose_p_and_g_submit");
        if(isPrime(p)){
            setP(BigInteger.valueOf(p));
            setG(BigInteger.valueOf(g));
            return "redirect:/diffiehellman_choose_a_and_b";
        } else{
            return "redirect:/diffiehellman_choose_p_and_g_error";
        }
        
    }

    @GetMapping("/diffiehellman_choose_a_and_b")
    public String diffiehellman_choose_a_and_b(@RequestParam(name="activePage", required = false, defaultValue = "diffiehellman") String activePage, Model model){
        model.addAttribute("activePage", "diffiehellman_choose_a_and_b");
        model.addAttribute("p", getP());
        model.addAttribute("g", getG());
        return "index.html";
    }

    @GetMapping("/diffiehellman_choose_a_and_b_error")
    public String diffiehellman_choose_a_and_b_error(@RequestParam(name="activePage", required = false, defaultValue = "diffiehellman") String activePage, Model model){
        model.addAttribute("activePage", "diffiehellman_choose_a_and_b_error");
        model.addAttribute("p", getP());
        model.addAttribute("g", getG());
        return "index.html";
    }

    @GetMapping("/diffiehellman_choose_a_and_b_submit")
    public String diffiehellman_choose_a_and_b_submit(@RequestParam(name="activePage", required = false, defaultValue = "diffiehellman") String activePage, @RequestParam(name="a", required = false, defaultValue = "0") int a, @RequestParam(name="b", required = false, defaultValue = "0") int b, Model model){
        model.addAttribute("activePage", "diffiehellman_choose_a_and_b_submit");
        model.addAttribute("p", getP());
        model.addAttribute("g", getG());
        // Wenn bei compareTo der erste Wert kleiner ist, wird -1 ausgegeben (Ziel)
        if(BigInteger.valueOf(a).compareTo(getP())==-1 && BigInteger.valueOf(b).compareTo(getP())==-1){
            setA(BigInteger.valueOf(a));
            setB(BigInteger.valueOf(b));
            return "redirect:/diffiehellmanresult";
        } else{
            return "redirect:/diffiehellman_choose_a_and_b_error";
        }
    }

    @GetMapping("/diffiehellmanresult")
    public String diffiehellmanresult(@RequestParam(name="activePage", required = false, defaultValue = "diffiehellman") String activePage, Model model){
        model.addAttribute("activePage", "diffiehellmanresult");
        generatekey();
        model.addAttribute("key", getK1());
        model.addAttribute("keysequal", getKeysequal());
        return "index.html";
    }

    public void generatekey(){
        setBigA(getG().modPow(a, p));
        setBigB(getG().modPow(b, p));
        setK1(getBigB().modPow(a, p));
        setK2(getBigA().modPow(b, p));
        if(K1.equals(K2)){
            setKeysequal(true);
            System.out.println(getK1());
        }
        else{
            System.out.println("Die Schlüssel gleichen sich nicht");
            System.out.println(getK1());
            System.out.println(getK2());
        }
    }

    // Primzahl Prüfung
    public boolean isPrime(int number){
        if(number <= 1){
            return false;
        }
       for(int i = 2; i <= number/2; i++){
           if((number%i) == 0)
               return  false;
       }
       return true;
    }
    

    // Setter und Getter
    public void setA(BigInteger a) {
        this.a = a;
    }
    public void setB(BigInteger b) {
        this.b = b;
    }
    public void setG(BigInteger g) {
        this.g = g;
    }
    public void setK1(BigInteger k1) {
        K1 = k1;
    }
    public void setK2(BigInteger k2) {
        K2 = k2;
    }
    public void setP(BigInteger p) {
        this.p = p;
    }
    public void setBigA(BigInteger bigA) {
        BigA = bigA;
    }
    public void setBigB(BigInteger bigB) {
        BigB = bigB;
    }
    public void setKeysequal(boolean keysequal) {
        this.keysequal = keysequal;
    }
    public BigInteger getA() {
        return a;
    }
    public BigInteger getB() {
        return b;
    }
    public BigInteger getG() {
        return g;
    }
    public BigInteger getK1() {
        return K1;
    }
    public BigInteger getK2() {
        return K2;
    }
    public BigInteger getP() {
        return p;
    }
    public BigInteger getBigA() {
        return BigA;
    }
    public BigInteger getBigB() {
        return BigB;
    }
    public boolean getKeysequal() {
        return keysequal;
    }

}