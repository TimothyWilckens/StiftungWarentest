package gymhum.de;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LueckentextController {

    int answerField1;
    int answerField2;
    boolean correctAnswer1;
    boolean correctAnswer2;
    boolean lueckentextBestanden;
    int selectedanswer1;
    int selectedanswer2;

    public LueckentextController(){
        // Richtige Antwort für das Feld wird hier angegeben, die einzelnen Werte stehen für den Wert Value bei den einzelnen Optionen in lueckentext.html, neue Felder können einfach hinzugefügt werden. 0 ist nur der Default-Wert wenn nichts ausgewählt wurde.
        setAnswerField1(1); 
        setAnswerField2(1);
        setCorrectAnswer1(false);
        setCorrectAnswer2(false);
        // LueckentextBestanden gibt an, ob in der aktuellen Session der Lückentext bereits einmal vollständig gelöst wurde. Dies kann durch die Funktion 
        setLueckentextBestanden(false);
        // Die Antworten für die Felder werden kurz zwischengespeichert
        setSelectedanswer1(0);
        setSelectedanswer2(0);
    }

    // preLueckentext prüft, ob der Lückentext in dieser Session bereits abgeschlossen wurde
    @GetMapping("/preLueckentext")
    public String preLueckentext(@RequestParam(name="activePage", required = false, defaultValue = "preLueckentext") String activePage, Model model){
        model.addAttribute("activePage", "preLueckentext");
        if(lueckentextBestanden == true){
            return "redirect:/lueckentextResult";
        } else {
            return "redirect:/lueckentext";
        }
    }

    @GetMapping("/lueckentext")
    public String lueckentext(@RequestParam(name="activePage", required = false, defaultValue = "lueckentext") String activePage, Model model){
        model.addAttribute("activePage", "lueckentext");
        return "index.html";
    }

    @GetMapping("/lueckentext_submit")
    public String lueckentext_submit(@RequestParam(name="activePage", required = false, defaultValue = "lueckentext") String activePage, @RequestParam(name="answer1", required = true, defaultValue = "0") int answer1, @RequestParam(name="answer2", required = true, defaultValue = "0") int answer2, Model model){ // Werte werden durch den Namen übergeben, welchen das Feld in html durch th:name erhält
        model.addAttribute("activePage", "lueckentext_submit");
        // Ausgwählte Werte werden zur Prüfung zwischengespeichert
        setSelectedanswer1(answer1);
        setSelectedanswer2(answer2);
        Prüfung();
        return "redirect:/lueckentextResult";
    }

    @GetMapping("/lueckentextResult")
    public String lueckentextResult(@RequestParam(name="activePage", required = false, defaultValue = "lueckentextResult") String activePage, Model model){
        model.addAttribute("activePage", "lueckentextResult");
        // Booleans für korrekte Antworten werden übergeben unter den Namen Result1 und Result2
        model.addAttribute("Result1", correctAnswer1);
        model.addAttribute("Result2", correctAnswer2);
        // Boolean ob Bestanden
        model.addAttribute("Bestanden", lueckentextBestanden);
        return "index.html";
    }

    @GetMapping("/lueckentextResult_submit")
    public String lueckentextResult_submit(@RequestParam(name="activePage", required = false, defaultValue = "lueckentextResult") String activePage, @RequestParam(name="answer1", required = true, defaultValue = "0") int answer1, @RequestParam(name="answer2", required = true, defaultValue = "0") int answer2, Model model){ 
        model.addAttribute("activePage", "lueckentextResult_submit");

        // Prüfung, ob die Werte bereits richtig waren, damit sie nicht durch den defaultValue überschrieben werden
        if(correctAnswer1 || true){
            setSelectedanswer1(answer1);
        }
        if(correctAnswer2 || true){
            setSelectedanswer2(answer2);
        }
        Prüfung();
        return "redirect:/lueckentextResult";
    }

    // Reset-Mapping für manuellen Reset durch Button nach Abschluss
    @GetMapping("/lueckentextReset")
    public String lueckentextReset(@RequestParam(name="activePage", required = false, defaultValue = "lueckentextReset") String activePage, Model model){
        model.addAttribute("activePage", "lueckentextReset");
        Reset();
        return "redirect:/lueckentext";
    }
    // Reset-Mapping für automatischen Reset bei Verlassen der Seite
    @GetMapping("/lueckentextAbbruch")
    public String lueckentextAbbruch(@RequestParam(name="activePage", required = false, defaultValue = "lueckentextAbbruch") String activePage, Model model){
        model.addAttribute("activePage", "lueckentextAbbruch");
        Reset();
        return "redirect:/?activePage=interaktive_elemente";
    }

    // METHODEN

    // Prüfung, ob gegebene Werte der Lösung entsprechen
    public void Prüfung() {
        if(selectedanswer1 == answerField1){
            correctAnswer1 = true;
            System.out.println("Antwort 1 Korrekt");
        } else {
            System.out.println("Falsche Antwort " + selectedanswer1);
        }
        if(selectedanswer2 == answerField2){
            correctAnswer2 = true;
            System.out.println("Antwort 2 Korrekt");
        } else {
            System.out.println("Falsche Antwort " + selectedanswer2);
        }
        if(correctAnswer1 && correctAnswer2){
            setLueckentextBestanden(true);
        }
    }

    // Reset Methode
    public void Reset() {
        setCorrectAnswer1(false);
        setCorrectAnswer2(false);
        setLueckentextBestanden(false);
        setSelectedanswer1(0);
        setSelectedanswer2(0);
    }


    // SETTER UND GETTER

    public void setAnswerField1(int answerField1) {
        this.answerField1 = answerField1;
    }
    public void setAnswerField2(int answerField2) {
        this.answerField2 = answerField2;
    }
    public void setCorrectAnswer1(boolean correctAnswer1) {
        this.correctAnswer1 = correctAnswer1;
    }
    public void setCorrectAnswer2(boolean correctAnswer2) {
        this.correctAnswer2 = correctAnswer2;
    }
    public void setLueckentextBestanden(boolean lueckentextBestanden) {
        this.lueckentextBestanden = lueckentextBestanden;
    }
    public void setSelectedanswer1(int selectedanswer1) {
        this.selectedanswer1 = selectedanswer1;
    }
    public void setSelectedanswer2(int selectedanswer2) {
        this.selectedanswer2 = selectedanswer2;
    }
    public int getAnswerField1() {
        return answerField1;
    }
    public int getAnswerField2() {
        return answerField2;
    }
    public Boolean getCorrectAnswer1() {
        return correctAnswer1;
    }
    public Boolean getCorrectAnswer2() {
        return correctAnswer2;
    }
    public Boolean getLueckentextBestanden() {
        return lueckentextBestanden;
    }
    public int getSelectedAnswer1() {
        return selectedanswer1;
    }
    public int getSelectedAnswer2() {
        return selectedanswer2;
    }

}