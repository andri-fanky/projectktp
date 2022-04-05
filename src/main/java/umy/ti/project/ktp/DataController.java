/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umy.ti.project.ktp;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Fanky
 */
@Controller
public class DataController {
    
    DataJpaController dataJpa = new DataJpaController();
    List<Data> data = new ArrayList();
    
    @RequestMapping("/")
    public String getDataKTP(Model model) {
        try {
            data = dataJpa.findDataEntities();
        } catch (Exception e) {
        }
        model.addAttribute("data", data);
        return "dataKTP";
    }
}
