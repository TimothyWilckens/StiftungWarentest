 package gymhum.de;

import java.math.BigInteger;
//test

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


public class RsaVerfahrenController {
    BigInteger p;
    BigInteger q;
    BigInteger n;
    BigInteger m;
    BigInteger d;
    BigInteger e;
    boolean keysequal;

    public RsaVerfahrenController(){
        setP(BigInteger.valueOf(13));
        setQ(BigInteger.valueOf(7));
        setE(BigInteger.valueOf(11));
           
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







    


    
}








