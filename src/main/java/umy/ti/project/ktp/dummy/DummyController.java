/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umy.ti.project.ktp.dummy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Fanky
 */
@Controller
public class DummyController {
    
    DummyJpaController dummyJpaController = new DummyJpaController();
    List<Dummy> data = new ArrayList<>();
    
    @RequestMapping("/read")
    public String getDummy() {
        try {
            data = dummyJpaController.findDummyEntities();
        } catch (Exception e) {
        }
        return "dummy";
    }
    
    @RequestMapping("/create")
    public String createDummy() {
        return "dummy/create";
    }
    
    @PostMapping(value = "/store", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String storeDummy(HttpServletRequest request, @RequestParam("gambar") MultipartFile file) throws ParseException, Exception {
        
        Dummy dummy = new Dummy();
        
        String id = request.getParameter("id");
        String tanggal = request.getParameter("tanggal");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tanggal);
        
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        byte[] gambar = file.getBytes();
        
        dummy.setId(Integer.parseInt(id));
        dummy.setTanggal(date);
        dummy.setGambar(gambar);
        
        dummyJpaController.create(dummy);
        return "dummy";
    }
}
